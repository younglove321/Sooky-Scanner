package smu.mp.reservationjihyun;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

public class MealActivity extends AppCompatActivity {

    private String country, d_date, a_date, select_seat;
    private int people;

    public static int[] mealImages = { R.drawable.meal01, R.drawable.meal02, R.drawable.meal03, R.drawable.meal04, R.drawable.meal05,
            R.drawable.meal06, R.drawable.meal07, R.drawable.meal08, R.drawable.meal09, R.drawable.meal10,
            R.drawable.meal11, R.drawable.meal12, R.drawable.meal13, R.drawable.meal14, R.drawable.meal15};

    public static String [] mealNm = {"영양쌈밥", "트래블 밀", "비빔밥" ,"베지테리언식(VLML)", "엄격한 채식(VGML) 채식", "베지테리언 힌두교식(AVML)" ,"베지테리언 생식(RVML)",
            "동양 채식(VOML)","힌두교식(HNML)", "이슬람식(MOML)","유대교식(KSML)","당뇨식(DBML)","저염식(LSML)","과일식","키즈밀"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        setTitle("기내식 선택");

        Intent intent = getIntent();
        people = intent.getIntExtra("people", 0);
        country = intent.getStringExtra("country");
        d_date = intent.getStringExtra("d_date");
        a_date = intent.getStringExtra("a_date");
        select_seat = intent.getStringExtra("select_seat");

        final GridView gv = findViewById(R.id.mealGridView);
        MealGridAdapter gAdapter = new MealGridAdapter(this,mealNm, mealImages, people, country, d_date, a_date, select_seat);
        gv.setAdapter(gAdapter);

    }
}
