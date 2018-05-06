package com.example.plenkuing.androidevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by PlenKuing on 2018/5/5.
 */

public class MyButton extends Button {
    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        Log.v("com.sise" , "the onKeyDown in MyButton");

        //返回true，表明该事件不会向外扩散
        return true;
    }

}

