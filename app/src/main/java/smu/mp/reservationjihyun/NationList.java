package smu.mp.reservationjihyun;

import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class NationList extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nation_list);

        setTitle("나라 선택하기");

        final GridView gv = (GridView)findViewById(R.id.gridView_nation);
        NationGridAdapter gAdapter = new NationGridAdapter(this);
        gv.setAdapter(gAdapter);
    }


}
