package com.feathersoft.githubber.architecture.network

import com.feathersoft.githubber.BuildConfig
import com.feathersoft.githubber.architecture.GitHubberJsonParser
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import org.jetbrains.annotations.NotNull
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

/**
 * Created by jormendez on 7/19/16.
 */
object GitHubApi {

  private val GITHUB_API_URL = "https://api.github.com/"

  private val mRetrofit: Retrofit
  private val mHttpClient: OkHttpClient
  private val mApis = HashMap<String, Any>()

  init {
    val clientBuilder = OkHttpClient.Builder()
    if(BuildConfig.DEBUG) {
      clientBuilder.addInterceptor(HttpLoggingInterceptor().setLevel(Level.BODY))
      clientBuilder.addInterceptor(DelayInterceptor())
    }
    mHttpClient = clientBuilder.build()

    mRetrofit = Retrofit.Builder()
      .baseUrl(GITHUB_API_URL)
      .addConverterFactory(GitHubberJsonParser.retrofitConverterFactory())
      .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
      .client(mHttpClient)
      .build()
  }

  @Suppress("UNCHECKED_CAST")
  fun <T: Any> endpoint(clazz: Class<T>): T {
    if(!mApis.containsKey(clazz.simpleName)) {
      mApis.put(clazz.simpleName, mRetrofit.create(clazz))
    }
    return mApis.get(clazz.simpleName) as T
  }

  inline fun <reified T: Any> endpoint() = endpoint(T::class.java)

  fun <T: Any> setup(@NotNull observable: Observable<T>) = observable
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())

  class DelayInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
      Thread.sleep(2000)
      return chain.proceed(chain.request())
    }
  }

}