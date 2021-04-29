package com.lawcubator.partners.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.lawcubator.partners.domain.Branch} entity.
 */
public class BranchDTO implements Serializable {

    private Long id;

    @NotNull
    private String branchName;

    @NotNull
    private String branchCity;

    @NotNull
    private String branchAddress1;

    private String branchAddress2;

    @NotNull
    private Integer branchAdmin;

    @NotNull
    private Integer orgID;

    @NotNull
    private Integer createdBy;

    @NotNull
    private Instant createTime;

    @NotNull
    private Integer updateBy;

    @NotNull
    private Instant updateTime;

    private OrganizationDTO organization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchCity() {
        return branchCity;
    }

    public void setBranchCity(String branchCity) {
        this.branchCity = branchCity;
    }

    public String getBranchAddress1() {
        return branchAddress1;
    }

    public void setBranchAddress1(String branchAddress1) {
        this.branchAddress1 = branchAddress1;
    }

    public String getBranchAddress2() {
        return branchAddress2;
    }

    public void setBranchAddress2(String branchAddress2) {
        this.branchAddress2 = branchAddress2;
    }

    public Integer getBranchAdmin() {
        return branchAdmin;
    }

    public void setBranchAdmin(Integer branchAdmin) {
        this.branchAdmin = branchAdmin;
    }

    public Integer getOrgID() {
        return orgID;
    }

    public void setOrgID(Integer orgID) {
        this.orgID = orgID;
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

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public OrganizationDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDTO organization) {
        this.organization = organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BranchDTO)) {
            return false;
        }

        BranchDTO branchDTO = (BranchDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, branchDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BranchDTO{" +
            "id=" + getId() +
            ", branchName='" + getBranchName() + "'" +
            ", branchCity='" + getBranchCity() + "'" +
            ", branchAddress1='" + getBranchAddress1() + "'" +
            ", branchAddress2='" + getBranchAddress2() + "'" +
            ", branchAdmin=" + getBranchAdmin() +
            ", orgID=" + getOrgID() +
            ", createdBy=" + getCreatedBy() +
            ", createTime='" + getCreateTime() + "'" +
            ", updateBy=" + getUpdateBy() +
            ", updateTime='" + getUpdateTime() + "'" +
            ", organization=" + getOrganization() +
            "}";
    }
}
