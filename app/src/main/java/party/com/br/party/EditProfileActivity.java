package party.com.br.party;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.helper.Utilities;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.bt_confirm)
    ImageButton mBtConfirm;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_email)
    EditText mEtEmail;
    @BindView(R.id.et_pass)
    EditText mEtPass;
    @BindView(R.id.et_confirm_pass)
    EditText mEtConfirmPass;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_adress)
    EditText mEtAdress;
    @BindView(R.id.iv_profile)
    ImageView mIvProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);

        mBtConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt_confirm:
                mBtConfirm.startAnimation(Utilities.animationAlpha());
                break;
        }
    }
}
