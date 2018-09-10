package com.tupledevelopment.leafe.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tupledevelopment.leafe.Activities.TimerActivity;
import com.tupledevelopment.leafe.Activities.ViewTos;
import com.tupledevelopment.leafe.DynamoMaps.AssignedForm;
import com.tupledevelopment.leafe.R;

import java.util.ArrayList;

public class ViewJobsAdapter extends RecyclerView.Adapter<ViewJobsAdapter.ViewHolder> {
    private ArrayList<AssignedForm> jobList;
    private JobOnClickListener mListener;
    private Context context;

    public ViewJobsAdapter(Context context, ArrayList<AssignedForm> jobList, JobOnClickListener mListener) {
        this.context = context;
        this.jobList = jobList;
        this.mListener = mListener;
    }

    @Override
    public ViewJobsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_view_jobs, parent, false);

        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.jobId.setText("Form ID:" + jobList.get(i).getFormID());
        viewHolder.empId.setText("EmployeeID: " + jobList.get(i).getEmployeeID());
        viewHolder.assignedForm = jobList.get(i);
        viewHolder.formId = jobList.get(i).getFormID();
        viewHolder.employeeId = jobList.get(i).getEmployeeID();
    }


    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public void addJobs(ArrayList<AssignedForm> jobList) {
        if (jobList != null) {
            this.jobList.addAll(jobList);
            notifyDataSetChanged();
        } else {
            Log.e("Jobs Adapter","NULL List");
        }
    }

    public void addJob(AssignedForm assignedForm){
        jobList.add(0, assignedForm);
        notifyItemInserted(0);
    }

    public AssignedForm getNewestJob() {
        return jobList.get(0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView jobName;
        private TextView jobDesc;
        private TextView jobId;
        private TextView empId;

        private String formId;
        private String employeeId;

        private AssignedForm assignedForm;

        public ViewHolder(View root) {
            super(root);
            root.setOnClickListener(this);
            jobName = (TextView) root.findViewById(R.id.viewJobName);
            jobDesc = (TextView) root.findViewById(R.id.viewJobsDescription);
            jobId = (TextView) root.findViewById(R.id.viewJobsFormID);
            empId = (TextView) root.findViewById(R.id.viewJobsEmployeeID);
        }

        @Override
        public void onClick(View v) {
            if (assignedForm.getFormState() == null || assignedForm.getFormState().isEmpty()){
                Intent intent = new Intent(context, TimerActivity.class);
                intent.putExtra("FormID", formId);
                intent.putExtra("EmployeeID", employeeId);
                context.startActivity(intent);
            } else if (assignedForm.getFormState().equalsIgnoreCase("NeedSig")){
                Intent intent = new Intent(context, ViewTos.class);
                intent.putExtra("FormID", formId);
                intent.putExtra("EmployeeID", employeeId);
                context.startActivity(intent);
            }else {
                Log.e("FormState", String.valueOf(assignedForm.getFormState() == "NeedSig"));
                Intent intent = new Intent(context, TimerActivity.class);
                intent.putExtra("FormID", formId);
                intent.putExtra("EmployeeID", employeeId);
                context.startActivity(intent);
            }
        }

    }

    public interface JobOnClickListener{
        public void onEventSelected(String eventId);
    }
}