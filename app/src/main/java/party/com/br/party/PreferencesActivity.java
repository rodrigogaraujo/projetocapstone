package party.com.br.party;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.dao.UserDao;
import party.com.br.party.entity.User;
import party.com.br.party.helper.PartyPreferences;
import party.com.br.party.listener.GetByTypeListener;

public class PreferencesActivity extends AppCompatActivity implements View.OnClickListener, GetByTypeListener<User> {

    @BindView(R.id.bt_confirm)
    ImageButton mBtConfirm;
    Switch mSwitchActive;
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
    Spinner mSpnFrase;
    private PartyPreferences mPartyPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        ButterKnife.bind(this);
        mSwitchActive = findViewById(R.id.switch_active);
        mSpnFrase = findViewById(R.id.spn_frase);
        mPartyPreferences = new PartyPreferences(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBtConfirm.setOnClickListener(this);
        new UserDao().getById(mPartyPreferences.getIdUser(), this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt_confirm:
                finish();
                break;
        }
    }

    @Override
    public void getByType(User user) {

    }
}
