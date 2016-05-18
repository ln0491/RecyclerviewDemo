package com.liunan.recyclerviewdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.liunan.recyclerviewdemo.adapter.MyRecyclerAdapter;
import com.liunan.recyclerviewdemo.bean.RecyclerBean;
import com.liunan.recyclerviewdemo.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MyRecyclerAdapter.MyItemClickListener, MyRecyclerAdapter.MyItemLongClickListener {

    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerview;
    List<RecyclerBean> mStaggedDatas;

    List<RecyclerBean> mDatas;
    @Bind(R.id.swiprefreshlayout)
    SwipeRefreshLayout mSwiprefreshlayout;


    private int[] mListIcons = new int[]{R.mipmap.g1, R.mipmap.g2, R.mipmap.g3, R.mipmap.g4,
            R.mipmap.g5, R.mipmap.g6, R.mipmap.g7, R.mipmap.g8, R.mipmap.g9, R.mipmap.g10, R
            .mipmap.g11, R.mipmap.g12, R.mipmap.g13, R.mipmap.g14, R.mipmap.g15, R.mipmap
            .g16, R.mipmap.g17, R.mipmap.g18, R.mipmap.g19, R.mipmap.g20, R.mipmap.g21, R
            .mipmap.g22, R.mipmap.g23, R.mipmap.g24, R.mipmap.g25, R.mipmap.g26, R.mipmap
            .g27, R.mipmap.g28, R.mipmap.g29};


    private int[] mStraggeredIcons = new int[]{R.mipmap.p1, R.mipmap.p2, R.mipmap.p3, R
            .mipmap.p4, R.mipmap.p5, R.mipmap.p6, R.mipmap.p7, R.mipmap.p8, R.mipmap.p9, R
            .mipmap.p10, R.mipmap.p11, R.mipmap.p12, R.mipmap.p13, R.mipmap.p14, R.mipmap
            .p15, R.mipmap.p16, R.mipmap.p17, R.mipmap.p18, R.mipmap.p19, R.mipmap.p20, R
            .mipmap.p21, R.mipmap.p22, R.mipmap.p23, R.mipmap.p24, R.mipmap.p25, R.mipmap
            .p26, R.mipmap.p27, R.mipmap.p28, R.mipmap.p29, R.mipmap.p30, R.mipmap.p31, R
            .mipmap.p32, R.mipmap.p33, R.mipmap.p34, R.mipmap.p35, R.mipmap.p36, R.mipmap
            .p37, R.mipmap.p38, R.mipmap.p39, R.mipmap.p40, R.mipmap.p41, R.mipmap.p42, R
            .mipmap.p43, R.mipmap.p44};


    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }

    private void initView() {

        mSwiprefreshlayout.setColorSchemeColors(Color.RED,Color.GREEN,Color.BLUE);




    }

    /**
     * 初始化监听器
     */
    private void initListener() {

        mSwiprefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "刷新成功", Toast.LENGTH_SHORT).show();
                        // 刷新UI
                        mRecyclerview.getAdapter().notifyDataSetChanged();
                        //刷新结束
                        mSwiprefreshlayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {

        mDatas = new ArrayList<RecyclerBean>();

        for (int i = 0; i < mListIcons.length; i++) {
            RecyclerBean recyclerBean = new RecyclerBean();

            recyclerBean.name = "我是Item - " + i;

            recyclerBean.icon = mListIcons[i];

            mDatas.add(recyclerBean);


        }

        mStaggedDatas = new ArrayList<RecyclerBean>();
        for (int i = 0; i < mStraggeredIcons.length; i++) {
            RecyclerBean recyclerBean = new RecyclerBean();

            recyclerBean.name = "我是Item - " + i;

            recyclerBean.icon = mStraggeredIcons[i];

            mStaggedDatas.add(recyclerBean);
        }


        //默认列表纵向
        initListV();


    }

    /**
     * 默认列表纵向
     */
    private void initListV() {
        //设置布局管理器

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(layoutManager);


        mRecyclerview.setItemAnimator(new DefaultItemAnimator());

        mRecyclerview.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
        //设置适配器
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(mDatas);


        adapter.setMyItemClickListener(this);

        adapter.setMyItemLongClickListener(this);

        mRecyclerview.setAdapter(adapter);
    }

    /**
     * 默认列表横向
     */
    private void initListH() {
        //设置布局管理器

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerview.setLayoutManager(layoutManager);
        //设置适配器
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(mDatas);


        mRecyclerview.setAdapter(adapter);
    }

    /**
     * 默认表格纵向
     */
    private void initGridV() {
        //设置布局管理器

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        mRecyclerview.setLayoutManager(layoutManager);
        //设置适配器
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(mDatas);


        mRecyclerview.setAdapter(adapter);
    }


    /**
     * 默认表格纵向
     */
    private void initGridH() {
        //设置布局管理器

        GridLayoutManager layoutManager = new GridLayoutManager(this, 4, GridLayoutManager.HORIZONTAL, false);
        mRecyclerview.setLayoutManager(layoutManager);
        //设置适配器
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(mDatas);


        mRecyclerview.setAdapter(adapter);
    }

    /**
     * 默认瀑布流纵向
     */
    private void initStaggedV() {
        //设置布局管理器

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerview.setLayoutManager(layoutManager);
        //设置适配器
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(mStaggedDatas);


        mRecyclerview.setAdapter(adapter);
    }

    /**
     * 默认瀑布流纵向
     */
    private void initStaggedH() {
        //设置布局管理器

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
        mRecyclerview.setLayoutManager(layoutManager);
        //设置适配器
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(mStaggedDatas);


        mRecyclerview.setAdapter(adapter);
    }





    /**
     * 建立ActionBar菜单
     *
     * @param menu 菜单
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 有菜单被选择时
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.menu_list_v:
                //列表纵向
                initListV();
                break;
            case R.id.menu_list_h:
                //列表横向
                initListH();
                break;
            case R.id.menu_grid_v:
                //表格纵向
                initGridV();
                break;
            case R.id.menu_grid_h:
                //表格横向
                initGridH();
                break;
            case R.id.menu_stagged_v:
                //瀑布流纵向
                initStaggedV();
                break;
            case R.id.menu_stagged_h:
                //瀑布流横向
                initStaggedH();
                break;
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(View view, int postion) {

        RecyclerBean recyclerBean = mDatas.get(postion);

        if(recyclerBean!=null){
            Toast.makeText(MainActivity.this, "RecyclerBean: "+recyclerBean.name, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onItemLongClick(View view, int postion) {

        RecyclerBean recyclerBean = mDatas.get(postion);
        if(recyclerBean!=null){


            Toast.makeText(MainActivity.this, "长按事件:"+recyclerBean.name+"===>", Toast.LENGTH_SHORT).show();
        }


    }
}
