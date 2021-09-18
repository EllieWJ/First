package com.swufestu.hello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity_teamscore extends AppCompatActivity {

    private static final String TAG="BallActivity";
    TextView output1,output2;
    int score1=0,score2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ballactivity);
        output1=findViewById(R.id.scorea);
        output2=findViewById(R.id.scoreb);

    }

    public void clicka(View btn){
        Log.i(TAG,"click:");
        if(btn.getId()==R.id.btna3){
            score1+=3;
        }else if(btn.getId()==R.id.btna2){
            score1+=2;
        }else if(btn.getId()==R.id.btna1){
            score1+=1;
        }else{
            //reset
            score1=0;
            score2=0;
        }
        show();

    }

    public void clickb(View btn){
        Log.i(TAG,"click:");
        if(btn.getId()==R.id.btnb3){
            score2+=3;
        }else if(btn.getId()==R.id.btnb2){
            score2+=2;
        }else if(btn.getId()==R.id.btnb1){
            score2+=1;
        }else{
            //reset
            score1=0;
            score2=0;
        }
        show();
    }
    private void show(){
        TextView output1=findViewById(R.id.out1);
        output1.setText(String.valueOf(score1));

        TextView output2=findViewById(R.id.out2);
        output2.setText(String.valueOf(score2));

    }
}