package edu.upc.ieee.esaapp;

import android.app.ActionBar;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import edu.upc.ieee.esaapp.models.Item;
import edu.upc.ieee.esaapp.models.Mission;
import edu.upc.ieee.esaapp.remote.GenericController;

import static android.graphics.Color.BLACK;

public class MissionDetail extends AppCompatActivity {

    private GenericController controller;
    Integer pk;
    Mission mission;
    LinearLayout linearLayout;
    ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_detail);
        controller = GenericController.getInstance();

        pk = getIntent().getIntExtra("EXTRA_LIST_POS_MISSION", 0);

        ScrollView scroll = new ScrollView(this);
        linearLayout = new LinearLayout(this);
        scroll.addView(linearLayout);
        linearLayout.setPadding(10, 10, 10, 10);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(scroll);
        ab = getActionBar();

        getMissionList();
    }

    private void getMissionList() {
        new MissionDetail.getMission().execute();
    }

    private class getMission extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            mission = controller.getMission(pk);
            return "ok";
        }

        @Override
        protected void onPostExecute(String s) {

            TextView textViewT = new TextView(getBaseContext());
            textViewT.setText(mission.getDescription());
            textViewT.setTextSize(30);
            textViewT.setPadding(15, 50, 15, 50);
            textViewT.setTextAppearance(getBaseContext(), android.R.style.TextAppearance_DeviceDefault_WindowTitle);
            linearLayout.addView(textViewT);
            setTitle(mission.getName());

            for(Item i: mission.getWeb()){
                if(i.getTag().equalsIgnoreCase("p")){
                    TextView textView = new TextView(getBaseContext());
                    textView.setText(i.getValue());
                    textView.setTextSize(20);
                    textView.setPadding(15, 50, 15, 50);
                    textView.setTextColor(BLACK);
                    textView.setTextAppearance(getBaseContext(), android.R.style.TextAppearance_DeviceDefault_Medium);
                    linearLayout.addView(textView);
                } else if(i.getTag().equalsIgnoreCase("img")){
                    ImageView imageView = new ImageView((getBaseContext()));
                    Picasso.with(getBaseContext()).load(i.getValue()).into(imageView);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    imageView.setMinimumHeight(900);
                    imageView.setPadding(10, 100, 10, 100);
                    linearLayout.addView(imageView);
                }
            }
            super.onPostExecute(s);
        }
    }
}
