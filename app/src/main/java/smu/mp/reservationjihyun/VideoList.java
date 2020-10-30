package smu.mp.reservationjihyun;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoList extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        setTitle("비디오 선택하기");

        final GridView gv = (GridView)findViewById(R.id.gridView_video);
        VideoGridAdapter videoGridAdapter = new VideoGridAdapter(this);
        gv.setAdapter(videoGridAdapter);
    }
}