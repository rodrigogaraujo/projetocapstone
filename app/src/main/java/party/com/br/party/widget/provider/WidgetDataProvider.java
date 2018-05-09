package party.com.br.party.widget.provider;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

import party.com.br.party.DetailActivity;
import party.com.br.party.R;
import party.com.br.party.entity.Event;
import party.com.br.party.helper.Constants;
import party.com.br.party.helper.PartyPreferences;

/**
 * Created by Isabelly on 09/05/2018.
 */

public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {

    List<Event> mEvents = new ArrayList<>();
    Context mContext;
    Intent mIntent;
    PartyPreferences mPartyPreferences;

    public WidgetDataProvider(Context context, Intent intent) {
        mContext = context;
        mIntent = intent;
        mPartyPreferences = new PartyPreferences(context);
    }

    @Override
    public void onCreate() {
        getData();
    }

    private void getData() {
        if (mPartyPreferences.getListEvents() != null)
            mEvents = mPartyPreferences.getListEvents();
    }


    @Override
    public void onDataSetChanged() {
        getData();
    }

    @Override
    public void onDestroy() {
        mEvents = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mEvents.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), android.R.layout.simple_list_item_1);
        if (mEvents.size() > 0) {
            remoteViews.setTextViewText(android.R.id.text1, mEvents.get(position).getName());
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.SEND_EVENT, mEvents.get(position));
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtras(bundle);
            PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.widget_list, pendingIntent);
        }
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
