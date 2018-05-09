package party.com.br.party.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import party.com.br.party.R;
import party.com.br.party.entity.User;
import party.com.br.party.holer.WhoGoHolder;

/**
 * Created by Isabelly on 08/05/2018.
 */

public class WhoGoAdapter extends RecyclerView.Adapter<WhoGoHolder> {

    private Context mContext;
    private List<User> mUsers;

    public WhoGoAdapter(Context context, List<User> users) {
        this.mContext = context;
        this.mUsers = users;
    }

    @NonNull
    @Override
    public WhoGoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WhoGoHolder(LayoutInflater.from(mContext).inflate(R.layout.row_who_go, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WhoGoHolder holder, int position) {
        holder.onBind(mUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void add(User user){
        mUsers.add(user);
        notifyDataSetChanged();
    }
}
