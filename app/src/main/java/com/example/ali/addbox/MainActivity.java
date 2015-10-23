package com.example.ali.addbox;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RelativeLayout containerLayout;
    Button button;
    static int totalEditTexts = 0;
    List<Boxes> boxes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        containerLayout = (RelativeLayout)findViewById(R.id.Test);
       // button = (Button)findViewById(R.id.button);

        //boxes =  new ArrayList<Boxes>();
        //Boxes b = new Boxes();
       // b.setid(1);
       // b.setvalue("first");
       // boxes.add(b);
        try {
            XMLPullParserHandler parser = new XMLPullParserHandler();
           boxes = parser.parse(getAssets().open("Box.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }


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

    public void onClick(View v) {

        if (totalEditTexts > 100)
            return;

        //TextView editText = new EditText(getBaseContext());
        TextView editText = new TextView(getBaseContext());
        editText.setText(String.valueOf(totalEditTexts));
        editText.setTextColor(Color.rgb(200, 0, 0));
        editText.setBackgroundColor((Color.rgb(0, 255, 255)));
        editText.setWidth(60);
        editText.setHeight(50);
        editText.setX(totalEditTexts * 62);
        editText.setY(0);
        editText.setId(totalEditTexts);
        totalEditTexts++;

        //TextView textView = new TextView(getBaseContext());
        //textView.setText(String.valueOf(totalEditTexts));
        //EditText editText = new EditText(getBaseContext());
        containerLayout.addView(editText);
        //setContentView(containerLayout);

       // RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) editText.getLayoutParams();
       // layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT;
      //  layoutParams.setMargins(23, 34, 0, 0);
        // RelativeLayout.LayoutParams()
       // editText.setLayoutParams(layoutParams);
      //  //if you want to identify the created editTexts, set a tag, like below
       // editText.setTag("EditText" + totalEditTexts);


    }
    public void onClick2(View v) {

        for (Boxes box : boxes) {
            if (totalEditTexts > 100)
                return;

            //TextView editText = new EditText(getBaseContext());
            TextView editText = new TextView(getBaseContext());
            editText.setText(box.getvalue());
            editText.setTextColor(Color.rgb(200, 0, 0));
            editText.setBackgroundColor((Color.rgb(0, 255, 255)));
            editText.setWidth(200);
            editText.setHeight(100);
            editText.setX(box.getid() * 210);
            editText.setY(0);
            editText.setId(box.getid());
            totalEditTexts++;
            containerLayout.addView(editText);
        }

    }
    public void onClick3(View v) {

        if(!boxes.isEmpty()) {
            TextView res = (TextView) findViewById(R.id.textView);
            res.setText(String.valueOf(boxes.size()));
        }

    }


}
