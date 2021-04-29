package com.lawcubator.partners.repository;

import com.lawcubator.partners.domain.Subdomain;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Subdomain entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubdomainRepository extends JpaRepository<Subdomain, Long> {}
