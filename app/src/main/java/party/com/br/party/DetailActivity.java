package party.com.br.party;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.entity.Event;
import party.com.br.party.helper.Constants;

public class DetailActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        mToolbar = findViewById(R.id.toolbar_detail);

        setSupportActionBar(mToolbar);
        if(mToolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        if(getIntent().getExtras() != null && getIntent().hasExtra(Constants.SEND_EVENT)) {
            mProgressBar.setVisibility(View.GONE);
            mEvent = getIntent().getExtras().getParcelable(Constants.SEND_EVENT);
            if(mEvent != null) {
                getSupportActionBar().setTitle(mEvent.getName());
                mTvAdress.setText(mEvent.getAdress());
                mTvContact.setText(mEvent.getContact());
                mTvPrice.setText("R$ 150,00");
                DateFormat format = new SimpleDateFormat("dd/MM", Locale.getDefault());
                if(mEvent.getDate() != null)
                    mTvSchedule.setText(format.format(mEvent.getDate()) + " às " + mEvent.getSchedule());
                else
                    mTvSchedule.setText(mEvent.getSchedule());
                if(!mEvent.getPicture().equals(""))
                    Picasso.get().load(mEvent.getPicture()).into(mIvBanner);
            }
        }
    }
}
