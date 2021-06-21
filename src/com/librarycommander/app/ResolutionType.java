package com.librarycommander.app;

public enum ResolutionType {

    ULTRA_HD("4K"),
    QUAD_HD("1440P"),
    FULL_HD("1080P"),
    HD("780P"),
    STANDARD_HD("480P"),
    BASIC_HD("360P"),
    ULTRA_BASIC_HD("280P");

    ResolutionType(String numericalRepresentation) {
        instanceVariable = numericalRepresentation;
    }

    private String instanceVariable;

    public String getInstanceVariable() {
        return instanceVariable;
    }
}
