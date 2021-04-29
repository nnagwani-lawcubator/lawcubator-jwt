package com.lawcubator.partners.service.mapper;

import com.lawcubator.partners.domain.*;
import com.lawcubator.partners.service.dto.SubdomainDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Subdomain} and its DTO {@link SubdomainDTO}.
 */
@Mapper(componentModel = "spring", uses = { OrganizationMapper.class })
public interface SubdomainMapper extends EntityMapper<SubdomainDTO, Subdomain> {
    @Mapping(target = "orgId", source = "orgId", qualifiedByName = "id")
    SubdomainDTO toDto(Subdomain s);
}
