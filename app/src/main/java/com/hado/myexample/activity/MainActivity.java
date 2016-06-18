package com.hado.myexample.activity;

import android.support.v7.widget.RecyclerView;

import com.hado.myexample.R;
import com.hado.myexample.adapter.ExerciseAdapter;
import com.hado.myexample.adapter.model.Exercise;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.rv_exercise)
    public RecyclerView rvExercise;
    private ExerciseAdapter exerciseAdapter;
    private List<Exercise> exerciseList;

    @Override
    protected int setContentViewID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        addExercise();
        exerciseAdapter = new ExerciseAdapter(this, exerciseList);
        rvExercise.setAdapter(exerciseAdapter);
    }

    private void addExercise() {
        exerciseList = new ArrayList<>();
        exerciseList.add(new Exercise(R.mipmap.ic_launcher, "Exercise 1", "Nothing to show"));
        exerciseList.add(new Exercise(R.mipmap.ic_launcher, "Exercise 1", "Nothing to show"));
        exerciseList.add(new Exercise(R.mipmap.ic_launcher, "Exercise 1", "Nothing to show"));
        exerciseList.add(new Exercise(R.mipmap.ic_launcher, "Exercise 1", "Nothing to show"));
    }

    @Override
    protected void initView() {

    }

}
