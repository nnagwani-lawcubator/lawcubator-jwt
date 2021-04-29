import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ISubdomain } from 'app/shared/model/organizations/subdomain.model';
import { getEntities as getSubdomains } from 'app/entities/organizations/subdomain/subdomain.reducer';
import { getEntity, updateEntity, createEntity, reset } from './organization.reducer';
import { IOrganization } from 'app/shared/model/organizations/organization.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IOrganizationUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const OrganizationUpdate = (props: IOrganizationUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { organizationEntity, subdomains, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/organization' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getSubdomains();
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
        ...organizationEntity,
        ...values,
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
          <h2 id="gatewayApp.organizationsOrganization.home.createOrEditLabel" data-cy="OrganizationCreateUpdateHeading">
            <Translate contentKey="gatewayApp.organizationsOrganization.home.createOrEditLabel">Create or edit a Organization</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : organizationEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="organization-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="organization-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="orgNameLabel" for="organization-orgName">
                  <Translate contentKey="gatewayApp.organizationsOrganization.orgName">Org Name</Translate>
                </Label>
                <AvField
                  id="organization-orgName"
                  data-cy="orgName"
                  type="text"
                  name="orgName"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="orgSizeLabel" for="organization-orgSize">
                  <Translate contentKey="gatewayApp.organizationsOrganization.orgSize">Org Size</Translate>
                </Label>
                <AvInput
                  id="organization-orgSize"
                  data-cy="orgSize"
                  type="select"
                  className="form-control"
                  name="orgSize"
                  value={(!isNew && organizationEntity.orgSize) || 'SMALL'}
                >
                  <option value="SMALL">{translate('gatewayApp.CompanySize.SMALL')}</option>
                  <option value="MID">{translate('gatewayApp.CompanySize.MID')}</option>
                  <option value="LARGE">{translate('gatewayApp.CompanySize.LARGE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="orgCityLabel" for="organization-orgCity">
                  <Translate contentKey="gatewayApp.organizationsOrganization.orgCity">Org City</Translate>
                </Label>
                <AvField
                  id="organization-orgCity"
                  data-cy="orgCity"
                  type="text"
                  name="orgCity"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="orgStateLabel" for="organization-orgState">
                  <Translate contentKey="gatewayApp.organizationsOrganization.orgState">Org State</Translate>
                </Label>
                <AvField
                  id="organization-orgState"
                  data-cy="orgState"
                  type="text"
                  name="orgState"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="orgAddress1Label" for="organization-orgAddress1">
                  <Translate contentKey="gatewayApp.organizationsOrganization.orgAddress1">Org Address 1</Translate>
                </Label>
                <AvField
                  id="organization-orgAddress1"
                  data-cy="orgAddress1"
                  type="text"
                  name="orgAddress1"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="orgAddress2Label" for="organization-orgAddress2">
                  <Translate contentKey="gatewayApp.organizationsOrganization.orgAddress2">Org Address 2</Translate>
                </Label>
                <AvField id="organization-orgAddress2" data-cy="orgAddress2" type="text" name="orgAddress2" />
              </AvGroup>
              <AvGroup>
                <Label id="orgAdminLabel" for="organization-orgAdmin">
                  <Translate contentKey="gatewayApp.organizationsOrganization.orgAdmin">Org Admin</Translate>
                </Label>
                <AvField
                  id="organization-orgAdmin"
                  data-cy="orgAdmin"
                  type="string"
                  className="form-control"
                  name="orgAdmin"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="orgCompanySizeLabel" for="organization-orgCompanySize">
                  <Translate contentKey="gatewayApp.organizationsOrganization.orgCompanySize">Org Company Size</Translate>
                </Label>
                <AvInput
                  id="organization-orgCompanySize"
                  data-cy="orgCompanySize"
                  type="select"
                  className="form-control"
                  name="orgCompanySize"
                  value={(!isNew && organizationEntity.orgCompanySize) || 'SMALL'}
                >
                  <option value="SMALL">{translate('gatewayApp.CompanySize.SMALL')}</option>
                  <option value="MID">{translate('gatewayApp.CompanySize.MID')}</option>
                  <option value="LARGE">{translate('gatewayApp.CompanySize.LARGE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="createdByLabel" for="organization-createdBy">
                  <Translate contentKey="gatewayApp.organizationsOrganization.createdBy">Created By</Translate>
                </Label>
                <AvField
                  id="organization-createdBy"
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
                <Label id="createTimeLabel" for="organization-createTime">
                  <Translate contentKey="gatewayApp.organizationsOrganization.createTime">Create Time</Translate>
                </Label>
                <AvInput
                  id="organization-createTime"
                  data-cy="createTime"
                  type="datetime-local"
                  className="form-control"
                  name="createTime"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.organizationEntity.createTime)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="updatedByLabel" for="organization-updatedBy">
                  <Translate contentKey="gatewayApp.organizationsOrganization.updatedBy">Updated By</Translate>
                </Label>
                <AvField
                  id="organization-updatedBy"
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
                <Label id="updateTimeLabel" for="organization-updateTime">
                  <Translate contentKey="gatewayApp.organizationsOrganization.updateTime">Update Time</Translate>
                </Label>
                <AvInput
                  id="organization-updateTime"
                  data-cy="updateTime"
                  type="datetime-local"
                  className="form-control"
                  name="updateTime"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.organizationEntity.updateTime)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/organization" replace color="info">
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
  subdomains: storeState.subdomain.entities,
  organizationEntity: storeState.organization.entity,
  loading: storeState.organization.loading,
  updating: storeState.organization.updating,
  updateSuccess: storeState.organization.updateSuccess,
});

const mapDispatchToProps = {
  getSubdomains,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(OrganizationUpdate);
