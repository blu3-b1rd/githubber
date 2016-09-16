package com.feathersoft.githubber.architecture.network

import rx.Subscriber

/**
 * Created by jormendez on 9/15/16.
 */
abstract class ApiSubscriber<T>: Subscriber<T>() {

  override fun onNext(response: T) { }

  override fun onError(error: Throwable?) { }

  override fun onCompleted() { }
}
