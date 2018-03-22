package party.com.br.party;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import party.com.br.party.adapter.EventAdapter;
import party.com.br.party.entity.Event;

public class MainActivity extends AppCompatActivity {

    private List<Event> mEvents;
    private EventAdapter mEventAdapter;
    private RecyclerView mRvEvents;
    private LinearLayoutManager mLayoutManager;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRvEvents = findViewById(R.id.rv_list_event);
        mToolbar = findViewById(R.id.toolbar_main);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mRvEvents.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvEvents.setLayoutManager(mLayoutManager);

        Event marcolandia = new Event("1", "Aniversário de Marcolândia", "21 aniversário da cidade de marcolândia", "",
                "Brasil,Piauí, Marcolândia, Avenida Corinto matos, 23", "21h", "+55 (89) 3439-1183", new Date(), new ArrayList<String>());
        List<String> locails = new ArrayList<>();
        locails.add("Brasil,Piauí, Marcolândia, Avenida Maria Concebida Costa, 29");
        Event g3 = new Event("2", "G3 Infotech", "Teesteeeeeeee", "",
                "Brasil,Piauí, Marcolândia, Avenida Maria Concebida Costa, 29", "21h", "+55 (89) 99463-0386", new Date(), locails);

        mEvents = new ArrayList<>();
        mEvents.add(marcolandia);
        mEvents.add(g3);
        mEventAdapter = new EventAdapter(this, mEvents);
        mRvEvents.setAdapter(mEventAdapter);
    }
}
