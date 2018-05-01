package party.com.br.party;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.helper.Utilities;

public class AddLocaleActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.bt_confirm)
    ImageButton mBtConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_locale);
        ButterKnife.bind(this);

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
