package party.com.br.party;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.entity.Event;
import party.com.br.party.helper.Constants;
import party.com.br.party.helper.Utilities;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Event mEvent;
    private Toolbar mToolbar;
    @BindView(R.id.tv_adress)
    TextView mTvAdress;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.tv_schedule)
    TextView mTvSchedule;
    @BindView(R.id.tv_contact)
    TextView mTvContact;
    @BindView(R.id.iv_banner)
    ImageView mIvBanner;
    @BindView(R.id.progress_event)
    ProgressBar mProgressBar;
    private MapView mMapView;
    private GoogleMap mGoogleMap;
    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        mToolbar = findViewById(R.id.toolbar_detail);

        setSupportActionBar(mToolbar);
        if (mToolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mMapView = findViewById(R.id.map_view);
        mMapView.onCreate(mapViewBundle);
        mMapView.getMapAsync(this);

        if (getIntent().getExtras() != null && getIntent().hasExtra(Constants.SEND_EVENT)) {
            mProgressBar.setVisibility(View.GONE);
            mEvent = getIntent().getExtras().getParcelable(Constants.SEND_EVENT);
            if (mEvent != null) {
                getSupportActionBar().setTitle(mEvent.getName());
                mTvAdress.setText(mEvent.getAdress());
                mTvContact.setText(mEvent.getContact());
                mTvPrice.setText("R$ 150,00");
                DateFormat format = new SimpleDateFormat("dd/MM", Locale.getDefault());
                if (mEvent.getDate() != null)
                    mTvSchedule.setText(format.format(mEvent.getDate()) + " às " + mEvent.getSchedule());
                else
                    mTvSchedule.setText("24/04 ás " + mEvent.getSchedule());
                if (!mEvent.getPicture().equals(""))
                    Picasso.get().load(mEvent.getPicture()).into(mIvBanner);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.clear();
        try {
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Utilities.getLocationFromAdress(mEvent.getAdress(),
                    this), Constants.DEFAULT_ZOOM));
            if(mEvent.getAdress() != null){
                LatLng latLng = Utilities.getLocationFromAdress(mEvent.getAdress(), this);
                mGoogleMap.addMarker(new MarkerOptions().position(latLng));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }
        mMapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
