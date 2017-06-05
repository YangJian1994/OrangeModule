package com.example.orangemodule.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.orangemodule.R;

import java.util.List;

/**
 * Created by 杨健 on 2017/6/5.
 * function: 商城界面的adapter
 */

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    private int[] data;
    private Context context;

    public ShopAdapter(int[] data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shop, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (data != null) {
            holder.moduleImage.setImageResource(data[position]);
        }
    }

    @Override
    public int getItemCount() {
        return data != null ? data.length : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView moduleImage;

        public ViewHolder(View itemView) {
            super(itemView);
            moduleImage = (ImageView) itemView.findViewById(R.id.module_image);
        }
    }
}
