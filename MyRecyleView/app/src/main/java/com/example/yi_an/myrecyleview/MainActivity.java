package com.example.yi_an.myrecyleview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MySpinner.SpinnerListener{
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    private String [] myDataset;
    private List<MyItem> myItemList;
    private String [] selectdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDataset  = new String[]{"test","test1","test3","4","5","6","7","8"};
        myItemList = new ArrayList<>();
        MyItem myItem1 = new MyItem("1","select","1/2/3/4/5/6");
        MyItem myItem2 = new MyItem("2","select","2/3/4/5/6/7");
        MyItem myItem3 = new MyItem("3","text",null);
        MyItem myItem4 = new MyItem("1","select","1/2/3/4/5/6");
        MyItem myItem5 = new MyItem("2","select","2/3/4/5/6/7");
        MyItem myItem6 = new MyItem("3","text","QAAQ");
        MyItem myItem7 = new MyItem("1","select","1/2/3/4/5/6");
        MyItem myItem8 = new MyItem("2","select","2/3/4/5/6/7");
        MyItem myItem9 = new MyItem("3","text","XDDDDD");
        MyItem myItem10 = new MyItem("1","select","1/2/3/4/5/6");
        MyItem myItem11 = new MyItem("2","select","2/3/4/5/6/7");
        MyItem myItem12 = new MyItem("3","text","@@");
        myItemList.add(myItem1);
        myItemList.add(myItem2);
        myItemList.add(myItem3);
        myItemList.add(myItem4);
        myItemList.add(myItem5);
        myItemList.add(myItem6);
        myItemList.add(myItem7);
        myItemList.add(myItem8);
        myItemList.add(myItem9);
        myItemList.add(myItem10);
        myItemList.add(myItem11);
        myItemList.add(myItem12);
        myItemList.add(myItem10);
        myItemList.add(myItem11);
        myItemList.add(myItem12);
        myItemList.add(myItem6);
        myItemList.add(myItem7);
        myItemList.add(myItem8);
        myItemList.add(myItem9);
        myItemList.add(myItem10);
        myItemList.add(myItem11);
        myItemList.add(myItem12);
        myItemList.add(myItem10);
        myItemList.add(myItem11);
        myItemList.add(myItem12);
        selectdata = new String[myItemList.size()];
        mRecyclerView = (RecyclerView)findViewById(R.id.myrecycle);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(this,myItemList);
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    public void getItem(String value,int position) {
        Log.d("spinner",position+":"+value+"");
        selectdata[position]=value;

    }

    private class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        private  String[] mdataset;
        private  List<MyItem> list;
        private  LayoutInflater  mLayoutInflater;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public MySpinner mySpinner;
            public ViewHolder(View v) {
                super(v);
                mySpinner = (MySpinner)v.findViewById(R.id.button);
            }
        }

        public class ViewHolder1 extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public ViewHolder1(View v) {
                super(v);
                mTextView = (TextView) v.findViewById(R.id.textView);

            }
        }

        public MyAdapter(Context context, List<MyItem> myItem ){
//            list.add(dataset);
            list = myItem;
            mLayoutInflater = LayoutInflater.from(context);
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.d("recycle","onCreateViewHolder");
//            View v = mLayoutInflater.inflate(R.layout.my_text_view,parent,false);
//            ViewHolder viewHolder = new ViewHolder(v);
            if(viewType==0) {
                View v = mLayoutInflater.inflate(R.layout.my_spinner, parent, false);
                return new ViewHolder(v);
            }else {
                View v = mLayoutInflater.inflate(R.layout.my_text_view, parent, false);
                return new ViewHolder1(v);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            Log.d("recycle","onBindViewHolder");
                Log.d("position",position+"");
                if(holder instanceof ViewHolder){
                    ((ViewHolder) holder).mySpinner.initContent(MainActivity.this,list.get(position).value.split("/"),position);
                    if(selectdata[position]!=null){
                        ((ViewHolder) holder).mySpinner.setText(selectdata[position],null);
                    }
                    for(int x = 0; x<selectdata.length;x++){
                        if(selectdata[x]!=null)
                            Log.d("selectdata",selectdata[x]);
                    }
                }else if(holder instanceof ViewHolder1){
                    ((ViewHolder1) holder).mTextView.setText(list.get(position).value);
                }
        }

        @Override
        public int getItemViewType(int position) {
            Log.d("recycle","getItemViewType");
            //Enum类提供了一个ordinal()方法，返回枚举类型的序数，这里ITEM_TYPE.ITEM1.ordinal()代表0， ITEM_TYPE.ITEM2.ordinal()代表1
            if("select".equals(list.get(position).type)){
                return 0;
            }else{
                return 1;
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }


    }
}

