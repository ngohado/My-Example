package com.hado.myexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hado.myexample.adapter.model.Exercise;
import com.hado.myexample.adapter.viewholder.ExerciseViewHolder;

import java.util.List;

/**
 * Created by Ngo Hado on 18-Jun-16.
 */
public class ExerciseAdapter extends BaseAdapter<Exercise> {

    enum Type {
        NORMAL,
        MORE
    }

    public ExerciseAdapter(Context context, List<Exercise> initData) {
        super(context, initData);
    }

    @Override
    protected int getDefaultItemViewType(int position) {
        return Type.NORMAL.ordinal();
    }

    @Override
    protected void onActualBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Type positionType = Type.values()[getDefaultItemViewType(position)];
        switch (positionType) {
            case NORMAL:
                ((ExerciseViewHolder) holder).setUpWith(dataSet.get(position));
                break;
            case MORE:
                break;
            default:
                ((ExerciseViewHolder) holder).setUpWith(dataSet.get(position));
        }
    }

    @Override
    protected RecyclerView.ViewHolder onActualCreateViewHolder(ViewGroup parent, int viewType) {
        Type type = Type.values()[viewType];
        switch (type) {
            case NORMAL:
                return new ExerciseViewHolder(LayoutInflater.from(context).inflate(ExerciseViewHolder.EXCERCISE_VIEW, parent, true));
            case MORE:
                break;
            default:
                return new ExerciseViewHolder(LayoutInflater.from(context).inflate(ExerciseViewHolder.EXCERCISE_VIEW, parent, true));
        }
        return null;
    }

    @Override
    public boolean hasMoreData() {
        return false;
    }
}
