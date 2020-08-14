package com.brocolisoftware.apprxcodigobueno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CompositeDisposableActivity extends AppCompatActivity {

    private DisposableObserver<String> numeroObserver;
    private DisposableObserver<String> numeroLetraObserver;

    private Observable<String> numeroObservable;
    private Observable<String> numeroLetraObservable;

    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composite_disposable);

        compositeDisposable = new CompositeDisposable();

        numeroObservable = Observable.just("1","2","3","4","5","6","7","8","9","10");
        numeroLetraObservable = Observable.just("uno","dos","tres","cuatro","cinco","seis","siete","ocho");

        numeroObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                Log.d("TAG1","onNext Numero: "+s+" Hilo "+ Thread.currentThread().getName());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("TAG1","onError Numero, Hilo "+ Thread.currentThread().getName());
            }

            @Override
            public void onComplete() {
                Log.d("TAG1","onComplete Numero, Hilo "+ Thread.currentThread().getName());
            }
        };

        numeroLetraObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                Log.d("TAG1","onNext Letra: "+s+" Hilo "+ Thread.currentThread().getName());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("TAG1","onError Letra, Hilo "+ Thread.currentThread().getName());
            }

            @Override
            public void onComplete() {
                Log.d("TAG1","onComplete Letra, Hilo "+ Thread.currentThread().getName());
            }
        };

        compositeDisposable.add(numeroObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(numeroObserver));

        compositeDisposable.add(numeroLetraObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(numeroLetraObserver));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}