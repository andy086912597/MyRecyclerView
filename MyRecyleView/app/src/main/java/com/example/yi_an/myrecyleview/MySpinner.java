package com.example.yi_an.myrecyleview;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yi_An on 2016/11/2.
 */

public class MySpinner extends Button {
    private SpinnerListener mListener;
    private List<Integer> list=new ArrayList<Integer>();
    public MySpinner(Context context) {
        super(context);
    }
    public MySpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    private String myStringBuffer ;
    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text+"    ▼", type);
    }
    public void initContent(Activity activity, final String[] m, final int position) {

        try {
            mListener = (SpinnerListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener "+e.getMessage()+" "+e.getLocalizedMessage());
        }

        setText(m[0], null);
        OnClickListener listener=new OnClickListener() {
            @Override
            public void onClick(View v) {
                final SpinnerDialog dialog=new SpinnerDialog(getContext());
                TextView textView=(TextView) dialog.findViewById(R.id.over_text);
                textView.setText("完成");
                textView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(list.size()==0){
                            setText(m[0], null);
                        }else{
                            StringBuffer buffer=new StringBuffer();
                            int size=list.size();
                            for (int i = 0; i < size; i++) {
                                buffer.append(m[list.get(i)]+',');
                            }
                            setText(buffer.toString().substring(0, buffer.length()-1),null);
                            myStringBuffer = buffer.toString().substring(0, buffer.length()-1);
                            mListener.getItem(myStringBuffer,position);
                        }
                        dialog.cancel();
                        list.clear();
                    }
                });
                dialog.show();
                ListView listView=(ListView) dialog.findViewById(R.id.listview);
                listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>
                        (getContext(),android.R.layout.simple_list_item_multiple_choice,m);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1,
                                            int arg2, long arg3) {
                        Integer object=Integer.valueOf(arg2);
                        if(list.contains(object)){
                            list.remove(object);
                        }else{
                            list.add(object);
                        }
                    }
                });
                dialog.show();
            }
        };
        super.setOnClickListener(listener);
    }

    public String getItem(){
        return myStringBuffer;
    }

    private class SpinnerDialog extends Dialog {
        public SpinnerDialog(Context context) {
            super(context, R.style.Theme_dialog);
            setContentView(R.layout.spiiner);
            Window window = getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.gravity = Gravity.CENTER;
            DisplayMetrics dm = new DisplayMetrics();
            getWindow().getWindowManager().getDefaultDisplay().getMetrics(dm);
            int screenWidth = dm.widthPixels;
            int screenHeigh = dm.heightPixels;
            params.height=(int) (screenHeigh*0.5);
            params.width=(int) (screenWidth*0.5);
            window.setAttributes(params);
        }
    }
    public interface SpinnerListener {
        public  void getItem(String value,int position);
    }
}
