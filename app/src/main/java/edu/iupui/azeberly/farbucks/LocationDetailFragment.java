package edu.iupui.azeberly.farbucks;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.iupui.azeberly.farbucks.model.Location;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocationDetailFragment extends Fragment {

    public TextView mLocationDetailTextView;
    public Location mLocation;


    public LocationDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // INFLATE THE LAYOUT FOR THIS FRAGMENT
        View v = inflater.inflate(R.layout.fragment_location_detail, container, false);

        // WIRE UP THE CONTROLS
        mLocationDetailTextView = (TextView) v.findViewById(R.id.location_detail_name);

        Bundle b = this.getArguments();
        if (b != null) {
            mLocation = (Location) b.getSerializable("Location");
        }

        // WRITE LOCATION DATA TO THE WIDGETS ON SCREEN
        mLocationDetailTextView.setText(mLocation.getName());

        return v;
    }

}
