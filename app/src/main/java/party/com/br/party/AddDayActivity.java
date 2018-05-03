package party.com.br.party;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.com.br.party.helper.NumberTextWatcherForThousand;
import party.com.br.party.helper.Utilities;

public class AddDayActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.bt_confirm)
    ImageButton mBtConfirm;
    @BindView(R.id.tv_value_basic)
    EditText mTvValueBasic;
    @BindView(R.id.tv_value_vip)
    EditText mTvValueVip;
    @BindView(R.id.tv_value_top)
    EditText mTvValueTop;
    @BindView(R.id.et_lote)
    EditText mEtLote;
    @BindView(R.id.iv_banner)
    ImageView mIvBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_day);
        ButterKnife.bind(this);

        mTvValueBasic.addTextChangedListener(new NumberTextWatcherForThousand(mTvValueBasic));
        mTvValueVip.addTextChangedListener(new NumberTextWatcherForThousand(mTvValueVip));
        mTvValueTop.addTextChangedListener(new NumberTextWatcherForThousand(mTvValueTop));

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
