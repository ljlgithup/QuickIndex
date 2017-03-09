package com.example.quick.index.adapter;

import java.util.ArrayList;

import com.example.quick.index.R;
import com.example.quick.index.bean.Person;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private ArrayList<Person> list;
	private Context context;
	
	
	public MyAdapter(ArrayList<Person> list,Context context){
		this.list = list;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		if(convertView==null){
			view = View.inflate(context, R.layout.item_person_list, null);
		}else{
			view = convertView;
		}
		TextView tv_letter = (TextView) view.findViewById(R.id.tv_letter);
		TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
		Person person = list.get(position);
		String currentLetter = person.getPingyin().charAt(0)+"";
		String letter = null;
		if(position==0){
			letter = currentLetter;
		}else{
			//获取上一个字母，跟当前字母比较。
			String previousLetters = list.get(position-1).getPingyin().charAt(0)+"";
			if(!TextUtils.equals(currentLetter, previousLetters)){
				letter = currentLetter;
			}
		}
		tv_letter.setText(letter);
		tv_letter.setVisibility(letter==null?View.GONE:View.VISIBLE);
		tv_name.setText(person.getName());
		return view;
	}

}
