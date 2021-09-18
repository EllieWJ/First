package com.swufestu.hello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private static  final String TAG="wwww";
    EditText edith,editw;
    TextView output1,output2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi);
        output1=findViewById(R.id.out1);
        output2=findViewById(R.id.out2);
        //output.setText("Hello Swufe");

       /* LocalDate localDate;
        localDate = LocalDate.now();
        output.setText("Today: "+localDate);*/
        Log.i("111","onCreate:开始运行了");
        Log.i(TAG,"onCreate:");

        edith=findViewById(R.id.inputheight);
        editw=findViewById(R.id.inputweight);
        //edit.setText("30");
        //edit.getText().toString();

        //Button btn=findViewById(R.id.btn);
        //btn.setOnClickListener(this);
        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output.setText("Hi~Andriod");
            }
        });*/



    }


    public void myclick(View v) {
        Log.i(TAG,"myclick:AAAAAAA");
        double result;
        DecimalFormat df=new DecimalFormat("#.00");
        String advice="";
        String numberh=edith.getText().toString();
        String numberw=editw.getText().toString();
        double height=Double.parseDouble(numberh);
        double weight=Double.parseDouble(numberw);
        result=weight/(height*height);
        //String nnumber=String.valueOf(result);
        if(result>=18.5&&result<=25)
        {
            advice="体重在正常范围";
        }else if(result>25&&result<=30){
            advice="体重状况为超重";
        }else if(result>30&&result<=40){
            advice="体重状况为肥胖";
        }else if(result>40){
            advice="体重状况为重度肥胖";
        }else if(result<18.5){
            advice="体重状况为偏瘦";
        }
        output1.setText(df.format(result));
        output2.setText(advice);
    }

}