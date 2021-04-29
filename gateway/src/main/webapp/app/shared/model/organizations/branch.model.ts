import dayjs from 'dayjs';
import { IOrganization } from 'app/shared/model/organizations/organization.model';

export interface IBranch {
  id?: number;
  branchName?: string;
  branchCity?: string;
  branchAddress1?: string;
  branchAddress2?: string | null;
  branchAdmin?: number;
  orgID?: number;
  createdBy?: number;
  createTime?: string;
  updateBy?: number;
  updateTime?: string;
  organization?: IOrganization | null;
}

export const defaultValue: Readonly<IBranch> = {};
