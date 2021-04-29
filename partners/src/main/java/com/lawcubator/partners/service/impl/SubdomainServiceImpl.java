package com.lawcubator.partners.service.impl;

import com.lawcubator.partners.domain.Subdomain;
import com.lawcubator.partners.repository.SubdomainRepository;
import com.lawcubator.partners.service.SubdomainService;
import com.lawcubator.partners.service.dto.SubdomainDTO;
import com.lawcubator.partners.service.mapper.SubdomainMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Subdomain}.
 */
@Service
@Transactional
public class SubdomainServiceImpl implements SubdomainService {

    private final Logger log = LoggerFactory.getLogger(SubdomainServiceImpl.class);

    private final SubdomainRepository subdomainRepository;

    private final SubdomainMapper subdomainMapper;

    public SubdomainServiceImpl(SubdomainRepository subdomainRepository, SubdomainMapper subdomainMapper) {
        this.subdomainRepository = subdomainRepository;
        this.subdomainMapper = subdomainMapper;
    }

    @Override
    public SubdomainDTO save(SubdomainDTO subdomainDTO) {
        log.debug("Request to save Subdomain : {}", subdomainDTO);
        Subdomain subdomain = subdomainMapper.toEntity(subdomainDTO);
        subdomain = subdomainRepository.save(subdomain);
        return subdomainMapper.toDto(subdomain);
    }

    @Override
    public Optional<SubdomainDTO> partialUpdate(SubdomainDTO subdomainDTO) {
        log.debug("Request to partially update Subdomain : {}", subdomainDTO);

        return subdomainRepository
            .findById(subdomainDTO.getId())
            .map(
                existingSubdomain -> {
                    subdomainMapper.partialUpdate(existingSubdomain, subdomainDTO);
                    return existingSubdomain;
                }
            )
            .map(subdomainRepository::save)
            .map(subdomainMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SubdomainDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Subdomains");
        return subdomainRepository.findAll(pageable).map(subdomainMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SubdomainDTO> findOne(Long id) {
        log.debug("Request to get Subdomain : {}", id);
        return subdomainRepository.findById(id).map(subdomainMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Subdomain : {}", id);
        subdomainRepository.deleteById(id);
    }
}
