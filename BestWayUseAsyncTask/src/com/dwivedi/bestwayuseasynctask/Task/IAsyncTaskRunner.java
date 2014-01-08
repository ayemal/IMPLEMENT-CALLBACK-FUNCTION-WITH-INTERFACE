package com.dwivedi.bestwayuseasynctask.Task;

import android.content.Context;

public interface IAsyncTaskRunner<T> {

	Context getContext();
 	void taskStarting();
 	void taskCompleted(T result);

}
