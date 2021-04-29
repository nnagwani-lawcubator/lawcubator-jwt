package com.lawcubator.partners.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Subdomain.
 */
@Entity
@Table(name = "subdomain")
public class Subdomain implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "subdomain_name", nullable = false)
    private String subdomainName;

    @NotNull
    @Column(name = "i_p_address", nullable = false)
    private String iPAddress;

    @NotNull
    @Column(name = "org_id", nullable = false)
    private Integer orgID;

    @NotNull
    @Column(name = "port", nullable = false)
    private String port;

    @NotNull
    @Column(name = "created_by", nullable = false)
    private Integer createdBy;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    @NotNull
    @Column(name = "updated_by", nullable = false)
    private Integer updatedBy;

    @NotNull
    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    @JsonIgnoreProperties(value = { "branches", "subdomain" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Organization orgId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Subdomain id(Long id) {
        this.id = id;
        return this;
    }

    public String getSubdomainName() {
        return this.subdomainName;
    }

    public Subdomain subdomainName(String subdomainName) {
        this.subdomainName = subdomainName;
        return this;
    }

    public void setSubdomainName(String subdomainName) {
        this.subdomainName = subdomainName;
    }

    public String getiPAddress() {
        return this.iPAddress;
    }

    public Subdomain iPAddress(String iPAddress) {
        this.iPAddress = iPAddress;
        return this;
    }

    public void setiPAddress(String iPAddress) {
        this.iPAddress = iPAddress;
    }

    public Integer getOrgID() {
        return this.orgID;
    }

    public Subdomain orgID(Integer orgID) {
        this.orgID = orgID;
        return this;
    }

    public void setOrgID(Integer orgID) {
        this.orgID = orgID;
    }

    public String getPort() {
        return this.port;
    }

    public Subdomain port(String port) {
        this.port = port;
        return this;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public Subdomain createdBy(Integer createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public Subdomain createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdatedBy() {
        return this.updatedBy;
    }

    public Subdomain updatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public Subdomain updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public Organization getOrgId() {
        return this.orgId;
    }

    public Subdomain orgId(Organization organization) {
        this.setOrgId(organization);
        return this;
    }

    public void setOrgId(Organization organization) {
        this.orgId = organization;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Subdomain)) {
            return false;
        }
        return id != null && id.equals(((Subdomain) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Subdomain{" +
            "id=" + getId() +
            ", subdomainName='" + getSubdomainName() + "'" +
            ", iPAddress='" + getiPAddress() + "'" +
            ", orgID=" + getOrgID() +
            ", port='" + getPort() + "'" +
            ", createdBy=" + getCreatedBy() +
            ", createTime='" + getCreateTime() + "'" +
            ", updatedBy=" + getUpdatedBy() +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
