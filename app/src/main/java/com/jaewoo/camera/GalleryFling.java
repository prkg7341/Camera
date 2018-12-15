package com.jaewoo.camera;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

public class GalleryFling extends Gallery {
    public GalleryFling(Context context) {
        super(context);
    }
    public GalleryFling(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
        int keyCode;
        if(e2.getX()>e1.getX()){
            keyCode = KeyEvent.KEYCODE_DPAD_LEFT;
        }
        else{
            keyCode = KeyEvent.KEYCODE_DPAD_RIGHT;
        }
        onKeyDown(keyCode, null);
        return true;
    }
}
