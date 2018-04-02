package edu.iupui.azeberly.farbucks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.iupui.azeberly.farbucks.model.FarbucksDataStore;
import edu.iupui.azeberly.farbucks.model.Location;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends Fragment {

    private RecyclerView mLocationRecyclerView;
    private LocationAdapter mLocationAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_location, container, false);

        // WIRE UP CONTROLS
        mLocationRecyclerView = (RecyclerView) v.findViewById(R.id.location_recycler_view);
        mLocationRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return v;
    }

    public void updateUI() {
        // QUERY FOR A LIST OF ALL LOCATIONS FROM THE DATABASE
        FarbucksDataStore farbucksDataStore = FarbucksDataStore.getInstance(getActivity().getApplication());
        List<Location> allLocations = farbucksDataStore.getLocations();

        // LOG OUTPUT THAT COUNTS THE NUMBER OF LOCATIONS IN THE DATABASE

        // WRITE LOCATION COUNT TO THE SCREEN

        mLocationAdapter = new LocationAdapter(allLocations);
        mLocationRecyclerView.setAdapter(mLocationAdapter);
    }

    // DECLARE ADAPTER CLASS
    private class LocationAdapter extends RecyclerView.Adapter<LocationHolder> {

        private List<Location> mLocationList;

        public LocationAdapter(List<Location> allLocations) {
            mLocationList = allLocations;
        }

        @Override
        public LocationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater =  LayoutInflater.from(getActivity());
            return new LocationHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(LocationHolder holder, int position) {
            Location location = mLocationList.get(position);
            holder.bindLocation(location);
        }

        @Override
        public int getItemCount() {
            return mLocationList.size();
        }
    } // END LocationAdapter class

    private class LocationHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mNameTextView;
        public TextView mAddressTextView;
        //public TextView

        public LocationHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.location_list_item, parent, false));
            mNameTextView = (TextView) itemView.findViewById(R.id.location_detail_name);
            mAddressTextView = (TextView) itemView.findViewById(R.id.location_address);

            itemView.setOnClickListener(this);
        }

        // DECLARE A VARIABLE TO HOLD A INSTANCE OF Location
        public Location mLocation;

        public void bindLocation(Location location) {
            mLocation = location;

            mNameTextView.setText(location.getName());
            mAddressTextView.setText(location.getAddress());
        } // END bindLocation METHOD

        @Override
        public void onClick(View v) {
            Log.d("FARBUCKS", "RecyclerView clicked");

            Bundle b = new Bundle();
            b.putSerializable("Location", mLocation);

            // CREATE INSTANCE OF DETAIL FRAGMENT
            LocationDetailFragment detailFragment = new LocationDetailFragment();

            // ADD BUNDLE TO OUR FRAGMENT OBJECT
            detailFragment.setArguments(b);

            // GET A REFERENCE TO THE FRAGMENT MANAGER
            FragmentManager fm = getActivity().getSupportFragmentManager();

            fm.beginTransaction()
                    .replace(R.id.content_single_fragment, detailFragment)
                    .addToBackStack(null)
                    .commit();
        } // END onClick METHOD
    }
}