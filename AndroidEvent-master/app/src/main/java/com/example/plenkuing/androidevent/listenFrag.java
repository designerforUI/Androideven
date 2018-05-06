package com.example.plenkuing.androidevent;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by PlenKuing on 2018/5/5.
 */

public class listenFrag extends Fragment implements View.OnClickListener{
    private Button insideBt;
    private Button outsideBt;
    private Button anonymousBt;
    private Button activityBt;
    private Button tagBt;
    private EditText et;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.listen_fg,container,false);


        return view;
    }


    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        insideBt=getActivity().findViewById(R.id.insideBt);
        outsideBt=getActivity().findViewById(R.id.outsideBt);
        anonymousBt=getActivity().findViewById(R.id.anonymousBt);
        activityBt=getActivity().findViewById(R.id.activityBt);
        tagBt=getActivity().findViewById(R.id.tagBt);
        et=getActivity().findViewById(R.id.et);
        insideBt.setOnClickListener(new MyClickLister());//内部类
        outsideBt.setOnClickListener(new OutsideListener(et,getActivity()));//外部类
        anonymousBt.setOnClickListener(new View.OnClickListener() {//匿名内部类
            @Override
            public void onClick(View view) {
                et.setText("匿名内部类");
            }
        });

        activityBt.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {//Activity本身作为监听器

        et.setText("Activity");
    }

    class MyClickLister implements View.OnClickListener{//内部类

        @Override
        public void onClick(View view) {
           // et=.findViewById(R.id.et);
            et.setText("内部类");
        }
    }

}
