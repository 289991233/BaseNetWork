package basenetword.jack.com.network.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import basenetword.jack.com.network.R;

public class ImageBiscuitActivity extends AppCompatActivity {
    ImageView mImageView;
    TextView mTextView;
    StringBuilder info = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_biscuit);
    }


}
