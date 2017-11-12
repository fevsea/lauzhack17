package edu.upc.ieee.esaapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.upc.ieee.esaapp.models.Mission;

/**
 * Created by alejandro on 11/11/17.
 */

public class MissionAdapter extends RecyclerView.Adapter<MissionAdapter.MissionViewHolder>{

    private List<Mission> missionList;

    public MissionAdapter(List<Mission> missionList) {
        this.missionList = missionList;
    }

    @Override
    public MissionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mission_card, parent, false);

        return new MissionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MissionViewHolder holder, int position) {
        holder.name.setText(missionList.get(position).getName());
        holder.description.setText(missionList.get(position).getDescription());
        Picasso.with(holder.name.getContext()).load(missionList.get(position).getImg()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return missionList.size();
    }

    public void updateData(List<Mission> missionList) {
        this.missionList = missionList;
        notifyDataSetChanged();
    }

    public class MissionViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView description;
        public ImageView image;

        public MissionViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.cv_name);
            description = (TextView) view.findViewById(R.id.cv_description);
            image = (ImageView) view.findViewById(R.id.cv_img);

        }
    }
}
