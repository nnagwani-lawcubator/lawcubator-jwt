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
import { getEntity, updateEntity, createEntity, reset } from './subdomain.reducer';
import { ISubdomain } from 'app/shared/model/organizations/subdomain.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISubdomainUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SubdomainUpdate = (props: ISubdomainUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { subdomainEntity, organizations, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/subdomain' + props.location.search);
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
        ...subdomainEntity,
        ...values,
        orgId: organizations.find(it => it.id.toString() === values.orgIdId.toString()),
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
          <h2 id="gatewayApp.organizationsSubdomain.home.createOrEditLabel" data-cy="SubdomainCreateUpdateHeading">
            <Translate contentKey="gatewayApp.organizationsSubdomain.home.createOrEditLabel">Create or edit a Subdomain</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : subdomainEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="subdomain-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="subdomain-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="subdomainNameLabel" for="subdomain-subdomainName">
                  <Translate contentKey="gatewayApp.organizationsSubdomain.subdomainName">Subdomain Name</Translate>
                </Label>
                <AvField
                  id="subdomain-subdomainName"
                  data-cy="subdomainName"
                  type="text"
                  name="subdomainName"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="iPAddressLabel" for="subdomain-iPAddress">
                  <Translate contentKey="gatewayApp.organizationsSubdomain.iPAddress">I P Address</Translate>
                </Label>
                <AvField
                  id="subdomain-iPAddress"
                  data-cy="iPAddress"
                  type="text"
                  name="iPAddress"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="orgIDLabel" for="subdomain-orgID">
                  <Translate contentKey="gatewayApp.organizationsSubdomain.orgID">Org ID</Translate>
                </Label>
                <AvField
                  id="subdomain-orgID"
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
                <Label id="portLabel" for="subdomain-port">
                  <Translate contentKey="gatewayApp.organizationsSubdomain.port">Port</Translate>
                </Label>
                <AvField
                  id="subdomain-port"
                  data-cy="port"
                  type="text"
                  name="port"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="createdByLabel" for="subdomain-createdBy">
                  <Translate contentKey="gatewayApp.organizationsSubdomain.createdBy">Created By</Translate>
                </Label>
                <AvField
                  id="subdomain-createdBy"
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
                <Label id="createTimeLabel" for="subdomain-createTime">
                  <Translate contentKey="gatewayApp.organizationsSubdomain.createTime">Create Time</Translate>
                </Label>
                <AvInput
                  id="subdomain-createTime"
                  data-cy="createTime"
                  type="datetime-local"
                  className="form-control"
                  name="createTime"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.subdomainEntity.createTime)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="updatedByLabel" for="subdomain-updatedBy">
                  <Translate contentKey="gatewayApp.organizationsSubdomain.updatedBy">Updated By</Translate>
                </Label>
                <AvField
                  id="subdomain-updatedBy"
                  data-cy="updatedBy"
                  type="string"
                  className="form-control"
                  name="updatedBy"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="updateTimeLabel" for="subdomain-updateTime">
                  <Translate contentKey="gatewayApp.organizationsSubdomain.updateTime">Update Time</Translate>
                </Label>
                <AvInput
                  id="subdomain-updateTime"
                  data-cy="updateTime"
                  type="datetime-local"
                  className="form-control"
                  name="updateTime"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.subdomainEntity.updateTime)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="subdomain-orgId">
                  <Translate contentKey="gatewayApp.organizationsSubdomain.orgId">Org Id</Translate>
                </Label>
                <AvInput id="subdomain-orgId" data-cy="orgId" type="select" className="form-control" name="orgIdId">
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
              <Button tag={Link} id="cancel-save" to="/subdomain" replace color="info">
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
  subdomainEntity: storeState.subdomain.entity,
  loading: storeState.subdomain.loading,
  updating: storeState.subdomain.updating,
  updateSuccess: storeState.subdomain.updateSuccess,
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

export default connect(mapStateToProps, mapDispatchToProps)(SubdomainUpdate);
