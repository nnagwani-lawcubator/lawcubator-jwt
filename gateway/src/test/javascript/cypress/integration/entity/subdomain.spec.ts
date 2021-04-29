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

describe('Subdomain e2e test', () => {
  let startingEntitiesCount = 0;

  before(() => {
    cy.window().then(win => {
      win.sessionStorage.clear();
    });

    cy.clearCookies();
    cy.intercept('GET', '/services/organizations/api/subdomains*').as('entitiesRequest');
    cy.visit('');
    cy.login('admin', 'admin');
    cy.clickOnEntityMenuItem('subdomain');
    cy.wait('@entitiesRequest').then(({ request, response }) => (startingEntitiesCount = response.body.length));
    cy.visit('/');
  });

  it('should load Subdomains', () => {
    cy.intercept('GET', '/services/organizations/api/subdomains*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('subdomain');
    cy.wait('@entitiesRequest');
    cy.getEntityHeading('Subdomain').should('exist');
    if (startingEntitiesCount === 0) {
      cy.get(entityTableSelector).should('not.exist');
    } else {
      cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
    }
    cy.visit('/');
  });

  it('should load details Subdomain page', () => {
    cy.intercept('GET', '/services/organizations/api/subdomains*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('subdomain');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityDetailsButtonSelector).first().click({ force: true });
      cy.getEntityDetailsHeading('subdomain');
      cy.get(entityDetailsBackButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should load create Subdomain page', () => {
    cy.intercept('GET', '/services/organizations/api/subdomains*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('subdomain');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('Subdomain');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.visit('/');
  });

  it('should load edit Subdomain page', () => {
    cy.intercept('GET', '/services/organizations/api/subdomains*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('subdomain');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityEditButtonSelector).first().click({ force: true });
      cy.getEntityCreateUpdateHeading('Subdomain');
      cy.get(entityCreateSaveButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should create an instance of Subdomain', () => {
    cy.intercept('GET', '/services/organizations/api/subdomains*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('subdomain');
    cy.wait('@entitiesRequest').then(({ request, response }) => (startingEntitiesCount = response.body.length));
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('Subdomain');

    cy.get(`[data-cy="subdomainName"]`).type('Facilitator', { force: true }).invoke('val').should('match', new RegExp('Facilitator'));

    cy.get(`[data-cy="iPAddress"]`)
      .type('Borders Square yellow', { force: true })
      .invoke('val')
      .should('match', new RegExp('Borders Square yellow'));

    cy.get(`[data-cy="orgID"]`).type('14330').should('have.value', '14330');

    cy.get(`[data-cy="port"]`).type('Lead viral', { force: true }).invoke('val').should('match', new RegExp('Lead viral'));

    cy.get(`[data-cy="createdBy"]`).type('70032').should('have.value', '70032');

    cy.get(`[data-cy="createTime"]`).type('2021-04-29T06:31').invoke('val').should('equal', '2021-04-29T06:31');

    cy.get(`[data-cy="updatedBy"]`).type('27037').should('have.value', '27037');

    cy.get(`[data-cy="updateTime"]`).type('2021-04-29T09:54').invoke('val').should('equal', '2021-04-29T09:54');

    cy.setFieldSelectToLastOfEntity('orgId');

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.intercept('GET', '/services/organizations/api/subdomains*').as('entitiesRequestAfterCreate');
    cy.visit('/');
    cy.clickOnEntityMenuItem('subdomain');
    cy.wait('@entitiesRequestAfterCreate');
    cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount + 1);
    cy.visit('/');
  });

  it('should delete last instance of Subdomain', () => {
    cy.intercept('GET', '/services/organizations/api/subdomains*').as('entitiesRequest');
    cy.intercept('GET', '/services/organizations/api/subdomains/*').as('dialogDeleteRequest');
    cy.intercept('DELETE', '/services/organizations/api/subdomains/*').as('deleteEntityRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('subdomain');
    cy.wait('@entitiesRequest').then(({ request, response }) => {
      startingEntitiesCount = response.body.length;
      if (startingEntitiesCount > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('subdomain').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest');
        cy.intercept('GET', '/services/organizations/api/subdomains*').as('entitiesRequestAfterDelete');
        cy.visit('/');
        cy.clickOnEntityMenuItem('subdomain');
        cy.wait('@entitiesRequestAfterDelete');
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount - 1);
      }
      cy.visit('/');
    });
  });
});
