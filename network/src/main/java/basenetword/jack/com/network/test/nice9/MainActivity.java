package basenetword.jack.com.network.test.nice9;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import basenetword.jack.com.network.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainnice);
    }

    public void onList(View v) {
        startActivity(DemoListActivity.class);
    }

    public void onDrag(View v) {
        startActivity(DemoDragActivity.class);
    }

    private void startActivity(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
    }
}
