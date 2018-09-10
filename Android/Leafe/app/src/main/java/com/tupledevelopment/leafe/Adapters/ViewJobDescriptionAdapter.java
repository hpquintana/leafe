package com.tupledevelopment.leafe.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tupledevelopment.leafe.DynamoMaps.AssignedForm;
import com.tupledevelopment.leafe.Activities.JobActivity;
import com.tupledevelopment.leafe.R;

import java.util.ArrayList;

public class ViewJobDescriptionAdapter extends RecyclerView.Adapter<ViewJobDescriptionAdapter.ViewHolder> {
    private ArrayList<AssignedForm> joblist;
    private Context context;

    public ViewJobDescriptionAdapter(Context context, ArrayList<AssignedForm> joblist, JobActivity activity){
        this.context = context;
        this.joblist = joblist;
    }

    @Override
    public ViewJobDescriptionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_view_job_description, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.jobId.setText("Form ID: " + joblist.get(i).getFormID());
        viewHolder.empId.setText("EmployeeID: " + joblist.get(i).getEmployeeID());
        viewHolder.formId = joblist.get(i).getFormID();
        viewHolder.employeeId = joblist.get(i).getEmployeeID();
    }

    @Override
    public int getItemCount() {
        return joblist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView jobName;
        private TextView jobDesc;
        private TextView jobId;
        private TextView empId;

        private String formId;
        private String employeeId;

        public ViewHolder (View root){
            super(root);
            jobName = (TextView) root.findViewById(R.id.viewJobName);
            jobDesc = (TextView) root.findViewById(R.id.viewJobsDescription);
            jobId = (TextView) root.findViewById(R.id.viewJobsFormID);
            empId = (TextView) root.findViewById(R.id.viewJobsEmployeeID);
        }
    }
}