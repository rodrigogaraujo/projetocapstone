package party.com.br.party;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.adapter.EventAdapter;
import party.com.br.party.entity.Event;

public class MainActivity extends AppCompatActivity {

    private List<Event> mEvents;
    private EventAdapter mEventAdapter;
    private RecyclerView mRvEvents;
    private LinearLayoutManager mLayoutManager;
    private Toolbar mToolbar;
    private BottomNavigationView mBottomView;@BindView(R.id.progress_event)
    ProgressBar mProgressEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRvEvents = findViewById(R.id.rv_list_event);
        mToolbar = findViewById(R.id.toolbar_main);
        mBottomView = findViewById(R.id.bn_navigation_bottom);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mBottomView.setSelectedItemId(R.id.action_home);

        mRvEvents.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvEvents.setLayoutManager(mLayoutManager);

        Event marcolandia = new Event("1", "Lollapalooza", "21 aniversário da cidade de marcolândia",
                "eletrônica", "http://ligadoamusica.com.br/wp-content/uploads/2015/08/lollapalooza-brasil-2016-banner.jpg",
                "Marcolândia", "Brasil,Piauí, Marcolândia, Avenida Corinto matos, 23", "21h", "+55 (89) 3439-1183", new Date(), new ArrayList<String>());
        List<String> locails = new ArrayList<>();
        locails.add("Brasil,Piauí, Marcolândia, Avenida Maria Concebida Costa, 29");
        Event g3 = new Event("2", "Dj Alok", "Teesteeeeeeee",
                "eletrônica","http://agito.com.br/agitoindaiatuba/eventos/1312/5d5df8b4f3_10445958_742604935777987_6258263799971385453_n.jpg",
                "Juazeiro do Norte", "Brasil,Piauí, Marcolândia, Avenida Maria Concebida Costa, 29", "21h", "+55 (89) 99463-0386", new Date(), locails);
        Event marcolandia1 = new Event("3", "Caldas Country", "21 aniversário da cidade de marcolândia",
                "forró","http://agito.com.br/agitouberaba/eventos/3754/4911ac111b_11358894_784061501692256_613804643_n.jpg",
                "Araripina", "Brasil,Piauí, Marcolândia, Avenida Corinto matos, 23", "21h", "+55 (89) 3439-1183", new Date(), new ArrayList<String>());
        Event marcolandia2 = new Event("4", "Aniversário de Marcolândia", "21 aniversário da cidade de marcolândia",
                "forró", "http://cafecomshah.com.br/wp-content/uploads/2017/06/banner-piu-piu-20-anos.jpg", "Marcolândia",
                "Brasil,Piauí, Avenida Corinto matos, 23", "21h", "+55 (89) 3439-1183", new Date(), new ArrayList<String>());
        Event marcolandia3 = new Event("5", "Aniversário de Marcolândia", "21 aniversário da cidade de marcolândia",
                "eletrônica", "", "Rio de Janeiro",
                "Brasil,Piauí, Marcolândia, Avenida Corinto matos, 23", "21h", "+55 (89) 3439-1183", new Date(), new ArrayList<String>());
        Event marcolandia4 = new Event("6", "Aniversário de Marcolândia", "21 aniversário da cidade de marcolândia",
                "eletrônica", "http://www.fmodia.com.br/wp-content/uploads/2017/06/Festa-Genesis-Wesley-Safadao-Banner.jpg",
                "São Paulo", "Brasil,Piauí, Marcolândia, Avenida Corinto matos, 23", "21h", "+55 (89) 3439-1183", new Date(), new ArrayList<String>());

        mEvents = new ArrayList<>();
        mEvents.add(marcolandia);
        mEvents.add(marcolandia1);
        mEvents.add(marcolandia2);
        mEvents.add(marcolandia3);
        mEvents.add(marcolandia4);
        mEvents.add(g3);
        mEventAdapter = new EventAdapter(this, mEvents);
        mRvEvents.setAdapter(mEventAdapter);
        mProgressEvent.setVisibility(View.GONE);

    }
}
