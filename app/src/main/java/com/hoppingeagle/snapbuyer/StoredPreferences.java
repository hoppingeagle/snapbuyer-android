package com.hoppingeagle.snapbuyer;

import org.androidannotations.annotations.sharedpreferences.DefaultBoolean;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

@SharedPref(SharedPref.Scope.UNIQUE)
public interface StoredPreferences {
    @DefaultBoolean(true)
    boolean firstTime();
}
