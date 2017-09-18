package pl.turek.powiat.powiatturecki;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap google_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        SupportMapFragment support_map_fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        support_map_fragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap google_map) {
        this.google_map = google_map;
        LatLng sydney = new LatLng(-34, 151);
        google_map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        google_map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
