package com.example.myapplication.ui.my.activity

import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.base.ActivityStackManager
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.databinding.ActivitySettingBinding
import com.example.myapplication.ui.app.LoginActivity
import com.example.myapplication.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingActivity : BaseActivity<ActivitySettingBinding>() {
    private val mLoginViewModel by viewModel<LoginViewModel>()
    override fun getLayoutId(): Int {
        return R.layout.activity_setting
    }

    override fun initActivity(savedInstanceState: Bundle?) {
        mBinding.holder = this  //笔记 有这个布局中设置的点击事件才会生效
        viewTitle.setLeftClickListener(View.OnClickListener { finish() })
    }

    fun logout(view: View) {
        launch {
            mLoginViewModel.loginOut().catch {
                toast(R.string.no_network)
            }.onStart {
                //笔记 可进行加载框的显示
            }.onCompletion {
                //笔记 可进行加载框的隐藏
            }.collectLatest {
                if (it.errorCode == 0) {
                    toast(R.string.login_out_succeed)
                    mLoginViewModel.clearUserInfo()
                    startActivity<LoginActivity>()
                    ActivityStackManager.finishAll()
                } else {
                    toast(R.string.login_out_failed)
                }
            }
        }
    }
}