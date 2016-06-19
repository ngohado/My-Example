package com.hado.myexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hado.myexample.adapter.callback.RecyclerViewItemClickListener;
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

    RecyclerViewItemClickListener<Exercise> callBack;

    public ExerciseAdapter(Context context, List<Exercise> initData, RecyclerViewItemClickListener<Exercise> callBack) {
        super(context, initData);
        this.callBack = callBack;
    }

    @Override
    protected int getDefaultItemViewType(int position) {
        return Type.NORMAL.ordinal();
    }

    @Override
    protected void onActualBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Type positionType = Type.values()[getDefaultItemViewType(position)];
        switch (positionType) {
            case NORMAL:
                ((ExerciseViewHolder) holder).setUpWith(dataSet.get(position));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callBack.onClick(position, dataSet.get(position));
                    }
                });
                break;
            case MORE:
                break;
            default:
        }
    }

    @Override
    protected RecyclerView.ViewHolder onActualCreateViewHolder(ViewGroup parent, int viewType) {
        Type type = Type.values()[viewType];
        View view;
        switch (type) {
            case NORMAL:
                view = LayoutInflater.from(context).inflate(ExerciseViewHolder.EXERCISE_VIEW, parent, false);
                return new ExerciseViewHolder(view);
            case MORE:
                break;
            default:
        }
        return null;
    }

    @Override
    public boolean hasMoreData() {
        return false;
    }
}
