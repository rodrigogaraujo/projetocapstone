package party.com.br.party.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import party.com.br.party.entity.Event;

/**
 * Created by Isabelly on 02/05/2018.
 */

public class PartyPreferences {

    private Gson mGson;
    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mSharedEditor;
    private List<Event> mListEvents;

    public PartyPreferences(Context context){
        mGson = new Gson();
        mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(Constants.PREFERENCES.PREFERENCES_NAME, Constants.PREFERENCES.MODE);
        mSharedEditor = mSharedPreferences.edit();
        mListEvents = new ArrayList<>();
    }

    public void saveUserPreferences(String id, String emailUser){
        mSharedEditor.putString(Constants.PREFERENCES.PREFERENCES_ID, id);
        mSharedEditor.putString(Constants.PREFERENCES.PREFERENCES_EMAIL, emailUser);
        mSharedEditor.commit();
    }

    public String getIdUser(){
        return mSharedPreferences.getString(Constants.PREFERENCES.PREFERENCES_ID, null);
    }

    public String getEmailUser(){
        return mSharedPreferences.getString(Constants.PREFERENCES.PREFERENCES_EMAIL, null);
    }

    public void clear() {
        mSharedEditor.clear();
        mSharedEditor.commit();
    }
    public void addListEvents(List<Event> ingredients){
        mListEvents.clear();
        mListEvents = ingredients;
        String json = mGson.toJson(mListEvents);
        mSharedEditor.putString(Constants.SEND_EVENTS, json);
        mSharedEditor.commit();
    }

    public List<Event> getListEvents(){
        String str = mSharedPreferences.getString(Constants.SEND_EVENTS, "");
        Type type = new TypeToken<List<Event>>(){}.getType();
        List<Event> events = mGson.fromJson(str, type);
        return events;
    }
}
