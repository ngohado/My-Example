package com.hado.myexample.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hado.myexample.R;
import com.hado.myexample.adapter.model.Exercise;
import com.hado.myexample.util.StringUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ngo Hado on 18-Jun-16.
 */
public class ExerciseViewHolder extends RecyclerView.ViewHolder {
    public static int EXCERCISE_VIEW = R.layout.load_more;

    @Bind(R.id.img_number)
    public ImageView imgExercise;

    @Bind(R.id.tv_exercise_name)
    public TextView tvExerciseName;

    @Bind(R.id.tv_exercise_description)
    public TextView tvExerciseDescription;

    public ExerciseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    /**
     * fill data from param to exercise item view
     * @param exercise contain data of exercise
     */
    public void setUpWith(Exercise exercise) {
        Glide.with(itemView.getContext()).load(exercise.image).into(imgExercise);
        StringUtil.displayText(exercise.exerciseName, tvExerciseName);
        StringUtil.displayText(exercise.exerciseDescription, tvExerciseDescription);
    }
}
