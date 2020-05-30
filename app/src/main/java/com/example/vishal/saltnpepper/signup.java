package com.example.vishal.saltnpepper;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signup extends AppCompatActivity {

    EditText phone;
    EditText email;
    EditText name;
    EditText pass;
    Button b;
    ProgressBar pb;
    ImageView i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        phone=findViewById(R.id.txtnumb);
        Intent intent = getIntent();

        Bundle b = getIntent().getExtras();

        String str2 = b.getString("numb");

        phone.setText(str2);
        phone.setEnabled(false);

        email=findViewById(R.id.txtemail);
        name=findViewById(R.id.txtname);
        pass=findViewById(R.id.txtpass);

        email.addTextChangedListener(new TextWatcher() {

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
                processButtonByemail();
            }
        });

        name.addTextChangedListener(new TextWatcher() {

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
                processButtonByname();
            }
        });

        pass.addTextChangedListener(new TextWatcher() {

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
                processButtonBypass();
            }
        });
    }

    private void processButtonBypass() {
        String inputText = pass.getText().toString();
        String inputText2 = name.getText().toString();
        String inputText3 = email.getText().toString();
        b=findViewById(R.id.btnsign);
        i=findViewById(R.id.imgtick4);
        if(inputText.length()>=8)
        {
            i.setVisibility(View.VISIBLE);
        }

        if(inputText.length()<8)
        {
            i.setVisibility(View.INVISIBLE);
        }

        if(inputText.length()>=8&&!inputText2.isEmpty()&&isEmailValid(inputText3))
        {
            b.setText("SIGN UP");

            b.setEnabled(true);
        }
        else if(!isEmailValid(inputText3))
        {
            b.setText("ENTER EMAIL");
            b.setEnabled(false);
        }
        else if(isEmailValid(inputText3)&&inputText2.isEmpty())
        {
            b.setText("ENTER NAME");
            b.setEnabled(false);
        }
        else if(inputText.length()<8&&!inputText2.isEmpty()&&isEmailValid(inputText3))
        {
            b.setText("ENTER PASSWORD");
            b.setEnabled(false);
        }
    }

    private void processButtonByname() {
        String inputText = name.getText().toString();
        String inputText2 = email.getText().toString();
        String inputText3 = pass.getText().toString();
        b=findViewById(R.id.btnsign);
        i=findViewById(R.id.imgtick3);
        if(inputText.isEmpty())
        {
            i.setVisibility(View.INVISIBLE);
        }

        if(!inputText.isEmpty())
        {
            i.setVisibility(View.VISIBLE);
        }
        if(inputText.isEmpty()&&isEmailValid(inputText2))
        {
            b.setText("ENTER NAME");
            b.setEnabled(false);
        }
        else if(!inputText.isEmpty()&&inputText3.length()<8)
        {
            b.setText("ENTER PASSWORD");
            b.setEnabled(false);
        }
        else if(!inputText.isEmpty()&&inputText3.length()>=8&&isEmailValid(inputText2))
        {
            b.setText("SIGN UP");
            b.setEnabled(true);
        }
    }

    private void processButtonByemail() {

        String inputText = email.getText().toString();
        b=findViewById(R.id.btnsign);
        i=findViewById(R.id.imgtick2);
        if(isEmailValid(inputText))
        {
            b.setText("ENTER NAME");
            i.setVisibility(View.VISIBLE);

        }else
        {
            b.setText("ENTER EMAIL");
            i.setVisibility(View.INVISIBLE);
            b.setEnabled(false);
        }
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void signing(View view) {
        String str,str1,str2,str3;
        str=phone.getText().toString();
        str1=email.getText().toString();
        str2=name.getText().toString();
        str3=pass.getText().toString();
        BackgroundTask backgroundTask=new BackgroundTask();
        backgroundTask.execute(str2,str1,str,str3);
        pb=findViewById(R.id.progressBar2);
        pb.setVisibility(View.INVISIBLE);
       // Toast.makeText(getApplicationContext(),"Account created",Toast.LENGTH_LONG).show();
    }
    public class BackgroundTask extends AsyncTask<String,Void,String>{
        String regsiter_user="";
        @Override
        protected void onPreExecute() {
            regsiter_user="https://grooviest-recruiter.000webhostapp.com/register_user.php";
        }

        @Override
        protected String doInBackground(String... args) {
            String str,str1,str2,str3;
            str=args[0];
            str1=args[1];
            str2=args[2];
            str3=args[3];
//            Toast.makeText(getApplicationContext(),str+str1+str2+str3,Toast.LENGTH_SHORT).show();
            try {
                URL url =new URL(regsiter_user);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data=URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(str,"UTF-8")+"&"
                        +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(str1,"UTF-8")+"&"+URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(str2,"UTF-8")
                        +"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(str3,"UTF-8");
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

            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

        }
    }
}
