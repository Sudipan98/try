package com.example.sudip.player;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity {
    private DrawerLayout mdrawerlayout;
    private ActionBarDrawerToggle mtoggle;
    private ArrayList<Completed> completed = new ArrayList<>();
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private SliderAdapter sliderAdapter;
    NavigationView navigationView;
    android.support.v4.app.FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        mdrawerlayout=(DrawerLayout)findViewById(R.id.drawer_layout2);
        mtoggle=new ActionBarDrawerToggle(this,mdrawerlayout,R.string.open,R.string.close);
        mdrawerlayout.addDrawerListener(mtoggle);
        mtoggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       /* mSlideViewPager =(ViewPager)findViewById(R.id.slodeViewPager);
        mDotLayout=(LinearLayout)findViewById(R.id.dotsLayout);
        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);*/
        completed.add(new Completed(01,  "Goal ID",3, 1,  false));
        completed.add(new Completed(01,  "Goal ID",3, 1,  false));
        //create our new array adapter
        ArrayAdapter<Completed> adapter;
        adapter = new completedArrayAdapter(this, 0, completed);

        //Find list view and bind it with the custom adapter
        ListView listView = (ListView) findViewById(R.id.customListView2);
        listView.setAdapter(adapter);
        navigationView=(NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.navtasks:
                        Intent intent = new Intent(Main4Activity.this, Main4Activity.class);
                        startActivity(intent);
                        break;

                }


                return false;
            }
        });

        //add event listener so we can handle clicks
      /*  AdapterView.OnItemClickListener adapterViewListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Property property = rentalProperties.get(position);

                Intent intent = new Intent(Main4Activity.this, Main3Activity.class);
                intent.putExtra("streetNumber", property.getTaskID());
                intent.putExtra("streetName", property.getTaskName());
                intent.putExtra("suburb", property.getGoalID());
                intent.putExtra("state", property.getPeople());
                intent.putExtra("image", property.getImage());

                startActivityForResult(intent, 1000);
            }
        };
        //set the listener to the list view
        listView.setOnItemClickListener(adapterViewListener);*/


        //fragmentTransaction=getSupportFragmentManager().beginTransaction();
        //fragmentTransaction.add(R.id.)


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mtoggle.onOptionsItemSelected(item))
            return true;



        return super.onOptionsItemSelected(item);

    }

    /*public void onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {

            case R.id.navworkpanel: {
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                break;
            }
        }
        //close navigation drawer
        mdrawerlayout.closeDrawer(GravityCompat.START);

    }*/


    //custom ArrayAdapater
    class completedArrayAdapter extends ArrayAdapter<Completed>{

        private Context context;
        private List<Completed> completed;

        //constructor, call on creation
        public completedArrayAdapter(Context context, int resource, ArrayList<Completed> objects) {
            super(context, resource, objects);

            this.context = context;
            this.completed = objects;
        }

        //called when rendering the list
        public View getView(int position, View convertView, ViewGroup parent) {

            //get the property we are displaying
            Completed complete = completed.get(position);

            //get the inflater and inflate the XML layout for each item
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            //conditionally inflate either standard or special template
            View view;
            if(complete.getFeatured() == true){
                view = inflater.inflate(R.layout.completed_layout, null);
            }else{
                view = inflater.inflate(R.layout.completed_layout, null);
            }


            //TextView description = (TextView) view.findViewById(R.id.description);
            TextView address = (TextView) view.findViewById(R.id.address);
            TextView bedroom = (TextView) view.findViewById(R.id.bedroom);
            TextView bathroom = (TextView) view.findViewById(R.id.bathroom);
            //TextView carspot = (TextView) view.findViewById(R.id.carspot);
            // TextView price = (TextView) view.findViewById(R.id.price);
            ImageView image = (ImageView) view.findViewById(R.id.image);


           /* Typeface custom_font = Typeface.createFromAsset(getAssets(),  "font/abc.otf");

            address.setTypeface(custom_font);*/

            //set address and description
            String completeAddress = complete.getTaskID() + " " +  complete.getGoalID() + " " ;
            address.setText(completeAddress);

            //display trimmed excerpt for description
            /*int descriptionLength = property.getDescription().length();
            if(descriptionLength >= 100){
                String descriptionTrim = property.getDescription().substring(0, 100) + "...";
                description.setText(descriptionTrim);
            }else{
                description.setText(property.getDescription());
            }*/

            //set price and rental attributes
            //price.setText("$" + String.valueOf(property.getPrice()));
            bedroom.setText("Points: " + String.valueOf(complete.getPoints()));
            bathroom.setText("  Man hours : " + String.valueOf(complete.getTimeleft()));


            Typeface custom_font = Typeface.createFromAsset(getAssets(),  "font/abc.otf");

            address.setTypeface(custom_font);
            bedroom.setTypeface(custom_font);
            bathroom.setTypeface(custom_font);
          //  carspot.setTypeface(custom_font);
            //description.setTypeface(custom_font);

            //get the image associated with this property
            /*int imageID = context.getResources().getIdentifier(property.getImage(), "drawable", context.getPackageName());
            image.setImageResource(imageID);*/

            return view;
        }
    }

}

