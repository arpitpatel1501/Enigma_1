package com.example.intel.testapplicationhackthon;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


     TextView txtSchool ;
    TextView txtClass ;
    TextView txtLibrary ;
    TextView txtBus ;

    public ArrayList<HashMap<String, String>> PlaceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView list = (ListView) findViewById(R.id.list);
        PlaceList = new ArrayList<HashMap<String, String>>();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        new GetStudentPlace(HomePage.this,list).execute("http://10.20.37.126/Service1.svc/GetPlace");


        txtBus = (TextView) findViewById(R.id.txtBus);
        txtClass = (TextView)findViewById(R.id.txtClass);
        txtLibrary = (TextView)findViewById(R.id.txtLibrary);
        txtSchool = (TextView)findViewById(R.id.txtSchool);

        setTitle("Home Page");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }





    ////////new classsss
    class GetStudentPlace extends AsyncTask<String, Void ,String> {

        String status= null;
        Activity context;
        ListView listView;

        public GetStudentPlace(Activity context, ListView listView){
            this.context =context;
            this.listView=listView;
        }
        protected void onPreExecute(){

        }
        protected String doInBackground(String... connUrl){
            HttpURLConnection conn=null;
            BufferedReader reader;

            try{
                final URL url=new URL(connUrl[0]);
                conn=(HttpURLConnection) url.openConnection();
                conn.addRequestProperty("Content-Type", "application/json; charset=utf-8");
                conn.setRequestMethod("GET");
                int result = conn.getResponseCode();
                if(result==200){

                    InputStream in=new BufferedInputStream(conn.getInputStream());
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder sb=new StringBuilder();
                    String line = null;

                    while((line=reader.readLine())!=null){
                        status=line;
                    }

                }


            }catch(Exception ex){
                ex.printStackTrace();
            }
            return status;
        }
        protected void onPostExecute(String result){
            super.onPostExecute(result);


            if(result!=null){
                try{

                    ArrayList<String> stringArrayList = new ArrayList<String>();
                    JSONArray jsonArray = new JSONArray(result);
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String Location= object.getString("Location");

                        HashMap<String, String> itemList = new HashMap<String, String>();
                        itemList.put("Student",Location);

                        PlaceList.add(itemList);
                    }
                   // SimpleAdapter adapter = new SimpleAdapter(HomePage.this, PlaceList,R.layout.studentlist, new String[]{"Student"},new int[]{R.id.txtStudent});
                    //((AdapterView<ListAdapter>) listView).setAdapter(adapter);


                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }else{
                Toast.makeText(HomePage.this,"Could not get any data.", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_StdDetail) {
            // Handle the camera action
        } else if (id == R.id.nav_StdTrack) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
