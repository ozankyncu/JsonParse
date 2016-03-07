package com.ozankyncu.json;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String url="http://www.json-generator.com/api/json/get/chmFcvaQKq?indent=2";
    TextView txt_isim,txt_yas,txt_aisim,txt_ayas;
    Button parse;
    private JSONHandler obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txt_isim=(TextView)findViewById(R.id.txt_isim);
        txt_yas=(TextView)findViewById(R.id.txt_yas);
        txt_aisim=(TextView)findViewById(R.id.txt_aisim);
        txt_ayas=(TextView)findViewById(R.id.txt_ayas);
        parse=(Button)findViewById(R.id.btn_parse);
        parse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj=new JSONHandler(url);
                obj.JSONAl();
                while (obj.parseEtmeTamamlandi);
                txt_isim.setText(obj.getIsim());
                txt_aisim.setText(obj.getArkadasIsmi());
                txt_yas.setText(obj.getYas());
                txt_ayas.setText(obj.getArkadasYas());
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
