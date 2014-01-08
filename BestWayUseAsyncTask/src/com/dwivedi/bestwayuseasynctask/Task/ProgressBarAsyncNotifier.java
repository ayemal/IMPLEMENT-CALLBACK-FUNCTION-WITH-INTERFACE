package com.dwivedi.bestwayuseasynctask.Task;


import android.app.ProgressDialog;

public class ProgressBarAsyncNotifier<T> extends DecoratedAsyncTaskRunner<T>
		implements IAsyncTaskRunner<T> {
	private ProgressDialog _progressDialog;
	private String _busyMessage;

	public ProgressBarAsyncNotifier(IAsyncTaskRunner<T> decoratedRunner,
			String busyMessage) {
		super(decoratedRunner);
		_busyMessage = busyMessage;
	}

	public void taskStarting() {
		_progressDialog = ProgressDialog.show(getContext(), "Please wait...",
				_busyMessage, true);
		super.taskStarting();
	}

	public void taskCompleted(T result) {
		_progressDialog.dismiss();
		super.taskCompleted(result);
	}

}
