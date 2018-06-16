package com.example.sudip.player;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
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

public class Main2Activity extends AppCompatActivity {
    private DrawerLayout mdrawerlayout;
    private ActionBarDrawerToggle mtoggle;
    private ArrayList<Property> rentalProperties = new ArrayList<>();
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private SliderAdapter sliderAdapter;
    NavigationView navigationView;
    android.support.v4.app.FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mdrawerlayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        mtoggle=new ActionBarDrawerToggle(this,mdrawerlayout,R.string.open,R.string.close);
        mdrawerlayout.addDrawerListener(mtoggle);
        mtoggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       /* mSlideViewPager =(ViewPager)findViewById(R.id.slodeViewPager);
        mDotLayout=(LinearLayout)findViewById(R.id.dotsLayout);
        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);*/
        rentalProperties.add(new Property(01, "Task Name", "Goal ID", "People", "Task Description",  "task", 3, 1, 50, false));
        rentalProperties.add(new Property(02, "Task Name", "Goal ID", "People", "Task Description",  "task", 3, 1, 50, false));
        rentalProperties.add(new Property(03, "Task Name", "Goal ID", "People", "Task Description",  "task", 3, 1, 50, false));
        rentalProperties.add(new Property(04, "Task Name", "Goal ID", "People", "Task Description",  "task", 3, 1, 50, false));
        rentalProperties.add(new Property(05, "Task Name", "Goal ID", "People", "Task Description",  "task", 3, 1, 50, false));
        //create our new array adapter
        ArrayAdapter<Property> adapter = new propertyArrayAdapter(this, 0, rentalProperties);

        //Find list view and bind it with the custom adapter
        ListView listView = (ListView) findViewById(R.id.customListView);
        listView.setAdapter(adapter);
        navigationView=(NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.navtasks:
                        Intent intent = new Intent(Main2Activity.this, Main4Activity.class);
                        startActivity(intent);
                        break;
                    case R.id.settings:
                        Intent intent1=new Intent (Main2Activity.this,Main5Activity.class);
                        startActivity(intent1);

                }


                return false;
            }
        });

        //add event listener so we can handle clicks
        AdapterView.OnItemClickListener adapterViewListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Property property = rentalProperties.get(position);

                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                intent.putExtra("streetNumber", property.getTaskID());
                intent.putExtra("streetName", property.getTaskName());
                intent.putExtra("suburb", property.getGoalID());
                intent.putExtra("state", property.getPeople());
                intent.putExtra("image", property.getImage());

                startActivityForResult(intent, 1000);
            }
        };
        //set the listener to the list view
        listView.setOnItemClickListener(adapterViewListener);


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
    class propertyArrayAdapter extends ArrayAdapter<Property>{

        private Context context;
        private List<Property> rentalProperties;

        //constructor, call on creation
        public propertyArrayAdapter(Context context, int resource, ArrayList<Property> objects) {
            super(context, resource, objects);

            this.context = context;
            this.rentalProperties = objects;
        }

        //called when rendering the list
        public View getView(int position, View convertView, ViewGroup parent) {

            //get the property we are displaying
            Property property = rentalProperties.get(position);

            //get the inflater and inflate the XML layout for each item
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            //conditionally inflate either standard or special template
            View view;
            if(property.getFeatured() == true){
                view = inflater.inflate(R.layout.property_layout, null);
            }else{
                view = inflater.inflate(R.layout.property_layout, null);
            }


            TextView description = (TextView) view.findViewById(R.id.description);
            TextView address = (TextView) view.findViewById(R.id.address);
            TextView bedroom = (TextView) view.findViewById(R.id.bedroom);
            TextView bathroom = (TextView) view.findViewById(R.id.bathroom);
            TextView carspot = (TextView) view.findViewById(R.id.carspot);
            // TextView price = (TextView) view.findViewById(R.id.price);
            ImageView image = (ImageView) view.findViewById(R.id.image);


           /* Typeface custom_font = Typeface.createFromAsset(getAssets(),  "font/abc.otf");

            address.setTypeface(custom_font);*/

            //set address and description
            String completeAddress = property.getTaskID() + " " + property.getTaskName() + ", " + property.getGoalID() + ", " + property.getPeople();
            address.setText(completeAddress);

            //display trimmed excerpt for description
            int descriptionLength = property.getDescription().length();
            if(descriptionLength >= 100){
                String descriptionTrim = property.getDescription().substring(0, 100) + "...";
                description.setText(descriptionTrim);
            }else{
                description.setText(property.getDescription());
            }

            //set price and rental attributes
            //price.setText("$" + String.valueOf(property.getPrice()));
            bedroom.setText("Points: " + String.valueOf(property.getPoints()));
            bathroom.setText("  Time left: " + String.valueOf(property.getTimeleft()));
            carspot.setText("  Completion : " + String.valueOf(property.getCompletion()));


            Typeface custom_font = Typeface.createFromAsset(getAssets(),  "font/abc.otf");

            address.setTypeface(custom_font);
            bedroom.setTypeface(custom_font);
            bathroom.setTypeface(custom_font);
            carspot.setTypeface(custom_font);
            description.setTypeface(custom_font);

            //get the image associated with this property
            int imageID = context.getResources().getIdentifier(property.getImage(), "drawable", context.getPackageName());
            image.setImageResource(imageID);

            return view;
        }
    }

}


