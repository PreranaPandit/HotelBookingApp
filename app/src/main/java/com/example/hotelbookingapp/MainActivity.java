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

    TextView tvLocation, tvRoomType, tvInDate, tvOutDate, tvAdults, tvChildren, tvRoom, tvSErvice, tvTax, tvTotal;

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

        //scroll view outputs data binding

        tvLocation = (TextView) findViewById(R.id.tvLocation);
        tvRoomType = (TextView) findViewById(R.id.tvRoomType);
        tvInDate = (TextView) findViewById(R.id.tvInDate);
        tvOutDate = (TextView) findViewById(R.id.tvOutDate);
        tvAdults = (TextView) findViewById(R.id.tvAdults);
        tvChildren = (TextView) findViewById(R.id.tvChildren);
        tvRoom = (TextView) findViewById(R.id.tvRoom);
        tvSErvice = (TextView) findViewById(R.id.tvService);
        tvTax = (TextView) findViewById(R.id.tvTax);
        tvTotal = (TextView) findViewById(R.id.tvTotal);


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

                tvLocation.setText("Location : "+spinLocation.getSelectedItem().toString());
                tvRoomType.setText("Room Type : "+spinRoomType.getSelectedItem().toString());
                tvInDate.setText(tvCheckInDate.getText().toString());
                tvOutDate.setText(tvCheckOutDate.getText().toString());
                tvAdults.setText("Number of Adults : "+etAdults.getText().toString());
                tvChildren.setText("NUmber of Children : "+etChildren.getText().toString());
                tvRoom.setText("NUmber of Rooms : "+etRoom.getText().toString());

                

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

