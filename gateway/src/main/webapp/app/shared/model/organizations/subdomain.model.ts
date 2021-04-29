import dayjs from 'dayjs';
import { IOrganization } from 'app/shared/model/organizations/organization.model';

export interface ISubdomain {
  id?: number;
  subdomainName?: string;
  iPAddress?: string;
  orgID?: number;
  port?: string;
  createdBy?: number;
  createTime?: string;
  updatedBy?: number;
  updateTime?: string;
  orgId?: IOrganization | null;
}

export const defaultValue: Readonly<ISubdomain> = {};
