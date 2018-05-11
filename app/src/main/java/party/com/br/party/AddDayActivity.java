package party.com.br.party;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.entity.Day;
import party.com.br.party.helper.Constants;
import party.com.br.party.helper.NumberTextWatcherForThousand;
import party.com.br.party.helper.Utilities;

public class AddDayActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.bt_confirm)
    ImageButton mBtConfirm;
    @BindView(R.id.tv_value_basic)
    EditText mTvValueBasic;
    @BindView(R.id.tv_value_vip)
    EditText mTvValueVip;
    @BindView(R.id.tv_value_top)
    EditText mTvValueTop;
    @BindView(R.id.et_day)
    EditText mEtLote;
    @BindView(R.id.et_description)
    EditText mEtDescription;
    @BindView(R.id.iv_banner)
    ImageView mIvBanner;
    @BindView(R.id.progress_day)
    ProgressBar mProgressBar;
    private String mPicture;
    private StorageReference mStorageReference;
    private Day mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_day);
        ButterKnife.bind(this);

        mPicture = "";
        mStorageReference = FirebaseStorage.getInstance().getReference();

        mTvValueBasic.addTextChangedListener(new NumberTextWatcherForThousand(mTvValueBasic));
        mTvValueVip.addTextChangedListener(new NumberTextWatcherForThousand(mTvValueVip));
        mTvValueTop.addTextChangedListener(new NumberTextWatcherForThousand(mTvValueTop));

        mBtConfirm.setOnClickListener(this);
        mIvBanner.setOnClickListener(this);
        if(savedInstanceState != null)
            setDay(savedInstanceState.getParcelable(Constants.SEND_DAY));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mDay == null)
            mDay = getDay();
        outState.clear();
        outState.putParcelable(Constants.SEND_DAY, mDay);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null)
            setDay(savedInstanceState.getParcelable(Constants.SEND_DAY));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt_confirm:
                buttonConfirm();
                break;
            case R.id.iv_banner:
                takePicture();
                break;
        }
    }

    private void takePicture() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, getString(R.string.select_picture)), Constants.PICTURE.SELECT_IMAGE);
    }

    private void enableEditText(Boolean b) {
        mEtDescription.setEnabled(b);
        mEtLote.setEnabled(b);
        mTvValueBasic.setEnabled(b);
        mTvValueTop.setEnabled(b);
        mTvValueVip.setEnabled(b);
        mBtConfirm.setEnabled(b);
        mIvBanner.setEnabled(b);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.PICTURE.SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    enableEditText(false);
                    mProgressBar.setVisibility(View.VISIBLE);
                    Uri selectedImgUri = data.getData();
                    StorageReference reference = mStorageReference.child(Constants.FIREBASE_STORAGE.CHILD_PHOTO_BANNER_DAY).child(selectedImgUri.getLastPathSegment());
                    reference.putFile(selectedImgUri).addOnSuccessListener(taskSnapshot -> {
                        mPicture = taskSnapshot.getDownloadUrl().toString();
                        mProgressBar.setVisibility(View.GONE);
                        Picasso.get().load(mPicture).into(mIvBanner);
                        enableEditText(true);
                        Utilities.confirmDialog(AddDayActivity.this, getString(R.string.banner_ok_title), getString(R.string.banner_ok));
                    });
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
            }
        }
    }

    private void buttonConfirm() {
        mBtConfirm.startAnimation(Utilities.animationAlpha());
        if (mEtDescription.getText().toString().equals(""))
            mEtDescription.setError(getString(R.string.field_));
        else {
            if (mTvValueBasic.getText().toString().equals(""))
                mTvValueBasic.setError(getString(R.string.field_));
            else {
                Day day = getDay();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constants.SEND_EVENT_DAY, day);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

    @NonNull
    private void setDay(Day day) {
        mPicture = day.getPicture();
        mTvValueBasic.setText(String.valueOf(day.getValueBasic()));
        mTvValueVip.setText(String.valueOf(day.getValueVip()));
        mTvValueTop.setText(String.valueOf(day.getValueTop()));
        mTvValueBasic.setText(String.valueOf(day.getValueBasic()));
        mEtDescription.setText(String.valueOf(day.getDescription()));
        mEtLote.setText(String.valueOf(day.getDay()));
    }

    @NonNull
    private Day getDay() {
        Day day = new Day();
        day.setPicture(mPicture);
        day.setValueBasic(mTvValueBasic.getText().toString());
        day.setValueVip(mTvValueVip.getText().toString());
        day.setValueTop(mTvValueTop.getText().toString());
        day.setSinger(mEtDescription.getText().toString());
        day.setDay(Integer.parseInt(mEtLote.getText().toString()));
        return day;
    }
}
