package party.com.br.party;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import party.com.br.party.dao.UserDao;
import party.com.br.party.entity.User;
import party.com.br.party.helper.PartyPreferences;
import party.com.br.party.listener.GetByTypeListener;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener, GetByTypeListener<User> {

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
    private PartyPreferences mPartyPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        mCircleImage = findViewById(R.id.circle_image);
        mPartyPreferences = new PartyPreferences(this);

        mBtEdit.setOnClickListener(this);
        mPbProfile.setVisibility(View.VISIBLE);
        new UserDao().getById(mPartyPreferences.getIdUser(), this);
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

    @Override
    public void getByType(User user) {
        mPbProfile.setVisibility(View.GONE);
        mTvAdress.setText(user.getAdress());
        mTvName.setText(user.getName());
        mTvEmail.setText(user.getEmail());
        mTvPhone.setText(user.getPhone());
        if (user.getPicture().equals(""))
            mCircleImage.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_logo));
        else
            Picasso.get().load(user.getPicture()).into(mCircleImage);
        if(!user.getId().equals(mPartyPreferences.getIdUser()))
            mBtEdit.setVisibility(View.GONE);
    }
}
