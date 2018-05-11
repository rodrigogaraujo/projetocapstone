package party.com.br.party;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.helper.Constants;
import party.com.br.party.helper.PartyPreferences;
import party.com.br.party.helper.Utilities;

public class EmailLoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.bt_confirm)
    ImageButton mBtConfirm;
    @BindView(R.id.et_email)
    EditText mEtEmail;
    @BindView(R.id.et_pass)
    EditText mEtPass;
    @BindView(R.id.progress_login)
    ProgressBar mProgressEmail;
    private PartyPreferences mPartyPreferences;
    private FirebaseAuth mFirebaseAuth;
    private String mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);
        ButterKnife.bind(this);
        mPartyPreferences = new PartyPreferences(this);
        mBtConfirm.setOnClickListener(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mEmail = "";
        if(savedInstanceState != null)
            mEmail = savedInstanceState.getString(Constants.SEND_PERSON);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt_confirm:
                loginValidate();
                break;
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null)
            mEmail = savedInstanceState.getString(Constants.SEND_PERSON);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mEmail.equals(""))
            mEmail = mEtEmail.getText().toString();
        outState.putString(Constants.SEND_PERSON, mEmail);
    }

    private void loginValidate() {
        if (Utilities.isConnected(this)) {
            if (mEtEmail.getText().toString().equals("") || mEtPass.getText().toString().equals("")) {
                mEtPass.setError(getString(R.string.error_empty));
                mEtEmail.setError(getString(R.string.error_empty));
            } else {
                mProgressEmail.setVisibility(View.VISIBLE);
                mBtConfirm.startAnimation(Utilities.animationAlpha());
                mEtPass.setEnabled(false);
                mEtEmail.setEnabled(false);
                mFirebaseAuth.fetchSignInMethodsForEmail(mEtEmail.getText().toString()).addOnCompleteListener(task -> {
                    if (task.getResult().getSignInMethods().size() > 0) {
                        signUserIn();
                    } else {
                        signUserUp(mEtEmail.getText().toString());
                    }
                });
            }
        } else {
            Utilities.confirmDialog(this, getString(R.string.error_conected), getString(R.string.error_conected_message));
        }
    }

    private void signUserUp(String email) {
        mProgressEmail.setVisibility(View.GONE);
        Intent i = new Intent(this, TypeUserActivity.class);
        i.putExtra(Constants.INTRO.SEND_EMAIL, email);
        startActivity(i);
        this.finish();
    }

    private void signUserIn() {
        mFirebaseAuth.signInWithEmailAndPassword(mEtEmail.getText().toString(), mEtPass.getText().toString()).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                mProgressEmail.setVisibility(View.GONE);
                mPartyPreferences.clear();
                mPartyPreferences.saveUserPreferences(task.getResult().getUser().getUid(), task.getResult().getUser().getEmail());
                startActivity(new Intent(this, InitActivity.class));
                this.finish();
            }
        }).addOnFailureListener(e -> {
            mEtEmail.setEnabled(true);
            mEtPass.setEnabled(true);
            mProgressEmail.setVisibility(View.GONE);
            try {
                throw e;
            } catch (FirebaseAuthInvalidCredentialsException ee) {
                mEtEmail.setError(getString(R.string.error_invalid_email));
                mEtPass.setError(getString(R.string.error_invalid_email));
                mEtPass.requestFocus();
                mEtPass.setText("");
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        });
    }
}
