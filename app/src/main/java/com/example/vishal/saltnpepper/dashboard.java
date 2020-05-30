package com.example.vishal.saltnpepper;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class dashboard extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView navigation;
    private FrameLayout mainframe;

   private nearmefrag nf;
   private searchfrag sf;
   private cartfrag cf;
   private accfrag af;
   String str2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

       // Toast.makeText(getApplicationContext(),"Inside Dashboard",Toast.LENGTH_SHORT).show();

        Intent intent = getIntent();

        Bundle bb = getIntent().getExtras();

        str2 = bb.getString("location");

        Bundle newb=new Bundle();
        newb.putString("location",str2);

        mTextMessage = (TextView) findViewById(R.id.message);
         navigation = (BottomNavigationView) findViewById(R.id.navigation);
         mainframe=(FrameLayout)findViewById(R.id.frame);

         nf=new nearmefrag();
         nf.setArguments(newb);

         sf=new searchfrag();
         cf=new cartfrag();
         af=new accfrag();

         setfragment(nf);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {

                    case R.id.nav_near :
                        navigation.setItemBackgroundResource(R.color.Orange);
                        setfragment(nf);
                        return true;

                    case R.id.nav_search:
                        navigation.setItemBackgroundResource(R.color.Orange);
                        setfragment(sf);
                        return true;

                    case R.id.nav_cartt:
                        navigation.setItemBackgroundResource(R.color.Orange);
                        setfragment(cf);
                        return true;

                    case R.id.nav_accountt:
                        navigation.setItemBackgroundResource(R.color.Orange);
                        setfragment(af);
                        return true;


                    default:
                        return false;
                }
            }
        });
    }

    private void setfragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();

    }

}
