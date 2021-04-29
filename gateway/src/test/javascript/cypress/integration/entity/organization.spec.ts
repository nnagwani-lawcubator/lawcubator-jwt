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

describe('Organization e2e test', () => {
  let startingEntitiesCount = 0;

  before(() => {
    cy.window().then(win => {
      win.sessionStorage.clear();
    });

    cy.clearCookies();
    cy.intercept('GET', '/services/organizations/api/organizations*').as('entitiesRequest');
    cy.visit('');
    cy.login('admin', 'admin');
    cy.clickOnEntityMenuItem('organization');
    cy.wait('@entitiesRequest').then(({ request, response }) => (startingEntitiesCount = response.body.length));
    cy.visit('/');
  });

  it('should load Organizations', () => {
    cy.intercept('GET', '/services/organizations/api/organizations*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('organization');
    cy.wait('@entitiesRequest');
    cy.getEntityHeading('Organization').should('exist');
    if (startingEntitiesCount === 0) {
      cy.get(entityTableSelector).should('not.exist');
    } else {
      cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
    }
    cy.visit('/');
  });

  it('should load details Organization page', () => {
    cy.intercept('GET', '/services/organizations/api/organizations*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('organization');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityDetailsButtonSelector).first().click({ force: true });
      cy.getEntityDetailsHeading('organization');
      cy.get(entityDetailsBackButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should load create Organization page', () => {
    cy.intercept('GET', '/services/organizations/api/organizations*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('organization');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('Organization');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.visit('/');
  });

  it('should load edit Organization page', () => {
    cy.intercept('GET', '/services/organizations/api/organizations*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('organization');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityEditButtonSelector).first().click({ force: true });
      cy.getEntityCreateUpdateHeading('Organization');
      cy.get(entityCreateSaveButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should create an instance of Organization', () => {
    cy.intercept('GET', '/services/organizations/api/organizations*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('organization');
    cy.wait('@entitiesRequest').then(({ request, response }) => (startingEntitiesCount = response.body.length));
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('Organization');

    cy.get(`[data-cy="orgName"]`)
      .type('open-source Buckinghamshire', { force: true })
      .invoke('val')
      .should('match', new RegExp('open-source Buckinghamshire'));

    cy.get(`[data-cy="orgSize"]`).select('MID');

    cy.get(`[data-cy="orgCity"]`)
      .type('d&#39;Ivoire Facilitator', { force: true })
      .invoke('val')
      .should('match', new RegExp('d&#39;Ivoire Facilitator'));

    cy.get(`[data-cy="orgState"]`).type('5th Salad', { force: true }).invoke('val').should('match', new RegExp('5th Salad'));

    cy.get(`[data-cy="orgAddress1"]`)
      .type('CSS Wooden overriding', { force: true })
      .invoke('val')
      .should('match', new RegExp('CSS Wooden overriding'));

    cy.get(`[data-cy="orgAddress2"]`).type('wireless', { force: true }).invoke('val').should('match', new RegExp('wireless'));

    cy.get(`[data-cy="orgAdmin"]`).type('2055').should('have.value', '2055');

    cy.get(`[data-cy="orgCompanySize"]`).select('SMALL');

    cy.get(`[data-cy="createdBy"]`).type('43529').should('have.value', '43529');

    cy.get(`[data-cy="createTime"]`).type('2021-04-28T17:35').invoke('val').should('equal', '2021-04-28T17:35');

    cy.get(`[data-cy="updatedBy"]`).type('97307').should('have.value', '97307');

    cy.get(`[data-cy="updateTime"]`).type('2021-04-29T01:09').invoke('val').should('equal', '2021-04-29T01:09');

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.intercept('GET', '/services/organizations/api/organizations*').as('entitiesRequestAfterCreate');
    cy.visit('/');
    cy.clickOnEntityMenuItem('organization');
    cy.wait('@entitiesRequestAfterCreate');
    cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount + 1);
    cy.visit('/');
  });

  it('should delete last instance of Organization', () => {
    cy.intercept('GET', '/services/organizations/api/organizations*').as('entitiesRequest');
    cy.intercept('GET', '/services/organizations/api/organizations/*').as('dialogDeleteRequest');
    cy.intercept('DELETE', '/services/organizations/api/organizations/*').as('deleteEntityRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('organization');
    cy.wait('@entitiesRequest').then(({ request, response }) => {
      startingEntitiesCount = response.body.length;
      if (startingEntitiesCount > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('organization').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest');
        cy.intercept('GET', '/services/organizations/api/organizations*').as('entitiesRequestAfterDelete');
        cy.visit('/');
        cy.clickOnEntityMenuItem('organization');
        cy.wait('@entitiesRequestAfterDelete');
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount - 1);
      }
      cy.visit('/');
    });
  });
});
