package com.dwivedi.bestwayuseasynctask;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.dwivedi.bestwayuseasynctask.POJO.XMLRootClass;
import com.dwivedi.bestwayuseasynctask.Task.IAsyncTaskRunner;
import com.dwivedi.bestwayuseasynctask.Task.ProgressBarAsyncNotifier;

public class MainActivity extends Activity implements
		IAsyncTaskRunner<XMLRootClass> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		CommanClass.addRecordToLog("OnCreate: MainActivity");
		HttpTask httpTask = new HttpTask(
				new ProgressBarAsyncNotifier<XMLRootClass>(this, "Connecting"));
		String URLString = "http://dl.dropboxusercontent.com/u/72603672/demo/abc.xml";
		httpTask.execute(URLString);
	}

	@Override
	public Context getContext() {
		return this;
	}

	@Override
	public void taskStarting() {
		System.out.println("Task Start");

	}

	@Override
	public void taskCompleted(XMLRootClass rootClass) {
 		System.out.println("Task complete " + rootClass);

	}

}
