package com.example.myapplication.ui.app

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.viewmodel.LoginViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    private val mLoginViewModel by viewModel<LoginViewModel>()
    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initActivity(savedInstanceState: Bundle?) {
        mBinding.holder = this  //笔记 有这个布局中设置的点击事件才会生效
        mBinding.username.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                buttonSetEnable()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
        mBinding.psd.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                buttonSetEnable()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
    }

    fun buttonSetEnable() {
        mBinding.buttonLogin.isEnabled =
            !mBinding.username.text.toString().isBlank() && !mBinding.psd.text.toString().isBlank()
    }

    fun login(view: View) {
        val username = mBinding.username.text.toString()
        val psd = mBinding.psd.text.toString()
        launch {//笔记 launch方法以非阻塞当前线程的方式，启动一个新的协程后台任务，并返回一个Job对象作为当前协程的引用
            mLoginViewModel.login(username, psd).catch {
                toast(R.string.no_network)
            }.onStart {
                //笔记 可进行加载框的显示
            }.onCompletion {
                //笔记 可进行加载框的隐藏
            }.collectLatest {
                if (it.body()?.errorCode == 0) {
                    mLoginViewModel.saveUser(it)
                    toast(R.string.login_succeed)
                    startActivity<MainActivity>()
                    overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right)
                    finish()
                } else {
                    it.body()?.errorMsg?.let { it1 -> toast(it1) }
                }
            }
        }
    }
}