package com.swufestu.hello;

import android.content.Intent;
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

import java.lang.reflect.Array;

public class MainActivity_rmb extends AppCompatActivity {
    private static final String TAG="MainActivity_rmb";
    private float dollarrate=0.1548f;
    private float eurorate=0.1323f;
    private float wonrate=182.5773f;
    EditText rmb;
    TextView output1;
    double num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rmb);
        rmb=findViewById(R.id.rmb);
        output1=findViewById(R.id.outnum);


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
            /**
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
}