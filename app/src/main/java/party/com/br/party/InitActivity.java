package party.com.br.party;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.adapter.EventAdapter;
import party.com.br.party.dao.UserDao;
import party.com.br.party.entity.Day;
import party.com.br.party.entity.Event;
import party.com.br.party.entity.LocaleTicket;
import party.com.br.party.entity.User;
import party.com.br.party.helper.PartyPreferences;
import party.com.br.party.helper.Utilities;
import party.com.br.party.listener.GetByTypeListener;

public class InitActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GetByTypeListener {

    private List<Event> mEvents;
    private EventAdapter mEventAdapter;
    private RecyclerView mRvEvents;
    private LinearLayoutManager mLayoutManager;
    private Toolbar mToolbar;
    private BottomNavigationView mBottomView;
    @BindView(R.id.progress_event)
    ProgressBar mProgressEvent;
    @BindView(R.id.verify_connection)
    TextView mTvConnection;
    private NavigationView mNavigationView;
    private PartyPreferences mPartyPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mPartyPreferences = new PartyPreferences(this);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        mRvEvents = findViewById(R.id.rv_list_event);
        mBottomView = findViewById(R.id.bn_navigation_bottom);

        ButterKnife.bind(this);

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

        new UserDao().getById(mPartyPreferences.getIdUser(), this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Utilities.isConnected(this)) {
            mRvEvents.setVisibility(View.VISIBLE);
            mTvConnection.setVisibility(View.GONE);
        }else{
            mRvEvents.setVisibility(View.GONE);
            mTvConnection.setVisibility(View.VISIBLE);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the camera action
            startActivity(new Intent(this, ProfileActivity.class));
        } else if (id == R.id.nav_event) {
            startActivity(new Intent(this, CreateEventActivity.class));
        } else if (id == R.id.nav_edit_profile) {
            startActivity(new Intent(this, EditProfileActivity.class));
        }  else if (id == R.id.nav_out) {
            startActivity(new Intent(this, EmailLoginActivity.class));
        }else if (id == R.id.nav_filter) {
            startActivity(new Intent(this, PreferencesActivity.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void getByType(Object o) {
        User user = (User) o;
        setMenu(mNavigationView, user);
    }

    private void setMenu(NavigationView navigationView, User user) {
        if (navigationView != null) {
            View view = navigationView.getHeaderView(0);
            ProgressBar progressBar = view.findViewById(R.id.progress_menu);
            ImageView pictureDrawer = view.findViewById(R.id.iv_profile);
            TextView nameDrawer = view.findViewById(R.id.tv_name);
            TextView emailDrawer = view.findViewById(R.id.tv_email);

            progressBar.setVisibility(View.GONE);
            pictureDrawer.setVisibility(View.VISIBLE);
            nameDrawer.setVisibility(View.VISIBLE);
            emailDrawer.setVisibility(View.VISIBLE);
            if (user.getPicture().equals(""))
                pictureDrawer.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_logo));
            else
                Picasso.get().load(user.getPicture()).into(pictureDrawer);
            nameDrawer.setText(user.getName());
            emailDrawer.setText(user.getEmail());
        }
    }
}
