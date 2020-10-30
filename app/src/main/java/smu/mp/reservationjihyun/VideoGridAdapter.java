package smu.mp.reservationjihyun;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import static androidx.core.content.ContextCompat.startActivity;
import static java.security.AccessController.getContext;

public class VideoGridAdapter extends BaseAdapter {
    Context context;

    public VideoGridAdapter(Context c){context=c;}

    public int getCount(){return  videoID.length;}

    public Object getItem(int position){
        return null;
    }
    public long getItemId(int position){
        return 0;
    }

    Integer[] videoID = {R.drawable.video1 , R.drawable.video2,
            R.drawable.video3, R.drawable.video4, R.drawable.video5, R.drawable.video6};

    Integer[] videoName = {R.string.video1, R.string.video2, R.string.video3,
            R.string.video4, R.string.video5, R.string.video6};




    public View getView(int position, View convertView, ViewGroup parent){

        final ImageView imageview = new ImageView(context);
        imageview.setLayoutParams(new GridView.LayoutParams(400,300));
        imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageview.setPadding(5,3,5,3);
        imageview.setImageResource(videoID[position]);

        final int pos= position;
        imageview.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {
                View dialogView = (View)View.inflate(
                        context,R.layout.dialog, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(context);


                dlg.setTitle(videoName[pos]);
                dlg.setMessage("비디오 선택");

                dlg.setIcon(R.drawable.airplane_icon);
                dlg.setView(dialogView);
                dlg.setPositiveButton("영상 보기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(context.getApplicationContext(), "선택 완료", Toast.LENGTH_SHORT).show();
                        String url ="https://www.youtube.com/watch?v=vJ9KdUA2rgA";

                        switch (pos){
                            case 0:
                                url ="https://www.youtube.com/watch?v=vJ9KdUA2rgA";
                                break;
                            case 1:
                                url ="https://www.youtube.com/watch?v=BoAllZ285ZY";
                                break;
                            case 2:
                                url ="https://www.youtube.com/watch?v=i3HBlzOjzGE";
                                break;
                            case 3:
                                url ="https://www.youtube.com/watch?v=2Rod7jNUoGU";
                                break;
                            case 4:
                                url ="https://www.youtube.com/watch?v=m1rYLejkJhs";
                                break;
                            case 5:
                                url ="https://www.youtube.com/watch?v=JkDsYkhCl-U";
                                break;

                        }


                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        intent.putExtra("videoName",videoName[pos]);
                        context.startActivity(intent);

                    }
                });
                dlg.setNegativeButton("다시 고르기", null);
                dlg.show();
            }
        });

        return  imageview;
    }





}


