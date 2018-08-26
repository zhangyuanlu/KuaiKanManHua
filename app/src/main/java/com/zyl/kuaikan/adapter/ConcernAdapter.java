package com.zyl.kuaikan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zyl.kuaikan.R;
import com.zyl.kuaikan.bean.UserTopicsBean;

import java.util.List;

public class ConcernAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private static final String TAG="ConcernAdapter";
    private Context context;
    private List<UserTopicsBean.DataBean.TopicsBean> topicsList;
    private onClickItemListener listener;
    public ConcernAdapter(Context context){
        this.context=context;
    }

    @Override
    public void onClick(View view) {
        int position= (Integer) view.getTag();
        listener.onClickItem(topicsList.get(position).getId());
    }

    public interface onClickItemListener{
        void onClickItem(int id);
    }
    public void setOnClickItemListener(onClickItemListener listener){
        this.listener=listener;
    }
    public void bindData(List<UserTopicsBean.DataBean.TopicsBean> topicsList){
        this.topicsList=topicsList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.concern_list_item,parent,false);
        RecyclerView.ViewHolder viewHolder=new NViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((NViewHolder)holder).draweeView.setImageURI(topicsList.get(position).getCover_image_url());
        ((NViewHolder)holder).tv_author.setText(topicsList.get(position).getUser().getNickname());
        ((NViewHolder)holder).tv_title.setText(topicsList.get(position).getTitle());
        ((NViewHolder)holder).tv_state.setText(topicsList.get(position).getLatest_comic_title());
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        if(topicsList!=null) {
            return topicsList.size();
        }else{
            return 0;
        }
    }
    class NViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView draweeView;
        TextView tv_title,tv_author,tv_state;
        public NViewHolder(View itemView) {
            super(itemView);
            draweeView=itemView.findViewById(R.id.sdv_cover);
            tv_title=itemView.findViewById(R.id.tv_title);
            tv_author=itemView.findViewById(R.id.tv_author);
            tv_state=itemView.findViewById(R.id.tv_state);
        }
    }
}
