package com.taohf.rvdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements ISortInter {

    private RecyclerView mRvContent;

    private Map<String, List<ItemBean>> mDataMap;
    private List<ItemBean> mContentList;

    private RvAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRvContent = findViewById(R.id.rv_content);

        mockData();

        mAdapter = new RvAdapter(mContentList, this);
        mRvContent.setLayoutManager(new LinearLayoutManager(this));
        mRvContent.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRvContent.setAdapter(mAdapter);
    }

    private void mockData() {
        mDataMap = new LinkedHashMap<>();

        for (int i = 0; i < 10; i++) {
            List<ItemBean> list = new ArrayList<>();
            String sortKey = "sort" + i;
            mDataMap.put(sortKey, list);

            for (int j = 0; j < 6; j++) {
                ItemBean itemBean = new ItemBean();
                if (j == 0) {
                    itemBean.setType(EItemType.SORT_TYPE);
                    itemBean.setSortKey(sortKey);
                } else {
                    itemBean.setType(EItemType.ITEM_TYPE);
                    itemBean.setIndex(j);
                    itemBean.setDesc("调试数据" + j);
                }
                list.add(itemBean);
            }
        }

        mContentList = new ArrayList<>();
        for (Map.Entry<String, List<ItemBean>> entry : mDataMap.entrySet()) {
            mContentList.addAll(entry.getValue());
        }
    }

    private void sortList(String sortKey) {
        List<ItemBean> list = mDataMap.get(sortKey);

        ItemBean first = list.remove(0);
        Collections.reverse(list);

        List<ItemBean> newList = new ArrayList<>();
        newList.add(first);
        newList.addAll(list);
        mDataMap.put(sortKey, newList);

        mContentList.clear();

        for (Map.Entry<String, List<ItemBean>> entry : mDataMap.entrySet()) {
            List<ItemBean> itemList = entry.getValue();
            mContentList.addAll(itemList);
        }

        mAdapter.setDataList(mContentList);
    }

    @Override
    public void onSort(String key, ESortType sortType) {
        sortList(key);
    }
}
