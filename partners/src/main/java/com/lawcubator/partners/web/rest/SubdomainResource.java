package com.lawcubator.partners.web.rest;

import com.lawcubator.partners.repository.SubdomainRepository;
import com.lawcubator.partners.service.SubdomainService;
import com.lawcubator.partners.service.dto.SubdomainDTO;
import com.lawcubator.partners.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.lawcubator.partners.domain.Subdomain}.
 */
@RestController
@RequestMapping("/api")
public class SubdomainResource {

    private final Logger log = LoggerFactory.getLogger(SubdomainResource.class);

    private static final String ENTITY_NAME = "partnersSubdomain";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SubdomainService subdomainService;

    private final SubdomainRepository subdomainRepository;

    public SubdomainResource(SubdomainService subdomainService, SubdomainRepository subdomainRepository) {
        this.subdomainService = subdomainService;
        this.subdomainRepository = subdomainRepository;
    }

    /**
     * {@code POST  /subdomains} : Create a new subdomain.
     *
     * @param subdomainDTO the subdomainDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new subdomainDTO, or with status {@code 400 (Bad Request)} if the subdomain has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/subdomains")
    public ResponseEntity<SubdomainDTO> createSubdomain(@Valid @RequestBody SubdomainDTO subdomainDTO) throws URISyntaxException {
        log.debug("REST request to save Subdomain : {}", subdomainDTO);
        if (subdomainDTO.getId() != null) {
            throw new BadRequestAlertException("A new subdomain cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SubdomainDTO result = subdomainService.save(subdomainDTO);
        return ResponseEntity
            .created(new URI("/api/subdomains/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /subdomains/:id} : Updates an existing subdomain.
     *
     * @param id the id of the subdomainDTO to save.
     * @param subdomainDTO the subdomainDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subdomainDTO,
     * or with status {@code 400 (Bad Request)} if the subdomainDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the subdomainDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/subdomains/{id}")
    public ResponseEntity<SubdomainDTO> updateSubdomain(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody SubdomainDTO subdomainDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Subdomain : {}, {}", id, subdomainDTO);
        if (subdomainDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, subdomainDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!subdomainRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SubdomainDTO result = subdomainService.save(subdomainDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, subdomainDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /subdomains/:id} : Partial updates given fields of an existing subdomain, field will ignore if it is null
     *
     * @param id the id of the subdomainDTO to save.
     * @param subdomainDTO the subdomainDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subdomainDTO,
     * or with status {@code 400 (Bad Request)} if the subdomainDTO is not valid,
     * or with status {@code 404 (Not Found)} if the subdomainDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the subdomainDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/subdomains/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<SubdomainDTO> partialUpdateSubdomain(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody SubdomainDTO subdomainDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Subdomain partially : {}, {}", id, subdomainDTO);
        if (subdomainDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, subdomainDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!subdomainRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SubdomainDTO> result = subdomainService.partialUpdate(subdomainDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, subdomainDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /subdomains} : get all the subdomains.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of subdomains in body.
     */
    @GetMapping("/subdomains")
    public ResponseEntity<List<SubdomainDTO>> getAllSubdomains(Pageable pageable) {
        log.debug("REST request to get a page of Subdomains");
        Page<SubdomainDTO> page = subdomainService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /subdomains/:id} : get the "id" subdomain.
     *
     * @param id the id of the subdomainDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the subdomainDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/subdomains/{id}")
    public ResponseEntity<SubdomainDTO> getSubdomain(@PathVariable Long id) {
        log.debug("REST request to get Subdomain : {}", id);
        Optional<SubdomainDTO> subdomainDTO = subdomainService.findOne(id);
        return ResponseUtil.wrapOrNotFound(subdomainDTO);
    }

    /**
     * {@code DELETE  /subdomains/:id} : delete the "id" subdomain.
     *
     * @param id the id of the subdomainDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/subdomains/{id}")
    public ResponseEntity<Void> deleteSubdomain(@PathVariable Long id) {
        log.debug("REST request to delete Subdomain : {}", id);
        subdomainService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
