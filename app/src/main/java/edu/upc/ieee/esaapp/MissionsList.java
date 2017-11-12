package edu.upc.ieee.esaapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.upc.ieee.esaapp.models.Mission;
import edu.upc.ieee.esaapp.remote.GenericController;

public class MissionsList extends AppCompatActivity {

    private List<Mission> missionList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MissionAdapter mAdapter;
    private GenericController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missions_list);

        controller = GenericController.getInstance();
        recyclerView = (RecyclerView) findViewById(R.id.missions_recycler_view);

        mAdapter = new MissionAdapter(missionList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        setTitle("ESA Missions");

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getBaseContext(), MissionDetail.class);
                        intent.putExtra("EXTRA_LIST_POS_MISSION", missionList.get(position).getPk());
                        startActivity(intent);
                    }
                })
        );

        getMissionList();

    }


    private void getMissionList() {
        new MissionsList.getMissions().execute();
    }

    private class getMissions extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            missionList = controller.getMissions();
            return "ok";
        }

        @Override
        protected void onPostExecute(String s) {
            mAdapter.updateData(missionList);
            super.onPostExecute(s);
        }
    }
}
