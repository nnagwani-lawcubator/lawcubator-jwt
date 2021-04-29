package com.lawcubator.partners.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.lawcubator.partners.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SubdomainTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Subdomain.class);
        Subdomain subdomain1 = new Subdomain();
        subdomain1.setId(1L);
        Subdomain subdomain2 = new Subdomain();
        subdomain2.setId(subdomain1.getId());
        assertThat(subdomain1).isEqualTo(subdomain2);
        subdomain2.setId(2L);
        assertThat(subdomain1).isNotEqualTo(subdomain2);
        subdomain1.setId(null);
        assertThat(subdomain1).isNotEqualTo(subdomain2);
    }
}
