package com.example.vishal.saltnpepper;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class login extends AppCompatActivity {

    private EditText phone;
    private Button btncon;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pb=findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);
        phone=findViewById(R.id.txtphone);
        phone.addTextChangedListener(new TextWatcher() {

            // Before EditText text change.
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            // This method is invoked after user input text in EditText.
            @Override
            public void afterTextChanged(Editable editable) {
                processButtonByTextLength();
            }
        });
    }

    private void processButtonByTextLength()
    {
        String inputText = phone.getText().toString();
        btncon=findViewById(R.id.btnpassword);
        if(inputText.length() ==10)
        {
            btncon.setText("CONTINUE");
            btncon.setEnabled(true);
        }else
        {
            btncon.setText("ENTER PHONE NUMBER");
            btncon.setEnabled(false);
        }
    }

    public void loginocreate(View view) {
        String inputText = phone.getText().toString();
        BackgroundTask backgroundTask=new BackgroundTask();
        backgroundTask.execute(inputText);
        pb=findViewById(R.id.progressBar);
        phone.onEditorAction(EditorInfo.IME_ACTION_DONE);
        /*if(inputText.equals("8220345015"))
        {
            final Intent i1=new Intent(this,loginaccess.class);
            Bundle bundle=new Bundle();
            bundle.putString("numb",inputText);
            i1.putExtras(bundle);
            pb.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    pb.setVisibility(View.INVISIBLE);
                    startActivity(i1);
                }
            }, 3000);

        }*/

      /*  else
        {
            final Intent i1=new Intent(this,signup.class);
            Bundle bundle=new Bundle();
            bundle.putString("numb",inputText);
            i1.putExtras(bundle);
            pb.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    startActivity(i1);
                }
            }, 3000);


        }*/

    }
    public class BackgroundTask extends AsyncTask<String,Void,String>{
        String check_url="";
        @Override
        protected void onPreExecute() {
            check_url="https://grooviest-recruiter.000webhostapp.com/login.php";
        }

        @Override
        protected String doInBackground(String... args) {
            String str;
            str=args[0];
            try {
                URL url=new URL(check_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data=URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(str,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
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
            DoWhat(result);

        }
    }
    public void DoWhat(String result){
        if(result.equals("1")){
            String inputText=phone.getText().toString();
            final Intent i1=new Intent(this,loginaccess.class);
            Bundle bundle=new Bundle();
            bundle.putString("numb",inputText);
            i1.putExtras(bundle);
            pb.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    pb.setVisibility(View.INVISIBLE);
                    startActivity(i1);
                }
            }, 3000);

        }
        else{
            String inputText;
            inputText=phone.getText().toString();
            final Intent i1=new Intent(this,signup.class);
            Bundle bundle=new Bundle();
            bundle.putString("numb",inputText);
            i1.putExtras(bundle);
            pb.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    startActivity(i1);
                }
            }, 3000);
        }

    }

    public void succes(View view) {


    }


    public void validate(View view) {
    }
}
