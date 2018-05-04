package party.com.br.party;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import party.com.br.party.helper.PartyPreferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PartyPreferences partyPreferences = new PartyPreferences(this);

        new Handler().postDelayed(() -> {
            if(partyPreferences.getIdUser() == null || partyPreferences.getEmailUser() == null){
                startActivity(new Intent(this, EmailLoginActivity.class));
            }else{
                startActivity(new Intent(this, InitActivity.class));
            }
            finish();
        }, 2000);
    }
}
