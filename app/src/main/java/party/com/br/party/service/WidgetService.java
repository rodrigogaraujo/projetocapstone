package party.com.br.party.service;

import android.content.Intent;
import android.widget.RemoteViewsService;

import party.com.br.party.widget.provider.WidgetDataProvider;

/**
 * Created by Isabelly on 09/05/2018.
 */

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetDataProvider(getApplicationContext(), intent);
    }
}
