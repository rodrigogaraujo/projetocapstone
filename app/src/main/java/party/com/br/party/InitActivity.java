package party.com.br.party;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.adapter.EventAdapter;
import party.com.br.party.dao.UserDao;
import party.com.br.party.entity.Event;
import party.com.br.party.entity.User;
import party.com.br.party.helper.Constants;
import party.com.br.party.helper.PartyPreferences;
import party.com.br.party.helper.Utilities;
import party.com.br.party.listener.GetByTypeListener;
import party.com.br.party.service.EventJobService;

public class InitActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GetByTypeListener<User> {

    @BindView(R.id.progress_event)
    ProgressBar mProgressEvent;
    @BindView(R.id.verify_connection)
    TextView mTvConnection;
    private List<Event> mEvents;
    private EventAdapter mEventAdapter;
    private RecyclerView mRvEvents;
    private LinearLayoutManager mLayoutManager;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private PartyPreferences mPartyPreferences;
    private DrawerLayout mDrawer;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseJobDispatcher mDispatcher;
    private Parcelable mListState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
        mDatabaseReference.keepSynced(true);

        mPartyPreferences = new PartyPreferences(this);

        mDrawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        mRvEvents = findViewById(R.id.rv_list_event);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mRvEvents.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvEvents.setLayoutManager(mLayoutManager);
        mEvents = new ArrayList<>();
        mEventAdapter = new EventAdapter(InitActivity.this, mEvents);
        mRvEvents.setAdapter(mEventAdapter);

        mProgressEvent.setVisibility(View.VISIBLE);

        verifyData();
        new UserDao().getById(mPartyPreferences.getIdUser(), this);
        if(mDispatcher == null)
            mDispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        if(savedInstanceState == null){
            if(!getIntent().hasExtra(Constants.SEND_EVENT_NOTIFICATION)) {
                Job myJob = mDispatcher.newJobBuilder()
                        .setService(EventJobService.class)
                        .setTag(Constants.TAG_DISPATCHER)
                        .build();
                mDispatcher.mustSchedule(myJob);
            }
        }else{
            onRestoreInstanceState(savedInstanceState);
        }
    }

    private void verifyData() {
        mDatabaseReference.child(Constants.FIREBASE_REALTIME.CHILD_EVENT).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mProgressEvent.setVisibility(View.GONE);
                if (dataSnapshot.exists()) {
                    mEventAdapter.clean();
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Event event = data.getValue(Event.class);
                        new UserDao().getById(mPartyPreferences.getIdUser(), user -> {
                            if(user.getInterest().contains(event.getType())){
                                mEventAdapter.add(event);
                            }else{
                                mEventAdapter.remove(event);
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        mListState = mLayoutManager.onSaveInstanceState();
        savedInstanceState.putParcelable(Constants.LIST_STATE_KEY, mListState);
        super.onSaveInstanceState(savedInstanceState);
    }

    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        if(state != null)
            mListState = state.getParcelable(Constants.LIST_STATE_KEY);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDispatcher.cancel(Constants.TAG_DISPATCHER);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Utilities.isConnected(this)) {
            mRvEvents.setVisibility(View.VISIBLE);
            mTvConnection.setVisibility(View.GONE);
        } else {
            mRvEvents.setVisibility(View.GONE);
            mTvConnection.setVisibility(View.VISIBLE);
        }
        if (mListState != null) {
            mLayoutManager.onRestoreInstanceState(mListState);
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
            startActivity(new Intent(this, EventActivity.class));
        } else if (id == R.id.nav_edit_profile) {
            startActivity(new Intent(this, EditProfileActivity.class));
        } else if (id == R.id.nav_out) {
            startActivity(new Intent(this, EmailLoginActivity.class));
        } else if (id == R.id.nav_filter) {
            startActivity(new Intent(this, PreferencesActivity.class));
            finish();
        }else if (id == R.id.nav_my_events) {
            startActivity(new Intent(this, MyEventsActivity.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void getByType(User user) {
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

            if (user.getType().equals(Constants.FIREBASE_REALTIME.CHILD_USER_TYPE_BALADA)) {
                navigationView.getMenu().findItem((R.id.nav_event)).setVisible(false);
                navigationView.getMenu().findItem((R.id.nav_my_events)).setVisible(false);
                navigationView.getMenu().findItem((R.id.group_event)).setVisible(false);
            }
        }
    }
}
