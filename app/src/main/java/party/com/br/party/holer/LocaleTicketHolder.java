package party.com.br.party.holer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.R;
import party.com.br.party.entity.LocaleTicket;

/**
 * Created by Isabelly on 25/04/2018.
 */

public class LocaleTicketHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_adress)
    TextView mTvAdress;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_email)
    TextView mTvEmail;
    public LocaleTicketHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void onBind(LocaleTicket locale){
        mTvAdress.setText(locale.getAdress());
        mTvPhone.setText(locale.getPhone());
        mTvEmail.setText(locale.getEmail());
    }
}
