package com.deso.welcome;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.SwitchPreference;

public class PrefsFragment extends PreferenceActivity implements Preference.OnPreferenceChangeListener{
    private static final String WB_TOGGLE = "wb_toggle";
    private static final String WB_DURATION = "wb_duration";

    public ListPreference mWBDuration;
    public SwitchPreference mWBToggle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = this.getSharedPreferences("WBPrefs", MODE_PRIVATE);
        //pref.getBoolean(WB_TOGGLE, true);
        getPreferenceManager().setSharedPreferencesName("WBPrefs");
        addPreferencesFromResource(R.xml.preferences);
        mWBDuration = (ListPreference) findPreference(WB_DURATION);
        mWBToggle = (SwitchPreference) findPreference(WB_TOGGLE);
        mWBDuration.setOnPreferenceChangeListener(this);
        mWBToggle.setOnPreferenceChangeListener(this);
        mWBDuration.setSummary(mWBDuration.getEntry());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object objValue) {
        SharedPreferences pref = this.getSharedPreferences("WBPrefs", MODE_PRIVATE);
        if (preference == mWBToggle) {
            boolean checked = ((SwitchPreference) preference).isChecked();
            pref.edit().putBoolean(WB_TOGGLE, checked);
            pref.edit().commit();
            return true;
        } else if (preference == mWBDuration) {
            int durvalue = Integer.valueOf((String) objValue);
            pref.edit().putInt(WB_DURATION, durvalue);
            pref.edit().commit();
            mWBDuration.setValue(String.valueOf(durvalue));
            mWBDuration.setSummary(mWBDuration.getEntry());
            return true;
        }
        return false;
    }
}
