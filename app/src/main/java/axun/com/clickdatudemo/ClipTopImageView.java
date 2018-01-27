package axun.com.clickdatudemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver;

/**
 * Created by Administrator on 2018/1/26.
 */

public class ClipTopImageView extends android.support.v7.widget.AppCompatImageView {
    private static final String TAG = "ClipTopImageView";
    private Bitmap bitmap;
    private int width;
    private int height;
    private boolean isLarge = false;
    public ClipTopImageView(Context context) {
        this(context,null);
    }

    public ClipTopImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ClipTopImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        width = getWidth();
        height = getHeight();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG,String.format("onMeasure width = %d,height=%d",getWidth(),getHeight()));
    }

    public void setBitmap(Bitmap orBitmap){
        bitmap = clipBitmap(orBitmap);
        final ViewTreeObserver vtb = getViewTreeObserver();
        vtb.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
               getViewTreeObserver().removeOnPreDrawListener(this);
//                int height = imageView.getHeight()
//                int width = imageView.getWidth()
                setImageBitmap(bitmap);
                return false;
            }
        });


    }

    public void setImageResoure(int resId){
        Drawable drawable;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            drawable = getResources().getDrawable(resId,null);
        }else {
            drawable =  getResources().getDrawable(resId);
        }
        bitmap = convertDrawable2Bitmap(drawable);
        setBitmap(bitmap);
    }

    private Bitmap clipBitmap(Bitmap bitmap){
        init();
        int pw = bitmap.getWidth();
        int ph = (height*pw)/width;
        if (ph<bitmap.getHeight()){
            isLarge = true;
        }else {
            isLarge = false;
        }
        return Bitmap.createBitmap(bitmap,0,0,pw,ph,null,false);
    }

    private Bitmap convertDrawable2Bitmap(Drawable drawable) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable)drawable;
        return bitmapDrawable.getBitmap();
    }
}
