package com.example.sudip.player;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Main5Activity extends AppCompatActivity {

    TextView t1;
    private DrawerLayout mdrawerlayout;
    private ActionBarDrawerToggle mtoggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        t1=(TextView)findViewById(R.id.textView14);
        mdrawerlayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        mtoggle=new ActionBarDrawerToggle(this,mdrawerlayout,R.string.open,R.string.close);
        mdrawerlayout.addDrawerListener(mtoggle);
        mtoggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main5Activity.this,Main6Activity.class);
                startActivity(intent);
            }
        });

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mtoggle.onOptionsItemSelected(item))
            return true;



        return super.onOptionsItemSelected(item);

    }

    /*public void onClick(View view)
    {
       // Intent intent=new Intent(Main5Activity.this,Main6Activity.class);


    }*/
}
