package party.com.br.party.dao;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

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

    public void update(User user){
        mDatabaseReference.child(Constants.FIREBASE_REALTIME.CHILD_USER).child(user.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> postValues = new HashMap<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    postValues.put(snapshot.getKey(), snapshot.getValue());
                }
                if (!user.getName().equals(""))
                    postValues.put(Constants.FIREBASE_REALTIME.CHILD_USER_NAME, user.getName());
                if (!user.getAdress().equals(""))
                    postValues.put(Constants.FIREBASE_REALTIME.CHILD_USER_ADRESS, user.getAdress());
                if (!user.getEmail().equals(""))
                    postValues.put(Constants.FIREBASE_REALTIME.CHILD_USER_EMAIL, user.getEmail());
                if (!user.getPhone().equals(""))
                    postValues.put(Constants.FIREBASE_REALTIME.CHILD_USER_PHONE, user.getPhone());
                if (!user.getPicture().equals(""))
                    postValues.put(Constants.FIREBASE_REALTIME.CHILD_USER_PICTURE, user.getPicture());
                if (!user.getType().equals(""))
                    postValues.put(Constants.FIREBASE_REALTIME.CHILD_USER_TYPE, user.getType());
                if (user.getInterest() != null)
                    postValues.put(Constants.FIREBASE_REALTIME.CHILD_USER_INTEREST, user.getInterest());
                mDatabaseReference.child(Constants.FIREBASE_REALTIME.CHILD_USER).child(user.getId()).updateChildren(postValues);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
