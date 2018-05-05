package party.com.br.party;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.entity.User;
import party.com.br.party.helper.Constants;
import party.com.br.party.helper.PartyPreferences;
import party.com.br.party.helper.Utilities;

public class CreateUserActivity extends AppCompatActivity implements View.OnClickListener{

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
    @BindView(R.id.progress_add_user)
    ProgressBar mProgress;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mFirebaseAuth;
    private Bundle mBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        ButterKnife.bind(this);
        mBtConfirm.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mEtPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher("BR"));
        }
        if(getIntent().getExtras() != null){
            mBundle = getIntent().getExtras();
            mEtEmail.setText(mBundle.getString(Constants.INTRO.SEND_EMAIL));
        }
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt_confirm:
                addUser();
                break;
        }
    }

    private void addUser() {
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
        }else{
            mProgress.setVisibility(View.VISIBLE);
            editEnable(false);
            User user = new User();
            user.setName(mEtName.getText().toString());
            user.setEmail(mEtEmail.getText().toString());
            user.setPhone(mEtPhone.getText().toString());
            user.setAdress(mEtAdress.getText().toString());
            user.setPicture("");
            user.setType(mBundle.getString(Constants.INTRO.SEND_TYPE));
            List<String> interests = new ArrayList<>();
            interests.add(Constants.TYPE_INTEREST.ELETRONICA);
            user.setInterest(interests);
            user.setStatus(true);
            user.setText(Constants.TEXTS_STATUS.OLA);
            mFirebaseAuth.createUserWithEmailAndPassword(user.getEmail(), mEtPass.getText().toString()).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    mProgress.setVisibility(View.GONE);
                    user.setId(task.getResult().getUser().getUid());
                    mDatabaseReference.child(Constants.FIREBASE_REALTIME.CHILD_USER).child(user.getId()).setValue(user);
                    PartyPreferences partyPreferences = new PartyPreferences(this);
                    partyPreferences.clear();
                    partyPreferences.saveUserPreferences(user.getId(), user.getEmail());
                    startActivity(new Intent(this, InitActivity.class));
                    this.finish();
                }else{
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException ex) {
                        mEtPass.setError(getString(R.string.pass_weak));
                        mEtPass.requestFocus();
                        mEtPass.setText("");
                        mEtConfirmPass.setText("");
                        mProgress.setVisibility(View.GONE);
                        editEnable(true);
                    } catch (FirebaseAuthEmailException ex) {
                        mEtEmail.setError(getString(R.string.error_invalid_email));
                        mEtPass.setText("");
                        mEtConfirmPass.setText("");
                        mEtEmail.requestFocus();
                        mProgress.setVisibility(View.GONE);
                        editEnable(true);
                    } catch (FirebaseAuthUserCollisionException ex) {
                        mEtEmail.setError(getString(R.string.error_email_exists));
                        mEtPass.setText("");
                        mEtConfirmPass.setText("");
                        mEtEmail.requestFocus();
                        mProgress.setVisibility(View.GONE);
                        editEnable(true);
                    } catch (Exception ex) {
                        mEtEmail.setError(getString(R.string.error_invalid_email));
                        mEtPass.setText("");
                        mEtConfirmPass.setText("");
                        mEtEmail.requestFocus();
                        mProgress.setVisibility(View.GONE);
                        editEnable(true);
                    }
                }
            });
        }
    }

    private void editEnable(boolean b) {
        mEtPhone.setEnabled(b);
        mEtAdress.setEnabled(b);
        mEtName.setEnabled(b);
        mEtEmail.setEnabled(b);
        mEtPass.setEnabled(b);
        mEtConfirmPass.setEnabled(b);
    }
}
