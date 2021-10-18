package com.swufestu.hello;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity_rmb extends AppCompatActivity implements Runnable{
    private static final String TAG="MainActivity_rmb";
    /**
    private float dollarrate=0.1548f;
    private float eurorate=0.1323f;
    private float wonrate=182.5773f;**/

    private float dollarrate;
    private float eurorate;
    private float wonrate;

    EditText rmb;
    TextView output1;
    Handler handler;
/**
    Date start ;
    Date now ;
    SharedPreferences spf;
**/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rmb);
        rmb=findViewById(R.id.rmb);
        output1=findViewById(R.id.outnum);
        //读取保存的数据
        SharedPreferences sp=getSharedPreferences("rate", Activity.MODE_PRIVATE);
        PreferenceManager.getDefaultSharedPreferences(this);
        //spf = getSharedPreferences("time", Activity.MODE_PRIVATE);

        dollarrate=sp.getFloat("dollar_rate",0.1548f);
        eurorate=sp.getFloat("euro_rate",0.1323f);
        wonrate=sp.getFloat("won_rate",182.5773f);
/**
        String dateString;
        dateString = spf.getString("dateString","");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Log.i(TAG,"dataString:"+dateString);
        if(dateString.equals("")){
            //若为空，则第一次获取时间
            //获取当前时间
            start = new Date(System.currentTimeMillis());//获取当前时间
            dateString = simpleDateFormat.format(start);//转为String
            SharedPreferences.Editor editor=spf.edit();
            editor.putString("dateString",dateString);
            editor.apply();
        }else{
            try {
                dateString = spf.getString("dateString", null);
                start =simpleDateFormat.parse(dateString) ;

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }  **/

        Log.i(TAG, "onCreate: get from sp dollar="+dollarrate);
        Log.i(TAG, "onCreate: get from sp euro="+eurorate);
        Log.i(TAG, "onCreate: get from sp won="+wonrate);

       // start =new Date(System.currentTimeMillis());

        handler=new Handler(Looper.myLooper()){
            public void handleMessage(@NonNull Message msg){
                Log.i(TAG, "handleMessage: 收到消息");
                if(msg.what==6){
                    /**
                    String str=(String) msg.obj;
                    Log.i(TAG, "handleMessage: str="+str);
                    output1.setText(str);**/
                    Bundle bdl=(Bundle)msg.obj;
                    dollarrate=bdl.getFloat("r1");
                    eurorate=bdl.getFloat("r2");
                    wonrate=bdl.getFloat("r3");

                    Log.i(TAG, "handleMessage:dollarrate="+dollarrate);
                    Log.i(TAG, "handleMessage:eurorate="+eurorate);
                    Log.i(TAG, "handleMessage:wonrate="+wonrate);

                    Toast.makeText(MainActivity_rmb.this, "数据已更新", Toast.LENGTH_SHORT).show();

                }
                super.handleMessage(msg);
            }
        };
        //开启线程
        //MyThrea td=new MyThrea();
        //td.setHandler(handler);

        Thread t = new Thread(this);
        t.start();


    }



    public void click(View btn){
        Log.i(TAG,"click:");
        float temp;
        String rmba=rmb.getText().toString();
        if(rmba.length()>0){
            temp=Float.parseFloat(rmba);
            double result=0.0;
            if(btn.getId()==R.id.DOLLAR){
                result=temp*dollarrate;
            }else if(btn.getId()==R.id.EURO){
                result=temp*eurorate;
            }else if(btn.getId()==R.id.WON){
                result=temp*wonrate;
            }else{
                result=0.0;
            }
            output1.setText(String.valueOf(result));
        }else{
            //重置输出
            output1.setText("Hello");
            Toast.makeText(this, "请输入金额后再进行计算", Toast.LENGTH_LONG).show();
        }
    }

    public void openconfig(View v){
        //打开窗口
        Log.i(TAG,"openconfig:");
        config();
    }

    private void config() {
        Intent config = new Intent(this, MainActivity_rmb2.class);
        config.putExtra("dollar_rate_key", dollarrate);
        config.putExtra("euro_rate_key", eurorate);
        config.putExtra("won_rate_key", wonrate);

        Log.i(TAG, "openone:dollarrate=" + dollarrate);
        Log.i(TAG, "openone:eurorate=" + eurorate);
        Log.i(TAG, "openone:wonrate=" + wonrate);

        //startActivity(config);
        startActivityForResult(config, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1&&resultCode==3){


            dollarrate=data.getFloatExtra("dollar_key",0.1f);
            eurorate=data.getFloatExtra("euro_key",0.1f);
            wonrate=data.getFloatExtra("won_key",0.1f);
            //保存数据到sp
            SharedPreferences sp=getSharedPreferences("rate", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor=sp.edit();
            editor.putFloat("dollar_rate",dollarrate);
            editor.putFloat("euro_rate",eurorate);
            editor.putFloat("won_rate",wonrate);
            editor.apply();

            Log.i(TAG, "onActivityResult: save ok");

            /**使用bundle获取数据
            Bundle bundle=new Bundle();
            dollarrate=bundle.getFloat("key_dollar",0.1f);
            eurorate=bundle.getFloat("key_euro",0.1f);
            wonrate=bundle.getFloat("key_won",0.1f);**/
            Log.i(TAG,"onActivityResult:dollarrate="+dollarrate);
            Log.i(TAG,"onActivityResult:eurorate="+eurorate);
            Log.i(TAG,"onActivityResult:wonrate="+wonrate);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rate,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_set){
            config();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void run() {
     //   while (true){
        /**
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
            now = new Date(System.currentTimeMillis()); //获取当前系统时间
            long cha = now.getTime() - start.getTime();
            double result = cha* 1.0 /(1000 * 60 * 60);**/
           // if (result>24) {
        /**
                SharedPreferences sp = getSharedPreferences("time", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.apply();
                editor.putString("dataString", simpleDateFormat.format(now));
                Log.i(TAG, "onEnd: " + simpleDateFormat.format(now)); **/
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //获取网络数据
                URL url=null;
                Bundle bundle=new Bundle();
                try {/**
                 url=new URL("https://www.usd-cny.com/bankofchina.htm");
                 HttpURLConnection http=(HttpURLConnection)url.openConnection();
                 InputStream in=http.getInputStream();
                 String html=inputStream2String(in);
                 Log.i(TAG, "run: html="+html); **/
                    Document doc=Jsoup.connect("https://www.usd-cny.com/bankofchina.htm").get();
                    Log.i(TAG, "run: title="+doc.title());

                    Elements h4s=doc.getElementsByTag("h4");
                    for(Element h4:h4s){
                        Log.i(TAG, "run: h4="+h4.text());
                    }

                    Elements tables=doc.getElementsByTag("table");
                    Element table=tables.first();

                    Elements trs=table.getElementsByTag("tr");
                    trs.remove(0);
                    for(Element tr:trs){
                        Elements tds=tr.getElementsByTag("td");
                        String cname=tds.get(0).text();
                        String cval=tds.get(5).text();

                        if("美元".equals(cname)){
                            bundle.putFloat("r1",100f/Float.parseFloat(cval));
                            Log.i(TAG, "run: 美元="+cval);
                        }else if("欧元".equals(cname)){
                            bundle.putFloat("r2",100f/Float.parseFloat(cval));
                            Log.i(TAG, "run: 欧元="+cval);
                        }else if("韩元".equals(cname)){
                            bundle.putFloat("r3",100f/Float.parseFloat(cval));
                            Log.i(TAG, "run: 韩元="+cval);
                        }

                    }
                    /**
                     for(int i=0;;i++){
                     Element t=tables.get(i);
                     Elements hrefs=t.getElementsByTag("a");
                     Elements types=t.getElementsByTag("td");
                     Element rate=types.last();
                     Log.i(TAG, "run: 下一行");
                     Log.i(TAG, "run: a="+hrefs.text());
                     Log.i(TAG, "run: td="+types.text());
                     Log.i(TAG, "run: rate="+rate.text());
                     }**/


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                Log.i(TAG, "run: start thread");
                //发送消息给主线程
                /**
                 Message msg=handler.obtainMessage();
                 msg.what=6;
                 msg.obj="Hello from run";**/
                Message msg=handler.obtainMessage(6,bundle);
                handler.sendMessage(msg);
                Log.i(TAG, "run: 消息已发送");

         //   }




     //   }

    }

    private String inputStream2String(InputStream inputStream) throws IOException {
        final int bufferSize=1024;
        final char[] buffer=new char[bufferSize];
        final StringBuilder out=new StringBuilder();
        Reader in=new InputStreamReader(inputStream,"gb2312");
        while(true){
            int rsz=in.read(buffer,0,buffer.length);
            if(rsz<0) break;
            out.append(buffer,0,rsz);
        }
        return out.toString();

    }
}