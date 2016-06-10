package org.cobaltians.kikou;

import android.app.Application;
import org.cobaltians.cobalt.Cobalt;

/**
 * Created by sebastien on 29/07/2014.
 */
public class kikouApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Cobalt.getInstance(this).setResourcePath("www/common/");
    }
}
