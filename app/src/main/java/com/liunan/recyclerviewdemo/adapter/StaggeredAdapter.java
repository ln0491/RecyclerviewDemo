package com.liunan.recyclerviewdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liunan.recyclerviewdemo.R;
import com.liunan.recyclerviewdemo.bean.RecyclerBean;

import java.util.List;

/**
 * @创建者 刘楠
 * @创建时间 2016-04-15 19:48
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 */
public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.MyViewHolder>{

    List<RecyclerBean> mDatas;


    public StaggeredAdapter(List<RecyclerBean> data ){
        mDatas = data;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        RecyclerBean recyclerBean = mDatas.get(position);

        holder.setListData(recyclerBean);
    }

    @Override
    public int getItemCount() {
        if(mDatas!=null){
            return mDatas.size();
        }
        return 0;
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{

        ImageView mIvIcon;
        TextView  mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_name);
            mIvIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
        }

        public void setListData(RecyclerBean recyclerBean) {

            mTextView.setText(recyclerBean.name);
            mIvIcon.setImageResource(recyclerBean.icon);
        }
    }
}
