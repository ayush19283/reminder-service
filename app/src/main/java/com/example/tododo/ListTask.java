package com.example.tododo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ListTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_task);
        Database db = new Database(this);

        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);


        Button add_work_btn = findViewById(R.id.add);
        add_work_btn.setOnClickListener(v -> {
            Intent intent = new Intent(ListTask.this, Add_Work.class);
            startActivity(intent);
        });

        ArrayList<ArrayList<Object>> all_tasks = db.get_all_task_details("n");
        ll.post(() -> {
            for (int i=0; i<all_tasks.size(); i++) {
                System.out.println(all_tasks);
                Log.d("todo", "szkjhcudjhsjghiuahgduyg");
                ArrayList<Object> task = all_tasks.get(i);
                RelativeLayout child_ll = (RelativeLayout) getLayoutInflater().inflate(R.layout.tem, null);
                child_ll.setId((Integer) task.get(0));
                Log.d("todotodo", "ididiid" + (Integer) task.get(0));
                child_ll.setPadding(20, 20, 20, 10);
                // child_ll.setOnClickListener(ListTask.this.child_select_listener);
                TextView chile_txt_view = child_ll.findViewById(R.id.textv);
                String tsk_title = (String) task.get(1);
                if (tsk_title.length() > 20){
                    tsk_title = tsk_title.substring(0, 20)+"...";
                }

                String boldText = tsk_title + "" + (Integer) task.get(0);
//                String normalText;
//                if (task.get(1).toString().length() >= 20) {
//                    normalText = "\n" + task.get(1).toString().substring(0, 20) + "...";
//                }
//                else{
//                    normalText = "\n" + task.get(1).toString();
//                }
                SpannableString str = new SpannableString(boldText );
                str.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                str.setSpan(new RelativeSizeSpan(1.2f), 0, boldText.length(), 0);
                chile_txt_view.setText(str);
                chile_txt_view.setTextColor(Color.parseColor("#000000"));

                View viewDivider = new View(ListTask.this);
                viewDivider.setBackgroundColor(Color.parseColor("#5e5e5e"));
                int dividerHeight = (int) (getResources().getDisplayMetrics().density * 1); // 1dp to pixels
                viewDivider.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dividerHeight));

                ll.addView(child_ll);
                ll.addView(viewDivider);

            }
            });




    }
}