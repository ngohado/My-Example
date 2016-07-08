package com.hado.myexample.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.hado.myexample.R;
import com.hado.myexample.adapter.ExerciseAdapter;
import com.hado.myexample.adapter.callback.RecyclerViewItemClickListener;
import com.hado.myexample.adapter.model.Exercise;
import com.hado.myexample.bluetooth.BluetoothActivity;
import com.hado.myexample.instagram.InstagramActivity;
import com.hado.myexample.notification.NotificationActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements RecyclerViewItemClickListener<Exercise> {

    @Bind(R.id.rv_exercise)
    public RecyclerView rvExercise;

    private ExerciseAdapter exerciseAdapter;
    private List<Exercise> exerciseList;


    public enum ExerciseEnum {
        BLUETOOTH,
        NOTIFICATION,
        INSTAGRAM
    }

    @Override
    public void onClick(int position, Exercise object) {
        if (object.exerciseEnum == null) {
            Toast.makeText(this, "Exercise is'nt exist", Toast.LENGTH_SHORT).show();
            return;
        }

        switch (object.exerciseEnum) {
            case BLUETOOTH:
                openExercise(BluetoothActivity.class);
                break;
            case NOTIFICATION:
                openExercise(NotificationActivity.class);
                break;
            case INSTAGRAM:
                openExercise(InstagramActivity.class);
                break;
            default:
        }
    }

    private void openExercise(Class clazz) {
        Intent intentOpen = new Intent(this, clazz);
        startActivity(intentOpen);
    }

    @Override
    protected int setContentViewID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        addExercise();
        exerciseAdapter = new ExerciseAdapter(this, exerciseList, this);
        rvExercise.setLayoutManager(new LinearLayoutManager(this));
        rvExercise.setAdapter(exerciseAdapter);
    }

    private void addExercise() {
        exerciseList = new ArrayList<>();
        exerciseList.add(new Exercise(R.mipmap.bluetooth, "Bluetooth", "19-Jun-2016", ExerciseEnum.BLUETOOTH));
        exerciseList.add(new Exercise(R.mipmap.notification, "Notification", "19-Jun-2016", ExerciseEnum.NOTIFICATION));
        exerciseList.add(new Exercise(R.mipmap.ic_launcher, "Instagram", "07-Jul-2016", ExerciseEnum.INSTAGRAM));
        exerciseList.add(new Exercise(R.mipmap.ic_launcher, "Exercise 1", "Nothing to show"));
    }

    @Override
    protected void initView() {

    }

}
