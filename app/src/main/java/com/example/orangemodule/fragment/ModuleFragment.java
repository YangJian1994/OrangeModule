package com.example.orangemodule.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.orangemodule.R;
import com.example.orangemodule.adapter.ModuleAdapter;
import com.example.orangemodule.bean.ModuleBean;
import com.example.orangemodule.dialog.PwdDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ModuleFragment extends Fragment {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private static final String[] MODULE_NAMES = {"空气模块", "电池模块", "U盘模块", "蓝牙模块", "手环模块"};
    private static final String[] MODULE_COLORS = {"#FF4081", "#FFC107", "#4CAF50", "#FF5722", "#2196F3", "#9C27B0"};

    private List<ModuleBean> data = new ArrayList<>();
    private ModuleAdapter adapter;

    private PwdDialog pwdDialog;

    public ModuleFragment() {
        // Required empty public constructor
    }

    public static ModuleFragment newInstance() {

        Bundle args = new Bundle();

        ModuleFragment fragment = new ModuleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_module, container, false);
        ButterKnife.bind(this, view);
        pwdDialog = new PwdDialog(getActivity());
        initData();
        initView();
        initListener();
        return view;
    }

    //初始化数据
    private void initData() {
        for (int i = 0; i < MODULE_NAMES.length; i++) {
            data.add(new ModuleBean(MODULE_NAMES[i], MODULE_COLORS[i]));
        }
    }

    //初始化界面
    private void initView() {
        adapter = new ModuleAdapter(getActivity(), data);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    //初始化监听
    private void initListener() {
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.YELLOW);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新模块显示
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        adapter.setOnItemClickListener(new ModuleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == 2) {
                    pwdDialog.show();
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        pwdDialog.dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
