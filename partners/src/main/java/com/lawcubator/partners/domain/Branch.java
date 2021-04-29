package com.lawcubator.partners.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Branch.
 */
@Entity
@Table(name = "branch")
public class Branch implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "branch_name", nullable = false)
    private String branchName;

    @NotNull
    @Column(name = "branch_city", nullable = false)
    private String branchCity;

    @NotNull
    @Column(name = "branch_address_1", nullable = false)
    private String branchAddress1;

    @Column(name = "branch_address_2")
    private String branchAddress2;

    @NotNull
    @Column(name = "branch_admin", nullable = false)
    private Integer branchAdmin;

    @NotNull
    @Column(name = "org_id", nullable = false)
    private Integer orgID;

    @NotNull
    @Column(name = "created_by", nullable = false)
    private Integer createdBy;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    @NotNull
    @Column(name = "update_by", nullable = false)
    private Integer updateBy;

    @NotNull
    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    @ManyToOne
    @JsonIgnoreProperties(value = { "branches", "subdomain" }, allowSetters = true)
    private Organization organization;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Branch id(Long id) {
        this.id = id;
        return this;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public Branch branchName(String branchName) {
        this.branchName = branchName;
        return this;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchCity() {
        return this.branchCity;
    }

    public Branch branchCity(String branchCity) {
        this.branchCity = branchCity;
        return this;
    }

    public void setBranchCity(String branchCity) {
        this.branchCity = branchCity;
    }

    public String getBranchAddress1() {
        return this.branchAddress1;
    }

    public Branch branchAddress1(String branchAddress1) {
        this.branchAddress1 = branchAddress1;
        return this;
    }

    public void setBranchAddress1(String branchAddress1) {
        this.branchAddress1 = branchAddress1;
    }

    public String getBranchAddress2() {
        return this.branchAddress2;
    }

    public Branch branchAddress2(String branchAddress2) {
        this.branchAddress2 = branchAddress2;
        return this;
    }

    public void setBranchAddress2(String branchAddress2) {
        this.branchAddress2 = branchAddress2;
    }

    public Integer getBranchAdmin() {
        return this.branchAdmin;
    }

    public Branch branchAdmin(Integer branchAdmin) {
        this.branchAdmin = branchAdmin;
        return this;
    }

    public void setBranchAdmin(Integer branchAdmin) {
        this.branchAdmin = branchAdmin;
    }

    public Integer getOrgID() {
        return this.orgID;
    }

    public Branch orgID(Integer orgID) {
        this.orgID = orgID;
        return this;
    }

    public void setOrgID(Integer orgID) {
        this.orgID = orgID;
    }

    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public Branch createdBy(Integer createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public Branch createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateBy() {
        return this.updateBy;
    }

    public Branch updateBy(Integer updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public Branch updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public Organization getOrganization() {
        return this.organization;
    }

    public Branch organization(Organization organization) {
        this.setOrganization(organization);
        return this;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Branch)) {
            return false;
        }
        return id != null && id.equals(((Branch) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Branch{" +
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
            "}";
    }
}
