package com.aihook.tableview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aihook.tableview.lib.TableDataAdapter;
import com.aihook.tableview.lib.TableFixHeaders;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TableFixHeaders table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        table = (TableFixHeaders) findViewById(R.id.table);

        ArrayList<ArrayList<String>> mList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            ArrayList<String> arrayList = new ArrayList<>();
            for (int j = 0; j < 30; j++) {
                arrayList.add(j, String.valueOf(j * 10000) + String.valueOf(j) + "进iwn中文");
            }
            mList.add(arrayList);
        }
        TableDataAdapter tableDataAdapter = new TableDataAdapter(this, mList, 40, 16);
        table.setAdapter(tableDataAdapter);
        System.out.println(this.getResources().getDisplayMetrics().density);
    }
}
