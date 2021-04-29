package com.lawcubator.partners.service.dto;

import com.lawcubator.partners.domain.enumeration.CompanySize;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.lawcubator.partners.domain.Organization} entity.
 */
public class OrganizationDTO implements Serializable {

    private Long id;

    @NotNull
    private String orgName;

    private CompanySize orgSize;

    @NotNull
    private String orgCity;

    @NotNull
    private String orgState;

    @NotNull
    private String orgAddress1;

    private String orgAddress2;

    @NotNull
    private Integer orgAdmin;

    @NotNull
    private CompanySize orgCompanySize;

    @NotNull
    private Integer createdBy;

    @NotNull
    private Instant createTime;

    @NotNull
    private Integer updatedBy;

    @NotNull
    private Instant updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public CompanySize getOrgSize() {
        return orgSize;
    }

    public void setOrgSize(CompanySize orgSize) {
        this.orgSize = orgSize;
    }

    public String getOrgCity() {
        return orgCity;
    }

    public void setOrgCity(String orgCity) {
        this.orgCity = orgCity;
    }

    public String getOrgState() {
        return orgState;
    }

    public void setOrgState(String orgState) {
        this.orgState = orgState;
    }

    public String getOrgAddress1() {
        return orgAddress1;
    }

    public void setOrgAddress1(String orgAddress1) {
        this.orgAddress1 = orgAddress1;
    }

    public String getOrgAddress2() {
        return orgAddress2;
    }

    public void setOrgAddress2(String orgAddress2) {
        this.orgAddress2 = orgAddress2;
    }

    public Integer getOrgAdmin() {
        return orgAdmin;
    }

    public void setOrgAdmin(Integer orgAdmin) {
        this.orgAdmin = orgAdmin;
    }

    public CompanySize getOrgCompanySize() {
        return orgCompanySize;
    }

    public void setOrgCompanySize(CompanySize orgCompanySize) {
        this.orgCompanySize = orgCompanySize;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrganizationDTO)) {
            return false;
        }

        OrganizationDTO organizationDTO = (OrganizationDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, organizationDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrganizationDTO{" +
            "id=" + getId() +
            ", orgName='" + getOrgName() + "'" +
            ", orgSize='" + getOrgSize() + "'" +
            ", orgCity='" + getOrgCity() + "'" +
            ", orgState='" + getOrgState() + "'" +
            ", orgAddress1='" + getOrgAddress1() + "'" +
            ", orgAddress2='" + getOrgAddress2() + "'" +
            ", orgAdmin=" + getOrgAdmin() +
            ", orgCompanySize='" + getOrgCompanySize() + "'" +
            ", createdBy=" + getCreatedBy() +
            ", createTime='" + getCreateTime() + "'" +
            ", updatedBy=" + getUpdatedBy() +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
