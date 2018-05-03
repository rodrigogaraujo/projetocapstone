package party.com.br.party;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.helper.Utilities;

public class EmailLoginActivity extends AppCompatActivity  implements View.OnClickListener{

    @BindView(R.id.bt_confirm)
    ImageButton mBtConfirm;
    @BindView(R.id.et_email)
    EditText mEtEmail;
    @BindView(R.id.et_pass)
    EditText mEtPass;
    @BindView(R.id.progress_login)
    ProgressBar mProgressEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);
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
