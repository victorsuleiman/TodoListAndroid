package com.example.victors_3375mt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<TaskItem> taskList = new ArrayList<TaskItem>();
    RecyclerView recyclerViewTasks;
    ImageView imgViewInfo;
    TextView textViewStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addSampleData();

        recyclerViewTasks = findViewById(R.id.recyclerViewTaskList);
        imgViewInfo = findViewById(R.id.imageViewInfo);
        textViewStatus = findViewById(R.id.textViewStatus);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        recyclerViewTasks.setLayoutManager(lm);

        TaskAdapter adapter = new TaskAdapter(taskList,this);
        recyclerViewTasks.setAdapter(adapter);

        imgViewInfo.setOnTouchListener(new CustomTouchListener(this){
            @Override
            public void onSingleClick() {
                int tasksToBeDone = tasksToBeDone(adapter.taskList);
                textViewStatus.setText("Tasks yet to be done: " + tasksToBeDone);
                super.onSingleClick();
            }

            @Override
            public void onDoubleClick() {
                int tasksToBeDone = starredTasksToBeDone(adapter.taskList);
                textViewStatus.setText("Starred Tasks yet to be done: " + tasksToBeDone);
                super.onDoubleClick();
            }
        });


    }

    void addSampleData() {
        taskList.add(new TaskItem("Watch Movie",false,false));
        taskList.add(new TaskItem("Do Laundry",false,false));
        taskList.add(new TaskItem("Pay Rent",false,false));
    }

    int tasksToBeDone(List<TaskItem> taskListRv) {
        int tasksToBeDone = 0;

        for (TaskItem t : taskListRv) {
            if (!t.isDone) tasksToBeDone ++;
        }

        return tasksToBeDone;
    }

    int starredTasksToBeDone(List<TaskItem> taskListRv) {
        int tasksToBeDone = 0;

        for (TaskItem t : taskListRv) {
            if (!t.isDone && t.isStarred) tasksToBeDone ++;
        }

        return tasksToBeDone;
    }
}