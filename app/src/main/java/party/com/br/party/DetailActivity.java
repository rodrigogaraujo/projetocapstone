package party.com.br.party;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.adapter.DetailAdapter;
import party.com.br.party.entity.Event;
import party.com.br.party.helper.Constants;
import party.com.br.party.helper.Utilities;

public class DetailActivity extends AppCompatActivity{

    private Event mEvent;
    private Toolbar mToolbar;
    @BindView(R.id.tv_adress)
    TextView mTvAdress;
    @BindView(R.id.tv_contact)
    TextView mTvContact;
    @BindView(R.id.tv_email)
    TextView mTvEmail;
    @BindView(R.id.tv_date)
    TextView mTvDate;
    @BindView(R.id.iv_banner)
    ImageView mIvBanner;
    @BindView(R.id.progress_event)
    ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private DetailAdapter mDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        mToolbar = findViewById(R.id.toolbar_detail);
        mRecyclerView = findViewById(R.id.rv_days_event);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        setSupportActionBar(mToolbar);
        if (mToolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        if (getIntent().getExtras() != null && getIntent().hasExtra(Constants.SEND_EVENT)) {
            mProgressBar.setVisibility(View.GONE);
            mEvent = getIntent().getExtras().getParcelable(Constants.SEND_EVENT);
            if (mEvent != null) {
                getSupportActionBar().setTitle(mEvent.getName());
                mTvAdress.setText(mEvent.getAdress());
                mTvContact.setText(mEvent.getContact());
                mTvEmail.setText(mEvent.getEmail());
                if (!mEvent.getPicture().equals(""))
                    Picasso.get().load(mEvent.getPicture()).into(mIvBanner);

                String stringDate;
                SimpleDateFormat format = new SimpleDateFormat("dd - MMMM", new Locale("Portuguese", "BR"));
                if(mEvent.getDate() == null)
                    stringDate = format.format(new Date());
                else
                    stringDate = format.format(mEvent.getDate());
                mTvDate.setText(getResources().getString(R.string.date_hours, String.valueOf(stringDate), String.valueOf(mEvent.getHours())));
                mDetailAdapter = new DetailAdapter(this, mEvent.getDays());
                mRecyclerView.setAdapter(mDetailAdapter);
            }
        }
    }
}
