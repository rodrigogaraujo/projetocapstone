package party.com.br.party;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.dao.UserDao;
import party.com.br.party.entity.User;
import party.com.br.party.helper.Constants;
import party.com.br.party.helper.PartyPreferences;
import party.com.br.party.helper.Utilities;
import party.com.br.party.listener.GetByTypeListener;

public class PreferencesActivity extends AppCompatActivity implements View.OnClickListener, GetByTypeListener<User>, AdapterView.OnItemSelectedListener {

    @BindView(R.id.cb_elec)
    CheckBox mCbElec;
    @BindView(R.id.cb_forro)
    CheckBox mCbForro;
    @BindView(R.id.cb_mpb)
    CheckBox mCbMpb;
    @BindView(R.id.cb_rock)
    CheckBox mCbRock;
    @BindView(R.id.cb_samba)
    CheckBox mCbSamba;
    @BindView(R.id.cb_sertanejo)
    CheckBox mCbSertanejo;
    private Spinner mSpnFrase;
    private Switch mSwitchActive;;
    private Toolbar mToolbar;
    private ArrayAdapter<String> mArrayAdapter;
    private PartyPreferences mPartyPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        ButterKnife.bind(this);

        mSwitchActive = findViewById(R.id.switch_active);
        mSpnFrase = findViewById(R.id.spn_frase);

        mToolbar = findViewById(R.id.toolbar_detail);
        setSupportActionBar(mToolbar);

        mPartyPreferences = new PartyPreferences(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setValueSpinner();

        mSwitchActive.setOnClickListener(this);
        mCbSamba.setOnClickListener(this);
        mCbRock.setOnClickListener(this);
        mCbMpb.setOnClickListener(this);
        mCbForro.setOnClickListener(this);
        mCbElec.setOnClickListener(this);
        mCbSertanejo.setOnClickListener(this);
        mSpnFrase.setOnItemSelectedListener(this);

        new UserDao().getById(mPartyPreferences.getIdUser(), this);
    }

    private void setValueSpinner() {
        List<String> texts = new ArrayList<>();
        texts.add(Constants.TEXTS_STATUS.DIRIGINDO);
        texts.add(Constants.TEXTS_STATUS.OCUPADO);
        texts.add(Constants.TEXTS_STATUS.OLA);
        texts.add(Constants.TEXTS_STATUS.TRABALHANDO);
        texts.add(Constants.TEXTS_STATUS.VIAJANDO);
        mArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, texts);
        mSpnFrase.setAdapter(mArrayAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, InitActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, InitActivity.class));
        finish();
    }

    private void changeStatus() {
        new UserDao().getById(mPartyPreferences.getIdUser(), new GetByTypeListener<User>() {
            @Override
            public void getByType(User user) {
                user.setStatus(user.isStatus());
                new UserDao().update(user);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.switch_active:
                changeStatus();
                break;
            case R.id.cb_elec:
                updateCheckbox(mCbElec, mPartyPreferences.getIdUser(), Constants.TYPE_INTEREST.ELETRONICA);
                break;
            case R.id.cb_forro:
                updateCheckbox(mCbForro, mPartyPreferences.getIdUser(), Constants.TYPE_INTEREST.FORRO);
                break;
            case R.id.cb_mpb:
                updateCheckbox(mCbMpb, mPartyPreferences.getIdUser(), Constants.TYPE_INTEREST.MPB);
                break;
            case R.id.cb_rock:
                updateCheckbox(mCbRock, mPartyPreferences.getIdUser(), Constants.TYPE_INTEREST.ROCK);
                break;
            case R.id.cb_samba:
                updateCheckbox(mCbSamba, mPartyPreferences.getIdUser(), Constants.TYPE_INTEREST.SAMBA);
                break;
            case R.id.cb_sertanejo:
                updateCheckbox(mCbSertanejo, mPartyPreferences.getIdUser(), Constants.TYPE_INTEREST.SERTANEJO);
                break;
        }
    }

    private void updateCheckbox(CheckBox cb, String id, String interest) {
        if (cb.isChecked()) {
            new UserDao().getById(id, new GetByTypeListener<User>() {
                @Override
                public void getByType(User user) {
                    List interestList = user.getInterest();
                    interestList.add(interest);
                    user.setInterest(interestList);
                    user.setStatus(!user.isStatus());
                    new UserDao().update(user);
                }
            });
        } else {
            new UserDao().getById(id, new GetByTypeListener<User>() {
                @Override
                public void getByType(User user) {
                    List interestList = user.getInterest();
                    if (interestList.size() > 1) {
                        interestList.remove(interest);
                        user.setInterest(interestList);
                        user.setStatus(!user.isStatus());
                        new UserDao().update(user);
                    } else {
                        cb.setChecked(true);
                        Utilities.confirmDialog(PreferencesActivity.this, getString(R.string.errror), getString(R.string.one_interest));
                    }
                }
            });
        }
    }

    @Override
    public void getByType(User user) {
        if (user.isStatus())
            mSwitchActive.setChecked(true);
        else
            mSwitchActive.setChecked(false);

        if (user.getInterest().contains(Constants.TYPE_INTEREST.SERTANEJO))
            mCbSertanejo.setChecked(true);
        else
            mCbSertanejo.setChecked(false);

        if (user.getInterest().contains(Constants.TYPE_INTEREST.ELETRONICA))
            mCbElec.setChecked(true);
        else
            mCbElec.setChecked(false);

        if (user.getInterest().contains(Constants.TYPE_INTEREST.FORRO))
            mCbForro.setChecked(true);
        else
            mCbForro.setChecked(false);

        if (user.getInterest().contains(Constants.TYPE_INTEREST.MPB))
            mCbMpb.setChecked(true);
        else
            mCbMpb.setChecked(false);

        if (user.getInterest().contains(Constants.TYPE_INTEREST.ROCK))
            mCbRock.setChecked(true);
        else
            mCbRock.setChecked(false);

        if (user.getInterest().contains(Constants.TYPE_INTEREST.SAMBA))
            mCbSamba.setChecked(true);
        else
            mCbSamba.setChecked(false);

        mSpnFrase.setSelection(mArrayAdapter.getPosition(user.getText()));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        new UserDao().getById(mPartyPreferences.getIdUser(), new GetByTypeListener<User>() {
            @Override
            public void getByType(User user) {
                user.setText(mArrayAdapter.getItem(position));
                user.setStatus(!user.isStatus());
                Log.d("okok", mArrayAdapter.getItem(position));
                new UserDao().update(user);
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
