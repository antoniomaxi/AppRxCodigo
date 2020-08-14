package com.brocolisoftware.apprxcodigobueno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class OperatorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operators);

        //probarJust();
        //probarJustArray();
        //probarFromArray();
        //probarRange();
        //probarRepeat();
        //probarInterval();
        //probarCreate();
        //probarCreateExeption();
        probarCreateTareaLargaDuracion();
        probarLambda();

    }

    private void probarLambda() {

        /*
        (argumentos)->(body)
        (arg1,arg2)->(body)
        Una interfaz funcional es aquella que solo tiene un metodo a implementar
        Para poder tener una funcion Lambda debemos tener una interfaz funcional

        */
    }

    //Interfaz con Lambda
    Sumar sumarL = (a,b)->{
        int resultado = a+b;
        return resultado;
    };


    //Interfaz tradicional
    Sumar sumar = new Sumar() {
        @Override
        public int apply(int a, int b) {
            int resultado = a+b;
            return resultado;
        }
    };

    private void probarCreateTareaLargaDuracion() {
        Log.d("TAG1","------------------CREATE LARGA DURACION------------------");

        Observable.create(
                new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                        try {
                            emitter.onNext(largaDuracion());
                        }catch(Exception e){
                            emitter.onError(e);
                        }

                    }
                }
        ).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.d("TAG1","Create Larga Duracion onNext:-> " + s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d("TAG1","Create Larga Duracion onComplete:-> ");
                    }
                }
        );


    }

    private String largaDuracion() {

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Terminado";
    }

    private void probarCreateExeption() {
        Log.d("TAG1","------------------CREATE EXCEPTION------------------");
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                try{
                    emitter.onNext(15/5);
                    emitter.onNext(3/0);

                }
                catch(Exception e){
                    emitter.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<Integer>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull Integer integer) {
                                Log.d("TAG1","CreateException onNext:-> " + integer);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Log.d("TAG1","CreateException onNext:-> " + e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );

    }

    private void probarCreate() {
        Log.d("TAG1","------------------CREATE------------------");
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                try{
                    emitter.onNext("A");
                    emitter.onNext("B");
                    emitter.onNext("C");
                    emitter.onNext("D");
                    emitter.onNext("E");
                    emitter.onNext("F");
                    emitter.onNext("G");
                }
                catch(Exception e){
                    emitter.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<String>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull String s) {
                                Log.d("TAG1","Create onNext:-> " + s);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );
    }

    private void probarInterval() {
        Log.d("TAG1","------------------INTERVAL------------------");
        Observable.interval(1, TimeUnit.SECONDS)
                .take(10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<Long>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull Long aLong) {
                                Log.d("TAG1","Interval onNext:-> " + aLong);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );
    }

    private void probarRepeat() {
        Log.d("TAG1","------------------REPEAT------------------");
        Observable.range(10,3)
                .repeat(4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<Integer>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull Integer integer) {
                                Log.d("TAG1","Repeat onNext:-> " + integer);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );

    }

    private void probarRange() {

        Log.d("TAG1","------------------RANGE------------------");
        Observable.range(5,17)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<Integer>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull Integer integer) {
                                Log.d("TAG1","Range onNext:-> " + integer);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );

    }

    private void probarFromArray() {

        Log.d("TAG1","------------------FROMARRAY------------------");
        String[] numeros = {"1","2","3","4","5","6","7","8","9","10"};
        Observable.fromArray(numeros)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<String>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull String s) {
                                Log.d("TAG1","FromArray onNext:-> " + s);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );
    }

    private void probarJustArray() {

        Log.d("TAG1","------------------JUSTARRAY------------------");
        String[] numeros = {"1","2","3","4","5","6","7","8","9","10"};
        Observable.just(numeros)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<String[]>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(String @NonNull [] strings) {
                                Log.d("TAG1","JUSTARRAY onNext:-> ");
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );
    }

    private void probarJust(){
        Log.d("TAG1","------------------JUST------------------");
        Observable.just("1","2","3","4","5","6","7","8","9","10")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<String>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull String s) {
                                Log.d("TAG1","Just onNext:-> "+s);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );
    }

}