package com.lawcubator.partners.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.lawcubator.partners.IntegrationTest;
import com.lawcubator.partners.domain.Subdomain;
import com.lawcubator.partners.repository.SubdomainRepository;
import com.lawcubator.partners.service.dto.SubdomainDTO;
import com.lawcubator.partners.service.mapper.SubdomainMapper;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link SubdomainResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SubdomainResourceIT {

    private static final String DEFAULT_SUBDOMAIN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUBDOMAIN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_I_P_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_I_P_ADDRESS = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORG_ID = 1;
    private static final Integer UPDATED_ORG_ID = 2;

    private static final String DEFAULT_PORT = "AAAAAAAAAA";
    private static final String UPDATED_PORT = "BBBBBBBBBB";

    private static final Integer DEFAULT_CREATED_BY = 1;
    private static final Integer UPDATED_CREATED_BY = 2;

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_UPDATED_BY = 1;
    private static final Integer UPDATED_UPDATED_BY = 2;

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/subdomains";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SubdomainRepository subdomainRepository;

    @Autowired
    private SubdomainMapper subdomainMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSubdomainMockMvc;

    private Subdomain subdomain;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Subdomain createEntity(EntityManager em) {
        Subdomain subdomain = new Subdomain()
            .subdomainName(DEFAULT_SUBDOMAIN_NAME)
            .iPAddress(DEFAULT_I_P_ADDRESS)
            .orgID(DEFAULT_ORG_ID)
            .port(DEFAULT_PORT)
            .createdBy(DEFAULT_CREATED_BY)
            .createTime(DEFAULT_CREATE_TIME)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updateTime(DEFAULT_UPDATE_TIME);
        return subdomain;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Subdomain createUpdatedEntity(EntityManager em) {
        Subdomain subdomain = new Subdomain()
            .subdomainName(UPDATED_SUBDOMAIN_NAME)
            .iPAddress(UPDATED_I_P_ADDRESS)
            .orgID(UPDATED_ORG_ID)
            .port(UPDATED_PORT)
            .createdBy(UPDATED_CREATED_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updatedBy(UPDATED_UPDATED_BY)
            .updateTime(UPDATED_UPDATE_TIME);
        return subdomain;
    }

    @BeforeEach
    public void initTest() {
        subdomain = createEntity(em);
    }

    @Test
    @Transactional
    void createSubdomain() throws Exception {
        int databaseSizeBeforeCreate = subdomainRepository.findAll().size();
        // Create the Subdomain
        SubdomainDTO subdomainDTO = subdomainMapper.toDto(subdomain);
        restSubdomainMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(subdomainDTO)))
            .andExpect(status().isCreated());

        // Validate the Subdomain in the database
        List<Subdomain> subdomainList = subdomainRepository.findAll();
        assertThat(subdomainList).hasSize(databaseSizeBeforeCreate + 1);
        Subdomain testSubdomain = subdomainList.get(subdomainList.size() - 1);
        assertThat(testSubdomain.getSubdomainName()).isEqualTo(DEFAULT_SUBDOMAIN_NAME);
        assertThat(testSubdomain.getiPAddress()).isEqualTo(DEFAULT_I_P_ADDRESS);
        assertThat(testSubdomain.getOrgID()).isEqualTo(DEFAULT_ORG_ID);
        assertThat(testSubdomain.getPort()).isEqualTo(DEFAULT_PORT);
        assertThat(testSubdomain.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testSubdomain.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSubdomain.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testSubdomain.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void createSubdomainWithExistingId() throws Exception {
        // Create the Subdomain with an existing ID
        subdomain.setId(1L);
        SubdomainDTO subdomainDTO = subdomainMapper.toDto(subdomain);

        int databaseSizeBeforeCreate = subdomainRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSubdomainMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(subdomainDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Subdomain in the database
        List<Subdomain> subdomainList = subdomainRepository.findAll();
        assertThat(subdomainList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkSubdomainNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = subdomainRepository.findAll().size();
        // set the field null
        subdomain.setSubdomainName(null);

        // Create the Subdomain, which fails.
        SubdomainDTO subdomainDTO = subdomainMapper.toDto(subdomain);

        restSubdomainMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(subdomainDTO)))
            .andExpect(status().isBadRequest());

        List<Subdomain> subdomainList = subdomainRepository.findAll();
        assertThat(subdomainList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkiPAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = subdomainRepository.findAll().size();
        // set the field null
        subdomain.setiPAddress(null);

        // Create the Subdomain, which fails.
        SubdomainDTO subdomainDTO = subdomainMapper.toDto(subdomain);

        restSubdomainMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(subdomainDTO)))
            .andExpect(status().isBadRequest());

        List<Subdomain> subdomainList = subdomainRepository.findAll();
        assertThat(subdomainList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkOrgIDIsRequired() throws Exception {
        int databaseSizeBeforeTest = subdomainRepository.findAll().size();
        // set the field null
        subdomain.setOrgID(null);

        // Create the Subdomain, which fails.
        SubdomainDTO subdomainDTO = subdomainMapper.toDto(subdomain);

        restSubdomainMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(subdomainDTO)))
            .andExpect(status().isBadRequest());

        List<Subdomain> subdomainList = subdomainRepository.findAll();
        assertThat(subdomainList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPortIsRequired() throws Exception {
        int databaseSizeBeforeTest = subdomainRepository.findAll().size();
        // set the field null
        subdomain.setPort(null);

        // Create the Subdomain, which fails.
        SubdomainDTO subdomainDTO = subdomainMapper.toDto(subdomain);

        restSubdomainMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(subdomainDTO)))
            .andExpect(status().isBadRequest());

        List<Subdomain> subdomainList = subdomainRepository.findAll();
        assertThat(subdomainList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = subdomainRepository.findAll().size();
        // set the field null
        subdomain.setCreatedBy(null);

        // Create the Subdomain, which fails.
        SubdomainDTO subdomainDTO = subdomainMapper.toDto(subdomain);

        restSubdomainMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(subdomainDTO)))
            .andExpect(status().isBadRequest());

        List<Subdomain> subdomainList = subdomainRepository.findAll();
        assertThat(subdomainList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = subdomainRepository.findAll().size();
        // set the field null
        subdomain.setCreateTime(null);

        // Create the Subdomain, which fails.
        SubdomainDTO subdomainDTO = subdomainMapper.toDto(subdomain);

        restSubdomainMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(subdomainDTO)))
            .andExpect(status().isBadRequest());

        List<Subdomain> subdomainList = subdomainRepository.findAll();
        assertThat(subdomainList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = subdomainRepository.findAll().size();
        // set the field null
        subdomain.setUpdatedBy(null);

        // Create the Subdomain, which fails.
        SubdomainDTO subdomainDTO = subdomainMapper.toDto(subdomain);

        restSubdomainMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(subdomainDTO)))
            .andExpect(status().isBadRequest());

        List<Subdomain> subdomainList = subdomainRepository.findAll();
        assertThat(subdomainList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = subdomainRepository.findAll().size();
        // set the field null
        subdomain.setUpdateTime(null);

        // Create the Subdomain, which fails.
        SubdomainDTO subdomainDTO = subdomainMapper.toDto(subdomain);

        restSubdomainMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(subdomainDTO)))
            .andExpect(status().isBadRequest());

        List<Subdomain> subdomainList = subdomainRepository.findAll();
        assertThat(subdomainList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllSubdomains() throws Exception {
        // Initialize the database
        subdomainRepository.saveAndFlush(subdomain);

        // Get all the subdomainList
        restSubdomainMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subdomain.getId().intValue())))
            .andExpect(jsonPath("$.[*].subdomainName").value(hasItem(DEFAULT_SUBDOMAIN_NAME)))
            .andExpect(jsonPath("$.[*].iPAddress").value(hasItem(DEFAULT_I_P_ADDRESS)))
            .andExpect(jsonPath("$.[*].orgID").value(hasItem(DEFAULT_ORG_ID)))
            .andExpect(jsonPath("$.[*].port").value(hasItem(DEFAULT_PORT)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getSubdomain() throws Exception {
        // Initialize the database
        subdomainRepository.saveAndFlush(subdomain);

        // Get the subdomain
        restSubdomainMockMvc
            .perform(get(ENTITY_API_URL_ID, subdomain.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(subdomain.getId().intValue()))
            .andExpect(jsonPath("$.subdomainName").value(DEFAULT_SUBDOMAIN_NAME))
            .andExpect(jsonPath("$.iPAddress").value(DEFAULT_I_P_ADDRESS))
            .andExpect(jsonPath("$.orgID").value(DEFAULT_ORG_ID))
            .andExpect(jsonPath("$.port").value(DEFAULT_PORT))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingSubdomain() throws Exception {
        // Get the subdomain
        restSubdomainMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewSubdomain() throws Exception {
        // Initialize the database
        subdomainRepository.saveAndFlush(subdomain);

        int databaseSizeBeforeUpdate = subdomainRepository.findAll().size();

        // Update the subdomain
        Subdomain updatedSubdomain = subdomainRepository.findById(subdomain.getId()).get();
        // Disconnect from session so that the updates on updatedSubdomain are not directly saved in db
        em.detach(updatedSubdomain);
        updatedSubdomain
            .subdomainName(UPDATED_SUBDOMAIN_NAME)
            .iPAddress(UPDATED_I_P_ADDRESS)
            .orgID(UPDATED_ORG_ID)
            .port(UPDATED_PORT)
            .createdBy(UPDATED_CREATED_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updatedBy(UPDATED_UPDATED_BY)
            .updateTime(UPDATED_UPDATE_TIME);
        SubdomainDTO subdomainDTO = subdomainMapper.toDto(updatedSubdomain);

        restSubdomainMockMvc
            .perform(
                put(ENTITY_API_URL_ID, subdomainDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(subdomainDTO))
            )
            .andExpect(status().isOk());

        // Validate the Subdomain in the database
        List<Subdomain> subdomainList = subdomainRepository.findAll();
        assertThat(subdomainList).hasSize(databaseSizeBeforeUpdate);
        Subdomain testSubdomain = subdomainList.get(subdomainList.size() - 1);
        assertThat(testSubdomain.getSubdomainName()).isEqualTo(UPDATED_SUBDOMAIN_NAME);
        assertThat(testSubdomain.getiPAddress()).isEqualTo(UPDATED_I_P_ADDRESS);
        assertThat(testSubdomain.getOrgID()).isEqualTo(UPDATED_ORG_ID);
        assertThat(testSubdomain.getPort()).isEqualTo(UPDATED_PORT);
        assertThat(testSubdomain.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSubdomain.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSubdomain.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testSubdomain.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingSubdomain() throws Exception {
        int databaseSizeBeforeUpdate = subdomainRepository.findAll().size();
        subdomain.setId(count.incrementAndGet());

        // Create the Subdomain
        SubdomainDTO subdomainDTO = subdomainMapper.toDto(subdomain);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubdomainMockMvc
            .perform(
                put(ENTITY_API_URL_ID, subdomainDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(subdomainDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Subdomain in the database
        List<Subdomain> subdomainList = subdomainRepository.findAll();
        assertThat(subdomainList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSubdomain() throws Exception {
        int databaseSizeBeforeUpdate = subdomainRepository.findAll().size();
        subdomain.setId(count.incrementAndGet());

        // Create the Subdomain
        SubdomainDTO subdomainDTO = subdomainMapper.toDto(subdomain);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSubdomainMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(subdomainDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Subdomain in the database
        List<Subdomain> subdomainList = subdomainRepository.findAll();
        assertThat(subdomainList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSubdomain() throws Exception {
        int databaseSizeBeforeUpdate = subdomainRepository.findAll().size();
        subdomain.setId(count.incrementAndGet());

        // Create the Subdomain
        SubdomainDTO subdomainDTO = subdomainMapper.toDto(subdomain);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSubdomainMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(subdomainDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Subdomain in the database
        List<Subdomain> subdomainList = subdomainRepository.findAll();
        assertThat(subdomainList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSubdomainWithPatch() throws Exception {
        // Initialize the database
        subdomainRepository.saveAndFlush(subdomain);

        int databaseSizeBeforeUpdate = subdomainRepository.findAll().size();

        // Update the subdomain using partial update
        Subdomain partialUpdatedSubdomain = new Subdomain();
        partialUpdatedSubdomain.setId(subdomain.getId());

        partialUpdatedSubdomain.updateTime(UPDATED_UPDATE_TIME);

        restSubdomainMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSubdomain.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSubdomain))
            )
            .andExpect(status().isOk());

        // Validate the Subdomain in the database
        List<Subdomain> subdomainList = subdomainRepository.findAll();
        assertThat(subdomainList).hasSize(databaseSizeBeforeUpdate);
        Subdomain testSubdomain = subdomainList.get(subdomainList.size() - 1);
        assertThat(testSubdomain.getSubdomainName()).isEqualTo(DEFAULT_SUBDOMAIN_NAME);
        assertThat(testSubdomain.getiPAddress()).isEqualTo(DEFAULT_I_P_ADDRESS);
        assertThat(testSubdomain.getOrgID()).isEqualTo(DEFAULT_ORG_ID);
        assertThat(testSubdomain.getPort()).isEqualTo(DEFAULT_PORT);
        assertThat(testSubdomain.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testSubdomain.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSubdomain.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testSubdomain.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateSubdomainWithPatch() throws Exception {
        // Initialize the database
        subdomainRepository.saveAndFlush(subdomain);

        int databaseSizeBeforeUpdate = subdomainRepository.findAll().size();

        // Update the subdomain using partial update
        Subdomain partialUpdatedSubdomain = new Subdomain();
        partialUpdatedSubdomain.setId(subdomain.getId());

        partialUpdatedSubdomain
            .subdomainName(UPDATED_SUBDOMAIN_NAME)
            .iPAddress(UPDATED_I_P_ADDRESS)
            .orgID(UPDATED_ORG_ID)
            .port(UPDATED_PORT)
            .createdBy(UPDATED_CREATED_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updatedBy(UPDATED_UPDATED_BY)
            .updateTime(UPDATED_UPDATE_TIME);

        restSubdomainMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSubdomain.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSubdomain))
            )
            .andExpect(status().isOk());

        // Validate the Subdomain in the database
        List<Subdomain> subdomainList = subdomainRepository.findAll();
        assertThat(subdomainList).hasSize(databaseSizeBeforeUpdate);
        Subdomain testSubdomain = subdomainList.get(subdomainList.size() - 1);
        assertThat(testSubdomain.getSubdomainName()).isEqualTo(UPDATED_SUBDOMAIN_NAME);
        assertThat(testSubdomain.getiPAddress()).isEqualTo(UPDATED_I_P_ADDRESS);
        assertThat(testSubdomain.getOrgID()).isEqualTo(UPDATED_ORG_ID);
        assertThat(testSubdomain.getPort()).isEqualTo(UPDATED_PORT);
        assertThat(testSubdomain.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSubdomain.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSubdomain.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testSubdomain.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingSubdomain() throws Exception {
        int databaseSizeBeforeUpdate = subdomainRepository.findAll().size();
        subdomain.setId(count.incrementAndGet());

        // Create the Subdomain
        SubdomainDTO subdomainDTO = subdomainMapper.toDto(subdomain);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubdomainMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, subdomainDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(subdomainDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Subdomain in the database
        List<Subdomain> subdomainList = subdomainRepository.findAll();
        assertThat(subdomainList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSubdomain() throws Exception {
        int databaseSizeBeforeUpdate = subdomainRepository.findAll().size();
        subdomain.setId(count.incrementAndGet());

        // Create the Subdomain
        SubdomainDTO subdomainDTO = subdomainMapper.toDto(subdomain);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSubdomainMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(subdomainDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Subdomain in the database
        List<Subdomain> subdomainList = subdomainRepository.findAll();
        assertThat(subdomainList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSubdomain() throws Exception {
        int databaseSizeBeforeUpdate = subdomainRepository.findAll().size();
        subdomain.setId(count.incrementAndGet());

        // Create the Subdomain
        SubdomainDTO subdomainDTO = subdomainMapper.toDto(subdomain);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSubdomainMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(subdomainDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Subdomain in the database
        List<Subdomain> subdomainList = subdomainRepository.findAll();
        assertThat(subdomainList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSubdomain() throws Exception {
        // Initialize the database
        subdomainRepository.saveAndFlush(subdomain);

        int databaseSizeBeforeDelete = subdomainRepository.findAll().size();

        // Delete the subdomain
        restSubdomainMockMvc
            .perform(delete(ENTITY_API_URL_ID, subdomain.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Subdomain> subdomainList = subdomainRepository.findAll();
        assertThat(subdomainList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
