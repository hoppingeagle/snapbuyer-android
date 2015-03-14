package com.hoppingeagle.snapbuyer;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.Map;

public class CategoryPreferences implements Serializable {
    @JsonProperty("preferences")
    Map<Long, Long> mPreferences;

    public Map<Long, Long> getPreferences() {
        return mPreferences;
    }

    public void setPreferences(Map<Long, Long> preferences) {
        mPreferences = preferences;
    }
}
