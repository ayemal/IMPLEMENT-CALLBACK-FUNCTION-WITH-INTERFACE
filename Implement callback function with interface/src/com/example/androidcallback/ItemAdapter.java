package com.example.androidcallback;

import java.util.ArrayList;
import java.util.List;

import android.provider.ContactsContract.CommonDataKinds.Im;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidcallback.POJO.XMLItemTagClass;

public class ItemAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	List<XMLItemTagClass> itemTagClass;

	public ItemAdapter(MainActivity mainActivity) {
		itemTagClass = new ArrayList<XMLItemTagClass>();
		inflater  = LayoutInflater.from(mainActivity);
		
 	}
	
	public void addItem(XMLItemTagClass item) {
 		itemTagClass.add(item);
 		notifyDataSetChanged();

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return itemTagClass.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		ViewHolder holder;
		if (convertView==null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.row, null);
			holder.rowText = (TextView)convertView.findViewById(R.id.textViewRow);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder)convertView.getTag();
		}
		
		holder.rowText.setText(itemTagClass.get(position).getUrl());
 		return convertView;
	}
	class ViewHolder{
		TextView rowText;
		ImageView rowImage;
	}

}
