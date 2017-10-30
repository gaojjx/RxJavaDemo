package com.example.com.myapplication.demo;

import android.content.Context;
import android.util.Log;

import com.example.com.myapplication.Api;
import com.example.com.myapplication.RetrofitProvider;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

import static com.example.com.myapplication.MainActivity.TAG;

/**
 * Created by gaojjx on 28/10/2017.
 */

public class RetrofitDemo {
    public void demo(final Context context) {
        final Api api = RetrofitProvider.get().create(Api.class);
        api.getTop250(0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "error");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "登录成功");
                    }
                });
    }
}
