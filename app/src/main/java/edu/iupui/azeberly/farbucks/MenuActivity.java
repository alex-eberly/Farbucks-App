package edu.iupui.azeberly.farbucks;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class MenuActivity extends SingleFragmentActivity {

    protected Fragment createFragment() {
        return new MenuFragment();
    }

}
