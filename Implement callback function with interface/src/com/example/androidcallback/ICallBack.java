package com.example.androidcallback;

import android.content.Context;

public interface ICallBack<Progress,Result> {
	
	Context getContext();
	void taskStart();
	void taskCompleted(Result result);
	void taskProgress(Progress... progress);
 
}
