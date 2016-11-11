package com.arjunalabs.android.recyclerparallax;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ParallaxRecyclerView recyclerView;
    ArrayList<ParallaxModel> parallaxModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create dummy data
        parallaxModelArrayList = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            ParallaxModel parallaxModel = new ParallaxModel();
            parallaxModel.setTitle("Row" + i);
            parallaxModelArrayList.add(parallaxModel);
        }

        // prepare the recyclerview
        recyclerView = (ParallaxRecyclerView) findViewById(R.id.recyclerview);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // do the adapter
        ParallaxAdapter parallaxAdapter = new ParallaxAdapter(parallaxModelArrayList, recyclerView.getHeight(), recyclerView.getY());
        // bind the adapter with the recyclerview
        recyclerView.setAdapter(parallaxAdapter);

        recyclerView.setupParallax(this, parallaxAdapter.getTranslationScale());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
