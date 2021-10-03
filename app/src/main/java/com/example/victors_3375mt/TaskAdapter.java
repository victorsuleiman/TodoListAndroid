package com.example.victors_3375mt;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    List<TaskItem> taskList;
    Context context;

    public TaskAdapter(List<TaskItem> taskList, Context context) {
        this.taskList = taskList;
        this.context = context;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_task_item,parent,false);

        TaskViewHolder myHolder = new TaskViewHolder(itemView);

        myHolder.imgViewDoneNotDone = itemView.findViewById(R.id.imageViewDoneNotDone);
        myHolder.textViewDesc = itemView.findViewById(R.id.textViewDesc);
        myHolder.imgViewStarred = itemView.findViewById(R.id.imageViewStarred);

        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.imgViewDoneNotDone.setImageResource(R.drawable.notdone);
        holder.textViewDesc.setText(taskList.get(position).taskDescription);
        holder.imgViewStarred.setImageResource(R.drawable.star);

        holder.imgViewStarred.setAlpha(0.25f);

        TaskItem currentTask = taskList.get(position);

        holder.imgViewDoneNotDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentTask.isDone) {
                    holder.imgViewDoneNotDone.setImageResource(R.drawable.done);
                    holder.textViewDesc.setPaintFlags(holder.textViewDesc.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    currentTask.isDone = true;
                } else {
                    holder.imgViewDoneNotDone.setImageResource(R.drawable.notdone);
                    holder.textViewDesc.setPaintFlags(holder.textViewDesc.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                    currentTask.isDone = false;
                }
            }
        });

        holder.imgViewStarred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentTask.isStarred) {
                    holder.imgViewStarred.setAlpha(0.75f);
                    currentTask.isStarred = true;
                } else {
                    holder.imgViewStarred.setAlpha(0.25f);
                    currentTask.isStarred = false;
                }
            }
        });




    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        ImageView imgViewDoneNotDone;
        TextView textViewDesc;
        ImageView imgViewStarred;


        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
