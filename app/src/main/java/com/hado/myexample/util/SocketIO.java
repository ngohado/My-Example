package com.hado.myexample.util;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by Hado on 13-Sep-16.
 */
public class SocketIO {
    private Socket socket;
    private static SocketIO socketIO;

    public SocketIO(String urlServer) {
        try {
            if (socket == null || !socket.connected()) {
                socket = IO.socket(urlServer);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public static SocketIO instance(String urlServer) {
        if (socketIO == null) {
            socketIO = new SocketIO(urlServer);
        }
        return socketIO;
    }

    public void emit(String event, Object data) {
        try {
            socket.emit(event, new JSONObject(new Gson().toJson(data)));
        } catch (JSONException e) {
            DebugLog.e(e.getMessage());
        }
    }


}
