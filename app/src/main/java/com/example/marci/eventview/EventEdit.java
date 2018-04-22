package com.example.marci.eventview;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EventEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                EditText calendarNameEvent = (EditText) findViewById(R.id.editText5);
                String nazwaKalendarz = calendarNameEvent.getText().toString();

                //data wydarzenia
                EditText dateEvent = (EditText) findViewById(R.id.txtdate);
                String data = dateEvent.getText().toString();

                //godzina początku wydarzenia
                EditText timeStart = (EditText) findViewById(R.id.txttime);
                String godzina_od = timeStart.getText().toString();

                //godzina końca wydarzenia
                EditText timeStop = (EditText) findViewById(R.id.txttime);
                String godzina_do = timeStop.getText().toString();

                //notatka do wydarzenia
                EditText noteEvent = (EditText) findViewById(R.id.editText6);
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

}
