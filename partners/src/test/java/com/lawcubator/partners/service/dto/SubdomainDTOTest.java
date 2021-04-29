package com.lawcubator.partners.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.lawcubator.partners.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SubdomainDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubdomainDTO.class);
        SubdomainDTO subdomainDTO1 = new SubdomainDTO();
        subdomainDTO1.setId(1L);
        SubdomainDTO subdomainDTO2 = new SubdomainDTO();
        assertThat(subdomainDTO1).isNotEqualTo(subdomainDTO2);
        subdomainDTO2.setId(subdomainDTO1.getId());
        assertThat(subdomainDTO1).isEqualTo(subdomainDTO2);
        subdomainDTO2.setId(2L);
        assertThat(subdomainDTO1).isNotEqualTo(subdomainDTO2);
        subdomainDTO1.setId(null);
        assertThat(subdomainDTO1).isNotEqualTo(subdomainDTO2);
    }
}
