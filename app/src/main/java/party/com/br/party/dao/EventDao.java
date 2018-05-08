package party.com.br.party.dao;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import party.com.br.party.entity.Event;
import party.com.br.party.helper.Constants;
import party.com.br.party.listener.GetByTypeListener;

/**
 * Created by Isabelly on 02/05/2018.
 */

public class EventDao {

    private DatabaseReference mDatabaseReference;

    public EventDao(){
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mDatabaseReference.keepSynced(true);
    }

    public void getById(String id, final GetByTypeListener<Event> listener) {
        Query query = mDatabaseReference.child(Constants.FIREBASE_REALTIME.CHILD_EVENT).child(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listener.getByType(dataSnapshot.getValue(Event.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public String create(Event event){
        event.setId(mDatabaseReference.push().getKey());
        mDatabaseReference.child(Constants.FIREBASE_REALTIME.CHILD_EVENT).child(event.getId()).setValue(event);
        return event.getId();
    }

    public void update(Event event){
        mDatabaseReference.child(Constants.FIREBASE_REALTIME.CHILD_EVENT).child(event.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> postValues = new HashMap<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    postValues.put(snapshot.getKey(), snapshot.getValue());
                }
                if (!event.getDescription().equals(""))
                    postValues.put(Constants.FIREBASE_REALTIME.CHILD_EVENT_DESCRIPTION, event.getDescription());
                if (!event.getAdress().equals(""))
                    postValues.put(Constants.FIREBASE_REALTIME.CHILD_EVENT_ADRESS, event.getAdress());
                if (!event.getEmail().equals(""))
                    postValues.put(Constants.FIREBASE_REALTIME.CHILD_EVENT_EMAIL, event.getEmail());
                if (!event.getContact().equals(""))
                    postValues.put(Constants.FIREBASE_REALTIME.CHILD_EVENT_CONTACT, event.getContact());
                if (!event.getPicture().equals(""))
                    postValues.put(Constants.FIREBASE_REALTIME.CHILD_EVENT_PICTURE, event.getPicture());
                if (!event.getType().equals(""))
                    postValues.put(Constants.FIREBASE_REALTIME.CHILD_EVENT_TYPE, event.getType());
                if (event.getDate() != null)
                    postValues.put(Constants.FIREBASE_REALTIME.CHILD_EVENT_DATE, event.getDate());
                if (event.getDays() != null)
                    postValues.put(Constants.FIREBASE_REALTIME.CHILD_EVENT_DAYS, event.getDays());
                if (event.getHours() >= 0)
                    postValues.put(Constants.FIREBASE_REALTIME.CHILD_EVENT_HOURS, event.getHours());
                if (event.getLocaleTickets() != null)
                    postValues.put(Constants.FIREBASE_REALTIME.CHILD_EVENT_LOCALE_TICKETS, event.getLocaleTickets());
                if (!event.getLocation().equals(""))
                    postValues.put(Constants.FIREBASE_REALTIME.CHILD_EVENT_LOCATION, event.getLocation());
                if (event.getName().equals(""))
                    postValues.put(Constants.FIREBASE_REALTIME.CHILD_EVENT_NAME, event.getName());
                if (event.getIdPersonGo() != null)
                    postValues.put(Constants.FIREBASE_REALTIME.CHILD_EVENT_PERSON_GO, event.getIdPersonGo());

                mDatabaseReference.child(Constants.FIREBASE_REALTIME.CHILD_EVENT).child(event.getId()).updateChildren(postValues);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
