package com.example.orangemodule.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.orangemodule.R;
import com.example.orangemodule.adapter.ShopAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFragment extends Fragment {


    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private static final int[] MODULE_IMAGES = {R.drawable.module_base, R.drawable.module_air, R.drawable.module_battery,
                                                R.drawable.module_breathalyzer, R.drawable.module_led, R.drawable.module_sd,
                                                R.drawable.module_speaker, R.drawable.module_temperature, R.drawable.module_usb};

    private ShopAdapter adapter;

    public ShopFragment() {
        // Required empty public constructor
    }

    public static ShopFragment newInstance() {

        Bundle args = new Bundle();

        ShopFragment fragment = new ShopFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        ButterKnife.bind(this, view);
        initView();
        initListener();
        return view;
    }

    //初始化界面
    private void initView() {
        adapter = new ShopAdapter(MODULE_IMAGES, getActivity());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    //初始化监听
    private void initListener() {
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.YELLOW);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新界面
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
