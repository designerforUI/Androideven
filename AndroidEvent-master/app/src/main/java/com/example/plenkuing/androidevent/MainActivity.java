package com.example.plenkuing.androidevent;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity implements RadioGroup.OnCheckedChangeListener{
    private FragmentManager fManager;
    private RadioGroup rg_tab_bar;
    private RadioButton listen_rbt;
    private listenFrag fg1;
    private Test fg2;
    private SettingFrag fg3;
    private Test2 fg4;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fManager=getFragmentManager();
        rg_tab_bar = findViewById(R.id.rg_tab_bar);//获取底部导航栏
        rg_tab_bar.setOnCheckedChangeListener(this);//设置状态改变事件
        listen_rbt = findViewById(R.id.listen_rg);
        listen_rbt.setChecked(true);//默认选中第一个
        onCheckedChanged(rg_tab_bar,R.id.listen_rg);//手动调用点击事件，默认的本来应该是可以点击的，不知道为啥不行了。。




        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == PROGRESS_DIALOG){
                    pd.setProgress(progressStatus);
                }
            }
        };
    }




    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkId) {//选择改变事件
        FragmentTransaction fragmentTransaction = fManager.beginTransaction();//开启事务
        hideAllFragment(fragmentTransaction);//隐藏所有的fragment
        //根据选择的不同，显示不同的fragment
        switch(checkId){
            case R.id.listen_rg:
                 fg1 = new listenFrag();
                fragmentTransaction.add(R.id.frag,fg1);
                //Toast.makeText(this,"hhh",Toast.LENGTH_LONG).show();
                break;
            case R.id.callback_rg:
                fg2 = new Test();
                fragmentTransaction.add(R.id.frag,fg2);
                break;
            case R.id.setting_rg:
                fg3 = new SettingFrag();
                fragmentTransaction.add(R.id.frag,fg3);
                break;
            case R.id.handler_rg:
                fg4 = new Test2();
                fragmentTransaction.add(R.id.frag,fg4);
                break;


        }
        fragmentTransaction.commit();
    }

    public void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg3 != null)fragmentTransaction.hide(fg3);
        if(fg4 != null)fragmentTransaction.hide(fg4);
    }

    public void tagClick(View v){
        EditText et = findViewById(R.id.et);
        et.setText("绑定到标签");
    }



    // 该程序模拟填充长度为100的数组
    private int[] data = new int[100];
    int hasData = 0;
    // 定义进度对话框的标识
    final int PROGRESS_DIALOG = 0x112;
    // 记录进度对话框的完成百分比
    int progressStatus = 0;
    ProgressDialog pd;
    // 定义一个负责更新的进度的Handler
    Handler handler;

    @Override
    public Dialog onCreateDialog(int id, Bundle status) {
        System.out.println("------create------");
        switch (id) {
            case PROGRESS_DIALOG:
                // 创建进度对话框
                pd = new ProgressDialog(this);
                pd.setMax(100);
                // 设置对话框的标题
                pd.setTitle("任务完成百分比");
                // 设置对话框 显示的内容
                pd.setMessage("耗时任务的完成百分比");
                // 设置对话框不能用“取消”按钮关闭
                pd.setCancelable(false);
                // 设置对话框的进度条风格
                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                // 设置对话框的进度条是否显示进度
                pd.setIndeterminate(false);
                break;
        }
        return pd;
    }
    // 该方法将在onCreateDialog方法调用之后被回调
    @Override
    public void onPrepareDialog(int id, Dialog dialog) {
        System.out.println("------prepare------");
        super.onPrepareDialog(id, dialog);
        switch (id) {
            case PROGRESS_DIALOG:
                // 对话框进度清零
                pd.incrementProgressBy(-pd.getProgress());
                new Thread() {
                    public void run() {
                        while (progressStatus < 100) {
                            // 获取耗时操作的完成百分比
                            progressStatus = doWork();
                            // 发送消息到Handler，请补全代码

                        }
                        // 如果任务已经完成
                        if (progressStatus >= 100) {
                            // 关闭对话框
                            pd.dismiss();
                        }
                    }
                }.start();
                break;
        }
    }
    // 模拟一个耗时的操作。
    public int doWork() {
        // 为数组元素赋值
        data[hasData++] = (int) (Math.random() * 100);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return hasData;
    }

}
