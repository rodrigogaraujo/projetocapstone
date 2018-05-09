package party.com.br.party;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import party.com.br.party.dao.UserDao;
import party.com.br.party.entity.User;
import party.com.br.party.helper.Constants;
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
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_adress)
    EditText mEtAdress;
    @BindView(R.id.pb_edit)
    ProgressBar mProgressBar;
    @BindView(R.id.bt_change_pass)
    Button mBtChangePass;
    private PartyPreferences mPartyPreferences;
    private String mPicture;
    private StorageReference mStorageReference;
    private CircleImageView mIvProfile;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        mIvProfile = findViewById(R.id.iv_profile);

        mPartyPreferences = new PartyPreferences(this);
        mStorageReference = FirebaseStorage.getInstance().getReference();

        mBtConfirm.setOnClickListener(this);
        mBtChangePass.setOnClickListener(this);
        mIvProfile.setOnClickListener(this);
        mProgressBar.setVisibility(View.VISIBLE);

        mPicture = "";
        if(savedInstanceState == null) {
            new UserDao().getById(mPartyPreferences.getIdUser(), this);
        }else{
            onRestoreInstanceState(savedInstanceState);
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt_confirm:
                confirmButton();
                break;
            case R.id.iv_profile:
                takePicture();
                break;
            case R.id.bt_change_pass:
                changePass();
                break;
        }
    }
    private void setUserData(User userData){
        mEtAdress.setText(userData.getAdress());
        mEtEmail.setText(userData.getEmail());
        mEtName.setText(userData.getName());
        mEtPhone.setText(userData.getName());
        mPicture = userData.getPicture();
        if (mPicture.equals(""))
            mIvProfile.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_logo));
        else
            Picasso.get().load(mPicture).into(mIvProfile);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mUser != null)
            setUserData(mUser);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(Constants.SEND_PERSON, getUser(null));
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null)
            mUser = savedInstanceState.getParcelable(Constants.SEND_PERSON);
    }

    private void changePass() {
        FirebaseAuth.getInstance().sendPasswordResetEmail(mEtEmail.getText().toString())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                       Utilities.confirmDialog(EditProfileActivity.this, getString(R.string.change_passs), getString(R.string.text_change_pass, mEtEmail.getText().toString()));
                    }
                });
    }

    private void takePicture() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, getString(R.string.select_picture)), Constants.PICTURE.SELECT_IMAGE);
    }

    private void enableEditText(Boolean b) {
        mEtPhone.setEnabled(b);
        mEtName.setEnabled(b);
        mEtAdress.setEnabled(b);
        mIvProfile.setEnabled(b);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.PICTURE.SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    enableEditText(false);
                    mProgressBar.setVisibility(View.VISIBLE);
                    Uri selectedImgUri = data.getData();
                    StorageReference reference = mStorageReference.child(Constants.FIREBASE_STORAGE.CHILD_PHOTO_PROFILE).child(selectedImgUri.getLastPathSegment());
                    reference.putFile(selectedImgUri).addOnSuccessListener(taskSnapshot -> {
                        mPicture = taskSnapshot.getDownloadUrl().toString();
                        mProgressBar.setVisibility(View.GONE);
                        Picasso.get().load(mPicture).into(mIvProfile);
                        enableEditText(true);
                        Utilities.confirmDialog(EditProfileActivity.this, getString(R.string.update_profile), getString(R.string.update_picture));
                    });
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
            }
        }
    }

    private User getUser(User user){
        if(user == null)
            user = new User();
        return new User(mPartyPreferences.getIdUser(), mEtName.getText().toString(), mPicture, mEtEmail.getText().toString(), mEtPhone.getText().toString(), mEtAdress.getText().toString(), "", "", null, !user.isStatus());
    }

    private void confirmButton() {
        if(Utilities.isConnected(this)) {
            mProgressBar.setVisibility(View.VISIBLE);
            mBtConfirm.startAnimation(Utilities.animationAlpha());
            if (mEtName.getText().toString().equals("")) {
                mEtName.setError(getString(R.string.error_empty));
            } else if (mEtAdress.getText().toString().equals("")) {
                mEtAdress.setError(getString(R.string.error_empty));
            } else if (mEtPhone.getText().toString().equals("")) {
                mEtPhone.setError(getString(R.string.error_empty));
            } else {
                new UserDao().getById(mPartyPreferences.getIdUser(), new GetByTypeListener<User>() {
                    @Override
                    public void getByType(User user1) {
                        new UserDao().update(getUser(user1));
                    }
                });
                mProgressBar.setVisibility(View.GONE);
                Utilities.confirmDialog(this, getString(R.string.update_profile), getString(R.string.update_ok));
            }
        }else {
            Utilities.confirmDialog(this, getString(R.string.error_conected), getString(R.string.error_conected_message));
        }
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
