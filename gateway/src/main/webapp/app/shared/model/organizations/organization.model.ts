import dayjs from 'dayjs';
import { IBranch } from 'app/shared/model/organizations/branch.model';
import { ISubdomain } from 'app/shared/model/organizations/subdomain.model';
import { CompanySize } from 'app/shared/model/enumerations/company-size.model';

export interface IOrganization {
  id?: number;
  orgName?: string;
  orgSize?: CompanySize | null;
  orgCity?: string;
  orgState?: string;
  orgAddress1?: string;
  orgAddress2?: string | null;
  orgAdmin?: number;
  orgCompanySize?: CompanySize;
  createdBy?: number;
  createTime?: string;
  updatedBy?: number;
  updateTime?: string;
  branches?: IBranch[] | null;
  subdomain?: ISubdomain | null;
}

export const defaultValue: Readonly<IOrganization> = {};
