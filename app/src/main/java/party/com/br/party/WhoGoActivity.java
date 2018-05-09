package party.com.br.party;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import party.com.br.party.adapter.WhoGoAdapter;
import party.com.br.party.dao.UserDao;
import party.com.br.party.entity.User;
import party.com.br.party.helper.Constants;
import party.com.br.party.listener.GetByTypeListener;

public class WhoGoActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LayoutManager mLayoutManager;
    private WhoGoAdapter mAdapter;
    private Toolbar mToolbar;
    private List<User> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_go);
        mRecyclerView = findViewById(R.id.rb_who);
        mToolbar = findViewById(R.id.toolbar_detail);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mList = new ArrayList<>();
        mAdapter = new WhoGoAdapter(this,mList );
        mRecyclerView.setAdapter(mAdapter);
        if(getIntent().hasExtra(Constants.SEND_EVENT) && getIntent().getExtras() != null){
            mList.clear();
            List<String> strings = getIntent().getStringArrayListExtra(Constants.SEND_EVENT);
            for(String str : strings){
                new UserDao().getById(str, new GetByTypeListener<User>() {
                    @Override
                    public void getByType(User user) {
                        mAdapter.add(user);
                    }
                });
            }
        }
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
