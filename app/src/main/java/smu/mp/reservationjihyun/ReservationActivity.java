package smu.mp.reservationjihyun;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class ReservationActivity extends AppCompatActivity {
    private Spinner people_spinner;
    private Button btn_seat;
    private TextView departure_date, arrival_date, arrival_country;
    private CalendarView calendarView;

    int y=0, m=0, d=0, y2=0, m2=0, d2=0;
    String d_date, a_date, country, s_people;
    int people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        Integer[] nationName = {R.string.nation1, R.string.nation2, R.string.nation3,
                R.string.nation4, R.string.nation5, R.string.nation6};

        arrival_country = (TextView)findViewById(R.id.arrival_country);
        people_spinner = (Spinner)findViewById(R.id.people);
        btn_seat = (Button)findViewById(R.id.btn_seat);
        departure_date = (TextView)findViewById(R.id.departure_date);
        arrival_date = (TextView)findViewById(R.id.arrival_date);

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());

        String year = yearFormat.format(currentTime);
        String month = monthFormat.format(currentTime);
        String day = dayFormat.format(currentTime);

//        departure_date.setText(year + "-" + month + "-" + day + "");
//        arrival_date.setText(year + "-" + month + "-" + day + "");

        Intent intent = getIntent();
        country = intent.getStringExtra("country");
        arrival_country.setText(country);


        departure_date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showDate();

            }
        });

        arrival_date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showDate2();

            }
        });

        people_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_seat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_people = people_spinner.getSelectedItem().toString();
                people = Integer.parseInt(s_people);

                Intent intent = new Intent(getApplicationContext(),SelectSeatActivity.class);
                intent.putExtra("people", people);
                intent.putExtra("country", country);
                intent.putExtra("d_date", d_date);
                intent.putExtra("a_date", a_date);
                startActivity(intent);
            }
        });
    }

    void showDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                y = year;
                m = month+1;
                d = dayOfMonth;
                d_date = String.valueOf(y) + String.valueOf(m) + String.valueOf(d);
                departure_date.setText(String.valueOf(y)+"-"+String.valueOf(m)+"-"+String.valueOf(d));
            }
        },2019, 11, 3);

        datePickerDialog.setMessage("출발 날짜 선택");
        datePickerDialog.show();
    }

    void showDate2() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                y2 = year;
                m2 = month+1;
                d2= dayOfMonth;
                a_date = String.valueOf(y2) + String.valueOf(m2) + String.valueOf(d2);
                arrival_date.setText(String.valueOf(y2)+"-"+String.valueOf(m2)+"-"+String.valueOf(d2));
            }
        },2019, 11, 3);

        datePickerDialog.setMessage("도착 날짜 선택");
        datePickerDialog.show();
    }

}
