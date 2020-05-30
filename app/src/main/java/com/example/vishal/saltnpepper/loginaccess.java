package com.example.vishal.saltnpepper;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class loginaccess extends AppCompatActivity {

    private Button btncon;
    private EditText phone;
    private EditText passwd;
    private TextView forgotpsswd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginaccess);
        phone=findViewById(R.id.txtphone);
        forgotpsswd=findViewById(R.id.textView15);
        Intent intent = getIntent();

        Bundle b = getIntent().getExtras();

        String str2 = b.getString("numb");

        phone.setText(str2);
        phone.setEnabled(false);

        passwd=findViewById(R.id.txtpasswd);
        forgotpsswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),ForgotPassword.class);
                startActivity(i);

            }
        });

        passwd.addTextChangedListener(new TextWatcher() {

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

    private void processButtonByTextLength() {

        String inputText = passwd.getText().toString();
        btncon=findViewById(R.id.btnpassword);
        if(inputText.length() >=8)
        {
            btncon.setText("LOGIN");
            btncon.setEnabled(true);
        }else
        {
            btncon.setText("ENTER PASSWORD");
            btncon.setEnabled(false);
        }
    }


    public void validate(View view) {

        /*if(passwd.getText().toString().equals("vishal11"))
        {
            Intent i=new Intent(this,location.class);
            startActivity(i);
        }*/
        String str=phone.getText().toString();
        BackgroundTask backgroundTask=new BackgroundTask();
        backgroundTask.execute(str);

    }
    public class BackgroundTask extends AsyncTask<String,String,String>{
        String login_url="";
        @Override
        protected void onPreExecute() {
            login_url="https://grooviest-recruiter.000webhostapp.com/cust_login.php";
        }

        @Override
        protected String doInBackground(String... args) {
            String str;
            str=args[0];
            try {
                URL url=new URL(login_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data=URLEncoder.encode("phoneno","UTF-8")+"="+URLEncoder.encode(str,"UTF-8");
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
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            GoToNext(result);
        }

    }
    public void GoToNext(String result){
        String str="";
        str=passwd.getText().toString();
        if(str.equals(result)){
            Intent i=new Intent(this,location.class);
            startActivity(i);
        }
        else{
            Toast.makeText(getApplicationContext(),"Incorrect Credentials"+str,Toast.LENGTH_SHORT).show();
        }


    }

}
