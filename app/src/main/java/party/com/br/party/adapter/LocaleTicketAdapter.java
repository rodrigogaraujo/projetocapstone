package party.com.br.party.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import party.com.br.party.R;
import party.com.br.party.entity.LocaleTicket;
import party.com.br.party.holer.LocaleTicketHolder;

/**
 * Created by Isabelly on 25/04/2018.
 */

public class LocaleTicketAdapter extends RecyclerView.Adapter<LocaleTicketHolder>{

    private Context mContext;
    private List<LocaleTicket> mLocales;

    public LocaleTicketAdapter(Context context, List<LocaleTicket> localeTickets){
        mContext = context;
        mLocales = localeTickets;
    }

    @NonNull
    @Override
    public LocaleTicketHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LocaleTicketHolder(LayoutInflater.from(mContext).inflate(R.layout.row_locale_ticket, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LocaleTicketHolder holder, int position) {
        holder.onBind(mLocales.get(position));
    }

    @Override
    public int getItemCount() {
        return mLocales.size();
    }
}
