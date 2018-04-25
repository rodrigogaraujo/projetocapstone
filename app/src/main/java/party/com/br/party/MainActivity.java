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
import party.com.br.party.entity.Day;
import party.com.br.party.entity.Event;
import party.com.br.party.entity.LocaleTicket;

public class MainActivity extends AppCompatActivity {

    private List<Event> mEvents;
    private EventAdapter mEventAdapter;
    private RecyclerView mRvEvents;
    private LinearLayoutManager mLayoutManager;
    private Toolbar mToolbar;
    private BottomNavigationView mBottomView;
    @BindView(R.id.progress_event)
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

        List<Day> days = new ArrayList<>();
        Day day = new Day("1", 20, 30, 40, 1, new Date(), "http://ligadoamusica.com.br/wp-content/uploads/2015/08/lollapalooza-brasil-2016-banner.jpg", "", "Dj Alok");
        Day day1 = new Day("1", 20, 30, 40, 1, new Date(), "http://agito.com.br/agitoindaiatuba/eventos/1312/5d5df8b4f3_10445958_742604935777987_6258263799971385453_n.jpg", "", "Dj Alok");
        days.add(day);
        days.add(day1);

        List<LocaleTicket> locails = new ArrayList<>();
        LocaleTicket localeTicket = new LocaleTicket("1","Avenida Maria Concebida Costa, 29", "(89)99463-0386", "rodrigoaraujo990@gmail.com");
        LocaleTicket localeTicket1 = new LocaleTicket("12","Avenida Maria Concebida Costa, 290", "(89)99463-0386", "rodrigoaraujo990@gmail.com");
        locails.add(localeTicket);
        locails.add(localeTicket1);


        Event marcolandia = new Event("1", "Lollapalooza", "21 aniversário da cidade de marcolândia",
                "eletrônica", "http://ligadoamusica.com.br/wp-content/uploads/2015/08/lollapalooza-brasil-2016-banner.jpg",
                "Marcolândia", "Avenida Corinto matos, 23", "+55 (89) 3439-1183", "rodrigoaraujo990@gmail.com", new Date(), 8, locails, days);


        Event g3 = new Event("2", "Dj Alok", "Teesteeeeeeee",
                "eletrônica", "http://agito.com.br/agitoindaiatuba/eventos/1312/5d5df8b4f3_10445958_742604935777987_6258263799971385453_n.jpg",
                "Juazeiro do Norte", "Avenida Maria Concebida Costa, 29", "+55 (89) 99463-0386","rodrigoaraujo990@gmail.com",  new Date(), 11, locails, days);


        mEvents = new ArrayList<>();
        mEvents.add(marcolandia);
        mEvents.add(g3);
        mEventAdapter = new EventAdapter(this, mEvents);
        mRvEvents.setAdapter(mEventAdapter);
        mProgressEvent.setVisibility(View.GONE);

    }
}
