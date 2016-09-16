package com.feathersoft.githubber.architecture

import android.support.annotation.StringRes
import android.support.v7.widget.Toolbar
import butterknife.BindView
import com.feathersoft.githubber.R

/**
 * Created by jormendez on 9/15/16.
 */
abstract class GitHubberToolBarActivity: GitHubberActivity() {

  @BindView(R.id.toolbar) lateinit var mToolbar: Toolbar
    get

  override fun setupContentView() {
    super.setupContentView()
    setupToolbar()
  }

  fun setupToolbar() {
    mToolbar.title = getString(getPageTitle())
    setSupportActionBar(mToolbar)
  }

  @StringRes fun getPageTitle(): Int {
    return R.string.app_name
  }

}