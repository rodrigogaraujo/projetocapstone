package party.com.br.party;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.adapter.DetailAdapter;
import party.com.br.party.adapter.LocaleTicketAdapter;
import party.com.br.party.dao.EventDao;
import party.com.br.party.entity.Event;
import party.com.br.party.helper.Constants;
import party.com.br.party.helper.PartyPreferences;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

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
    @BindView(R.id.tv_more_info)
    TextView mTvMoreInfo;
    @BindView(R.id.iv_banner)
    ImageView mIvBanner;
    @BindView(R.id.ib_edit)
    ImageButton mIbEdit;
    @BindView(R.id.bt_go)
    ImageButton mBtGo;
    @BindView(R.id.bt_who)
    Button mBtWho;
    @BindView(R.id.progress_event)
    ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerViewLocale;
    private DetailAdapter mDetailAdapter;
    private LocaleTicketAdapter mLocaleTicketAdapter;
    private PartyPreferences mPartyPreferences;
    private CardView mCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        mToolbar = findViewById(R.id.toolbar_detail);
        mRecyclerView = findViewById(R.id.rv_days_event);
        mRecyclerViewLocale = findViewById(R.id.rv_points);
        mCard = findViewById(R.id.card_edit);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerViewLocale.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mPartyPreferences = new PartyPreferences(this);

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
                if (mEvent.getDate() == null)
                    stringDate = format.format(new Date());
                else
                    stringDate = format.format(mEvent.getDate());
                mTvDate.setText(getResources().getString(R.string.date_hours, String.valueOf(stringDate), String.valueOf(mEvent.getHours())));
                if (mEvent.getDescription().equals(""))
                    mTvMoreInfo.setText(R.string.no_details);
                else
                    mTvMoreInfo.setText(mEvent.getDescription());
                mDetailAdapter = new DetailAdapter(this, mEvent.getDays());
                mRecyclerView.setAdapter(mDetailAdapter);

                mLocaleTicketAdapter = new LocaleTicketAdapter(this, mEvent.getLocaleTickets());
                mRecyclerViewLocale.setAdapter(mLocaleTicketAdapter);
                if (mEvent.getIdAdmin().equals(mPartyPreferences.getIdUser())) {
                    mIbEdit.setVisibility(View.VISIBLE);
                    mCard.setVisibility(View.VISIBLE);
                    mBtGo.setEnabled(false);
                }
                if(mEvent.getIdPersonGo() != null)
                    if (mEvent.getIdPersonGo().contains(mPartyPreferences.getIdUser()))
                        mBtGo.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
            }
        }
        mIbEdit.setOnClickListener(this);
        mBtGo.setOnClickListener(this);
        mBtWho.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.ib_edit:
                sendEvent();
                break;
            case R.id.bt_go:
                btGo();
                break;
            case R.id.bt_who:
                btWhoGo();
                break;
        }
    }

    private void btGo() {
        List<String> ids;
        if(mEvent.getIdPersonGo() != null) {
            ids = mEvent.getIdPersonGo();
            if (mEvent.getIdPersonGo().contains(mPartyPreferences.getIdUser())) {
                mBtGo.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_no));
                ids.remove(mPartyPreferences.getIdUser());
            } else {
                mBtGo.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                ids.add(mPartyPreferences.getIdUser());
            }
            mEvent.getIdPersonGo().clear();
        }else{
            ids = new ArrayList<>();
        }
        mEvent.setIdPersonGo(ids);
        new EventDao().update(mEvent);
    }

    private void btWhoGo() {
    }

    private void sendEvent() {
        Intent i = new Intent(this, EventActivity.class);
        i.putExtra(Constants.SEND_EVENT, mEvent.getId());
        startActivity(i);
        finish();
    }
}
