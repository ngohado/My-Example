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
import com.hado.myexample.musicplayer.MusicActivity;
import com.hado.myexample.notification.NotificationActivity;
import com.hado.myexample.pagertransformer.PagerTransformerActivity;
import com.hado.myexample.realm.LoginActivity;
import com.hado.myexample.rxjava.RXJavaActivity;
import com.hado.myexample.socketio.SocketActivity;
import com.hado.myexample.terris.GameActivity;

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
        INSTAGRAM,
        PAGER_TRANSFORMER,
        REALM,
        MUSIC_PLAYER,
        RXJAVA,
        SOCKET,
        GAME
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
            case PAGER_TRANSFORMER:
                openExercise(PagerTransformerActivity.class);
                break;
            case REALM:
                openExercise(LoginActivity.class);
                break;
            case MUSIC_PLAYER:
                openExercise(MusicActivity.class);
                break;
            case RXJAVA:
                openExercise(RXJavaActivity.class);
                break;
            case SOCKET:
                openExercise(SocketActivity.class);
                break;
            case GAME:
                openExercise(GameActivity.class);
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
        exerciseList.add(new Exercise(R.mipmap.instagram, "Instagram", "07-Jul-2016", ExerciseEnum.INSTAGRAM));
        exerciseList.add(new Exercise(R.mipmap.transformers, "Pager Transformer", "08-Jul-2016", ExerciseEnum.PAGER_TRANSFORMER));
        exerciseList.add(new Exercise(R.mipmap.realm, "Reaml Login", "10-Jul-2016", ExerciseEnum.REALM));
        exerciseList.add(new Exercise(R.mipmap.realm, "Music Player", "15-Jul-2016", ExerciseEnum.MUSIC_PLAYER));
        exerciseList.add(new Exercise(R.mipmap.rxjava, "RX Java", "11-Sep-2016", ExerciseEnum.RXJAVA));
        exerciseList.add(new Exercise(R.mipmap.rxjava, "Socket IO", "14-Sep-2016", ExerciseEnum.SOCKET));
        exerciseList.add(new Exercise(R.mipmap.rxjava, "GAME", "19-Sep-2016", ExerciseEnum.GAME));
    }

    @Override
    protected void initView() {

    }

}
