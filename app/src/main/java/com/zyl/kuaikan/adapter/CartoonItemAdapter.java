package com.zyl.kuaikan.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zyl.kuaikan.R;
import com.zyl.kuaikan.bean.PopularCartoon;

import java.util.List;

public class CartoonItemAdapter extends BaseAdapter{
    private Context mContext;
    private List<PopularCartoon> mCartoons;

    public CartoonItemAdapter(Context context){
        this.mContext=context;
    }

    public void bindData(List<PopularCartoon> cartoons){
        this.mCartoons=cartoons;
    }

    @Override
    public int getCount() {
        if(mCartoons!=null){
            return mCartoons.size();
        }else{
            return 0;
        }
    }

    @Override
    public PopularCartoon getItem(int position) {
        return mCartoons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView==null){
            holder=new Holder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.item_home_pop,null);
            holder.draweeView=convertView.findViewById(R.id.iv_picture);
            holder.tv_author=convertView.findViewById(R.id.tv_author);
            holder.tv_popIndex=convertView.findViewById(R.id.tv_popIndex);
            holder.tv_title=convertView.findViewById(R.id.tv_title);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }
        holder.draweeView.setImageURI(Uri.parse(mCartoons.get(position).getBitmapUrl()));
        holder.tv_title.setText(mCartoons.get(position).getTitle());
        holder.tv_author.setText(mCartoons.get(position).getAuthor());
        holder.tv_popIndex.setText(mCartoons.get(position).getPopIndex());
        return convertView;
    }
    class Holder{
        SimpleDraweeView draweeView;
        TextView tv_author,tv_popIndex,tv_title;
    }
}
