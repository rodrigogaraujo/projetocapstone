package party.com.br.party;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.dao.UserDao;
import party.com.br.party.entity.User;
import party.com.br.party.helper.PartyPreferences;
import party.com.br.party.helper.Utilities;
import party.com.br.party.listener.GetByTypeListener;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener, GetByTypeListener<User> {

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
    @BindView(R.id.pb_edit)
    ProgressBar mProgressBar;
    private PartyPreferences mPartyPreferences;
    private String mPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        mPartyPreferences = new PartyPreferences(this);

        mBtConfirm.setOnClickListener(this);
        mProgressBar.setVisibility(View.VISIBLE);
        mPicture = "";
        new UserDao().getById(mPartyPreferences.getIdUser(), this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt_confirm:
                confirmButton();
                break;
        }
    }

    private void confirmButton() {
        mProgressBar.setVisibility(View.VISIBLE);
        mBtConfirm.startAnimation(Utilities.animationAlpha());
        if(mEtName.getText().toString().equals("")){
            mEtName.setError(getString(R.string.error_empty));
        }else if(mEtAdress.getText().toString().equals("")){
            mEtAdress.setError(getString(R.string.error_empty));
        }else if(mEtPhone.getText().toString().equals("")){
            mEtPhone.setError(getString(R.string.error_empty));
        }else if(!mEtConfirmPass.getText().toString().equals(mEtPass.getText().toString())){
            mEtConfirmPass.setError(getString(R.string.error_invalid_pass));
            mEtPass.setError(getString(R.string.error_invalid_pass));
        }else {
            User user = new User(mPartyPreferences.getIdUser(), mEtName.getText().toString(), mPicture, mEtEmail.getText().toString(), mEtPhone.getText().toString(), mEtAdress.getText().toString(), "", null);
            new UserDao().update(user);
            mProgressBar.setVisibility(View.GONE);
            confirmDialog();
        }
    }

    private void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.update_profile);
        builder.setMessage(R.string.update_ok);
        builder.setPositiveButton(getString(R.string.ok), null);
        builder.show();
    }

    @Override
    public void getByType(User user) {
        if (user.getPicture().equals(""))
            mIvProfile.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_logo));
        else
            Picasso.get().load(user.getPicture()).into(mIvProfile);
        mEtAdress.setText(user.getAdress());
        mEtEmail.setText(user.getEmail());
        mEtName.setText(user.getName());
        mEtPhone.setText(user.getPhone());
        mProgressBar.setVisibility(View.GONE);
    }
}
