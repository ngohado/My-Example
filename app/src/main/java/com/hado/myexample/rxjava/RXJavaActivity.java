package com.hado.myexample.rxjava;

import android.widget.Button;

import com.hado.myexample.R;
import com.hado.myexample.activity.BaseActivity;
import com.hado.myexample.util.DebugLog;

import java.util.concurrent.Callable;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Single;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RXJavaActivity extends BaseActivity {

    @Bind(R.id.btn_observable_sync)
    Button btnObservableSync;

    @Bind(R.id.btn_observable_async)
    Button btnObservableAsync;

    Subscription colorSubscription;
    Subscription colorSubscriptionAsync;
    Subscription subject1;
    Subscription subject2;

    @Override
    protected void initData() {
        subject1 = RxEventUtil.getInstance().getEvents().subscribe(new Action1<Object>() {
            @Override
            public void call(Object object) {
                Integer integer = (Integer) object;
                DebugLog.d("Subject 1: " + integer);
            }
        });

        subject2 = RxEventUtil.getInstance().getEvents().subscribe(new Action1<Object>() {
            @Override
            public void call(Object object) {
                Integer integer = (Integer) object;
                DebugLog.d("Subject 2: " + integer);
            }
        });
    }

    @Override
    protected void initView() {
    }

    @Override
    protected int setContentViewID() {
        return R.layout.activity_rxjava;
    }

    @OnClick(R.id.btn_observable_sync)
    public void observableSyncOnClick() {
        colorSubscription = Observable.just(Integer.valueOf(R.color.colorAccent)).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer color) {
                btnObservableSync.setTextColor(color);
            }
        });
    }

    @OnClick(R.id.btn_observable_async)
    public void observableAsyncOnClick() {
        colorSubscriptionAsync = Observable.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return waitToGetColor();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                btnObservableAsync.setTextColor(integer);
            }
        });
    }

    int i = 0;

    @OnClick(R.id.btn_subject)
    public void subjectOnClick() {
        RxEventUtil.getInstance().emmit(i++);
    }

    Subscription mapSubcription;

    @OnClick(R.id.btn_map)
    public void mapOnClick() {
        mapSubcription = Single.just(4).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return String.valueOf(integer);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                DebugLog.d("Map: " + s);
            }
        });
    }

    private Integer waitToGetColor() {
        try {
            Thread.sleep(5000);
            return Integer.valueOf(R.color.colorPrimary);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onDestroy() {
        if (colorSubscription != null && colorSubscription.isUnsubscribed()) {
            colorSubscription.unsubscribe();
        }

        if (colorSubscriptionAsync != null && colorSubscriptionAsync.isUnsubscribed()) {
            colorSubscriptionAsync.unsubscribe();
        }

        if (subject1 != null && subject1.isUnsubscribed()) {
            subject1.unsubscribe();
        }

        if (subject2 != null && subject2.isUnsubscribed()) {
            subject2.unsubscribe();
        }

        if (mapSubcription != null && mapSubcription.isUnsubscribed()) {
            mapSubcription.unsubscribe();
        }


        super.onDestroy();
    }
}
