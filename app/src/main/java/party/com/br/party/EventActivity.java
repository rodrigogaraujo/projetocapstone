package party.com.br.party;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.santalu.maskedittext.MaskEditText;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.adapter.DetailAdapter;
import party.com.br.party.adapter.LocaleTicketAdapter;
import party.com.br.party.dao.EventDao;
import party.com.br.party.entity.Day;
import party.com.br.party.entity.Event;
import party.com.br.party.entity.LocaleTicket;
import party.com.br.party.helper.Constants;
import party.com.br.party.helper.Lists;
import party.com.br.party.helper.PartyPreferences;
import party.com.br.party.helper.Utilities;
import party.com.br.party.listener.GetByTypeListener;

public class EventActivity extends AppCompatActivity implements View.OnClickListener, GetByTypeListener<Event> {

    @BindView(R.id.iv_banner)
    ImageView mIvBanner;
    @BindView(R.id.bt_confirm)
    ImageButton mBtConfirm;
    @BindView(R.id.bt_add_day)
    ImageButton mBtAddDay;
    @BindView(R.id.bt_remove_day)
    ImageButton mBtRemoveDay;
    @BindView(R.id.bt_add_locale)
    ImageButton mBtAddLocale;
    @BindView(R.id.bt_remove_locale)
    ImageButton mBtRemoveLocale;
    @BindView(R.id.et_description)
    EditText mEtDescription;
    @BindView(R.id.et_more_details)
    EditText mEtMoreDetails;
    @BindView(R.id.et_adress)
    EditText mEtAdress;
    @BindView(R.id.et_time)
    EditText mEtTime;
    @BindView(R.id.et_email)
    EditText mEtEmail;
    @BindView(R.id.et_city)
    EditText mEtCity;
    @BindView(R.id.spn_state)
    Spinner mSpnState;
    @BindView(R.id.tv_no_itens_locale)
    TextView mTvNoItensLocales;
    @BindView(R.id.tv_no_itens_days)
    TextView mTvNoItensDays;
    @BindView(R.id.tv_update_picture)
    TextView mTvTextPicture;
    @BindView(R.id.spn_type)
    Spinner mSpnType;
    @BindView(R.id.progress_create_event)
    ProgressBar mProgressBar;
    private String mPicture;
    private StorageReference mStorageReference;
    private MaskEditText mEtPhone;
    private EditText mEtDate;
    private RecyclerView mRvDays;
    private RecyclerView mRvLocales;
    private DetailAdapter mDetailAdapter;
    private LocaleTicketAdapter mLocaleTicketAdapter;
    private List<Day> mDays;
    private List<LocaleTicket> mLocales;
    private List<String> mIdsGo;
    private String mIdEvent;
    private SimpleDateFormat mDateFormat;
    private PartyPreferences mPartyPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        ButterKnife.bind(this);
        mEtPhone = findViewById(R.id.et_phone);
        mEtDate = findViewById(R.id.et_date);
        mRvDays = findViewById(R.id.rv_days_event);
        mRvLocales = findViewById(R.id.rv_locale_ticket);

        mPartyPreferences = new PartyPreferences(this);

        mIdEvent = "";
        mPicture = "";
        mStorageReference = FirebaseStorage.getInstance().getReference();

        if (getIntent().hasExtra(Constants.SEND_EVENT) && getIntent().getExtras() != null) {
            mIdEvent = getIntent().getStringExtra(Constants.SEND_EVENT);
            new EventDao().getById(mIdEvent, this);
        }

        mIdsGo = new ArrayList<>();

        mDays = new ArrayList<>();
        mDetailAdapter = new DetailAdapter(this, mDays);
        mRvDays.setHasFixedSize(true);
        mRvDays.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRvDays.setAdapter(mDetailAdapter);

        mLocales = new ArrayList<>();
        mLocaleTicketAdapter = new LocaleTicketAdapter(this, mLocales);
        mRvLocales.setHasFixedSize(true);
        mRvLocales.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvLocales.setAdapter(mLocaleTicketAdapter);

