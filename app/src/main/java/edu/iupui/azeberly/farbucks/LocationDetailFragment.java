package edu.iupui.azeberly.farbucks;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.iupui.azeberly.farbucks.model.Location;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocationDetailFragment extends Fragment implements OnMapReadyCallback{

    public TextView mLocationDetailTextView;
    public Location mLocation;
    public TextView mLocationAddressTextView;
    public TextView mLocationCityTextView;
    public ImageView mLocationImageView;
    public MapView mLocationMapView;
    public GoogleMap mGoogleMap;


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
        mLocationAddressTextView = (TextView) v.findViewById(R.id.location_detail_address);
        mLocationCityTextView = (TextView) v.findViewById(R.id.location_detail_city);
        mLocationImageView = (ImageView) v.findViewById(R.id.location_detail_image);
        mLocationMapView = (MapView) v.findViewById(R.id.location_detail_map);

        Bundle b = this.getArguments();
        if (b != null) {
            mLocation = (Location) b.getSerializable("Location");
        }

        // WRITE LOCATION DATA TO THE WIDGETS ON SCREEN
        mLocationDetailTextView.setText(mLocation.getName());
        mLocationAddressTextView.setText(mLocation.getAddress());
        mLocationCityTextView.setText(mLocation.getCity());

        //SET UP STORE IMAGE
        String url = "https://storage.googleapis.com/farbucks_store_images/"+mLocation.getStoreImage()+".jpg";

        Glide
                .with(getContext())
                .load(url)
                .into(mLocationImageView);

        // SET UP THE LOCATION MAP
        mLocationMapView.onCreate(savedInstanceState);

        // GET THE GOOGLE MAP AND PLACE IT INTO THE MAPVIEW
        mLocationMapView.getMapAsync(this);

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        LatLng stadiumCoordinates = new LatLng(Double.parseDouble(mLocation.getLatitude()),
                Double.parseDouble(mLocation.getLongitude()));

        mGoogleMap.addMarker(new MarkerOptions().position(stadiumCoordinates));

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stadiumCoordinates, 13));

        mLocationMapView.onResume();
    }

}
