package party.com.br.party.holer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
    private Context mContext;
    private Day mDay;

    public DetailHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
    }

    public void onBind(Day day){
        mDay = day;
        mTvDay.setText(mContext.getResources().getString(R.string.day_singer, String.valueOf(day.getDay()), day.getSinger()));
        mTvValueBasic.setText(String.valueOf(mDay.getValueBasic()));
        mTvValueVip.setText(String.valueOf(mDay.getValueVip()));
        mTvValueTop.setText(String.valueOf(mDay.getValueTop()));
    }
}
