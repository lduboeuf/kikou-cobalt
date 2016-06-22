package org.cobaltians.kikou.activities;

import org.cobaltians.kikou.fragments.MainFragment;

import org.cobaltians.cobalt.Cobalt;
import org.cobaltians.cobalt.activities.CobaltActivity;
import org.cobaltians.cobalt.fragments.CobaltFragment;

public class MainActivity extends CobaltActivity {

	protected CobaltFragment getFragment() {
		return Cobalt.getInstance(this).getFragmentForController(MainFragment.class, "default", "index.html");
	}
}
