package party.com.br.party;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
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
    private BottomNavigationView mBottomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRvEvents = findViewById(R.id.rv_list_event);
        mToolbar = findViewById(R.id.toolbar_main);
        mBottomView = findViewById(R.id.bn_navigation_bottom);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mBottomView.setSelectedItemId(R.id.action_home);

        mRvEvents.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvEvents.setLayoutManager(mLayoutManager);

        Event marcolandia = new Event("1", "Aniversário de Marcolândia", "21 aniversário da cidade de marcolândia", "",
                "Brasil,Piauí, Marcolândia, Avenida Corinto matos, 23", "21h", "+55 (89) 3439-1183", new Date(), new ArrayList<String>());
        List<String> locails = new ArrayList<>();
        locails.add("Brasil,Piauí, Marcolândia, Avenida Maria Concebida Costa, 29");
        Event g3 = new Event("2", "G3 Infotech", "Teesteeeeeeee", "",
                "Brasil,Piauí, Marcolândia, Avenida Maria Concebida Costa, 29", "21h", "+55 (89) 99463-0386", new Date(), locails);
        Event marcolandia1 = new Event("1", "Aniversário de Marcolândia", "21 aniversário da cidade de marcolândia", "",
                "Brasil,Piauí, Marcolândia, Avenida Corinto matos, 23", "21h", "+55 (89) 3439-1183", new Date(), new ArrayList<String>());
        Event marcolandia2 = new Event("1", "Aniversário de Marcolândia", "21 aniversário da cidade de marcolândia",
                "http://cafecomshah.com.br/wp-content/uploads/2017/06/banner-piu-piu-20-anos.jpg",
                "Brasil,Piauí, Marcolândia, Avenida Corinto matos, 23", "21h", "+55 (89) 3439-1183", new Date(), new ArrayList<String>());
        Event marcolandia3 = new Event("1", "Aniversário de Marcolândia", "21 aniversário da cidade de marcolândia", "",
                "Brasil,Piauí, Marcolândia, Avenida Corinto matos, 23", "21h", "+55 (89) 3439-1183", new Date(), new ArrayList<String>());
        Event marcolandia4 = new Event("1", "Aniversário de Marcolândia", "21 aniversário da cidade de marcolândia",
                "http://www.fmodia.com.br/wp-content/uploads/2017/06/Festa-Genesis-Wesley-Safadao-Banner.jpg",
                "Brasil,Piauí, Marcolândia, Avenida Corinto matos, 23", "21h", "+55 (89) 3439-1183", new Date(), new ArrayList<String>());

        mEvents = new ArrayList<>();
        mEvents.add(marcolandia);
        mEvents.add(marcolandia1);
        mEvents.add(marcolandia2);
        mEvents.add(marcolandia3);
        mEvents.add(marcolandia4);
        mEvents.add(g3);
        mEventAdapter = new EventAdapter(this, mEvents);
        mRvEvents.setAdapter(mEventAdapter);
    }
}
