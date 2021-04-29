import {
  entityTableSelector,
  entityDetailsButtonSelector,
  entityDetailsBackButtonSelector,
  entityCreateButtonSelector,
  entityCreateSaveButtonSelector,
  entityEditButtonSelector,
  entityDeleteButtonSelector,
  entityConfirmDeleteButtonSelector,
} from '../../support/entity';

describe('Branch e2e test', () => {
  let startingEntitiesCount = 0;

  before(() => {
    cy.window().then(win => {
      win.sessionStorage.clear();
    });

    cy.clearCookies();
    cy.intercept('GET', '/services/organizations/api/branches*').as('entitiesRequest');
    cy.visit('');
    cy.login('admin', 'admin');
    cy.clickOnEntityMenuItem('branch');
    cy.wait('@entitiesRequest').then(({ request, response }) => (startingEntitiesCount = response.body.length));
    cy.visit('/');
  });

  it('should load Branches', () => {
    cy.intercept('GET', '/services/organizations/api/branches*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('branch');
    cy.wait('@entitiesRequest');
    cy.getEntityHeading('Branch').should('exist');
    if (startingEntitiesCount === 0) {
      cy.get(entityTableSelector).should('not.exist');
    } else {
      cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
    }
    cy.visit('/');
  });

  it('should load details Branch page', () => {
    cy.intercept('GET', '/services/organizations/api/branches*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('branch');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityDetailsButtonSelector).first().click({ force: true });
      cy.getEntityDetailsHeading('branch');
      cy.get(entityDetailsBackButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should load create Branch page', () => {
    cy.intercept('GET', '/services/organizations/api/branches*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('branch');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('Branch');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.visit('/');
  });

  it('should load edit Branch page', () => {
    cy.intercept('GET', '/services/organizations/api/branches*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('branch');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityEditButtonSelector).first().click({ force: true });
      cy.getEntityCreateUpdateHeading('Branch');
      cy.get(entityCreateSaveButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should create an instance of Branch', () => {
    cy.intercept('GET', '/services/organizations/api/branches*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('branch');
    cy.wait('@entitiesRequest').then(({ request, response }) => (startingEntitiesCount = response.body.length));
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('Branch');

    cy.get(`[data-cy="branchName"]`)
      .type('Account synthesize Small', { force: true })
      .invoke('val')
      .should('match', new RegExp('Account synthesize Small'));

    cy.get(`[data-cy="branchCity"]`)
      .type('invoice Mobility', { force: true })
      .invoke('val')
      .should('match', new RegExp('invoice Mobility'));

    cy.get(`[data-cy="branchAddress1"]`)
      .type('invoice Licensed Account', { force: true })
      .invoke('val')
      .should('match', new RegExp('invoice Licensed Account'));

    cy.get(`[data-cy="branchAddress2"]`).type('e-business', { force: true }).invoke('val').should('match', new RegExp('e-business'));

    cy.get(`[data-cy="branchAdmin"]`).type('44887').should('have.value', '44887');

    cy.get(`[data-cy="orgID"]`).type('37757').should('have.value', '37757');

    cy.get(`[data-cy="createdBy"]`).type('74726').should('have.value', '74726');

    cy.get(`[data-cy="createTime"]`).type('2021-04-28T23:16').invoke('val').should('equal', '2021-04-28T23:16');

    cy.get(`[data-cy="updateBy"]`).type('89889').should('have.value', '89889');

    cy.get(`[data-cy="updateTime"]`).type('2021-04-28T20:59').invoke('val').should('equal', '2021-04-28T20:59');

    cy.setFieldSelectToLastOfEntity('organization');

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.intercept('GET', '/services/organizations/api/branches*').as('entitiesRequestAfterCreate');
    cy.visit('/');
    cy.clickOnEntityMenuItem('branch');
    cy.wait('@entitiesRequestAfterCreate');
    cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount + 1);
    cy.visit('/');
  });

  it('should delete last instance of Branch', () => {
    cy.intercept('GET', '/services/organizations/api/branches*').as('entitiesRequest');
    cy.intercept('GET', '/services/organizations/api/branches/*').as('dialogDeleteRequest');
    cy.intercept('DELETE', '/services/organizations/api/branches/*').as('deleteEntityRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('branch');
    cy.wait('@entitiesRequest').then(({ request, response }) => {
      startingEntitiesCount = response.body.length;
      if (startingEntitiesCount > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('branch').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest');
        cy.intercept('GET', '/services/organizations/api/branches*').as('entitiesRequestAfterDelete');
        cy.visit('/');
        cy.clickOnEntityMenuItem('branch');
        cy.wait('@entitiesRequestAfterDelete');
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount - 1);
      }
      cy.visit('/');
    });
  });
});
