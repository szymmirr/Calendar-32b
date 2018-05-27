package com.example.marci.eventview;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.os.Bundle;

import android.app.AlertDialog;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EventView extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText txtdate,txttime,txtaddress,txtcalendar,txtnote;
    Button btnAddData;
    Button btnviewAll;
    Button btnDelete;
    Button btnviewUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDb = new DatabaseHelper(this);

        txtdate = (EditText)findViewById(R.id.txtdate);
        txttime = (EditText)findViewById(R.id.txttime);
        txtaddress = (EditText)findViewById(R.id.txtaddress);
        txtcalendar = (EditText)findViewById(R.id.txtcalendar);
        txtnote = (EditText)findViewById(R.id.txtnote);

        btnAddData = (Button)findViewById(R.id.button_add);
        btnviewAll = (Button)findViewById(R.id.button_viewAll);
        AddData();
        viewAll();
    }

    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(txtdate.getText().toString(),
                                txttime.getText().toString(),
                                txtaddress.getText().toString(),
                                txtcalendar.getText().toString(),
                                txtnote.getText().toString() );
                        if(isInserted == true)
                            Toast.makeText(EventView.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(EventView.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id:"+ res.getString(0)+"\n");
                            buffer.append("Date:"+ res.getString(1)+"\n");
                            buffer.append("Time:"+ res.getString(2)+"\n");
                            buffer.append("Address:"+ res.getString(3)+"\n");
                            buffer.append("Calendar:"+ res.getString(4)+"\n");
                            buffer.append("Note:"+ res.getString(8)+"\n\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void sendMessage(View view) {
        Intent i = new Intent(this, EventEdit.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_view, menu);
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