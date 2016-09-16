package com.feathersoft.githubber

import android.os.Bundle
import android.support.annotation.Nullable
import android.view.animation.AnimationUtils
import android.widget.ImageView
import butterknife.BindView
import com.feathersoft.githubber.architecture.GitHubberActivity
import com.feathersoft.githubber.architecture.network.ApiSubscriber
import com.feathersoft.githubber.architecture.network.GitHubFun

class SplashActivity : GitHubberActivity() {

  @BindView(R.id.splash_img_github_shadow) lateinit var mImageGithubShadow: ImageView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun setupContent() {
    mImageGithubShadow.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotation))
    GitHubFun.inspire().subscribe(object: ApiSubscriber<String>(){
      override fun onNext(response: String) {
        goHome(response)
      }

      override fun onError(error: Throwable?) {
        goHome(null)
      }
    })
  }

  override fun getContentViewRes(): Int {
    return R.layout.activity_splash
  }

  fun goHome(@Nullable inspirationMessage: String?) {
    startActivity(HomeActivity.newIntent(this@SplashActivity, inspirationMessage))
    finish()
  }

}
