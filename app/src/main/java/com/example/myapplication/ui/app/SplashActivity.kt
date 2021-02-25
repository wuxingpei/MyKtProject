package com.example.myapplication.ui.app

import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.base.MyApplication
import com.example.myapplication.data.PreferencesHelper
import com.example.myapplication.databinding.ActivitySplashBinding
import com.example.myapplication.utils.StatusBarUtil
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initActivity(savedInstanceState: Bundle?) {
        StatusBarUtil.setTransparentForImageViewInFragment(this, null)
        launch {//笔记 launch方法以非阻塞当前线程的方式，启动一个新的协程后台任务，并返回一个Job对象作为当前协程的引用(延时操作)
            delay(2000)
            if (PreferencesHelper.hasLogin(MyApplication.instance)) {
                startActivity<MainActivity>()
            } else {
                startActivity<LoginActivity>()
            }
            overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right)
            finish()
        }
    }

}