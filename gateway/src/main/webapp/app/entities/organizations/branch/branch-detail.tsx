import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './branch.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBranchDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BranchDetail = (props: IBranchDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { branchEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="branchDetailsHeading">
          <Translate contentKey="gatewayApp.organizationsBranch.detail.title">Branch</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{branchEntity.id}</dd>
          <dt>
            <span id="branchName">
              <Translate contentKey="gatewayApp.organizationsBranch.branchName">Branch Name</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchName}</dd>
          <dt>
            <span id="branchCity">
              <Translate contentKey="gatewayApp.organizationsBranch.branchCity">Branch City</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchCity}</dd>
          <dt>
            <span id="branchAddress1">
              <Translate contentKey="gatewayApp.organizationsBranch.branchAddress1">Branch Address 1</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchAddress1}</dd>
          <dt>
            <span id="branchAddress2">
              <Translate contentKey="gatewayApp.organizationsBranch.branchAddress2">Branch Address 2</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchAddress2}</dd>
          <dt>
            <span id="branchAdmin">
              <Translate contentKey="gatewayApp.organizationsBranch.branchAdmin">Branch Admin</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchAdmin}</dd>
          <dt>
            <span id="orgID">
              <Translate contentKey="gatewayApp.organizationsBranch.orgID">Org ID</Translate>
            </span>
          </dt>
          <dd>{branchEntity.orgID}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="gatewayApp.organizationsBranch.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{branchEntity.createdBy}</dd>
          <dt>
            <span id="createTime">
              <Translate contentKey="gatewayApp.organizationsBranch.createTime">Create Time</Translate>
            </span>
          </dt>
          <dd>{branchEntity.createTime ? <TextFormat value={branchEntity.createTime} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="updateBy">
              <Translate contentKey="gatewayApp.organizationsBranch.updateBy">Update By</Translate>
            </span>
          </dt>
          <dd>{branchEntity.updateBy}</dd>
          <dt>
            <span id="updateTime">
              <Translate contentKey="gatewayApp.organizationsBranch.updateTime">Update Time</Translate>
            </span>
          </dt>
          <dd>{branchEntity.updateTime ? <TextFormat value={branchEntity.updateTime} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <Translate contentKey="gatewayApp.organizationsBranch.organization">Organization</Translate>
          </dt>
          <dd>{branchEntity.organization ? branchEntity.organization.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/branch" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/branch/${branchEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ branch }: IRootState) => ({
  branchEntity: branch.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BranchDetail);
