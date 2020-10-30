package smu.mp.reservationjihyun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    Intent intent;
    String meal_nm;
    String d_date, a_date, seat, country;
    int people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setTitle("에약 완료");

        TextView tv1 = findViewById(R.id.result_destination);
        TextView tv2 = findViewById(R.id.result_people);
        TextView tv3 = findViewById(R.id.result_d_date);
        TextView tv4 = findViewById(R.id.result_meal_nm);
        TextView tv5 = findViewById(R.id.result_seat);
        TextView tv6 = findViewById(R.id.result_a_date);


        intent = getIntent();
        meal_nm = intent.getStringExtra("meal_nm");
        country = intent.getStringExtra("country");
        d_date = intent.getStringExtra("d_date");
        a_date = intent.getStringExtra("a_date");
        people = intent.getIntExtra("people", 0);
        seat = intent.getStringExtra("select_seat");
//
//        setResult(RESULT_OK, intent); //응답 전달 후
//        finish();

        tv1.setText("목적지 : " + country);
        tv2.setText("인원 : " + people);
        tv3.setText("출발 날짜 : " + d_date);
        tv4.setText("기내식 : " + meal_nm);
        tv5.setText("좌석 : " + seat);
        tv6.setText("도착 날짜 : " + a_date);

        TextView home_btn = findViewById(R.id.btn_home);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent2);
            }
        });


    }
}
