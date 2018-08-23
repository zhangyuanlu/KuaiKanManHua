package com.zyl.kuaikan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zyl.kuaikan.R;
import com.zyl.kuaikan.bean.ChapterListBean;

public class ChapterListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String TAG="ChapterListAdapter";
    public static final int TYPE_HEAD=1;
    public static final int TYPE_NORMAL=2;
    public static final int TYPE_FOOT=3;

    private Context mContext;
    private ChapterListBean chapterListBean;

    public ChapterListAdapter(Context context){
        this.mContext=context;
    }
    public void bindData(ChapterListBean bean){
        this.chapterListBean=bean;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        RecyclerView.ViewHolder viewHolder=null;
        switch (viewType){
            case TYPE_HEAD:{
                View view=layoutInflater.inflate(R.layout.chapter_list_item_head,null);
                viewHolder=new HeadViewHolder(view);
                break;
            }
            case TYPE_NORMAL:{
                View view=layoutInflater.inflate(R.layout.chapter_list_item_normal,null);
                viewHolder=new NormalViewHolder(view);
                break;
            }
            case TYPE_FOOT:{
                View view=layoutInflater.inflate(R.layout.chapter_list_item_foot,null);
                viewHolder=new FootViewHolder(view);
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
            ((HeadViewHolder) holder).draweeView.setImageURI(chapterListBean.getCoverUrl());
            ((HeadViewHolder) holder).tv_title.setText(chapterListBean.getTitle());
            ((HeadViewHolder) holder).tv_author.setText(chapterListBean.getAuthor());
            ((HeadViewHolder) holder).tv_brief.setText(chapterListBean.getBrief());
            ((HeadViewHolder) holder).tv_heat.setText(chapterListBean.getHeatIndex());
            ((HeadViewHolder) holder).tv_praise.setText(chapterListBean.getPraiseIndex());
        }else if(holder instanceof NormalViewHolder){
            ((NormalViewHolder) holder).draweeView.setImageURI(chapterListBean.getChapterItems().get(position).getChapterCoverUrl());
            ((NormalViewHolder) holder).tv_title.setText(chapterListBean.getChapterItems().get(position).getChapterTitle());
            ((NormalViewHolder) holder).tv_index.setText(chapterListBean.getChapterItems().get(position).getChapterPraise());
            ((NormalViewHolder) holder).tv_time.setText(chapterListBean.getChapterItems().get(position).getChapterTime());
        }else{

        }
    }

    @Override
    public int getItemCount() {
        if(chapterListBean==null){
            return 0;
        }else{
            return chapterListBean.getChapterItems().size()+2;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE_HEAD;
        }else if(position==chapterListBean.getChapterItems().size()+1){
            return TYPE_FOOT;
        }else{
            return TYPE_NORMAL;
        }
    }

    class HeadViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView draweeView;
        TextView tv_title,tv_author,tv_brief,tv_heat,tv_praise;
        Button bt_first,bt_follow;
        public HeadViewHolder(View itemView) {
            super(itemView);
            draweeView=itemView.findViewById(R.id.sdv_cover);
            tv_title=itemView.findViewById(R.id.tv_title);
            tv_author=itemView.findViewById(R.id.tv_author);
            tv_brief=itemView.findViewById(R.id.tv_brief);
            tv_heat=itemView.findViewById(R.id.tv_heat_index);
            tv_praise=itemView.findViewById(R.id.tv_pop_index);
            bt_first=itemView.findViewById(R.id.bt_go_first);
            bt_follow=itemView.findViewById(R.id.bt_follow);
        }
    }
    class NormalViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView draweeView;
        TextView tv_title,tv_index,tv_time;
        public NormalViewHolder(View itemView) {
            super(itemView);
            draweeView=itemView.findViewById(R.id.sdv_chapter);
            tv_title=itemView.findViewById(R.id.tv_chapter_title);
            tv_index=itemView.findViewById(R.id.tv_chapter_index);
            tv_time=itemView.findViewById(R.id.tv_chapter_time);
        }
    }
    class FootViewHolder extends RecyclerView.ViewHolder{
        RecyclerView recyclerView;
        public FootViewHolder(View itemView) {
            super(itemView);
            recyclerView=itemView.findViewById(R.id.recyclerView_foot);
        }
    }
}
