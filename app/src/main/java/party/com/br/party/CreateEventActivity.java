package party.com.br.party;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.helper.Utilities;

public class CreateEventActivity extends AppCompatActivity implements View.OnClickListener {

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
    @BindView(R.id.et_date)
    EditText mEtDate;
    @BindView(R.id.et_time)
    EditText mEtTime;
    @BindView(R.id.et_email)
    EditText mEtEmail;
    @BindView(R.id.et_phone)
    EditText mEtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        ButterKnife.bind(this);

        mBtConfirm.setOnClickListener(this);
        mBtAddDay.setOnClickListener(this);
        mBtRemoveDay.setOnClickListener(this);
        mBtAddLocale.setOnClickListener(this);
        mBtRemoveLocale.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.bt_confirm :
                mBtConfirm.startAnimation(Utilities.animationAlpha());
                break;
            case R.id.bt_add_day :
                addDay();
                break;
            case R.id.bt_remove_day :
                mBtRemoveDay.startAnimation(Utilities.animationAlpha());
                break;
            case R.id.bt_add_locale :
                addLocale();
                break;
            case R.id.bt_remove_locale :
                mBtRemoveLocale.startAnimation(Utilities.animationAlpha());
                break;
        }
    }

    private void addDay() {
        mBtAddDay.startAnimation(Utilities.animationAlpha());
        startActivity(new Intent(this, AddDayActivity.class));
    }

    private void addLocale() {
        mBtAddLocale.startAnimation(Utilities.animationAlpha());
        startActivity(new Intent(this, AddLocaleActivity.class));
    }
}
