package com.lawcubator.partners.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.lawcubator.partners.domain.Subdomain} entity.
 */
public class SubdomainDTO implements Serializable {

    private Long id;

    @NotNull
    private String subdomainName;

    @NotNull
    private String iPAddress;

    @NotNull
    private Integer orgID;

    @NotNull
    private String port;

    @NotNull
    private Integer createdBy;

    @NotNull
    private Instant createTime;

    @NotNull
    private Integer updatedBy;

    @NotNull
    private Instant updateTime;

    private OrganizationDTO orgId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubdomainName() {
        return subdomainName;
    }

    public void setSubdomainName(String subdomainName) {
        this.subdomainName = subdomainName;
    }

    public String getiPAddress() {
        return iPAddress;
    }

    public void setiPAddress(String iPAddress) {
        this.iPAddress = iPAddress;
    }

    public Integer getOrgID() {
        return orgID;
    }

    public void setOrgID(Integer orgID) {
        this.orgID = orgID;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
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

    public OrganizationDTO getOrgId() {
        return orgId;
    }

    public void setOrgId(OrganizationDTO orgId) {
        this.orgId = orgId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubdomainDTO)) {
            return false;
        }

        SubdomainDTO subdomainDTO = (SubdomainDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, subdomainDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SubdomainDTO{" +
            "id=" + getId() +
            ", subdomainName='" + getSubdomainName() + "'" +
            ", iPAddress='" + getiPAddress() + "'" +
            ", orgID=" + getOrgID() +
            ", port='" + getPort() + "'" +
            ", createdBy=" + getCreatedBy() +
            ", createTime='" + getCreateTime() + "'" +
            ", updatedBy=" + getUpdatedBy() +
            ", updateTime='" + getUpdateTime() + "'" +
            ", orgId=" + getOrgId() +
            "}";
    }
}
