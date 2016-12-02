package org.cobaltians.kikou.fragments;

import android.app.Activity;

import org.json.JSONObject;

import org.cobaltians.cobalt.fragments.CobaltFragment;
import org.cobaltians.kikou.activities.WithSidemenuActivity;

public class DefaultFragment extends CobaltFragment {

    @Override
    protected boolean onUnhandledCallback(String callback, JSONObject data) {
        return false;
    }

    @Override
    protected boolean onUnhandledEvent(String event, JSONObject data, String callback) {
        Activity activity = getActivity();
        if (activity != null
            && activity instanceof WithSidemenuActivity) {
            switch (event) {
                case "sidemenu:disable":
                    ((WithSidemenuActivity) activity).setDrawerState(false);
                    return true;
                case "sidemenu:enable":
                    ((WithSidemenuActivity) activity).setDrawerState(true);
                    return true;
                case "sidemenu:show":
                    ((WithSidemenuActivity) activity).setDrawerVisible(true);
                    return true;
                case "sidemenu:hide":
                    ((WithSidemenuActivity) activity).setDrawerVisible(false);
                    return true;
                case "sidemenu:toggle":
                    ((WithSidemenuActivity) activity).setDrawerToggle();
                    return true;
                default:
                    return false;
            }
        }

        return false;
    }

    @Override
    protected void onUnhandledMessage(JSONObject message) {

    }
}
