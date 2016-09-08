package com.hado.myexample.realm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.HashMap;

/**
 * Created by Hado on 06-Aug-16.
 */
public class StateMaintainer {

    private final String mStateMaintainerTAG;

    private FragmentManager fragmentManager;

    private StateManagerFragment mStateManagerFragment;

    public StateMaintainer(FragmentManager fragmentManager, String mStateMaintainerTAG) {
        this.mStateMaintainerTAG = mStateMaintainerTAG;
        this.fragmentManager = fragmentManager;
    }

    public boolean isFirstTime() {
        mStateManagerFragment = (StateManagerFragment) fragmentManager.findFragmentByTag(mStateMaintainerTAG);

        if (mStateManagerFragment == null) {
            mStateManagerFragment = new StateManagerFragment();
            fragmentManager.beginTransaction().add(mStateManagerFragment, mStateMaintainerTAG).commit();
            return true;
        } else {
            return false;
        }
    }

    public void put(Object object) {
        mStateManagerFragment.put(object);
    }

    public void put(String key, Object object) {
        mStateManagerFragment.put(key, object);
    }

    public <T> T get(String key) {
        return (T) mStateManagerFragment.get(key);
    }

    public boolean hasKey(String key) {
        return mStateManagerFragment.get(key) != null;
    }

    public static class StateManagerFragment extends Fragment {
        private HashMap<String, Object> mData = new HashMap<>();

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }

        public void put(Object object) {
            mData.put(object.getClass().getSimpleName(), object);
        }

        public void put(String key, Object object) {
            mData.put(key, object);
        }

        public <T> T get(String key) {
            return (T) mData.get(key);
        }
    }
}


