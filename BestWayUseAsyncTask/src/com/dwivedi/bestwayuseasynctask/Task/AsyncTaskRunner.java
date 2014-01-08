package com.dwivedi.bestwayuseasynctask.Task;


import android.content.Context;
import android.os.AsyncTask;

public abstract class AsyncTaskRunner<T, TU> extends AsyncTask<T, Integer, TU> {
    private IAsyncTaskRunner<TU> _asyncTaskRunner;

    public AsyncTaskRunner(IAsyncTaskRunner<TU> asyncTaskRunner) {
        _asyncTaskRunner = asyncTaskRunner;
    }

    protected Context getContext() {
        return _asyncTaskRunner.getContext();
    }

    @Override
    protected void onPreExecute() {
        _asyncTaskRunner.taskStarting();
    }

    @Override
    protected void onPostExecute(TU result) {
         _asyncTaskRunner.taskCompleted(result);
    }
}