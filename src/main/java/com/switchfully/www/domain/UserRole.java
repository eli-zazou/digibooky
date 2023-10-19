package com.switchfully.www.domain;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static com.switchfully.www.domain.Feature.*;

public enum UserRole {
    ADMIN(asList(VIEW_MEMBERS, MANAGE_USER, ADD_ADMIN, MANAGE_BOOKS)),
    LIBRARIAN(asList(MANAGE_BOOKS)),
    MEMBER(asList());

    private final List<Feature> featureList;

    UserRole(List<Feature> featureList) {
        this.featureList = featureList;
    }

    public boolean containsFeature(Feature feature) {
        return featureList.contains(feature);
    }

}
