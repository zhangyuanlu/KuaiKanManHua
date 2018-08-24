package com.zyl.kuaikan.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zyl.kuaikan.R;
import com.zyl.kuaikan.bean.ChapterListBean;

import java.util.List;

public class ChapterListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{

    public static final String TAG="ChapterListAdapter";
    public static final int TYPE_HEAD=1;
    public static final int TYPE_NORMAL=2;
    public static final int TYPE_FOOT=3;

    private Context mContext;
    private ChapterListBean chapterListBean;
    private FootAdapter footAdapter;
    private OnRecyclerViewItemClickListener mListener;

    public interface OnRecyclerViewItemClickListener{
        void onClickChapter(String url);
        void onFollow();
        void onFirstChapter(String url);
        void onCommonCartoon(String url);
    }
    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener listener){
        this.mListener=listener;
    }
    public ChapterListAdapter(Context context){
        this.mContext=context;
        footAdapter=new FootAdapter(context);
    }
    public void bindData(ChapterListBean bean){
        this.chapterListBean=bean;
        footAdapter.bindData(bean.getCommonCartoons());
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
                view.setOnClickListener(this);
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
            ((NormalViewHolder) holder).draweeView.setImageURI(chapterListBean.getChapterItems().get(position-1).getChapterCoverUrl());
            ((NormalViewHolder) holder).tv_title.setText(chapterListBean.getChapterItems().get(position-1).getChapterTitle());
            ((NormalViewHolder) holder).tv_index.setText(chapterListBean.getChapterItems().get(position-1).getChapterPraise());
            ((NormalViewHolder) holder).tv_time.setText(chapterListBean.getChapterItems().get(position-1).getChapterTime());
            holder.itemView.setTag(chapterListBean.getChapterItems().get(position-1).getChapterUrl());
        }else if(holder instanceof FootViewHolder){
            if(((FootViewHolder) holder).recyclerView.getAdapter()==null) {
                ((FootViewHolder) holder).recyclerView.setAdapter(footAdapter);
            }
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

    @Override
    public void onClick(View v) {
        if(mListener!=null){
            mListener.onClickChapter((String) v.getTag());
        }
    }

    class HeadViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView draweeView;
        TextView tv_title,tv_author,tv_brief,tv_heat,tv_praise;
        Button bt_first,bt_follow,bt_more_brief;
        LinearLayout layout_bt_more_brief;
        ImageView iv_ic_more_brief;
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
            bt_more_brief=itemView.findViewById(R.id.bt_more_brief);
            layout_bt_more_brief=itemView.findViewById(R.id.layout_more_brief);
            iv_ic_more_brief=itemView.findViewById(R.id.iv_more_brief);
            bt_first.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener!=null) {
                        mListener.onFirstChapter(chapterListBean.getChapterItems().get(0).getChapterUrl());
                    }
                }
            });
            bt_follow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener!=null) {
                        mListener.onFollow();
                    }
                }
            });
            bt_more_brief.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Drawable drawable;
                    if(tv_brief.getMaxLines()==2){
                        tv_brief.setMaxLines(5);
                        drawable=mContext.getDrawable(R.drawable.ic_expand_less_black);
                    }else{
                        tv_brief.setMaxLines(2);
                        drawable=mContext.getDrawable(R.drawable.ic_expand_more_black);
                    }
                    iv_ic_more_brief.setImageDrawable(drawable);
                }
            });
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
            LinearLayoutManager layoutManager=new LinearLayoutManager(mContext);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);
        }
    }
    class FootAdapter extends RecyclerView.Adapter<FootAdapter.ViewHolder> implements View.OnTouchListener{
        private Context mContext;
        private List<ChapterListBean.CommonCartoon> commonCartoonList;
        private Handler mHandler;
        public FootAdapter(Context context){
            this.mContext=context;
            mHandler=new Handler();
        }
        public void bindData(List<ChapterListBean.CommonCartoon> list){
            this.commonCartoonList=list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(mContext).inflate(R.layout.chapter_list_item_foot_item,null);
            view.setOnTouchListener(this);
            ViewHolder holder=new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.draweeView.setImageURI(commonCartoonList.get(position).getCoverUrl());
            holder.tv_title.setText(commonCartoonList.get(position).getTitle());
            holder.tv_praise.setText(commonCartoonList.get(position).getPraiseIndex());
            holder.tv_discuss.setText(commonCartoonList.get(position).getDiscussIndex());
            holder.tv_brief.setText(commonCartoonList.get(position).getBrief());
            holder.itemView.setTag(commonCartoonList.get(position).getUrl());
        }

        @Override
        public int getItemCount() {
            return commonCartoonList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            SimpleDraweeView draweeView;
            TextView tv_title,tv_praise,tv_discuss,tv_brief;
            FrameLayout layout_brief;
            public ViewHolder(View view){
                super(view);
                draweeView=view.findViewById(R.id.sdv_comm_cover);
                tv_title=view.findViewById(R.id.tv_foot_title);
                tv_praise=view.findViewById(R.id.tv_foot_praise);
                tv_discuss=view.findViewById(R.id.tv_foot_discuss);
                tv_brief=view.findViewById(R.id.tv_foot_brief);
                layout_brief=view.findViewById(R.id.layout_foot_brief);
            }
        }

        /**
         * 由于RecycleView会将MOVE事件拦截，导致onTouch事件收不到DOWN_UP，此处非最优解
         * @param view
         * @param motionEvent
         * @return
         */
        @Override
        public boolean onTouch(final View view, MotionEvent motionEvent) {
            if(motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                view.findViewById(R.id.layout_foot_brief).setVisibility(View.VISIBLE);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.findViewById(R.id.layout_foot_brief).setVisibility(View.GONE);
                    }
                },1000);

            }else if(motionEvent.getAction()== MotionEvent.ACTION_UP){
                if(mListener!=null) {
                    mListener.onCommonCartoon((String) view.getTag());
                }
            }
            return true;
        }
    }
}
