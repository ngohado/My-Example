package com.hado.myexample.pagertransformer;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hado.myexample.R;
import com.hado.myexample.pagertransformer.glide.BitmapViewBackgroundTarget;
import com.hado.myexample.util.StringUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClockScreenFragment extends Fragment {
    private static final String DATA = "DATA";

    @Bind(R.id.tv_days)
    public TextView tvDay;

    @Bind(R.id.tv_temperature)
    public TextView tvTemperature;

    @Bind(R.id.img_background)
    public ImageView ivBackground;

    @Bind(R.id.container)
    public RelativeLayout container;

    public static ClockScreenFragment getInstance(PagerItem item) {
        ClockScreenFragment fragment = new ClockScreenFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(DATA, item);
        fragment.setArguments(bundle);
        return fragment;
    }

    public ClockScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clock_screen, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PagerItem item = handleArgument();
        StringUtil.displayText(item.day, tvDay);
        StringUtil.displayText(item.temperature, tvTemperature);
        Glide.with(this).load(item.backgroundRes).asBitmap().centerCrop().into(new BitmapViewBackgroundTarget(container));
        Glide.with(this).load(item.backgroundRes).into(ivBackground);
    }

    private PagerItem handleArgument() {
        Bundle bundle = getArguments();
        return bundle.getParcelable(DATA);
    }

}
