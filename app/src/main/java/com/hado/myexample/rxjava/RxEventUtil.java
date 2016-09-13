package com.hado.myexample.rxjava;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Hado on 11-Sep-16.
 */
public class RxEventUtil {
    private static PublishSubject<Object> mPublishSubject;
    private static RxEventUtil mInstance;

    public RxEventUtil() {
        mPublishSubject = PublishSubject.create();
    }

    public static RxEventUtil getInstance() {
        if (mInstance == null) {
            mInstance = new RxEventUtil();
        }

        return mInstance;
    }

    public static void emmit(Object object) {
        mPublishSubject.onNext(object);
    }

    public static Observable<Object> getEvents() {
        return mPublishSubject;
    }
}
