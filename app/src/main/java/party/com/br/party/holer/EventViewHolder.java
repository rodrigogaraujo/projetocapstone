package party.com.br.party.holer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.DetailActivity;
import party.com.br.party.R;
import party.com.br.party.entity.Event;
import party.com.br.party.helper.Constants;
import party.com.br.party.helper.RoundedTransformation;

/**
 * Created by g3infotech on 22/03/18.
 */

public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.iv_banner)
    ImageView mIvBanner;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_city_date)
    TextView mTvCityDate;
    @BindView(R.id.pb_picture)
    ProgressBar mPbPicture;
    private Event mEvent;
    private Context mContext;

    public EventViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
    }

    public void onBind(Event event) {
        if (event != null) {
            if (!event.getPicture().equals("")) {
                mPbPicture.setVisibility(View.VISIBLE);
                Picasso.get().load(event.getPicture()).transform(new RoundedTransformation(10,0)).into(mIvBanner, new Callback() {
                    @Override
                    public void onSuccess() {
                        mPbPicture.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }
            mTvName.setText(event.getName());
            mIvBanner.setOnClickListener(this);
            String stringDate = DateFormat.getDateInstance().format(event.getDate());
            mTvCityDate.setText(mContext.getResources().getString(R.string.date_city, event.getLocation(), stringDate));
            mEvent = event;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.iv_banner:
                Intent i = new Intent(mContext, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constants.SEND_EVENT, mEvent);
                i.putExtras(bundle);
                mContext.startActivity(i);
                break;
        }
    }
}
