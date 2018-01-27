package axun.com.clickdatudemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;

public class MainActivity extends AppCompatActivity {

    private ClipTopImageView mClicpImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mClicpImage = (ClipTopImageView) findViewById(R.id.clicp_image);
        final ViewTreeObserver vtb = mClicpImage.getViewTreeObserver();
        vtb.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mClicpImage.getViewTreeObserver().removeOnPreDrawListener(this);
//                int height = imageView.getHeight()
//                int width = imageView.getWidth()
                mClicpImage.setImageResoure(R.mipmap.test);
                return false;
            }
        });
    }


}
