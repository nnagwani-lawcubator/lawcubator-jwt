import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Subdomain from './subdomain';
import SubdomainDetail from './subdomain-detail';
import SubdomainUpdate from './subdomain-update';
import SubdomainDeleteDialog from './subdomain-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SubdomainUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SubdomainUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SubdomainDetail} />
      <ErrorBoundaryRoute path={match.url} component={Subdomain} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={SubdomainDeleteDialog} />
  </>
);

export default Routes;
