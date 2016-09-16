package com.feathersoft.githubber.ui

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.feathersoft.githubber.R

/**
 * Created by jormendez on 9/15/16.
 */
object GitHubberProgress {

  var progressDialog: GitHubberDialog? = null

  fun show(context: Context) {
    show(context, true)
  }

  fun show(context: Context, cancelable: Boolean) {
    if(progressDialog?.isShowing ?: false) { return }
    progressDialog = GitHubberDialog(context)
    progressDialog?.setCancelable(cancelable)
    progressDialog?.show()
  }

  fun dismiss() {
    progressDialog?.dismissSmoothly()
    progressDialog = null
  }

  class GitHubberDialog: Dialog {

    @BindView(R.id.progress_image) lateinit var mProgressImage: ImageView

    constructor(context: Context?) : this(context, R.style.GitHubber_Theme_Dialog)
    constructor(context: Context?, themeResId: Int) : super(context, themeResId) {
      requestWindowFeature(Window.FEATURE_NO_TITLE)
      setContentView(R.layout.layout_progress)
      ButterKnife.bind(this)
      mProgressImage.startAnimation(AnimationUtils.loadAnimation(context, R.anim.progress))
    }

    fun dismissSmoothly() {
      val dismissAnimation = AnimationUtils.loadAnimation(context, R.anim.dismiss)
      dismissAnimation.setAnimationListener(object: Animation.AnimationListener {
        override fun onAnimationEnd(animation: Animation?) {
          dismiss()
        }
        override fun onAnimationStart(animation: Animation?) { }
        override fun onAnimationRepeat(animation: Animation?) { }

      })
      mProgressImage.startAnimation(dismissAnimation)
    }

  }

}