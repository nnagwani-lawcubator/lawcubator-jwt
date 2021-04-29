package com.lawcubator.partners.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SubdomainMapperTest {

    private SubdomainMapper subdomainMapper;

    @BeforeEach
    public void setUp() {
        subdomainMapper = new SubdomainMapperImpl();
    }
}
