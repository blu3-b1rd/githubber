package com.feathersoft.githubber.architecture

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Reader

/**
 * Created by jormendez on 8/14/16.
 */
object GitHubberJsonParser {

  val mGson: Gson
  val mConverterFactory: Converter.Factory

  init {
    mGson = GsonBuilder().setLenient().create()
    mConverterFactory = GsonConverterFactory.create(mGson)
  }

  fun retrofitConverterFactory(): Converter.Factory {
    return mConverterFactory
  }

  fun toJson(src: Any): String {
    return mGson.toJson(src)
  }

  fun <T: Any> fromJson(json: String, clazz: Class<T>): T {
    return mGson.fromJson(json, clazz)
  }

  fun <T: Any> fromJson(json: Reader, clazz: Class<T>): T {
    return mGson.fromJson(json, clazz)
  }

}