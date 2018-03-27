package party.com.br.party;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import party.com.br.party.entity.Event;
import party.com.br.party.helper.Constants;

public class DetailActivity extends AppCompatActivity {

    private Event mEvent;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mToolbar = findViewById(R.id.toolbar_detail);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        if(getIntent().getExtras() != null && getIntent().hasExtra(Constants.SEND_EVENT)) {
            mEvent = getIntent().getExtras().getParcelable(Constants.SEND_EVENT);
            getSupportActionBar().setTitle(mEvent.getName());
        }
    }
}
