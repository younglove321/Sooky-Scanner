package smu.mp.reservationjihyun;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

class MealGridAdapter extends BaseAdapter {

    String [] result;
    int [] mealID;
    String country, d_date, a_date, select_seat;
    int people;
    private Context context;
    private static LayoutInflater inflater = null;

    public MealGridAdapter(MealActivity mealActivity, String[] mealNM, int[] mealImages, int people, String country, String d_date, String a_date, String select_seat){
        // TODO Auto-generated constructor stub
        result= mealNM;
        context = mealActivity;
        mealID = mealImages;
        this.people = people;
        this.country = country;
        this.d_date = d_date;
        this.a_date = a_date;
        this.select_seat = select_seat;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public int getCount() {
        return mealID.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class Holder{
        TextView meal_text;
        ImageView meal_img;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Holder holder = new Holder();
        final View view;

        view = inflater.inflate(R.layout.meal_custom_layout,null);
        holder.meal_text = view.findViewById(R.id.textView);
        holder.meal_img = view.findViewById(R.id.imageView);

        holder.meal_text.setText(result[position]);
        holder.meal_img.setImageResource(mealID[position]);



        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final View dialogView = View.inflate(context, R.layout.dialog, null);

                final AlertDialog.Builder dig = new AlertDialog.Builder(context);
                ImageView ivPoster = dialogView.findViewById(R.id.imageViewForMeal);
                ivPoster.setImageResource(mealID[position]);
                dig.setTitle(result[position]);

                //선택한 기내식에 대한 설명
                TextView message = dialogView.findViewById(R.id.dig_message);
                message.setText(result[position]);

                dig.setIcon(R.drawable.fork);
                dig.setView(dialogView);

                dig.setPositiveButton("선택", yesButtonClickListener);
                dig.setNegativeButton("닫기" , null);
                dig.show();
            }

            private DialogInterface.OnClickListener yesButtonClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(context, ResultActivity.class);
                    intent.putExtra("meal_nm",result[position]);
                    intent.putExtra("people",people);
                    intent.putExtra("country",country);
                    intent.putExtra("d_date",d_date);
                    intent.putExtra("a_date",a_date);
                    intent.putExtra("select_seat",select_seat);
                    context.startActivity(intent);
                }
            };

        });
        return view;
    }


}

