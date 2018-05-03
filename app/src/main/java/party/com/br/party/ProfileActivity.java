package party.com.br.party;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.ib_edit)
    ImageButton mBtEdit;
    @BindView(R.id.pb_profile)
    ProgressBar mPbProfile;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_active_awnser)
    TextView mTvActiveAwnser;
    @BindView(R.id.tv_status)
    TextView mTvStatus;
    @BindView(R.id.tv_email)
    TextView mTvEmail;
    @BindView(R.id.tv_adress)
    TextView mTvAdress;
    @BindView(R.id.bt_block)
    ImageButton mBtBlock;
    @BindView(R.id.bt_silence)
    ImageButton mBtSilence;
    @BindView(R.id.bt_favorite)
    ImageButton mBtFavorite;
    CircleImageView mCircleImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        mCircleImage = findViewById(R.id.circle_image);

        mBtEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.ib_edit :
                startActivity(new Intent(this, EditProfileActivity.class));
                break;
        }
    }
}
