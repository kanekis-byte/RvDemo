package com.taohf.rvdemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * RvAdapter
 *
 * @author hongfei.tao
 * @date 2018/10/31 15:28
 */
public class RvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ISortInter mHost;
    private List<ItemBean> mDataList;

    public RvAdapter(List<ItemBean> list, ISortInter host) {
        mDataList = list;
        mHost = host;
    }

    public void setDataList(List<ItemBean> dataList) {
        this.mDataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        switch (type) {
            case 0:
                View sortView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sort_view, viewGroup, false);
                final SortViewHolder holder = new SortViewHolder(sortView);
                holder.mTvTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getAdapterPosition();
                        ItemBean itemBean = mDataList.get(position);
                        mHost.onSort(itemBean.getSortKey(), itemBean.getSortType() == ESortType.ASC ? ESortType.DESC : ESortType.ASC);
                    }
                });
                return holder;
            case 1:
            default:
                View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view, viewGroup, false);
                return new ItemViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ItemBean itemBean = mDataList.get(viewHolder.getAdapterPosition());

        switch (getItemViewType(position)) {
            case 0:
                if (viewHolder instanceof SortViewHolder) {
                    ((SortViewHolder) viewHolder).mTvTitle.setText(itemBean.getSortKey());
                }
                break;
            case 1:
            default:
                if (viewHolder instanceof ItemViewHolder) {
                    ((ItemViewHolder) viewHolder).mTvContent.setText(itemBean.getDesc());
                }
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        ItemBean itemBean = mDataList.get(position);
        switch (itemBean.getType()) {
            case SORT_TYPE:
                return 0;
            case ITEM_TYPE:
            default:
                return 1;
        }
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvContent;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvContent = itemView.findViewById(R.id.tv_content);
        }
    }

    static class SortViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvTitle;

        public SortViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_title);
        }
    }

}
