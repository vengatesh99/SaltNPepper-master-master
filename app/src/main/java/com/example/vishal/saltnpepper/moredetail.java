package com.example.vishal.saltnpepper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class moredetail extends AppCompatActivity {

    EditText t1,t2,t3;
    Button b;
    String str2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moredetail);
        t1=(EditText)findViewById(R.id.location);
        t2=(EditText)findViewById(R.id.house);
        t3=(EditText)findViewById(R.id.landm);

        b=(Button)findViewById(R.id.button3);
        b.setEnabled(false);
        Intent intent = getIntent();

        Bundle bb = getIntent().getExtras();

        str2 = bb.getString("location");

        t1.setText(str2);
        t1.setEnabled(false);

        t2.addTextChangedListener(new TextWatcher() {

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
                processButtonByhouse();
            }
        });

        t3.addTextChangedListener(new TextWatcher() {

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
                processButtonByLAND();
            }
        });
    }

    private void processButtonByLAND() {

        if(t2.length()>0&&t3.length()==0)
        {
            b.setEnabled(false);
            b.setText("Enter landmark");
        }

        else if(t2.length()>0&&t3.length()>0)
        {
            b.setEnabled(true);
            b.setText("SAVE AND CONTINUE");
        }
        else if(t2.length()==0&&t3.length()>0)
        {
            b.setEnabled(false);
            b.setText("ENTER HOUSE NO.");
        }

    }

    private void processButtonByhouse() {

        if(t2.length()>0&&t3.length()==0)
        {
            b.setEnabled(false);
            b.setText("Enter landmark");
        }

        else if(t2.length()>0&&t3.length()>0)
        {
            b.setEnabled(true);
            b.setText("SAVE AND CONTINUE");
        }

    }

    public void locset(View view) {

        Toast.makeText(getApplicationContext(),"Delievery address set",Toast.LENGTH_LONG).show();
        Intent i=new Intent(this,dashboard.class);
        Bundle bundle=new Bundle();
        bundle.putString("location",str2);
        i.putExtras(bundle);
        startActivity(i);

    }
}
