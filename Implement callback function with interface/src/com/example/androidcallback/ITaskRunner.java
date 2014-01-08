package com.example.androidcallback;

import android.os.AsyncTask;

public abstract class ITaskRunner<Params, Progress, Result> extends
		AsyncTask<Params, Progress, Result> {

	private ICallBack<Progress, Result> callBack;

	public ITaskRunner(ICallBack<Progress, Result> callBack) {
		this.callBack = callBack;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		callBack.taskStart();
	}

	@Override
	protected void onPostExecute(Result result) {

		callBack.taskCompleted(result);

	}

	@Override
	protected void onProgressUpdate(Progress... values) {

		callBack.taskProgress(values);
	}
}
