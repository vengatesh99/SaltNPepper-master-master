package com.example.vishal.saltnpepper;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

public class ForgotPassword extends AppCompatActivity {
    EditText editText;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        editText=findViewById(R.id.editText2);
        btnSend=findViewById(R.id.button5);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String test=editText.getText().toString();
                if(test.length()==10){
                    BackgroundTask backgroundTask=new BackgroundTask();
                    backgroundTask.execute(test);
                    //sendSMS();

                }

            }
        });

    }
    public void sendSMS(String msg){
        String number=editText.getText().toString();
        SmsManager manager=SmsManager.getDefault();
        manager.sendTextMessage(number,null,msg,null,null);
        Toast.makeText(getApplicationContext(),"sent sucessfully",Toast.LENGTH_SHORT).show();

    }
    public class BackgroundTask extends AsyncTask<String,Void,String>{
        String otp_str="";
        String str="";
        String msg;
        @Override
        protected void onPreExecute() {
            otp_str="https://grooviest-recruiter.000webhostapp.com/otp.php";
        }

        @Override
        protected String doInBackground(String... args) {
            str=args[0];
            try {
                URL url=new URL(otp_str);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                Random rand = new Random();
                msg = String.format("%04d", rand.nextInt(10000));
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data=URLEncoder.encode("phoneno","UTF-8")+"="+URLEncoder.encode(str,"UTF-8")+"&"+URLEncoder.encode("otp","UTF-8")+"="+URLEncoder.encode(msg,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            if(result.equals("1")){
                sendSMS(msg);
                Toast.makeText(getApplicationContext(),"OTP sent",Toast.LENGTH_SHORT).show();



            }
            else {
                Toast.makeText(getApplicationContext(),"Error sending otp",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
