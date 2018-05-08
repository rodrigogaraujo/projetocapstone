package party.com.br.party.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import party.com.br.party.R;
import party.com.br.party.entity.Event;
import party.com.br.party.holer.EventViewHolder;

/**
 * Created by g3infotech on 22/03/18.
 */

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private Context mContext;
    private List<Event> mEvents;

    public EventAdapter(Context context, List<Event> events){
        mContext = context;
        mEvents = events;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_event, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.onBind(mEvents.get(position));
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    public void add(Event event){
        mEvents.add(event);
        notifyDataSetChanged();
    }
}
