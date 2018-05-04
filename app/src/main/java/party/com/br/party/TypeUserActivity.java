package party.com.br.party;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.helper.Constants;
import party.com.br.party.helper.Utilities;

public class TypeUserActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.bt_confirm)
    ImageButton mBtConfirm;
    RadioButton mBtBalada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_user);
        ButterKnife.bind(this);
        mBtBalada = findViewById(R.id.rb_balada);
        mBtConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt_confirm:
                buttonConfirm();
                break;
        }
    }

    private void buttonConfirm() {
        mBtConfirm.startAnimation(Utilities.animationAlpha());
        if (mBtBalada.isChecked()) {
            Intent i = new Intent(this, CreateUserActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(Constants.INTRO.SEND_EMAIL, getIntent().getStringExtra(Constants.INTRO.SEND_EMAIL));
            if (getIntent().hasExtra(Constants.INTRO.SEND_EMAIL)) {
                bundle.putString(Constants.INTRO.SEND_TYPE, Constants.FIREBASE_REALTIME.CHILD_USER_TYPE_BALADA);
            } else {
                bundle.putString(Constants.INTRO.SEND_TYPE, Constants.FIREBASE_REALTIME.CHILD_USER_TYPE_PROMOTOR);
            }
            i.putExtras(bundle);
            startActivity(i);
            this.finish();
        }
    }
}
