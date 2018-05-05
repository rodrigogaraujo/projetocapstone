package party.com.br.party.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import party.com.br.party.R;


/**
 * Created by g3infotech on 29/03/18.
 */

public class Utilities {

    public static Animation animationAlpha(){
        Animation animation = new AlphaAnimation(1.0f, 0.0f);
        animation.setDuration(1000);
        return animation;
    }

    public static void confirmDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(context.getString(R.string.ok), null);
        builder.show();
    }

    public static boolean isConnected(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }

}
