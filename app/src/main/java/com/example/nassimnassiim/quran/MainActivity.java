package com.example.nassimnassiim.quran;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.HashMap;

public class MainActivity extends Activity {

    private ListView maListViewPerso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        maListViewPerso = (ListView) findViewById(R.id.listviewperso);
        SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), DataListe.getListeItem(), R.layout.modelitem,
                new String[] {"img", "description"}, new int[] {R.id.img, R.id.description});
        maListViewPerso.setAdapter(mSchedule);
        maListViewPerso.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                HashMap<String, String> map = (HashMap<String, String>) maListViewPerso.getItemAtPosition(position);
                Intent procc = new Intent();
                Bundle bound = new Bundle();
                bound.putString("url",map.get("url"));
                bound.putString("decrip", map.get("description"));
                procc.putExtras(bound);
                setResult(RESULT_OK,procc);
                finish();
            }
        });
    }
}
