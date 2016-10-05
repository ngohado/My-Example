package com.hado.myexample.socketio;

import android.widget.Button;
import android.widget.EditText;

import com.hado.myexample.R;
import com.hado.myexample.activity.BaseActivity;
import com.hado.myexample.util.DebugLog;
import com.hado.myexample.util.SocketIO;
import com.hado.myexample.util.SocketResponseData;

import butterknife.Bind;
import butterknife.OnClick;
import io.socket.client.Socket;

public class SocketActivity extends BaseActivity {

    @Bind(R.id.btn_connect)
    Button btnConnect;

    @Bind(R.id.edt_message)
    EditText edtMessage;

    private Socket mSocket;
    private static final String LOGIN_EVENT = "login_event";


    @Override
    protected void initData() {
        mSocket = SocketIO.instance("http://192.168.137.1:3000").getSocket();
        new SocketResponseData<>(mSocket, LOGIN_EVENT, new SocketResponseData.SocketDataListener<User>() {
            @Override
            public void onSuccess(User data) {
                DebugLog.d(data.toString());
            }
        }, User.class);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int setContentViewID() {
        return R.layout.activity_socket;
    }

    @OnClick(R.id.btn_connect)
    public void connectOnClick() {
        mSocket.connect();
    }

    @OnClick(R.id.btn_send)
    public void sendOnClick() {

    }
}
