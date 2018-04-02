package edu.iupui.azeberly.farbucks;

import android.support.v4.app.Fragment;


public class HomeActivity extends SingleFragmentActivity {

    protected Fragment createFragment() {
        return new HomeFragment();
    }

}
