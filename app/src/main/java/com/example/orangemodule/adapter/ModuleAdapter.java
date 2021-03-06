package com.example.orangemodule.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.orangemodule.R;
import com.example.orangemodule.activity.UDiskActivity;
import com.example.orangemodule.bean.ModuleBean;
import com.example.orangemodule.dialog.PwdDialog;

import java.util.List;

/**
 * Created by 杨健 on 2017/6/5.
 * function: module界面的adapter
 */

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ViewHolder> {

    private Context context;
    private List<ModuleBean> data;
    private OnItemClickListener onItemClickListener = null;

    public ModuleAdapter(Context context, List<ModuleBean> data) {
        this.context = context;
        this.data = data;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_module, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (data != null) {
            ModuleBean moduleBean = data.get(position);
            holder.moduleName.setText(moduleBean.getModuleName());
            holder.moduleLayout.setBackgroundColor(Color.parseColor(moduleBean.getModuleBackground()));
        }
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int p = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, p);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView moduleName;
        private LinearLayout moduleLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            moduleName = (TextView) itemView.findViewById(R.id.module_name);
            moduleLayout = (LinearLayout) itemView.findViewById(R.id.module_layout);
        }
    }

    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


}
