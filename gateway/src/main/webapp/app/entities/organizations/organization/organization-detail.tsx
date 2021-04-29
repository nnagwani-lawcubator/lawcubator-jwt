import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './organization.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOrganizationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const OrganizationDetail = (props: IOrganizationDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { organizationEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="organizationDetailsHeading">
          <Translate contentKey="gatewayApp.organizationsOrganization.detail.title">Organization</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{organizationEntity.id}</dd>
          <dt>
            <span id="orgName">
              <Translate contentKey="gatewayApp.organizationsOrganization.orgName">Org Name</Translate>
            </span>
          </dt>
          <dd>{organizationEntity.orgName}</dd>
          <dt>
            <span id="orgSize">
              <Translate contentKey="gatewayApp.organizationsOrganization.orgSize">Org Size</Translate>
            </span>
          </dt>
          <dd>{organizationEntity.orgSize}</dd>
          <dt>
            <span id="orgCity">
              <Translate contentKey="gatewayApp.organizationsOrganization.orgCity">Org City</Translate>
            </span>
          </dt>
          <dd>{organizationEntity.orgCity}</dd>
          <dt>
            <span id="orgState">
              <Translate contentKey="gatewayApp.organizationsOrganization.orgState">Org State</Translate>
            </span>
          </dt>
          <dd>{organizationEntity.orgState}</dd>
          <dt>
            <span id="orgAddress1">
              <Translate contentKey="gatewayApp.organizationsOrganization.orgAddress1">Org Address 1</Translate>
            </span>
          </dt>
          <dd>{organizationEntity.orgAddress1}</dd>
          <dt>
            <span id="orgAddress2">
              <Translate contentKey="gatewayApp.organizationsOrganization.orgAddress2">Org Address 2</Translate>
            </span>
          </dt>
          <dd>{organizationEntity.orgAddress2}</dd>
          <dt>
            <span id="orgAdmin">
              <Translate contentKey="gatewayApp.organizationsOrganization.orgAdmin">Org Admin</Translate>
            </span>
          </dt>
          <dd>{organizationEntity.orgAdmin}</dd>
          <dt>
            <span id="orgCompanySize">
              <Translate contentKey="gatewayApp.organizationsOrganization.orgCompanySize">Org Company Size</Translate>
            </span>
          </dt>
          <dd>{organizationEntity.orgCompanySize}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="gatewayApp.organizationsOrganization.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{organizationEntity.createdBy}</dd>
          <dt>
            <span id="createTime">
              <Translate contentKey="gatewayApp.organizationsOrganization.createTime">Create Time</Translate>
            </span>
          </dt>
          <dd>
            {organizationEntity.createTime ? (
              <TextFormat value={organizationEntity.createTime} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="gatewayApp.organizationsOrganization.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{organizationEntity.updatedBy}</dd>
          <dt>
            <span id="updateTime">
              <Translate contentKey="gatewayApp.organizationsOrganization.updateTime">Update Time</Translate>
            </span>
          </dt>
          <dd>
            {organizationEntity.updateTime ? (
              <TextFormat value={organizationEntity.updateTime} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/organization" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/organization/${organizationEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ organization }: IRootState) => ({
  organizationEntity: organization.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(OrganizationDetail);
