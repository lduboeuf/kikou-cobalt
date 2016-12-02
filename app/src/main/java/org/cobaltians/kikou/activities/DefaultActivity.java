package org.cobaltians.kikou.activities;

import org.cobaltians.kikou.fragments.DefaultFragment;

import org.cobaltians.cobalt.activities.CobaltActivity;
import org.cobaltians.cobalt.fragments.CobaltFragment;

/**
 * Created by sebastienfamel on 24/12/15.
 */
public class DefaultActivity extends CobaltActivity {
    @Override
    protected CobaltFragment getFragment() {
        return new DefaultFragment();
    }
}
