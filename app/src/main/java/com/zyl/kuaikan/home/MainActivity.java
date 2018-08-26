package com.zyl.kuaikan.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.GridView;

import com.zyl.kuaikan.bean.LoginUserBean;
import com.zyl.kuaikan.chapterList.ChapterListActivity;
import com.zyl.kuaikan.R;
import com.zyl.kuaikan.adapter.CartoonItemAdapter;
import com.zyl.kuaikan.base.BaseActivity;
import com.zyl.kuaikan.bean.PopularCartoon;
import com.zyl.kuaikan.bean.SearchAutoComp;
import com.zyl.kuaikan.util.Utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends BaseActivity<HomePageContract.Presenter> implements HomePageContract.View,AdapterView.OnItemClickListener,TextWatcher{
    private static final String TAG="MainActivity";
    private List<PopularCartoon> list;
    private AutoCompleteTextView textViewInput;
    private Button bt_search,bt_day1,bt_day2,bt_day3,bt_day4,bt_day5,bt_day6,bt_day7;
    private CartoonItemAdapter mAdapter;
    private List<SearchAutoComp.DataBean.TopicBean> autoComps=null;
    private GridView mGridView;
    private ContentLoadingProgressBar progressBar;
    private int dayIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list=new ArrayList<>();
        mAdapter=new CartoonItemAdapter(this);

        initView();
        changeDayText();
        presenter.loadPopCartoons(false,0);
    }

    private void initView(){
        super.initView(this);
        textViewInput=findViewById(R.id.auto_search);
        bt_search=findViewById(R.id.bt_search);
        bt_day1=findViewById(R.id.bt_day1);
        bt_day2=findViewById(R.id.bt_day2);
        bt_day3=findViewById(R.id.bt_day3);
        bt_day4=findViewById(R.id.bt_day4);
        bt_day5=findViewById(R.id.bt_day5);
        bt_day6=findViewById(R.id.bt_day6);
        bt_day7=findViewById(R.id.bt_day7);
        mGridView=findViewById(R.id.gridView);
        progressBar=findViewById(R.id.progressbar);
        textViewInput.addTextChangedListener(this);
        bt_search.setOnClickListener(this);
        bt_day1.setOnClickListener(this);
        bt_day2.setOnClickListener(this);
        bt_day3.setOnClickListener(this);
        bt_day4.setOnClickListener(this);
        bt_day5.setOnClickListener(this);
        bt_day6.setOnClickListener(this);
        bt_day7.setOnClickListener(this);
        mGridView.setOnItemClickListener(this);
        mGridView.setEmptyView(progressBar);
        mGridView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.bt_search:{
                break;
            }
            case R.id.bt_day1:{
                dayIndex=0;
                changepopList();
                break;
            }
            case R.id.bt_day2:{
                dayIndex=1;
                changepopList();
                break;
            }
            case R.id.bt_day3:{
                dayIndex=2;
                changepopList();
                break;
            }
            case R.id.bt_day4:{
                dayIndex=3;
                changepopList();
                break;
            }
            case R.id.bt_day5:{
                dayIndex=4;
                changepopList();
                break;
            }
            case R.id.bt_day6:{
                dayIndex=5;
                changepopList();
                break;
            }
            case R.id.bt_day7:{
                dayIndex=6;
                changepopList();
                break;
            }
        }
    }
    private void changepopList(){
        changeDayTextColor();
        list.clear();
        mAdapter.notifyDataSetChanged();
        presenter.loadPopCartoons(false,dayIndex);
    }
    private void changeDayText(){
        Date date= Utilities.getNetTime();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        String[] week=getResources().getStringArray(R.array.week);
        int i=calendar.get(Calendar.DAY_OF_WEEK)-1;
        if(i<0)i=0;
        bt_day1.setText(getString(R.string.today));
        bt_day2.setText(getString(R.string.yesterday));
        bt_day3.setText(week[(i+5)%7]);
        bt_day4.setText(week[(i+4)%7]);
        bt_day5.setText(week[(i+3)%7]);
        bt_day6.setText(week[(i+2)%7]);
        bt_day7.setText(week[(i+1)%7]);
    }

    private void changeDayTextColor(){
        bt_day1.setTextColor(dayIndex==0?Color.parseColor("#FFE600"):Color.BLACK);
        bt_day2.setTextColor(dayIndex==1?Color.parseColor("#FFE600"):Color.BLACK);
        bt_day3.setTextColor(dayIndex==2?Color.parseColor("#FFE600"):Color.BLACK);
        bt_day4.setTextColor(dayIndex==3?Color.parseColor("#FFE600"):Color.BLACK);
        bt_day5.setTextColor(dayIndex==4?Color.parseColor("#FFE600"):Color.BLACK);
        bt_day6.setTextColor(dayIndex==5?Color.parseColor("#FFE600"):Color.BLACK);
        bt_day7.setTextColor(dayIndex==6?Color.parseColor("#FFE600"):Color.BLACK);
    }

    @Override
    public HomePageContract.Presenter initPresenter() {
        return new HomePagerPresenter(this);
    }

    @Override
    public void setDayPops(List<PopularCartoon> popularCartoons) {
        list.addAll(popularCartoons);
        mAdapter.bindData(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setSearchAutoComp(SearchAutoComp searchAutoComp) {
        autoComps=searchAutoComp.getData().getTopic();
        String[] mSearchArray=new String[autoComps.size()];
        for(int i=0;i<autoComps.size();i++){
            mSearchArray[i]=autoComps.get(i).getTitle();
        }
        ArrayAdapter<String> mSearchAdapter=new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,mSearchArray);
        textViewInput.setAdapter(mSearchAdapter);
        mSearchAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String url=list.get(position).getUrlDetail();
        Intent intent=new Intent(this,ChapterListActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String keyWord=editable.toString();
        presenter.getAutoCompBindList(keyWord);
    }
}
