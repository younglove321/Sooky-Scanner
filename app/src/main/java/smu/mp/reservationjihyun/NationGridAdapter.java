package smu.mp.reservationjihyun;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
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

public class NationGridAdapter extends BaseAdapter {
    Context context;

    public NationGridAdapter(Context c){context=c;}

    public int getCount(){return  nationID.length;}

    public Object getItem(int position){
        return null;
    }
    public long getItemId(int position){
        return 0;
    }

    Integer[] nationID = {R.drawable.usa , R.drawable.switzerland,
            R.drawable.canada, R.drawable.vietnam, R.drawable.france, R.drawable.spain};

    String[] nationName = {"미국", "스위스", "캐나다", "베트남", "프랑스", "스페인"};

    public View getView(int position, View convertView, ViewGroup parent){

        final ImageView imageview = new ImageView(context);
        imageview.setLayoutParams(new GridView.LayoutParams(900,450));
        imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageview.setPadding(5,3,5,3);
        imageview.setImageResource(nationID[position]);

        final int pos= position;
        imageview.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                View dialogView = (View)View.inflate(
                        context,R.layout.dialog, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(context);


                dlg.setTitle(nationName[pos]);
                dlg.setMessage("(으)로 떠나볼까요?");

                dlg.setIcon(R.drawable.airplane_icon);
                dlg.setView(dialogView);
                dlg.setPositiveButton("네!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(context.getApplicationContext(), "나라 선택 완료", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, ReservationActivity.class);
                        intent.putExtra("country",nationName[pos]);
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


