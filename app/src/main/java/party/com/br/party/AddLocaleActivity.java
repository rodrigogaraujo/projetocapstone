package party.com.br.party;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.santalu.maskedittext.MaskEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.entity.LocaleTicket;
import party.com.br.party.helper.Constants;
import party.com.br.party.helper.Utilities;

public class AddLocaleActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.bt_confirm)
    ImageButton mBtConfirm;
    @BindView(R.id.tv_adress)
    EditText mEtAdress;
    @BindView(R.id.tv_email)
    EditText mEtEmail;
    MaskEditText mEtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_locale);
        ButterKnife.bind(this);
        mEtPhone = findViewById(R.id.tv_phone);
        mBtConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt_confirm:
                buttonConfirm();
                break;
        }
    }

    private void buttonConfirm() {
        mBtConfirm.startAnimation(Utilities.animationAlpha());
        if(mEtAdress.getText().toString().equals("")){
            mEtAdress.setError(getString(R.string.field_));
        }else{
            if(mEtEmail.getText().toString().equals("") && mEtPhone.getText().toString().equals("")){
                mEtAdress.setError(getString(R.string.field_));
            }else{
                LocaleTicket localeTicket = new LocaleTicket();
                localeTicket.setAdress(mEtAdress.getText().toString());
                localeTicket.setEmail(mEtEmail.getText().toString());
                localeTicket.setPhone(mEtPhone.getText().toString());
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constants.SEND_EVENT_LOCALE, localeTicket);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        }

    }
}
