package com.example.marci.eventview;
import java.sql.Time;
import java.time.Clock;
import java.util.Calendar;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

@SuppressLint("ValidFragment")
public class TimeDialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    EditText txttime;
    public TimeDialog(View view){
        txttime=(EditText)view;
    }
    public Dialog onCreateDialog(Bundle savedInstanceState) {


// Use the current date as the default date in the dialog
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        // Create a new instance of DatePickerDialog and return it
        return new TimePickerDialog(getActivity(),this,hour,min,true);


    }

    public void onTimeSet(TimePicker view, int hour, int min) {
        //show to the selected date in the text box
        String time=hour+":"+min;
        txttime.setText(time);
    }

}
