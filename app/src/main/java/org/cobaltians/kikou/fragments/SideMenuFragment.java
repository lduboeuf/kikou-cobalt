package org.cobaltians.kikou.fragments;

import android.os.Bundle;
import android.util.Log;


import org.cobaltians.kikou.activities.WithSidemenuActivity;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import org.cobaltians.cobalt.Cobalt;
import org.cobaltians.cobalt.fragments.CobaltFragment;

public class SideMenuFragment extends CobaltFragment {

    private HashMap<String, CobaltFragment> mFragmentStack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFragmentStack = new HashMap<>();
    }

    @Override
    protected String getPage() {
        return "menu.html";
    }

    @Override
    protected boolean onUnhandledCallback(String callback, JSONObject data) {
        return false;
    }

    @Override
    protected boolean onUnhandledEvent(String event, JSONObject data, String callback) {
        switch (event) {
            case "sidemenu:switch":
                try {
                    String id = data.getString("id");
                    String controller = data.getString("controller");
                    String page = data.getString("page");
                    JSONObject dataForFragment = data.optJSONObject("data");

                    CobaltFragment fragment;
                    if (mFragmentStack.containsKey(id)) {
                        fragment = mFragmentStack.get(id);
                    }
                    else {
                        fragment = Cobalt.getInstance(mContext).getFragmentForController(DefaultFragment.class, controller, page);
                        mFragmentStack.put(id, fragment);
                        ((WithSidemenuActivity) mContext).setDataNavigation(dataForFragment);
                    }

                    ((WithSidemenuActivity) mContext).switchFragment(fragment);

                    Bundle bundle = Cobalt.getInstance(mContext).getConfigurationForController(controller);
                    if (bundle.containsKey(Cobalt.kBars)) {
                        try {
                            JSONObject actionBar = new JSONObject(bundle.getString(Cobalt.kBars));
                            setBars(actionBar);
                        }
                        catch (JSONException exception) {
                            setBars(null);
                            if (Cobalt.DEBUG) {
                                Log.e(Cobalt.TAG, TAG + " - onCreate: bars configuration parsing failed. " + bundle.getString(Cobalt.kBars));
                            }
                            exception.printStackTrace();
                        }
                    }
                    else setBars(new JSONObject());
                }
                catch (JSONException e) {
                    Log.d(TAG, TAG + " - onUnhandledEvent: missing id, controller and/or page field(s) or not string(s)");
                    e.printStackTrace();
                }
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onUnhandledMessage(JSONObject message) {

    }
}
