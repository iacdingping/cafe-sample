package com.vach.cafe.client.chef.feed;


import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.subjects.PublishSubject;

import static com.vach.util.Util.cast;

public class EventStreamManager {

  Map<Feed, PublishSubject> streamMap = new HashMap<>();

  public <T> Observable<T> getStreamFor(Feed feed) {
    return this.<T>getSubject(feed).asObservable();
  }

  public <T> void onNext(Feed feed, T data) {
    this.<T>getSubject(feed).onNext(data);
  }

  public void onError(Feed feed, Throwable err){
    this.getSubject(feed).onError(err);
  }

  public void complete(Feed feed) {
    this.getSubject(feed).onCompleted();
  }

  private <T> PublishSubject<T> getSubject(Feed feed) {
    return cast(
        streamMap
            .computeIfAbsent(feed, s -> PublishSubject.create())
    );
  }
}
