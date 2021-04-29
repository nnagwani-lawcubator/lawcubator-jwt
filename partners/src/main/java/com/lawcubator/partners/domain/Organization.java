package com.lawcubator.partners.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lawcubator.partners.domain.enumeration.CompanySize;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Organization.
 */
@Entity
@Table(name = "organization")
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "org_name", nullable = false)
    private String orgName;

    @Enumerated(EnumType.STRING)
    @Column(name = "org_size")
    private CompanySize orgSize;

    @NotNull
    @Column(name = "org_city", nullable = false)
    private String orgCity;

    @NotNull
    @Column(name = "org_state", nullable = false)
    private String orgState;

    @NotNull
    @Column(name = "org_address_1", nullable = false)
    private String orgAddress1;

    @Column(name = "org_address_2")
    private String orgAddress2;

    @NotNull
    @Column(name = "org_admin", nullable = false)
    private Integer orgAdmin;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "org_company_size", nullable = false)
    private CompanySize orgCompanySize;

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

    @OneToMany(mappedBy = "organization")
    @JsonIgnoreProperties(value = { "organization" }, allowSetters = true)
    private Set<Branch> branches = new HashSet<>();

    @JsonIgnoreProperties(value = { "orgId" }, allowSetters = true)
    @OneToOne(mappedBy = "orgId")
    private Subdomain subdomain;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Organization id(Long id) {
        this.id = id;
        return this;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public Organization orgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public CompanySize getOrgSize() {
        return this.orgSize;
    }

    public Organization orgSize(CompanySize orgSize) {
        this.orgSize = orgSize;
        return this;
    }

    public void setOrgSize(CompanySize orgSize) {
        this.orgSize = orgSize;
    }

    public String getOrgCity() {
        return this.orgCity;
    }

    public Organization orgCity(String orgCity) {
        this.orgCity = orgCity;
        return this;
    }

    public void setOrgCity(String orgCity) {
        this.orgCity = orgCity;
    }

    public String getOrgState() {
        return this.orgState;
    }

    public Organization orgState(String orgState) {
        this.orgState = orgState;
        return this;
    }

    public void setOrgState(String orgState) {
        this.orgState = orgState;
    }

    public String getOrgAddress1() {
        return this.orgAddress1;
    }

    public Organization orgAddress1(String orgAddress1) {
        this.orgAddress1 = orgAddress1;
        return this;
    }

    public void setOrgAddress1(String orgAddress1) {
        this.orgAddress1 = orgAddress1;
    }

    public String getOrgAddress2() {
        return this.orgAddress2;
    }

    public Organization orgAddress2(String orgAddress2) {
        this.orgAddress2 = orgAddress2;
        return this;
    }

    public void setOrgAddress2(String orgAddress2) {
        this.orgAddress2 = orgAddress2;
    }

    public Integer getOrgAdmin() {
        return this.orgAdmin;
    }

    public Organization orgAdmin(Integer orgAdmin) {
        this.orgAdmin = orgAdmin;
        return this;
    }

    public void setOrgAdmin(Integer orgAdmin) {
        this.orgAdmin = orgAdmin;
    }

    public CompanySize getOrgCompanySize() {
        return this.orgCompanySize;
    }

    public Organization orgCompanySize(CompanySize orgCompanySize) {
        this.orgCompanySize = orgCompanySize;
        return this;
    }

    public void setOrgCompanySize(CompanySize orgCompanySize) {
        this.orgCompanySize = orgCompanySize;
    }

    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public Organization createdBy(Integer createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public Organization createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdatedBy() {
        return this.updatedBy;
    }

    public Organization updatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public Organization updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public Set<Branch> getBranches() {
        return this.branches;
    }

    public Organization branches(Set<Branch> branches) {
        this.setBranches(branches);
        return this;
    }

    public Organization addBranch(Branch branch) {
        this.branches.add(branch);
        branch.setOrganization(this);
        return this;
    }

    public Organization removeBranch(Branch branch) {
        this.branches.remove(branch);
        branch.setOrganization(null);
        return this;
    }

    public void setBranches(Set<Branch> branches) {
        if (this.branches != null) {
            this.branches.forEach(i -> i.setOrganization(null));
        }
        if (branches != null) {
            branches.forEach(i -> i.setOrganization(this));
        }
        this.branches = branches;
    }

    public Subdomain getSubdomain() {
        return this.subdomain;
    }

    public Organization subdomain(Subdomain subdomain) {
        this.setSubdomain(subdomain);
        return this;
    }

    public void setSubdomain(Subdomain subdomain) {
        if (this.subdomain != null) {
            this.subdomain.setOrgId(null);
        }
        if (subdomain != null) {
            subdomain.setOrgId(this);
        }
        this.subdomain = subdomain;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Organization)) {
            return false;
        }
        return id != null && id.equals(((Organization) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Organization{" +
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
