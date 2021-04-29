import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IOrganization } from 'app/shared/model/organizations/organization.model';
import { getEntities as getOrganizations } from 'app/entities/organizations/organization/organization.reducer';
import { getEntity, updateEntity, createEntity, reset } from './branch.reducer';
import { IBranch } from 'app/shared/model/organizations/branch.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBranchUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BranchUpdate = (props: IBranchUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { branchEntity, organizations, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/branch' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getOrganizations();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.createTime = convertDateTimeToServer(values.createTime);
    values.updateTime = convertDateTimeToServer(values.updateTime);

    if (errors.length === 0) {
      const entity = {
        ...branchEntity,
        ...values,
        organization: organizations.find(it => it.id.toString() === values.organizationId.toString()),
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="gatewayApp.organizationsBranch.home.createOrEditLabel" data-cy="BranchCreateUpdateHeading">
            <Translate contentKey="gatewayApp.organizationsBranch.home.createOrEditLabel">Create or edit a Branch</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : branchEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="branch-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="branch-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="branchNameLabel" for="branch-branchName">
                  <Translate contentKey="gatewayApp.organizationsBranch.branchName">Branch Name</Translate>
                </Label>
                <AvField
                  id="branch-branchName"
                  data-cy="branchName"
                  type="text"
                  name="branchName"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="branchCityLabel" for="branch-branchCity">
                  <Translate contentKey="gatewayApp.organizationsBranch.branchCity">Branch City</Translate>
                </Label>
                <AvField
                  id="branch-branchCity"
                  data-cy="branchCity"
                  type="text"
                  name="branchCity"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="branchAddress1Label" for="branch-branchAddress1">
                  <Translate contentKey="gatewayApp.organizationsBranch.branchAddress1">Branch Address 1</Translate>
                </Label>
                <AvField
                  id="branch-branchAddress1"
                  data-cy="branchAddress1"
                  type="text"
                  name="branchAddress1"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="branchAddress2Label" for="branch-branchAddress2">
                  <Translate contentKey="gatewayApp.organizationsBranch.branchAddress2">Branch Address 2</Translate>
                </Label>
                <AvField id="branch-branchAddress2" data-cy="branchAddress2" type="text" name="branchAddress2" />
              </AvGroup>
              <AvGroup>
                <Label id="branchAdminLabel" for="branch-branchAdmin">
                  <Translate contentKey="gatewayApp.organizationsBranch.branchAdmin">Branch Admin</Translate>
                </Label>
                <AvField
                  id="branch-branchAdmin"
                  data-cy="branchAdmin"
                  type="string"
                  className="form-control"
                  name="branchAdmin"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="orgIDLabel" for="branch-orgID">
                  <Translate contentKey="gatewayApp.organizationsBranch.orgID">Org ID</Translate>
                </Label>
                <AvField
                  id="branch-orgID"
                  data-cy="orgID"
                  type="string"
                  className="form-control"
                  name="orgID"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="createdByLabel" for="branch-createdBy">
                  <Translate contentKey="gatewayApp.organizationsBranch.createdBy">Created By</Translate>
                </Label>
                <AvField
                  id="branch-createdBy"
                  data-cy="createdBy"
                  type="string"
                  className="form-control"
                  name="createdBy"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="createTimeLabel" for="branch-createTime">
                  <Translate contentKey="gatewayApp.organizationsBranch.createTime">Create Time</Translate>
                </Label>
                <AvInput
                  id="branch-createTime"
                  data-cy="createTime"
                  type="datetime-local"
                  className="form-control"
                  name="createTime"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.branchEntity.createTime)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="updateByLabel" for="branch-updateBy">
                  <Translate contentKey="gatewayApp.organizationsBranch.updateBy">Update By</Translate>
                </Label>
                <AvField
                  id="branch-updateBy"
                  data-cy="updateBy"
                  type="string"
                  className="form-control"
                  name="updateBy"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="updateTimeLabel" for="branch-updateTime">
                  <Translate contentKey="gatewayApp.organizationsBranch.updateTime">Update Time</Translate>
                </Label>
                <AvInput
                  id="branch-updateTime"
                  data-cy="updateTime"
                  type="datetime-local"
                  className="form-control"
                  name="updateTime"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.branchEntity.updateTime)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="branch-organization">
                  <Translate contentKey="gatewayApp.organizationsBranch.organization">Organization</Translate>
                </Label>
                <AvInput id="branch-organization" data-cy="organization" type="select" className="form-control" name="organizationId">
                  <option value="" key="0" />
                  {organizations
                    ? organizations.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/branch" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  organizations: storeState.organization.entities,
  branchEntity: storeState.branch.entity,
  loading: storeState.branch.loading,
  updating: storeState.branch.updating,
  updateSuccess: storeState.branch.updateSuccess,
});

const mapDispatchToProps = {
  getOrganizations,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BranchUpdate);
