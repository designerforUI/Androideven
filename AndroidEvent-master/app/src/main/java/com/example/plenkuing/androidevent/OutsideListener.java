package com.example.plenkuing.androidevent;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by PlenKuing on 2018/5/5.
 */

public class OutsideListener implements View.OnClickListener {
    private EditText et;
    private Activity act;

    public OutsideListener(EditText et, Activity act) {
        this.et = et;
        this.act = act;
    }

    @Override
    public void onClick(View view) {
        et.setText("外部类");
    }
}
