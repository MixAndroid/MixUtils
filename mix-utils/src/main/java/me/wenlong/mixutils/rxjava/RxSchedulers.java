package me.wenlong.mixutils.rxjava;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * des   : Rx调度器 author: Gavin email : guowenlong20000@gmail.com time  : 2016年08月26日 下午 3:47.
 */
public class RxSchedulers {

  public static <T> Observable.Transformer<T, T> io_main() {
    return new Observable.Transformer<T, T>() {
      @Override
      public Observable<T> call(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
      }
    };
  }
}
