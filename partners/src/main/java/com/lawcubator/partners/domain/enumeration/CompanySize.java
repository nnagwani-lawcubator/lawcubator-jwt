package com.lawcubator.partners.domain.enumeration;

/**
 * The CompanySize enumeration.
 */
public enum CompanySize {
    SMALL("1-50"),
    MID("51-250"),
    LARGE("250+");

    private final String value;

    CompanySize(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
