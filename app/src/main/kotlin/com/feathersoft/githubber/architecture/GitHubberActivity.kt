package com.feathersoft.githubber.architecture

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife

/**
 * Created by jormendez on 9/15/16.
 */
abstract class GitHubberActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setupContentView()
    setupContent()
  }

  open fun setupContentView() {
    setContentView(getContentViewRes())
    ButterKnife.bind(this)
  }

  fun rootView(): View {
    var rootView = findViewById(android.R.id.content)
    if(rootView is ViewGroup) {
      return rootView.getChildAt(0)
    } else {
      return rootView
    }
  }

  /**
   * Will be called when the GitHubberActivity attempts to set the content view
   */
  abstract @LayoutRes fun getContentViewRes(): Int

  /**
   * Will be called once the content view layout has been set, and the views has been bound. Every
   * view content and listener must be set during this callback
   */
  abstract fun setupContent()

}