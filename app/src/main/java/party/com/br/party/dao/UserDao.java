package party.com.br.party.dao;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import party.com.br.party.entity.User;
import party.com.br.party.helper.Constants;
import party.com.br.party.listener.GetByTypeListener;

/**
 * Created by Isabelly on 02/05/2018.
 */

public class UserDao {

    private DatabaseReference mDatabaseReference;

    public UserDao(){
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mDatabaseReference.keepSynced(true);
    }

    public void getById(String id, final GetByTypeListener<User> listener) {
        Query query = mDatabaseReference.child(Constants.FIREBASE_REALTIME.CHILD_USER).child(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listener.getByType(dataSnapshot.getValue(User.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}
