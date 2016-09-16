package com.feathersoft.githubber

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import butterknife.BindView
import com.feathersoft.githubber.architecture.GitHubberActivity

class SplashActivity : GitHubberActivity() {

  @BindView(R.id.splash_img_github_shadow) lateinit var mImageGithubShadow: ImageView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun setupContent() {
    mImageGithubShadow.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotation))
  }

  override fun getContentViewRes(): Int {
    return R.layout.activity_splash
  }

}
