package party.com.br.party;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Switch;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PreferencesActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.bt_confirm)
    ImageButton mBtConfirm;
    @BindView(R.id.et_km)
    EditText mEtKm;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        ButterKnife.bind(this);
        mSwitchActive = findViewById(R.id.switch_active);
        mSpnFrase = findViewById(R.id.spn_frase);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBtConfirm.setOnClickListener(this);
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
}
