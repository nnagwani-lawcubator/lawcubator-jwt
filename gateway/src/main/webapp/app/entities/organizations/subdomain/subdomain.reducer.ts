import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISubdomain, defaultValue } from 'app/shared/model/organizations/subdomain.model';

export const ACTION_TYPES = {
  FETCH_SUBDOMAIN_LIST: 'subdomain/FETCH_SUBDOMAIN_LIST',
  FETCH_SUBDOMAIN: 'subdomain/FETCH_SUBDOMAIN',
  CREATE_SUBDOMAIN: 'subdomain/CREATE_SUBDOMAIN',
  UPDATE_SUBDOMAIN: 'subdomain/UPDATE_SUBDOMAIN',
  PARTIAL_UPDATE_SUBDOMAIN: 'subdomain/PARTIAL_UPDATE_SUBDOMAIN',
  DELETE_SUBDOMAIN: 'subdomain/DELETE_SUBDOMAIN',
  RESET: 'subdomain/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISubdomain>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type SubdomainState = Readonly<typeof initialState>;

// Reducer

export default (state: SubdomainState = initialState, action): SubdomainState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SUBDOMAIN_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SUBDOMAIN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_SUBDOMAIN):
    case REQUEST(ACTION_TYPES.UPDATE_SUBDOMAIN):
    case REQUEST(ACTION_TYPES.DELETE_SUBDOMAIN):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_SUBDOMAIN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_SUBDOMAIN_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SUBDOMAIN):
    case FAILURE(ACTION_TYPES.CREATE_SUBDOMAIN):
    case FAILURE(ACTION_TYPES.UPDATE_SUBDOMAIN):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_SUBDOMAIN):
    case FAILURE(ACTION_TYPES.DELETE_SUBDOMAIN):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SUBDOMAIN_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_SUBDOMAIN):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_SUBDOMAIN):
    case SUCCESS(ACTION_TYPES.UPDATE_SUBDOMAIN):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_SUBDOMAIN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_SUBDOMAIN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'services/organizations/api/subdomains';

// Actions

export const getEntities: ICrudGetAllAction<ISubdomain> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_SUBDOMAIN_LIST,
    payload: axios.get<ISubdomain>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<ISubdomain> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SUBDOMAIN,
    payload: axios.get<ISubdomain>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ISubdomain> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SUBDOMAIN,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISubdomain> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SUBDOMAIN,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<ISubdomain> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_SUBDOMAIN,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISubdomain> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SUBDOMAIN,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
