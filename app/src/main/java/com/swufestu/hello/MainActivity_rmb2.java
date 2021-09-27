package com.swufestu.hello;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity_rmb2 extends AppCompatActivity {
    private static final String TAG="MainActivity_rmb2";
    private EditText output1,output2,output3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rmb2);

        Intent intent=getIntent();
        float dollar=intent.getFloatExtra("dollar_rate_key",0.0f);
        float euro=intent.getFloatExtra("euro_rate_key",0.0f);
        float won=intent.getFloatExtra("won_rate_key",0.0f);

        Log.i(TAG,"openone:dollar="+dollar);
        Log.i(TAG,"openone:euro="+euro);
        Log.i(TAG,"openone:won="+won);

        output1=findViewById(R.id.input1d);
        output2=findViewById(R.id.input2e);
        output3=findViewById(R.id.input3w);
        //将汇率填入到控件中
        output1.setText(String.valueOf(dollar));
        output2.setText(String.valueOf(euro));
        output3.setText(String.valueOf(won));
    }

    public void opensave(View v){
        Log.i(TAG,"opensave:");
        float dollar=Float.parseFloat(output1.getText().toString());
        float euro=Float.parseFloat(output2.getText().toString());
        float won=Float.parseFloat(output3.getText().toString());

        Log.i(TAG,"opensave:dollarrate="+dollar);
        Log.i(TAG,"opensave:eurorate="+euro);
        Log.i(TAG,"opensave:wonrate="+won);
/**
        Intent save=getIntent();
        Bundle bdl=new Bundle();
        bdl.putFloat("dollar_key",dollar);
        bdl.putFloat("euro_key",euro);
        bdl.putFloat("won_key",won);
        save.putExtras(bdl);
 **/

        Intent save=getIntent();
        save.putExtra("dollar_key",dollar);
        save.putExtra("euro_key",euro);
        save.putExtra("won_key",won);

        //startActivity(save);
        setResult(3,save);
        finish();

    }
}