package com.lawcubator.partners.service;

import com.lawcubator.partners.service.dto.SubdomainDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.lawcubator.partners.domain.Subdomain}.
 */
public interface SubdomainService {
    /**
     * Save a subdomain.
     *
     * @param subdomainDTO the entity to save.
     * @return the persisted entity.
     */
    SubdomainDTO save(SubdomainDTO subdomainDTO);

    /**
     * Partially updates a subdomain.
     *
     * @param subdomainDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SubdomainDTO> partialUpdate(SubdomainDTO subdomainDTO);

    /**
     * Get all the subdomains.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SubdomainDTO> findAll(Pageable pageable);

    /**
     * Get the "id" subdomain.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SubdomainDTO> findOne(Long id);

    /**
     * Delete the "id" subdomain.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
