package party.com.br.party.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import party.com.br.party.R;
import party.com.br.party.entity.Day;
import party.com.br.party.holer.DetailHolder;

/**
 * Created by Isabelly on 24/04/2018.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailHolder> {

    private List<Day> mDays;
    private Context mContext;

    public DetailAdapter(Context context, List<Day> days){
        mContext = context;
        mDays = days;
    }

    @NonNull
    @Override
    public DetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DetailHolder(LayoutInflater.from(mContext).inflate(R.layout.row_days_event, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DetailHolder holder, int position) {
        holder.onBind(mDays.get(position));
    }

    @Override
    public int getItemCount() {
        if(mDays != null)
            return mDays.size();
        else
            return 0;
    }

    public void addItem(Day day) {
        mDays.add(day);
        notifyDataSetChanged();
    }

    public void addItens(List<Day> days) {
        mDays.addAll(days);
        notifyDataSetChanged();
    }
}
