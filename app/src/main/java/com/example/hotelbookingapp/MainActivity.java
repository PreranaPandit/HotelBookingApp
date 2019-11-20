package com.example.hotelbookingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //Referencing
    private Spinner spinLocation;
    private Spinner spinRoomType;
    private TextView tvCheckInDate;
    private TextView tvCheckOutDate;
    private EditText etAdults, etChildren, etRoom;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Binding
        spinLocation = (Spinner) findViewById(R.id.spinLocation);
        spinRoomType = (Spinner) findViewById(R.id.spinRoomType);
        tvCheckInDate = (TextView) findViewById(R.id.tvCheckInDate);
        tvCheckOutDate = (TextView) findViewById(R.id.tvCheckOutDate);
        etAdults = (EditText) findViewById(R.id.etAdults);
        etChildren = (EditText) findViewById(R.id.etChildren);
        etRoom = (EditText) findViewById(R.id.etRoom);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);

        //passing an array to the location in spinner
        String location[] = {"Bhaktapur","Pokhara","Chitwan"};
        ArrayAdapter adapter = new ArrayAdapter<>
                (
                        this,
                        android.R.layout.simple_list_item_1, location
                );
        spinLocation.setAdapter(adapter);

        //passing an array to the room type in spinner
        String roomType[] = {"Deluxe","AC","Platinum"};
        ArrayAdapter adapterRoom = new ArrayAdapter<>
                (
                        this,
                        android.R.layout.simple_list_item_1, roomType
                );
        spinRoomType.setAdapter(adapterRoom);

        //setting OnClick Listener on check in date
        tvCheckInDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            loadCalendar();
            }
        });

        //setting Onclick listener on check out date
        tvCheckOutDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             loadCheckOutCalendar();
            }
        });


        //button mode

        //validation should be done at first

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }


    private void loadCalendar()
    {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = "CheckIn Date : " +year +"/"+month+"/"+dayOfMonth;
                tvCheckInDate.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    private void loadCheckOutCalendar()
    {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = "CheckOut Date : " +year +"/"+month+"/"+dayOfMonth;
                tvCheckOutDate.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();
    }


    }

