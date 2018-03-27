package party.com.br.party.holer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.Format;
import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.R;
import party.com.br.party.entity.Event;
import party.com.br.party.helper.RoundedTransformation;

/**
 * Created by g3infotech on 22/03/18.
 */

public class EventViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_banner)
    ImageView mIvBanner;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_city_date)
    TextView mTvCityDate;
    private Format mFormat;

    public EventViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mFormat = new SimpleDateFormat("dd-MM-yyyy");
    }

    public void onBind(Event event) {
        if (event != null) {
            if (!event.getPicture().equals("")) {
                Picasso.get().load(event.getPicture()).transform(new RoundedTransformation(10,0)).into(mIvBanner);
            }
            mTvName.setText(event.getName());
            mTvCityDate.setText(event.getLocation() + ", " + mFormat.format(event.getDate()));
        }
    }
}
