package com.dwivedi.bestwayuseasynctask.Task;


import android.content.Context;

public class DecoratedAsyncTaskRunner<T> {
	private IAsyncTaskRunner<T> _decoratedRunner;

	public DecoratedAsyncTaskRunner(IAsyncTaskRunner<T> decoratedRunner) {
		_decoratedRunner = decoratedRunner;
	}

	public Context getContext() {
		return _decoratedRunner.getContext();
	}

	public void taskStarting() {
		_decoratedRunner.taskStarting();
	}

	public void taskCompleted(T result) {
		_decoratedRunner.taskCompleted(result);
	}
}
