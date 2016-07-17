package com.hado.myexample.musicplayer;

import android.os.Binder;

/**
 * Created by Ngo Hado on 16-Jul-16.
 */
public class MusicServiceBinder extends Binder {
    private MusicService service;

    public MusicServiceBinder(MusicService service) {
        this.service = service;
    }

    public MusicService getService() {
        return service;
    }
}
