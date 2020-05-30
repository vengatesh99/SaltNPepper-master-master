package com.example.vishal.saltnpepper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class location extends AppCompatActivity {

     private static final String[] place=new String[]{"Pelamedu","RCR","VCRoad","anna nagar east","mogappair east","mogappair west","padi","nungambakkam","besant nagar","adayar","avadi","mandavalli","purasaiwakkam","valasaravakkam","virungambakkam","vadapalani","velacheri","shenoy nagar","arumbakkam"};
    AutoCompleteTextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
         t=findViewById(R.id.place);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,place);

        t.setAdapter(adapter);

        t.addTextChangedListener(new TextWatcher() {

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
                processButtonBysearch();
            }
        });
    }

    private void processButtonBysearch() {

        String input= t.getText().toString();
        for(int i=0;i<place.length;i++)
        {
             if(input.equals(place[i]))
             {
                 Intent i1=new Intent(this,delivloc.class);
                 Bundle bundle=new Bundle();
                 bundle.putString("location",input);
                 i1.putExtras(bundle);
                 startActivity(i1);
             }
        }
    }

    public void gpss(View view) {

        Intent i=new Intent(this,GPS.class);
        startActivity(i);
    }
}
