package party.com.br.party.holer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.ProfileActivity;
import party.com.br.party.R;
import party.com.br.party.entity.User;
import party.com.br.party.helper.Constants;

/**
 * Created by Isabelly on 08/05/2018.
 */

public class WhoGoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.iv_profile)
    ImageView mIvProfile;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_adress)
    TextView mTvAdress;
    private User mUser;
    private Context mContext;

    public WhoGoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mContext = itemView.getContext();
    }

    public void onBind(User user){
        if(!user.getPicture().equals(""))
            Picasso.get().load(user.getPicture()).into(mIvProfile);
        mTvName.setText(user.getName());
        mTvAdress.setText(user.getAdress());
        mUser = user;
    }


    @Override
    public void onClick(View v) {
        Intent i = new Intent(mContext, ProfileActivity.class);
        i.putExtra(Constants.SEND_PERSON, mUser.getId());
        mContext.startActivity(i);
    }
}
