package com.penaorange.gmapkp;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

/**
 * Created by Pena Orange on 10/09/2015.
 */
public class Dashboard extends FragmentActivity
        implements
        GoogleApiClient.OnConnectionFailedListener,
        OnMapReadyCallback {

    private GoogleMap mMap;
    private UiSettings mUiSetting;
    private GoogleApiClient mGoogleApiClient;
    static final LatLng TutorialsPoint = new LatLng(-6.867668, 107.593349);
    EditText et;

    AutoCompleteTextView jbView;
    Spinner spinner;

    // These settings are the same as the settings for the map. They will in fact give you updates
    // at the maximal rates currently possible.
    private static final LocationRequest REQUEST = LocationRequest.create()
            .setInterval(5000)         // 5 seconds
            .setFastestInterval(16)    // 16ms = 60fps
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        et = (EditText) findViewById(R.id.LKText);
//        jbView = (AutoCompleteTextView) findViewById(R.id.autocomplete_jenisBarang);
//
//        String[] jb = getResources().getStringArray(R.array.jenis_barang);
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, jb);
//        jbView.setAdapter(adapter);

        spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.jenis_barang, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    public void onPause() {
        super.onPause();
        mGoogleApiClient.disconnect();
    }

    /**
     * Button to get current Location. This demonstrates how to get the current Location as required
     * without needing to register a LocationListener.
     */
    public void showMyLocation(View view) {
        if (mGoogleApiClient.isConnected()) {
            Location location = new Location(LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient));
            double lat = location.getLatitude();
            double lang = location.getLongitude();
            List<Address> addressList = null;

            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocation(lat, lang, 1);
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "Lokasi tidak ada ", Toast.LENGTH_SHORT).show();
            }

            Address address = addressList.get(0);

            String msg = "Lokasiku di " + address.getSubLocality() + ", " +
                    address.getThoroughfare() + ", " + address.getSubAdminArea() +
                    " dengan koordinat lat : " + lat + " dan long : " + lang;
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

            et.setText(msg);
        } else {
            Toast.makeText(getApplicationContext(), "Tidak ada koneksi", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Implementation of {@link GoogleApiClient.OnConnectionFailedListener}.
     */
    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Toast.makeText(getApplicationContext(), "Tidak ada koneksi, aplikasi tidak berfungsi", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        mMap.setMyLocationEnabled(true);
        mUiSetting = mMap.getUiSettings();
        mUiSetting.setZoomControlsEnabled(true);

        Marker TP = mMap.addMarker(new MarkerOptions().
                position(TutorialsPoint).title("TutorialsPoint2"));
    }
}

