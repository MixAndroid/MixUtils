package me.wenlong.mixutils.rxjava;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import me.wenlong.mixutils.util.LogUtils;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * des   : 用RxJava实现的EventBus author: Gavin email : guowenlong20000@gmail.com time  : 2016年08月29日 下午
 * 3:30.
 */
public class RxBus {

  private static RxBus instance;

  public static synchronized RxBus $() {
    if (null == instance) {
      instance = new RxBus();
    }
    return instance;
  }

  private RxBus() {
  }

  @SuppressWarnings("rawtypes")
  private ConcurrentHashMap<Object, List<Subject>> subjectMapper = new ConcurrentHashMap<>();

  /**
   * 订阅事件源
   */
  public RxBus OnEvent(Observable<?> mObservable, Action1<Object> mAction1) {
    mObservable.observeOn(AndroidSchedulers.mainThread())
        .subscribe(mAction1, new Action1<Throwable>() {
          @Override
          public void call(Throwable throwable) {
            throwable.printStackTrace();
          }
        });
    return $();
  }

  /**
   * 注册事件源
   */
  @SuppressWarnings({"rawtypes"})
  public <T> Observable<T> register(@NonNull Object tag) {
    List<Subject> subjectList = subjectMapper.get(tag);
    if (null == subjectList) {
      subjectList = new ArrayList<Subject>();
      subjectMapper.put(tag, subjectList);
    }
    Subject<T, T> subject;
    subjectList.add(subject = PublishSubject.create());
    LogUtils.d(tag + "  size:" + subjectList.size());
    return subject;
  }

  @SuppressWarnings("rawtypes")
  public void unregister(@NonNull Object tag) {
    List<Subject> subjects = subjectMapper.get(tag);
    if (null != subjects) {
      subjectMapper.remove(tag);
    }
  }

  /**
   * 取消监听
   */
  @SuppressWarnings("rawtypes")
  public RxBus unregister(@NonNull Object tag,
      @NonNull Observable<?> observable) {
    if (null == observable) {
      return $();
    }
    List<Subject> subjects = subjectMapper.get(tag);
    if (null != subjects) {
      subjects.remove((Subject<?, ?>) observable);
      if (isEmpty(subjects)) {
        subjectMapper.remove(tag);
        LogUtils.d(tag + "  size:" + subjects.size());
      }
    }
    return $();
  }

  public void post(@NonNull Object content) {
    post(content.getClass().getName(), content);
  }

  /**
   * 触发事件
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public void post(@NonNull Object tag, @NonNull Object content) {
    LogUtils.d("post" + "eventName: " + tag);
    List<Subject> subjectList = subjectMapper.get(tag);
    if (!isEmpty(subjectList)) {
      for (Subject subject : subjectList) {
        subject.onNext(content);
        LogUtils.d("eventName: " + tag);
      }
    }
  }

  @SuppressWarnings("rawtypes")
  public static boolean isEmpty(Collection<Subject> collection) {
    return null == collection || collection.isEmpty();
  }
}
