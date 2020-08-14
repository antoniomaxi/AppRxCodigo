package com.brocolisoftware.apprxcodigobueno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PruebaIntroduccionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_introduccion);

        Observable<String> numerosObservable = Observable.just("1","2","3","4","5","6","7","8","9","10");

        Observer<String> numerosObserver = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d("TAG1","onSubscribe, Hilo "+ Thread.currentThread().getName());
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d("TAG1","onNext: "+s+" Hilo "+ Thread.currentThread().getName());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("TAG1","onError, Hilo "+ Thread.currentThread().getName());
            }

            @Override
            public void onComplete() {
                Log.d("TAG1","onComplete, Hilo "+ Thread.currentThread().getName());
            }
        };

        numerosObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(numerosObserver);

    }
}