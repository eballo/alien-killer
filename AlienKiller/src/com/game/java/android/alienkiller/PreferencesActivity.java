/**
 * 
 */
package com.game.java.android.alienkiller;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * @author nekun
 *
 */
public class PreferencesActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
	}

}
