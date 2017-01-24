package com.example.charl.bandapp;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayTotalActivity extends Activity {
    MediaPlayer musicPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_display_total);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String band_string = extras.getString("BandName");
        String ticket_string = extras.getString("Total");
        String first_string = extras.getString("FirstName");
        String last_string = extras.getString("LastName");
        String email_string = extras.getString("emailAdd");
        ArrayList<String> snacks_list = extras.getStringArrayList("Snacks");
        String pay_string = extras.getString("payMethod");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, snacks_list);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        TextView textView =(TextView) findViewById(R.id.totalView);
        TextView snackView = (TextView) findViewById(R.id.snack_list);

        StringBuilder b = new StringBuilder();
        for (String s : snacks_list){
            b.append(s+"\n");
        }

        snackView.setText(b.toString());


        // ListView listView = (ListView) findViewById(R.id.snack_list);
       // listView.setAdapter(adapter);





        textView.setText("Total Cost: " + ticket_string + "\n" + "Band: " + band_string
        +"\n" + " " + "\n" + "First Name: " + first_string + "\n" + "Last Name: " + last_string + "\n"
        + "E-Mail Address: " + email_string + "\n" + "Payment Method: " +pay_string + "\n" + "\n" + "Snacks: ");





        if (band_string.equals("Blink 182")) {
            musicPlayer = MediaPlayer.create(this, R.raw.bored);
            musicPlayer.start();
            imageView.setImageResource(R.drawable.blink182);
        } else if (band_string.equals("Haken")) {
            musicPlayer = MediaPlayer.create(this, R.raw.earthrise);
            musicPlayer.start();
            imageView.setImageResource(R.drawable.haken);
        } else if (band_string.equals("Radiohead")) {
            musicPlayer = MediaPlayer.create(this, R.raw.paranoid);
            musicPlayer.start();
            imageView.setImageResource(R.drawable.rhead);
        }

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_total);

    }

    public void onClick(View v) {
        try {
            musicPlayer.reset();
            musicPlayer.prepare();
            musicPlayer.stop();
            musicPlayer.release();
            musicPlayer = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBack(View v) {
        this.finish();
        TextView snackView = (TextView) findViewById(R.id.snack_list);
        snackView.setText(" ");
        try {
            musicPlayer.reset();
            musicPlayer.prepare();
            musicPlayer.stop();
            musicPlayer.release();
            musicPlayer = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

