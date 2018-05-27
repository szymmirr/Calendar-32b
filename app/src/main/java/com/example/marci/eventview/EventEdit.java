package com.example.marci.eventview;

import android.app.FragmentTransaction;
import android.content.Context;
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

public class EventEdit extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText txtdate,txttime,txtaddress,txtcalendar,txtnote;
    Button btnAddData;
    Button btnviewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();

                //nazwa
                String nazwa = ""; // dodać pole nazwy wydarzenia

                //typZadania
                Event.TypZadania typZadania = Event.TypZadania.valueOf("pole z listy rozwijanej"); //Dodaj pole listy rozwijanej z polami klasy event dla enum typZadania

                //priorytet
                Event.Priorytet priorytet = Event.Priorytet.valueOf("pole z listy rozwijanej"); //Dodaj pole listy rozwijanej z polami klasy event dla enum priorytet

                //nazwa kalendarza
                EditText calendarNameEvent = (EditText) findViewById(R.id.txtcalendar);
                //txtcalendar = calendarNameEvent;
                String nazwaKalendarz = calendarNameEvent.getText().toString();

                //data wydarzenia
                EditText dateEvent = (EditText) findViewById(R.id.txtdate);
                //txtdate = dateEvent;
                String data = dateEvent.getText().toString();

                //godzina początku wydarzenia
                EditText timeStart = (EditText) findViewById(R.id.txttime);
                //txttime = timeStart;
                String godzina_od = timeStart.getText().toString();

                //godzina końca wydarzenia
                EditText timeStop = (EditText) findViewById(R.id.txttime);
                //txttime = timeStart;
                String godzina_do = timeStop.getText().toString();

                //notatka do wydarzenia
                EditText noteEvent = (EditText) findViewById(R.id.txtnote);
                //txtnote = timeStart;
                String notatka = noteEvent.getText().toString();

                //robisz obiekt z wszytskimi danymi powyższymi
                Event wydarzenie1 = new Event(nazwa, typZadania, priorytet, data, godzina_od, godzina_do, notatka);

                CharSequence text = "Zmiany w wydarzeniu zapisane!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                finish();
            }
        });
    }

    public void onStart(){
        super.onStart();

        EditText txtDate=(EditText)findViewById(R.id.txtdate);
        txtDate.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View view, boolean hasfocus){
                if(hasfocus){
                    DateDialog dialog=new DateDialog(view);
                    FragmentTransaction ft =getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");

                }
            }

        });

        EditText txtTime=(EditText)findViewById(R.id.txttime);
        txtTime.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View view, boolean hasfocus){
                if(hasfocus){
                    TimeDialog dialog=new TimeDialog(view);
                    FragmentTransaction ft =getFragmentManager().beginTransaction();
                    dialog.show(ft, "TimePicker");

                }
            }

        });


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
                            Toast.makeText(EventEdit.this,"Dodano wydarzenie",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(EventEdit.this,"Data not Inserted",Toast.LENGTH_LONG).show();
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
                            buffer.append("Data:"+ res.getString(1)+"\n");
                            buffer.append("Czas:"+ res.getString(2)+"\n");
                            buffer.append("Adres:"+ res.getString(3)+"\n");
                            buffer.append("Kalendarz:"+ res.getString(4)+"\n");
                            buffer.append("Notatka:"+ res.getString(5)+"\n\n");
                        }

                        // Show all data
                        showMessage("Kalendarz",buffer.toString());

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

}