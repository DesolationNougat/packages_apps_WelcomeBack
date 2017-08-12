package com.deso.welcome;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PrefsFragment extends PreferenceActivity implements Preference.OnPreferenceChangeListener{
    private static final String WB_TOGGLE = "wb_toggle";
    public SwitchPreference mWBToggle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //pref.getBoolean(WB_TOGGLE, true);
        getPreferenceManager().setSharedPreferencesName("WBPrefs");
        addPreferencesFromResource(R.xml.preferences);
        mWBToggle = (SwitchPreference) findPreference(WB_TOGGLE);
        mWBToggle.setOnPreferenceChangeListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object objValue) {
        SharedPreferences pref = this.getSharedPreferences("WBPrefs", MODE_PRIVATE);
        if  (preference == mWBToggle) {
            boolean checked = ((SwitchPreference)preference).isChecked();
            pref.edit().putBoolean(WB_TOGGLE, checked);
            pref.edit().commit();
            return true;
        }
        return false;
    }
}
