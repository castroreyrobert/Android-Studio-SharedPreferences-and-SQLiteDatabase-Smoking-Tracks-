package com.example.castroreyrobert.sharedpreferencesdemo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView tvDate, tvSticks;
    String sticks_pref;
    int sticks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button buttonAdd = (Button)findViewById(R.id.buttonAdd);
        Button buttonSubtract = (Button)findViewById(R.id.buttonSubtract);
        tvSticks = (TextView)findViewById(R.id.tvSticks);
        tvDate = (TextView)findViewById(R.id.tvDate);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //DATE PROPERTIES
        //Get or Generate Date
        Date todayDate = new Date();

        //Get an instance of the formatter
       // DateFormat dateFormat = DateFormat.getDateTimeInstance();

        //If you want to show only the date then you will use
         DateFormat dateFormat = DateFormat.getDateInstance();

        //Format date
        final String todayDateTimeString = dateFormat.format(todayDate);
        tvDate.setText(todayDateTimeString);

        final SharedPreferences pref = getSharedPreferences("my_pref", MODE_PRIVATE);

        final String sticks_pref = pref.getString(todayDateTimeString, "0");

        sticks = Integer.parseInt(sticks_pref);

        tvSticks.setText(String.valueOf(sticks_pref));


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sticks ++;

                SharedPreferences.Editor editor = pref.edit();
                editor.putString(todayDateTimeString, String.valueOf(sticks));
                editor.commit();

                tvSticks.setText(sticks + "");
            }
        });

        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sticks == 0 ){
                    Toast.makeText(MainActivity.this, "You haven't smoked today!", Toast.LENGTH_SHORT).show();
                }else {
                    sticks --;

                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString(todayDateTimeString, String.valueOf(sticks));
                    editor.commit();

                    tvSticks.setText(sticks + "");
                }
            }
        });
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
