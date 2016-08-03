package com.once2go.model;

/**
 * Created by once2go on 03.08.16.
 */
public enum ObjectExtension {

    IMAGES("images"),
    INFO("full"),
    FULL_INFO("full,images"),
    METADATA("metadata");

    private final String mExtension;

    ObjectExtension(String extension) {
        mExtension = extension;
    }

    public String value() {
        return mExtension;
    }
}
