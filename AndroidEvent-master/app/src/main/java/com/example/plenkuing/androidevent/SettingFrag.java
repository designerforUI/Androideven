package com.example.plenkuing.androidevent;

import android.app.Fragment;
import android.content.res.Configuration;
import android.media.VolumeShaper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by PlenKuing on 2018/5/5.
 */

public class SettingFrag extends Fragment {
    EditText ori;
    EditText navigation;
    EditText touch;
    EditText mnc;
    Button bt;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 获取应用界面中的界面组件
        ori = getActivity().findViewById(R.id.ori);
        navigation = getActivity().findViewById(R.id.navigation);
        touch = getActivity().findViewById(R.id.touch);
        mnc = getActivity().findViewById(R.id.mnc);
       bt = getActivity().findViewById(R.id.Bt);
        // 为按钮绑定事件监听器
        bt.setOnClickListener     (new View.OnClickListener() {
            public void onClick(View source) {
                // 获取系统的Configuration对象
                Configuration cfg =getResources().getConfiguration();
                String screen = cfg.orientation == Configuration.ORIENTATION_LANDSCAPE ?
                        "横向屏幕": "竖向屏幕";
                String mncCode = cfg.mnc + "";
                String naviName = cfg.orientation == Configuration.NAVIGATION_NONAV ? "没有方向控制": cfg.orientation == Configuration.NAVIGATION_WHEEL ? "滚轮控制方向": cfg.orientation == Configuration.NAVIGATION_DPAD ? "方向键控制方向": "轨迹球控制方向";
                navigation.setText(naviName);
                String touchName = cfg.touchscreen == Configuration.TOUCHSCREEN_NOTOUCH ? "无触摸屏": cfg.touchscreen == Configuration.TOUCHSCREEN_STYLUS ? "触摸笔式触摸屏": "接受手指的触摸屏";
                ori.setText(screen);
                mnc.setText(mncCode);
                touch.setText(touchName);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
       View view =  inflater.inflate(R.layout.setting_fg,container,false);
       return view;
    }
}
