package party.com.br.party.helper;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by g3infotech on 29/03/18.
 */

public class Utilities {

    public static LatLng getLocationFromAdress(String adress, Context mContext) throws IOException {
        double latitude = -33.8523341;
        double longitude = 151.2106085;
        Geocoder coder = new Geocoder(mContext, Locale.getDefault());
        adress.replace(",", " ");
        List<Address> addresses = coder.getFromLocationName(adress, 1);
        if (addresses.size() > 0) {
            latitude = addresses.get(0).getLatitude();
            longitude = addresses.get(0).getLongitude();
        }
        return new LatLng(latitude, longitude);
    }

    public static Animation animationAlpha(){
        Animation animation = new AlphaAnimation(1.0f, 0.0f);
        animation.setDuration(1000);
        return animation;
    }

    public static boolean isConnected(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }

}
