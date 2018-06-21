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
import android.util.Log;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private String playerId;
    android.support.v4.app.FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mdrawerlayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        mtoggle=new ActionBarDrawerToggle(this,mdrawerlayout,R.string.open,R.string.close);
        mdrawerlayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        Bundle extras = getIntent().getExtras();  //used to share data b/w activities
         playerId = extras.getString("Player ID");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addTasks();

       /* mSlideViewPager =(ViewPager)findViewById(R.id.slodeViewPager);
        mDotLayout=(LinearLayout)findViewById(R.id.dotsLayout);
        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);*/
        //rentalProperties.add(new Property(01, "Task Name", "Goal ID", "People", "Task Description",  "task", 3, 1, 50, false));
        //rentalProperties.add(new Property(02, "Task Name", "Goal ID", "People", "Task Description",  "task", 3, 1, 50, false));
        //rentalProperties.add(new Property(03, "Task Name", "Goal ID", "People", "Task Description",  "task", 3, 1, 50, false));
        //rentalProperties.add(new Property(04, "Task Name", "Goal ID", "People", "Task Description",  "task", 3, 1, 50, false));
        //rentalProperties.add(new Property(05, "Task Name", "Goal ID", "People", "Task Description",  "task", 3, 1, 50, false));
        //create our new array adapter
        //ArrayAdapter<Property> adapter;
        //adapter = new propertyArrayAdapter(this, 0, rentalProperties);

        //Find list view and bind it with the custom adapter
        ListView listView = (ListView) findViewById(R.id.customListView);
        //listView.setAdapter(adapter);
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
                        break;
                    case R.id.navalltasks:
                        Intent intent2=new Intent (Main2Activity.this,Main2Activity.class);
                        intent2.putExtra("Player ID",playerId);
                        startActivity(intent2);

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
                //intent.putExtra("state", property.getPeople());
                intent.putExtra("image", property.getImage());

                startActivityForResult(intent, 1000);
            }
        };
        //set the listener to the list view
        listView.setOnItemClickListener(adapterViewListener);


        //fragmentTransaction=getSupportFragmentManager().beginTransaction();
        //fragmentTransaction.add(R.id.)


    }
    public void addTasks(){

        //String BASE_URL = "http://192.168.0.104/task/";
        String BASE_URL = api.BASE_TASK_URL;

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        //Player playerObj = new Player(account.getId(),account.getGivenName(),account.getEmail(),"male","tisl",account.getDisplayName(),"task_id","1/1/1");
        StringRequest sr = new StringRequest(Request.Method.GET, BASE_URL+"allTasks/1/1/"+playerId,
                new Response.Listener<String>() {

                    public void onResponse(String response) {
                        Log.e("HttpClient", "success! response: " + response.toString());

                        try {
                            JSONArray mJsonArray = new JSONArray(response);
                            int arrayLen = mJsonArray.length();
                            for(int i=0;i<arrayLen;i++){
                                JSONObject obj = mJsonArray.getJSONObject(i);
                                JSONObject taskObj = obj.getJSONObject("task");
                                Log.d(" task obj ",obj.toString());
                                String success= obj.getString("success");
                                int state = Integer.parseInt(obj.getString("state"));
                                Log.d(" success", success);
                                Log.d(" task Id ", taskObj.getString("taskId").toString());
                                if(success.equals("true")){
                                    if(state==1){
                                        rentalProperties.add(new Property(Integer.parseInt(taskObj.getString("taskId")), taskObj.getString("taskName"), 123456,taskObj.getString("gitRipoLink"), taskObj.getString("taskDes"),  taskObj.getString("taskImage"), Integer.parseInt(taskObj.getString("Points")), Integer.parseInt(taskObj.getString("timeLimit")), 50, true));
                                    }else if(state==0 ){
                                        rentalProperties.add(new Property(Integer.parseInt(taskObj.getString("taskId")), taskObj.getString("taskName"), 123456, taskObj.getString("gitRipoLink"), taskObj.getString("taskDes"), taskObj.getString("taskImage"), Integer.parseInt(taskObj.getString("Points")), Integer.parseInt(taskObj.getString("timeLimit")), 50, false));
                                    }else{

                                    }
                                }else{
                                    rentalProperties.add(new Property(Integer.parseInt(taskObj.getString("taskId")), taskObj.getString("taskName"), 123456,taskObj.getString("gitRipoLink"), taskObj.getString("taskDes"),  taskObj.getString("taskImage"), Integer.parseInt(taskObj.getString("Points")), Integer.parseInt(taskObj.getString("timeLimit")), 50, false));
                                }


                            }
                            Log.d(" end loop "," end ");
                            if(rentalProperties.isEmpty()){
                                Log.d(" empty rentalProp ","empty ");
                            }else{
                                Log.d(" rentalProp "," not empty ");
                                ArrayAdapter<Property> adapter = new propertyArrayAdapter(getApplicationContext(), 0, rentalProperties);

                                //Find list view and bind it with the custom adapter
                                ListView listView = (ListView) findViewById(R.id.customListView);
                                listView.setAdapter(adapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        Log.e("HttpClient", "error: " + error.toString());

                    }
                });
        queue.add(sr);
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
                view = inflater.inflate(R.layout.featured_layout, null);
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
            String completeAddress = property.getTaskID() + " " + property.getTaskName() + ", " + property.getGoalID()  ;
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