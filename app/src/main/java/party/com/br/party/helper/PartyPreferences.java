package party.com.br.party.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Isabelly on 02/05/2018.
 */

public class PartyPreferences {

    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mSharedEditor;

    public PartyPreferences(Context context){
        mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(Constants.PREFERENCES.PREFERENCES_NAME, Constants.PREFERENCES.MODE);
        mSharedEditor = mSharedPreferences.edit();
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


}
