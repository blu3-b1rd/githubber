package com.feathersoft.githubber

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.feathersoft.githubber.architecture.GitHubberToolBarActivity
import com.feathersoft.githubber.architecture.network.ApiSubscriber
import com.feathersoft.githubber.architecture.network.GitHubFun
import com.feathersoft.githubber.ui.GitHubberProgress

class HomeActivity : GitHubberToolBarActivity() {

  companion object {
    val ARG_INSPIRATIONAL_MESSAGE = "arg_inspirational_message"

    fun newIntent(@NonNull context: Context, @Nullable inspirationalMessage: String?): Intent {
      val intent = Intent(context, HomeActivity::class.java)
      intent.putExtra(ARG_INSPIRATIONAL_MESSAGE, inspirationalMessage)
      return intent
    }
  }

  @BindView(R.id.home_text_inspire) lateinit var mTextInspire: TextView

  var mInspirationalMessage: String? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    inspireMyUser(intent?.getStringExtra(ARG_INSPIRATIONAL_MESSAGE))
  }

  override fun getContentViewRes(): Int {
    return R.layout.activity_home
  }

  override fun setupContent() { }

  fun inspireMyUser(inspirationalMessage: String?) {
    mInspirationalMessage = inspirationalMessage
    mTextInspire.text =
      if(mInspirationalMessage.isNullOrEmpty()) getString(R.string.home_no_inspiration)
      else mInspirationalMessage
  }

  @OnClick(R.id.home_btn_inspire_me)
  fun onInspirationRequested() {
    GitHubberProgress.show(this)
    GitHubFun.inspire().subscribe(object: ApiSubscriber<String>() {
      override fun onNext(response: String) {
        GitHubberProgress.dismiss()
        inspireMyUser(response)
      }

      override fun onError(error: Throwable?) {
        GitHubberProgress.dismiss()
        inspireMyUser(null)
      }
    })
  }

}