        ArrayAdapter<String> mAdapterState = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Lists.getStates());
        mSpnState.setAdapter(mAdapterState);

        ArrayAdapter<String> mAdapterType = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Lists.getTypes());
        mSpnType.setAdapter(mAdapterType);

        mBtConfirm.setOnClickListener(this);
        mBtAddDay.setOnClickListener(this);
        mBtRemoveDay.setOnClickListener(this);
        mBtAddLocale.setOnClickListener(this);
        mBtRemoveLocale.setOnClickListener(this);
        mIvBanner.setOnClickListener(this);

        mDateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("BR"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        verifyDatas();

        if (!mIdEvent.equals("")) {
            new EventDao().getById(mIdEvent, this);
        }
    }

    private void verifyDatas() {
        if (mLocaleTicketAdapter.getItemCount() > 0) {
            mTvNoItensLocales.setVisibility(View.GONE);
            mRvLocales.setVisibility(View.VISIBLE);
        } else {
            mTvNoItensLocales.setVisibility(View.VISIBLE);
            mRvLocales.setVisibility(View.GONE);
        }

        if (mDetailAdapter.getItemCount() > 0) {
            mTvNoItensDays.setVisibility(View.GONE);
            mRvDays.setVisibility(View.VISIBLE);
        } else {
            mTvNoItensDays.setVisibility(View.VISIBLE);
            mRvDays.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt_confirm:
                confirmButton();
                break;
            case R.id.bt_add_day:
                addDay();
                break;
            case R.id.bt_remove_day:
                mBtRemoveDay.startAnimation(Utilities.animationAlpha());
                break;
            case R.id.bt_add_locale:
                addLocale();
                break;
            case R.id.bt_remove_locale:
                mBtRemoveLocale.startAnimation(Utilities.animationAlpha());
                break;
            case R.id.iv_banner:
                takePicture();
                break;
        }
    }

    private void takePicture() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, getString(R.string.select_picture)), Constants.PICTURE.SELECT_IMAGE);
    }

    private void enableEditText(Boolean b) {
        mEtDescription.setEnabled(b);
        mEtPhone.setEnabled(b);
        mEtDate.setEnabled(b);
        mEtMoreDetails.setEnabled(b);
        mEtTime.setEnabled(b);
        mEtEmail.setEnabled(b);
        mEtAdress.setEnabled(b);
        mEtCity.setEnabled(b);
        mIvBanner.setEnabled(b);
        mBtAddDay.setEnabled(b);
        mBtConfirm.setEnabled(b);
        mBtAddLocale.setEnabled(b);
        mBtRemoveDay.setEnabled(b);
        mBtRemoveLocale.setEnabled(b);
    }

    private void confirmButton() {
        mBtConfirm.startAnimation(Utilities.animationAlpha());
        Event event = new Event();
        try {
            if(mEtDate.getText().toString().equals("") || mEtDescription.getText().toString().equals("") || mEtTime.getText().toString().equals("")
                    || mEtAdress.getText().toString().equals("") || mEtCity.getText().toString().equals("") || mEtEmail.getText().toString().equals("")
                    || mEtPhone.getText().toString().equals("")){
                Utilities.confirmDialog(this, getString(R.string.error_empty), getString(R.string.error_create_event));
            }else {
                event.setIdAdmin(mPartyPreferences.getIdUser());
                event.setDate(mDateFormat.parse(mEtDate.getText().toString()));
                event.setName(mEtDescription.getText().toString());
                event.setAdress(mEtAdress.getText().toString());
                event.setContact(mEtPhone.getText().toString());
                event.setEmail(mEtEmail.getText().toString());
                event.setHours(Integer.parseInt(mEtTime.getText().toString()));
                event.setDescription(mEtMoreDetails.getText().toString());
                event.setPicture(mPicture);
                event.setLocation(mEtCity.getText().toString().concat(getString(R.string.virg)).concat(mSpnState.getSelectedItem().toString()));
                event.setType(mSpnType.getSelectedItem().toString());
                event.setDays(mDays);
                event.setLocaleTickets(mLocales);
                mIdsGo.add(mPartyPreferences.getIdUser());
                event.setIdPersonGo(mIdsGo);
                if (mDays.size() == 0) {
                    Utilities.confirmDialog(this, getString(R.string.error_empty), getString(R.string.error_day_empty));
                } else if (mLocales.size() == 0) {
                    Utilities.confirmDialog(this, getString(R.string.error_empty), getString(R.string.error_locale_empty));
                } else {
                    if (!mIdEvent.equals("")) {
                        event.setId(mIdEvent);
                        new EventDao().update(event);
                        Utilities.confirmDialog(this, getString(R.string.update_event_title), getString(R.string.update_event));
                    } else {
                        String id = new EventDao().create(event);
                        Utilities.confirmDialog(this, getString(R.string.event_create_title), getString(R.string.event_create));
                        mIdEvent = id;
                    }
                }
            }
        } catch (ParseException e) {
            Utilities.confirmDialog(this, getString(R.string.error_date), getString(R.string.format_correct));
        }
    }

    private void addDay() {
        mBtAddDay.startAnimation(Utilities.animationAlpha());
        Intent intent = new Intent(this, AddDayActivity.class);
        startActivityForResult(intent, Constants.EVENT_ACTION.ADD_DAY);
    }

    private void addLocale() {
        mBtAddLocale.startAnimation(Utilities.animationAlpha());
        Intent intent = new Intent(this, AddLocaleActivity.class);
        startActivityForResult(intent, Constants.EVENT_ACTION.ADD_LOCALE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.EVENT_ACTION.ADD_DAY) {
            if (resultCode == Activity.RESULT_OK) {
                Day day = data.getExtras().getParcelable(Constants.SEND_EVENT_DAY);
                if (!mDays.contains(day)) {
                    mDays.add(day);
                }
            }
        } else if (requestCode == Constants.EVENT_ACTION.ADD_LOCALE) {
            if (resultCode == Activity.RESULT_OK) {
                LocaleTicket localeTicket = data.getExtras().getParcelable(Constants.SEND_EVENT_LOCALE);
                if (!mLocales.contains(localeTicket)) {
                    mLocales.add(localeTicket);
                }
            }
        } else if (requestCode == Constants.PICTURE.SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    enableEditText(false);
                    mProgressBar.setVisibility(View.VISIBLE);
                    mTvTextPicture.setVisibility(View.VISIBLE);
                    Uri selectedImgUri = data.getData();
                    StorageReference reference = mStorageReference.child(Constants.FIREBASE_STORAGE.CHILD_PHOTO_BANNER).child(selectedImgUri.getLastPathSegment());
                    reference.putFile(selectedImgUri).addOnSuccessListener(taskSnapshot -> {
                        mPicture = taskSnapshot.getDownloadUrl().toString();
                        mProgressBar.setVisibility(View.GONE);
                        mTvTextPicture.setVisibility(View.GONE);
                        Picasso.get().load(mPicture).into(mIvBanner);
                        enableEditText(true);
                        Utilities.confirmDialog(EventActivity.this, getString(R.string.banner_ok_title), getString(R.string.banner_ok));
                    });
                }
            }
        }
    }

    @Override
    public void getByType(Event event) {
        if (event.getDate() != null)
            mEtDate.setText(mDateFormat.format(event.getDate()));
        mEtDescription.setText(event.getName());
        mEtAdress.setText(event.getAdress());
        mEtPhone.setText(event.getContact());
        mEtEmail.setText(event.getEmail());
        mEtTime.setText(String.valueOf(event.getHours()));
        mEtMoreDetails.setText(event.getDescription());
        mPicture = event.getPicture();
        Picasso.get().load(mPicture).into(mIvBanner);
        String line = event.getLocation();
        String values[] = line.split(",");
        mEtCity.setText(values[0]);
        mSpnState.setSelection(Lists.getStates().indexOf(values[1]));
        mSpnType.setSelection(Lists.getTypes().indexOf(event.getType()));
        mDays.clear();
        mDays.addAll(event.getDays());
        mLocales.clear();
        mLocales.addAll(event.getLocaleTickets());
        mIdsGo.clear();
        mIdsGo.addAll(event.getIdPersonGo());
        verifyDatas();
    }
}
