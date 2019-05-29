package com.developerbrothers.calendarsample_master;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    public GregorianCalendar cal_month, cal_month_copy;
    private HwAdapter hwAdapter;
    private TextView tv_month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeCollection.date_collection_arr=new ArrayList<HomeCollection>();
        HomeCollection.date_collection_arr.add( new HomeCollection("2019-01-01" ,"Новый год","Выходной","Праздник"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2019-01-07" ,"Рождество","Выходной","Праздник"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2019-02-23" ,"День Защитника Отечества","Выходной","Праздник"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2019-03-08" ,"Международный Женский День","Выходной","Праздник"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2019-03-10" ,"Масленица","Выходной","Праздник"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2019-04-07" ,"Благовещение","Выходной","Праздник"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2019-04-28" ,"Пасха","Выходной","Праздник"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2019-05-09" ,"День Победы","Выходной","Праздник"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2019-06-12" ,"День России","Выходной","Праздник"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2019-06-16" ,"Троица","Выходной","Праздник"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2019-07-08" ,"День семьи, любви и верности","Выходной","Праздник"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2019-08-28" ,"Успение Пресвятой Богородицы","Выходной","Праздник"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2019-09-01" ,"День Знаний","Выходной","Праздник"));



        cal_month = (GregorianCalendar) GregorianCalendar.getInstance();
        cal_month_copy = (GregorianCalendar) cal_month.clone();
        hwAdapter = new HwAdapter(this, cal_month,HomeCollection.date_collection_arr);

        tv_month = (TextView) findViewById(R.id.tv_month);
        tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));


        ImageButton previous = (ImageButton) findViewById(R.id.ib_prev);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cal_month.get(GregorianCalendar.MONTH) == 4&&cal_month.get(GregorianCalendar.YEAR)==2017) {
                    //cal_month.set((cal_month.get(GregorianCalendar.YEAR) - 1), cal_month.getActualMaximum(GregorianCalendar.MONTH), 1);
                    Toast.makeText(MainActivity.this, "Сведения о событии доступны только для текущего сеанса.", Toast.LENGTH_SHORT).show();
                }
                else {
                    setPreviousMonth();
                    refreshCalendar();
                }


            }
        });
        ImageButton next = (ImageButton) findViewById(R.id.Ib_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cal_month.get(GregorianCalendar.MONTH) == 5&&cal_month.get(GregorianCalendar.YEAR)==2018) {
                    //cal_month.set((cal_month.get(GregorianCalendar.YEAR) + 1), cal_month.getActualMinimum(GregorianCalendar.MONTH), 1);
                    Toast.makeText(MainActivity.this, "Сведения о событии доступны только для текущего сеанса.", Toast.LENGTH_SHORT).show();
                }
                else {
                    setNextMonth();
                    refreshCalendar();
                }
            }
        });
        GridView gridview = (GridView) findViewById(R.id.gv_calendar);
        gridview.setAdapter(hwAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String selectedGridDate = HwAdapter.day_string.get(position);
                ((HwAdapter) parent.getAdapter()).getPositionList(selectedGridDate, MainActivity.this);
            }

        });
    }
    protected void setNextMonth() {
        if (cal_month.get(GregorianCalendar.MONTH) == cal_month.getActualMaximum(GregorianCalendar.MONTH)) {
            cal_month.set((cal_month.get(GregorianCalendar.YEAR) + 1), cal_month.getActualMinimum(GregorianCalendar.MONTH), 1);
        } else {
            cal_month.set(GregorianCalendar.MONTH,
                    cal_month.get(GregorianCalendar.MONTH) + 1);
        }
    }

    protected void setPreviousMonth() {
        if (cal_month.get(GregorianCalendar.MONTH) == cal_month.getActualMinimum(GregorianCalendar.MONTH)) {
            cal_month.set((cal_month.get(GregorianCalendar.YEAR) - 1), cal_month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            cal_month.set(GregorianCalendar.MONTH, cal_month.get(GregorianCalendar.MONTH) - 1);
        }
    }

    public void refreshCalendar() {
        hwAdapter.refreshDays();
        hwAdapter.notifyDataSetChanged();
        tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));
    }
}
