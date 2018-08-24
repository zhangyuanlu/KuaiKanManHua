package com.zyl.kuaikan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zyl.kuaikan.R;
import com.zyl.kuaikan.bean.ChapterContentBean;

public class ChapterContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    private static final String TAG="ChapterContentAdapter";

    private Context context;
    private ChapterContentBean contentBean;
    private onClickItemListener listener;

    private static final int TYPE_NORMAL=0;
    private static final int TYPE_HEAD=1;
    private static final int TYPE_FOOT=2;
    public  ChapterContentAdapter(Context context){
        this.context=context;
    }
    public void bindData(ChapterContentBean bean){
        this.contentBean=bean;
    }
    public void setonClickItemListener(onClickItemListener listener){
        this.listener=listener;
    }

    public interface onClickItemListener{
        void onClickNext(String url);
        void onClickLast(String url);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        RecyclerView.ViewHolder viewHolder=null;
        switch (viewType){
            case TYPE_HEAD:{
                View view=layoutInflater.inflate(R.layout.chapter_content_item_foot_head,null);
                viewHolder=new HeadViewHolder(view);
                break;
            }
            case TYPE_NORMAL:{
                View view=layoutInflater.inflate(R.layout.chapter_content_item_normal,null);
                viewHolder=new NormalViewHolder(view);
                break;
            }
            default:
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof HeadViewHolder){
            ((HeadViewHolder) holder).bt_next.setTag(contentBean.getNextUrl());
            ((HeadViewHolder) holder).bt_last.setTag(contentBean.getLastUrl());
        }else if(holder instanceof NormalViewHolder){
            ((NormalViewHolder) holder).draweeView.setImageURI(contentBean.getPictureList().get(position-1));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0||position==getItemCount()-1){
            return TYPE_HEAD;
        }else{
            return TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        if(contentBean==null){
            return 0;
        }else{
            return contentBean.getPictureList().size()+2;
        }
    }

    class HeadViewHolder extends RecyclerView.ViewHolder{
        Button bt_last,bt_next;
        public HeadViewHolder(View itemView) {
            super(itemView);
            bt_last=itemView.findViewById(R.id.bt_last);
            bt_next=itemView.findViewById(R.id.bt_next);
            bt_last.setOnClickListener(ChapterContentAdapter.this);
            bt_next.setOnClickListener(ChapterContentAdapter.this);
        }
    }
    class NormalViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView draweeView;
        public NormalViewHolder(View itemView) {
            super(itemView);
            draweeView=itemView.findViewById(R.id.sdv_content);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_last:{
                listener.onClickLast((String) v.getTag());
                break;
            }
            case R.id.bt_next:{
                listener.onClickNext((String) v.getTag());
                break;
            }
            default:
                break;
        }
    }
}
