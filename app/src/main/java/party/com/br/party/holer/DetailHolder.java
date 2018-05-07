package party.com.br.party.holer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.R;
import party.com.br.party.entity.Day;

/**
 * Created by Isabelly on 24/04/2018.
 */

public class DetailHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_detail)
    ImageView mIvDetail;
    @BindView(R.id.tv_day)
    TextView mTvDay;
    @BindView(R.id.tv_value_basic)
    TextView mTvValueBasic;
    @BindView(R.id.tv_value_vip)
    TextView mTvValueVip;
    @BindView(R.id.tv_value_top)
    TextView mTvValueTop;
    @BindView(R.id.pb_picture)
    ProgressBar mProgressBar;
    private Context mContext;
    private Day mDay;

    public DetailHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
    }

    public void onBind(Day day){
        mDay = day;
        mProgressBar.setVisibility(View.VISIBLE);
        mTvDay.setText(mContext.getResources().getString(R.string.day_singer, String.valueOf(day.getDay()), day.getSinger()));
        mTvValueBasic.setText(mContext.getResources().getString(R.string.price_basic, String.valueOf(mDay.getValueBasic())));
        mTvValueVip.setText(mContext.getResources().getString(R.string.price_vip, String.valueOf(mDay.getValueVip())));
        mTvValueTop.setText(mContext.getResources().getString(R.string.price_top, String.valueOf(mDay.getValueTop())));
        if(!mDay.getPicture().equals("")){
            Picasso.get().load(mDay.getPicture()).fit().centerInside().into(mIvDetail, new Callback() {
                @Override
                public void onSuccess() {
                    mProgressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError(Exception e) {
                    Log.d("erroorr", e.getMessage());
                }
            });
        }else
            mProgressBar.setVisibility(View.GONE);
    }
}
