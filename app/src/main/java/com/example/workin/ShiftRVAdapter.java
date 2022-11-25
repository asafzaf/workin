package com.example.workin;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShiftRVAdapter extends RecyclerView.Adapter<ShiftRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<ShiftModal> shiftModalArrayList;
    private Context context;

    // constructor
    public ShiftRVAdapter(ArrayList<ShiftModal> shiftModalArrayList, Context context) {
        this.shiftModalArrayList = shiftModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shift_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        ShiftModal modal = shiftModalArrayList.get(position);
        holder.companyNameTV.setText("Company: " + modal.getCompany());
        holder.locationTV.setText("Location: " + modal.getWorkplace());
        holder.shiftDescTV.setText(modal.getReason());
        holder.shiftDurationTV.setText("Total: " + modal.getTotal_time());
        holder.sfhitstartTV.setText("Start: " + modal.getStart_time());
        holder.shiftendTV.setText("End: " + modal.getEnd_time());
        holder.shiftDateTV.setText("Date: " + modal.getFull_date());
        holder.projectTV.setText("Project: " + modal.getProject_t());


    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return shiftModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView companyNameTV, locationTV, shiftDateTV, shiftDescTV, shiftDurationTV, sfhitstartTV, shiftendTV, projectTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            companyNameTV = itemView.findViewById(R.id.idTVCompanyName);
            locationTV = itemView.findViewById((R.id.idTVshiftLOCATION));
            shiftDurationTV = itemView.findViewById(R.id.idTVshiftDuration);
            shiftDateTV = itemView.findViewById(R.id.idTVshiftDATE);
            sfhitstartTV = itemView.findViewById(R.id.idTVstartTime);
            shiftendTV = itemView.findViewById(R.id.idTVendTime);
            shiftDescTV = itemView.findViewById(R.id.idTVshiftDescription);
            projectTV = itemView.findViewById(R.id.idTVshiftProject);
        }
    }
}

