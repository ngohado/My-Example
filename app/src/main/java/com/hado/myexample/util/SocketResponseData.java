package com.hado.myexample.util;

import com.google.gson.Gson;

import java.util.concurrent.Callable;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Hado on 14-Sep-16.
 */
public class SocketResponseData<T> implements Emitter.Listener {
    private SocketDataListener<T> mListener;
    private Class<T> mClazz;

    public SocketResponseData(Socket socket, String event, SocketDataListener<T> listener, final Class<T> clazz) {
        mListener = listener;
        mClazz = clazz;
        socket.on(event, this);
    }

    @Override
    public void call(Object... args) {
        final String jsonReceive = (String) args[0];
        Single.fromCallable(new Callable<T>() {
            @Override
            public T call() throws Exception {
                return new Gson().fromJson(jsonReceive, mClazz);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<T>() {
                    @Override
                    public void call(T t) {
                        mListener.onSuccess(t);
                    }
                });
    }

    public interface SocketDataListener<T> {
        void onSuccess(T data);
    }
}

