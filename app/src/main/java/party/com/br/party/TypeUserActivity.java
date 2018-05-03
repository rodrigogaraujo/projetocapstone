package party.com.br.party;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.helper.Utilities;

public class TypeUserActivity extends AppCompatActivity implements View.OnClickListener{

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
                mBtConfirm.startAnimation(Utilities.animationAlpha());
                break;
        }
    }
}
