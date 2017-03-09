package com.example.quick.index.activity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.example.quick.index.R;
import com.example.quick.index.R.layout;
import com.example.quick.index.adapter.MyAdapter;
import com.example.quick.index.bean.Cheeses;
import com.example.quick.index.bean.Person;
import com.example.quick.index.ui.QuickIndexBar;
import com.example.quick.index.ui.QuickIndexBar.OnLetterUpdateListener;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnLetterUpdateListener{

	private ListView lv_list;
	private QuickIndexBar quick_bar;
	private TextView tv_toast_index;
	private ArrayList<Person> sortList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initDate();
	}

	private void initView() {
		lv_list = (ListView) findViewById(R.id.lv_list);
		tv_toast_index = (TextView) findViewById(R.id.tv_toast_index);
		quick_bar = (QuickIndexBar) findViewById(R.id.quick_bar);
		quick_bar.SetOnLetterUpdateListener(this);
	}

	private void initDate() {
		sortList = FillDateAndSort();
		MyAdapter myAdapter = new MyAdapter(sortList,this);
		lv_list.setAdapter(myAdapter);
	}
	/**
	 * 添加数据并排序
	 */
	private ArrayList<Person> FillDateAndSort() {
		ArrayList<Person> list = new ArrayList<Person>();
		for(int i=0;i<Cheeses.NAMES.length;i++){
			list.add(new Person(Cheeses.NAMES[i]));
		}
		//排序
		Collections.sort(list);
		return list;
	}
	Handler handler = new Handler();
	
	/**
	 * 接受自定义控件返回的字母
	 */
	@Override
	public void OnLetterUpdate(String letter) {
		for(int i=0;i<sortList.size();i++){
			String l = sortList.get(i).getPingyin().charAt(0)+"";
			if(TextUtils.equals(l, letter)){
				lv_list.setSelection(i);
				break;
			}
		}
		tv_toast_index.setText(letter);
		tv_toast_index.setVisibility(View.VISIBLE);
		handler.removeCallbacksAndMessages(null);
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				tv_toast_index.setVisibility(View.GONE);
			}
		}, 200);
	}
}
