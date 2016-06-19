package com.hado.myexample.adapter.model;

import com.hado.myexample.activity.MainActivity;

/**
 * Created by Ngo Hado on 17-Jun-16.
 */
public class Exercise {
    public int image;
    public String exerciseName;
    public String exerciseDescription;
    public MainActivity.ExerciseEnum exerciseEnum;

    public Exercise() {

    }

    public Exercise(int image, String exerciseName, String exerciseDescription, MainActivity.ExerciseEnum exerciseEnum) {
        this.image = image;
        this.exerciseName = exerciseName;
        this.exerciseDescription = exerciseDescription;
        this.exerciseEnum = exerciseEnum;
    }

    public Exercise(int image, String exerciseName, String exerciseDescription) {
        this.image = image;
        this.exerciseName = exerciseName;
        this.exerciseDescription = exerciseDescription;
        exerciseEnum = null;
    }
}
