import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './subdomain.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISubdomainDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SubdomainDetail = (props: ISubdomainDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { subdomainEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="subdomainDetailsHeading">
          <Translate contentKey="gatewayApp.organizationsSubdomain.detail.title">Subdomain</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{subdomainEntity.id}</dd>
          <dt>
            <span id="subdomainName">
              <Translate contentKey="gatewayApp.organizationsSubdomain.subdomainName">Subdomain Name</Translate>
            </span>
          </dt>
          <dd>{subdomainEntity.subdomainName}</dd>
          <dt>
            <span id="iPAddress">
              <Translate contentKey="gatewayApp.organizationsSubdomain.iPAddress">I P Address</Translate>
            </span>
          </dt>
          <dd>{subdomainEntity.iPAddress}</dd>
          <dt>
            <span id="orgID">
              <Translate contentKey="gatewayApp.organizationsSubdomain.orgID">Org ID</Translate>
            </span>
          </dt>
          <dd>{subdomainEntity.orgID}</dd>
          <dt>
            <span id="port">
              <Translate contentKey="gatewayApp.organizationsSubdomain.port">Port</Translate>
            </span>
          </dt>
          <dd>{subdomainEntity.port}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="gatewayApp.organizationsSubdomain.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{subdomainEntity.createdBy}</dd>
          <dt>
            <span id="createTime">
              <Translate contentKey="gatewayApp.organizationsSubdomain.createTime">Create Time</Translate>
            </span>
          </dt>
          <dd>
            {subdomainEntity.createTime ? <TextFormat value={subdomainEntity.createTime} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="gatewayApp.organizationsSubdomain.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{subdomainEntity.updatedBy}</dd>
          <dt>
            <span id="updateTime">
              <Translate contentKey="gatewayApp.organizationsSubdomain.updateTime">Update Time</Translate>
            </span>
          </dt>
          <dd>
            {subdomainEntity.updateTime ? <TextFormat value={subdomainEntity.updateTime} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <Translate contentKey="gatewayApp.organizationsSubdomain.orgId">Org Id</Translate>
          </dt>
          <dd>{subdomainEntity.orgId ? subdomainEntity.orgId.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/subdomain" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/subdomain/${subdomainEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ subdomain }: IRootState) => ({
  subdomainEntity: subdomain.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SubdomainDetail);
