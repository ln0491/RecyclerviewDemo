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
 * @创建时间 2016-04-15 19:01
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>  {

    List<RecyclerBean> mDatas;

    private MyItemClickListener mMyItemClickListener;

    private MyItemLongClickListener mMyItemLongClickListener;


    /**
     * 设置点击监听事件
     */
    public interface MyItemClickListener {
        public void onItemClick(View view,int postion);
    }


    public void setMyItemClickListener(MyItemClickListener myItemClickListener) {
        mMyItemClickListener = myItemClickListener;
    }


    public interface MyItemLongClickListener{
        public void onItemLongClick(View view,int postion);
    }


    public void setMyItemLongClickListener(MyItemLongClickListener myItemLongClickListener) {
        mMyItemLongClickListener = myItemLongClickListener;
    }

    public MyRecyclerAdapter(List<RecyclerBean> data ){
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

    public class MyViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        ImageView mIvIcon;
        TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_name);
            mIvIcon = (ImageView) itemView.findViewById(R.id.iv_icon);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }






        public void setListData(RecyclerBean recyclerBean) {

            mTextView.setText(recyclerBean.name);
            mIvIcon.setImageResource(recyclerBean.icon);
        }

        @Override
        public void onClick(View v) {
            mMyItemClickListener.onItemClick(v,getAdapterPosition());
        }


        @Override
        public boolean onLongClick(View v) {


            if(mMyItemLongClickListener!=null){
                mMyItemLongClickListener.onItemLongClick(v,getAdapterPosition());
            }

            return true;
        }
    }
}
