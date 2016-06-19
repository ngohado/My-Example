package com.hado.myexample.adapter.callback;

/**
 * Created by Ngo Hado on 19-Jun-16.
 */
public interface RecyclerViewItemClickListener<T> {
    void onClick(int position, T object);
}
