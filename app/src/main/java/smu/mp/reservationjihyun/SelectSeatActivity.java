package smu.mp.reservationjihyun;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SelectSeatActivity extends AppCompatActivity {
    private Button btn_submit, btn_cancel;
    private TextView tv;

//    String select_seat1 = "";
    String select_seat[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_seat);

        setTitle("좌석 선택");

        Intent intent = getIntent();
        final int people = intent.getIntExtra("people", 0);
        final String country = intent.getStringExtra("country");
        final String d_date = intent.getStringExtra("d_date");
        final String a_date = intent.getStringExtra("a_date");


        GridView gv = (GridView)findViewById(R.id.seat);
        final SeatGridAdapter gAdapter = new SeatGridAdapter(this, people);
        gv.setAdapter(gAdapter);

        btn_submit = (Button)findViewById(R.id.btn_submit);
        btn_cancel = (Button)findViewById(R.id.btn_cancel);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List seat_list = new ArrayList(gAdapter.select_seat);
                String seats = "";

                for(int i = 0; i<seat_list.size(); i++)
                    seats = seats + seat_list.get(i) + "";

                Intent intent2 = new Intent(getApplicationContext(), MealActivity.class);
                intent2.putExtra("select_seat", seats);
                intent2.putExtra("people", people);
                intent2.putExtra("country", country);
                intent2.putExtra("d_date", d_date);
                intent2.putExtra("a_date", a_date);
                startActivity(intent2);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ReservationActivity.class);
                startActivity(intent);
            }
        });

    }


    class SeatGridAdapter extends BaseAdapter {
        int cnt = 0;
        Context context;
        int people;
//        String seat_finish;
        LayoutInflater inf;
        List select_seat;

        Integer[] seatID = {R.drawable.blank, R.drawable.seata, R.drawable.seatb, R.drawable.seatc, R.drawable.seatd,
                R.drawable.seat1, R.drawable.seat_no, R.drawable.seat_before, R.drawable.seat_before, R.drawable.seat_before,
                R.drawable.seat2, R.drawable.seat_before, R.drawable.seat_before, R.drawable.seat_no, R.drawable.seat_no,
                R.drawable.seat3, R.drawable.seat_before, R.drawable.seat_no, R.drawable.seat_before, R.drawable.seat_before,
                R.drawable.seat4, R.drawable.seat_no, R.drawable.seat_before, R.drawable.seat_before, R.drawable.seat_before,
                R.drawable.seat5, R.drawable.seat_before, R.drawable.seat_before, R.drawable.seat_no, R.drawable.seat_no,
                R.drawable.seat6, R.drawable.seat_before, R.drawable.seat_no, R.drawable.seat_before, R.drawable.seat_before};

        Integer[] seatFlag = {2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 2, 0, 0, 1, 1,2, 0, 1, 0, 0,2, 1, 0, 0, 0, 2, 0, 0, 1, 1, 2, 0, 1, 0, 0};
        String[] seatNum = {"", "", "", "", "", "","A1","B1","C1","D1","", "A2","B2","C2","D2", "",
                "A3","B3","C3","D3", "", "A4","B4","C4","D4", "", "A5","B5","C5","D5","", "A6","B6","C6", "D6"};

        public SeatGridAdapter(Context context, int people) {
            this.context = context;
            this.people = people;
            select_seat = new ArrayList();
        }
        public int getCount() { return seatID.length; }

        public Object getItem(int position){
            return seatNum[position];
        }

        public long getItemId(int position){
            return position;
        }


        public View getView(int position, View convertView, ViewGroup parent){

            final ImageView imageview;
            if(convertView == null){
                imageview = new ImageView(context);
                imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageview.setPadding(-50,20,0,0);

                imageview.setImageResource(seatID[position]);

                final int pos = position;
                imageview.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        if(pos == 6 || pos == 13 || pos == 14 || pos == 17 || pos == 21 || pos == 28 || pos == 29 || pos == 32){
                            Toast.makeText(context, "이 좌석은 선택할 수 없습니다.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            if(seatFlag[pos] == 0) {
                                if(cnt >= people){
                                    seatID[pos] = R.drawable.seat_before;
                                    Toast.makeText(context, "더 이상 선택할 수 없습니다.", Toast.LENGTH_SHORT).show();
                                    imageview.setImageResource(R.drawable.seat_before);
                                    seatFlag[pos] = 0;
                                }
                                else{
                                    seatID[pos] = R.drawable.seat_after;
                                    imageview.setImageResource(R.drawable.seat_after);
                                    seatFlag[pos] = 1;
                                    cnt++;
                                    select_seat.add(seatNum[pos]);
//                                    seat_finish = seatNum[pos];
//                                    System.out.println("seat_finish = " + seat_finish);
                                }
                            }
                            else if(seatFlag[pos] == 1){
                                seatID[pos] = R.drawable.seat_before;
                                imageview.setImageResource(R.drawable.seat_before);
                                seatFlag[pos] = 0;
                                cnt--;
                                if(select_seat.contains(seatNum[pos]))
                                    select_seat.remove(seatNum[pos]);
                            }
                        }
                    }
                });

            }else{
                imageview = (ImageView)convertView;
            }

            return imageview;
        }
    }
}

