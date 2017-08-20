package com.cn.rxjava02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 原创作者：谷哥的小弟
 * 博客地址：http://blog.csdn.net/lfdfhl
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test06();
    }

    /**
     * RxJava最简单的使用
     * 执行流程：
     * Observer onSubscribe ---> Observable subscribe ---> Observer onNext ---> Observer onComplete
     */
    private void test01(){
        Observable observable=Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(@NonNull ObservableEmitter emitter) throws Exception {
                Log.i(Constant.TAG,"Observable subscribe");
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("3");
                emitter.onComplete();
            }
        });

        Observer observer=new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(Constant.TAG,"Observer onSubscribe");
            }

            @Override
            public void onNext(@NonNull Object o) {
                Log.i(Constant.TAG,"Observer onNext "+o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(Constant.TAG,"Observer onError");
            }

            @Override
            public void onComplete() {
                Log.i(Constant.TAG,"Observer onComplete");
            }
        };

        observable.subscribe(observer);

    }

    /**
     * Disposable
     * 调用dispose()后上游会继续发送剩余的事件，
     * 但是下游停止接手事件
     */
    private void test02(){
        Observable observable=Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });

        Observer<Integer> observer=new Observer<Integer>() {
            private Disposable disposable;
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                this.disposable=d;
                Log.i(Constant.TAG,"onSubscribe");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.i(Constant.TAG,"onNext "+integer);
                if (integer==2){
                    disposable.dispose();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(Constant.TAG,"onError");
            }

            @Override
            public void onComplete() {
                Log.i(Constant.TAG,"onComplete");
            }
        };

        observable.subscribe(observer);

    }

    /**
     * Consumer
     * Consumer与Observer类似；但是其只关心onNext事件
     */
    private void test03(){
        Observable observable=Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });

        Consumer<Integer> consumer=new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                Log.i(Constant.TAG,"onNext "+integer);
            }
        };
        observable.subscribe(consumer);
    }

    /**
     * 线程关系
     * 默认情况下Observable和Observer在同一个线程
     */
    private void test04(){
        long id=Thread.currentThread().getId();
        Log.i(Constant.TAG,"Activity thread id = "+id);

        Observable observable=Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(@NonNull ObservableEmitter emitter) throws Exception {
                long id=Thread.currentThread().getId();
                Log.i(Constant.TAG,"Observable subscribe thread id = "+id);
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("3");
                emitter.onComplete();
            }
        });

        Observer observer=new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                long id=Thread.currentThread().getId();
                Log.i(Constant.TAG,"Observer onSubscribe thread id = "+id);
            }

            @Override
            public void onNext(@NonNull Object o) {
                Log.i(Constant.TAG,"onNext "+o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(Constant.TAG,"onError");
            }

            @Override
            public void onComplete() {
                Log.i(Constant.TAG,"onComplete");
            }
        };

        observable.subscribe(observer);

    }

    /**
     * RxJava内置线程调度器
     * subscribeOn() 指定上游发送事件的线程
     * observeOn() 指定下游接收事件的线程
     *
     * RxJava常见内置线程
     * Schedulers.io() 代表io操作的线程, 通常用于网络,读写文件等io密集型的操作
     * Schedulers.computation() 代表CPU计算密集型的操作, 例如需要大量计算的操作
     * Schedulers.newThread() 代表一个常规的新线程
     * AndroidSchedulers.mainThread() 代表Android的主线程
     */
    private void test05(){
        long id=Thread.currentThread().getId();
        Log.i(Constant.TAG,"Activity thread id = "+id);

        Observable observable=Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(@NonNull ObservableEmitter emitter) throws Exception {
                long id=Thread.currentThread().getId();
                Log.i(Constant.TAG,"Observable subscribe thread id = "+id);
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("3");
                emitter.onComplete();
            }
        });

        Observer observer=new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                long id=Thread.currentThread().getId();
                Log.i(Constant.TAG,"Observer onSubscribe thread id = "+id);
            }

            @Override
            public void onNext(@NonNull Object o) {
                Log.i(Constant.TAG,"onNext "+o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(Constant.TAG,"onError");
            }

            @Override
            public void onComplete() {
                Log.i(Constant.TAG,"onComplete");
            }
        };

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    /**
     * Retrofit结合RxJava进行网络请求
     * 请注意：RxJava2CallAdapterFactory
     */
    private void test06(){

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Observer<List<Repo>> observer=new Observer<List<Repo>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(Constant.TAG,"Observer onSubscribe");
            }

            @Override
            public void onNext(@NonNull List<Repo> repos) {
                Log.i(Constant.TAG,"Observer onNext size="+repos.size());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(Constant.TAG,"Observer onError");
            }

            @Override
            public void onComplete() {
                Log.i(Constant.TAG,"Observer onComplete");
            }
        };

        GitHubService gitHubService=retrofit.create(GitHubService.class);

        gitHubService.listRepos("octocat")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
