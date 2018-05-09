package party.com.br.party;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.adapter.EventAdapter;
import party.com.br.party.dao.UserDao;
import party.com.br.party.entity.Event;
import party.com.br.party.helper.Constants;
import party.com.br.party.helper.PartyPreferences;

public class MyEventsActivity extends AppCompatActivity {

    @BindView(R.id.progress_event)
    ProgressBar mProgressEvent;
    @BindView(R.id.verify_connection)
    TextView mTvConnection;
    private RecyclerView mRvEvents;
    private List<Event> mEvents;
    private EventAdapter mEventAdapter;
    private LinearLayoutManager mLayoutManager;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private PartyPreferences mPartyPreferences;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_events);
        ButterKnife.bind(this);
        mRvEvents = findViewById(R.id.rv_list_event);
        mToolbar = findViewById(R.id.toolbar_detail);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
        mDatabaseReference.keepSynced(true);

        mPartyPreferences = new PartyPreferences(this);

        mRvEvents.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvEvents.setLayoutManager(mLayoutManager);

        mEvents = new ArrayList<>();
        mEventAdapter = new EventAdapter(this, mEvents);
        mRvEvents.setAdapter(mEventAdapter);
        mProgressEvent.setVisibility(View.VISIBLE);

        mDatabaseReference.child(Constants.FIREBASE_REALTIME.CHILD_EVENT).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mProgressEvent.setVisibility(View.GONE);
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Event event = data.getValue(Event.class);
                        if(event.getIdAdmin().equals(mPartyPreferences.getIdUser()))
                            mEventAdapter.add(event);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

