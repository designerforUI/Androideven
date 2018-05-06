package com.example.plenkuing.androidevent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class HandleFrag extends Activity {
    private TextView show;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show = (TextView) findViewById(R.id.textView1);
        webView = (WebView) findViewById(R.id.webview);
    }

    public void download(View source) throws MalformedURLException{
        DownTask task = new DownTask(this);
        task.execute(new URL("http://m.blog.csdn.net/blog/lxq_xsyu"));
    }

    class DownTask extends AsyncTask<URL, Integer, String>{
        ProgressDialog pdialog;
        Context mContext;
        int hasRead = 0;
        public DownTask(Context ctx){
            mContext = ctx;
        }

        /**
         * 该方法在执行后台耗时操作前被调用，初始化
         */
        @Override
        protected void onPreExecute() {
            pdialog = new ProgressDialog(mContext);
            pdialog.setTitle("任务正在执行中");
            pdialog.setMessage("任务正在执行中，请耐心等待...");
            pdialog.setCancelable(false);
            pdialog.setMax(182);
            pdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pdialog.setIndeterminate(false);
            pdialog.show();
        }

        /**
         * 重写该方法就是后台线程将要完成的任务，该方法可以调用
         * publishProgress(Progress.. values)方法更新任务的执行进度。
         */
        @Override
        protected String doInBackground(URL... params) {
            StringBuilder sb = new StringBuilder();
            try {
                URLConnection conn = params[0].openConnection();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), "utf-8"));
                String line = null;
                while((line = br.readLine()) != null){
                    sb.append(line + "\n");
                    hasRead++;
                    publishProgress(hasRead);
                }
                return sb.toString();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
        }

        /**
         * 当doInBackground完成后，系统自动调用
         */
        @Override
        protected void onPostExecute(String result) {
            //show.setText(result);
            webView.loadDataWithBaseURL(null, result, "text/html", "utf-8", null);
            pdialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            show.setText("已经读取了【" + values[0] + "】行！");
            pdialog.setProgress(values[0]);
        }

    }
}

