package party.com.br.party.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import java.util.ArrayList;
import java.util.List;

import party.com.br.party.DetailActivity;
import party.com.br.party.R;
import party.com.br.party.entity.Event;
import party.com.br.party.helper.Constants;
import party.com.br.party.helper.PartyPreferences;
import party.com.br.party.service.WidgetService;

/**
 * Implementation of App Widget functionality.
 */
public class AppWidget extends AppWidgetProvider {

    private PartyPreferences mPartyPreferences;

    @Override
    public void onReceive(Context context, Intent intent) {
        mPartyPreferences = new PartyPreferences(context);
        if (Constants.EVENT_ACTION.SEND_EVENT_WIDGET.equals(intent.getAction())) {
            Bundle bundle = intent.getExtras();
            if (bundle.getParcelable(Constants.SEND_EVENT) != null) {
                Event event = bundle.getParcelable(Constants.SEND_EVENT);
                if(mPartyPreferences.getListEvents() != null) {
                    if (mPartyPreferences.getListEvents().size() > 5) {
                        List<Event> events = mPartyPreferences.getListEvents();
                        events.remove(events.size() - 1);
                        if(!events.contains(event))
                            events.add(event);
                        mPartyPreferences.addListEvents(events);
                    }
                }else{
                    List<Event> events = new ArrayList<>();
                    events.add(event);
                    mPartyPreferences.addListEvents(events);
                }
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                ComponentName thisWidget = new ComponentName(context, AppWidget.class);
                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
                try {
                    appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list);
                    onUpdate(context, appWidgetManager, appWidgetIds);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views =  new RemoteViews(context.getPackageName(), R.layout.app_widget);
            Intent widget = new Intent(context, WidgetService.class);

            views.setRemoteAdapter(R.id.widget_list, widget);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

