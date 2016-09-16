package com.feathersoft.githubber.architecture.network

import retrofit2.http.GET
import rx.Observable

/**
 * Created by jormendez on 9/15/16.
 */
object GitHubFun {

  fun inspire(): Observable<String> {
    return GitHubApi.setup(GitHubApi.endpoint<GitHubFunEndpoint>().zen())
  }

  interface GitHubFunEndpoint {
    @GET("zen")
    fun zen(): Observable<String>
  }

}