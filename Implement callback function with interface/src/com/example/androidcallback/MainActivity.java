package com.example.androidcallback;

// http://android-er.blogspot.hk/2013/09/implement-callback-function-with.html

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;

import com.example.androidcallback.POJO.XMLItemTagClass;
import com.example.androidcallback.POJO.XMLRootClass;

public class MainActivity extends ListActivity implements
		ICallBack<XMLItemTagClass, XMLRootClass> {

	private static final String URL_FIRST_TASK = "http://dl.dropboxusercontent.com/u/72603672/demo/abc.xml";
	MyAsyncTask myAsyncTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myAsyncTask = new MyAsyncTask(this);
		myAsyncTask.execute(URL_FIRST_TASK);

		ListAdapter adapter = new ItemAdapter(this);
		setListAdapter(adapter);
	}

	@Override
	public void taskStart() {
		Log.i("MainClass", "Task Start");
	}

	@Override
	public void taskCompleted(XMLRootClass result) {

		Log.i("MainClass", "Task completed result ");

	}

	@Override
	public void taskProgress(XMLItemTagClass... progress) {

		((ItemAdapter) getListAdapter()).addItem(progress[0]);

	}

	@Override
	public Context getContext() {
		return this;
	}

}
