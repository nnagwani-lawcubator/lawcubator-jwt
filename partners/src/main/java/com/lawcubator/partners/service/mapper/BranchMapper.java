package com.lawcubator.partners.service.mapper;

import com.lawcubator.partners.domain.*;
import com.lawcubator.partners.service.dto.BranchDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Branch} and its DTO {@link BranchDTO}.
 */
@Mapper(componentModel = "spring", uses = { OrganizationMapper.class })
public interface BranchMapper extends EntityMapper<BranchDTO, Branch> {
    @Mapping(target = "organization", source = "organization", qualifiedByName = "id")
    BranchDTO toDto(Branch s);
}
