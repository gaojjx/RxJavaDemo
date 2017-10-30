package com.example.com.myapplication.demo;


import android.util.Log;

import com.example.com.myapplication.Api;
import com.example.com.myapplication.RetrofitProvider;
import com.example.com.myapplication.entity.UserExtraInfoRequest;
import com.example.com.myapplication.entity.UserExtraInfoResponse;
import com.example.com.myapplication.entity.UserInfo;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.example.com.myapplication.MainActivity.TAG;

/**
 * Created by gaojjx on 30/10/2017.
 */

public class ChapterFour {
    public static void demo1() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG, "e.onNext 1");
                e.onNext(1);
                Log.d(TAG, "e.onNext 2");
                e.onNext(2);
                Log.d(TAG, "e.onNext 3");
                e.onNext(3);
                Log.d(TAG, "e.onNext 4");
                e.onNext(4);
                e.onComplete();
            }
        });
        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.d(TAG, "e.onNext A");
                e.onNext("A");
                Log.d(TAG, "e.onNext B");
                e.onNext("B");
                Log.d(TAG, "e.onNext C");
                e.onNext("C");
                e.onComplete();
            }
        });
        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        });
    }

    public static void demo2() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG, "e.onNext 1");
                e.onNext(1);
                Log.d(TAG, "e.onNext 2");
                e.onNext(2);
                Log.d(TAG, "e.onNext 3");
                e.onNext(3);
                Log.d(TAG, "e.onNext 4");
                e.onNext(4);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());
        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.d(TAG, "e.onNext A");
                e.onNext("A");
                Log.d(TAG, "e.onNext B");
                e.onNext("B");
                Log.d(TAG, "e.onNext C");
                e.onNext("C");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());
        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        });
    }

    public static void practice() {
        final Api api = RetrofitProvider.get().create(Api.class);
        Observable<UserExtraInfoResponse> observable1 =
                api.getUserExtraInfo(new UserExtraInfoRequest()).subscribeOn(Schedulers.io());
        Observable<UserExtraInfoResponse> observable2 =
                api.getUserExtraInfo(new UserExtraInfoRequest()).subscribeOn(Schedulers.io());
        Observable.zip(observable1, observable2, new BiFunction<UserExtraInfoResponse, UserExtraInfoResponse, UserInfo>() {
            @Override
            public UserInfo apply(UserExtraInfoResponse userExtraInfoResponse, UserExtraInfoResponse userExtraInfoResponse2) throws Exception {
                return new UserInfo();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserInfo>() {
                    @Override
                    public void accept(UserInfo userInfo) throws Exception {
                        //do something
                    }
                });
    }
}
