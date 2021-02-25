package com.example.myapplication.ui.my.activity

import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.databinding.ActivityUserCenterBinding
import kotlinx.android.synthetic.main.activity_setting.*
import org.jetbrains.anko.toast

class UserCenterActivity : BaseActivity<ActivityUserCenterBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_user_center
    }

    override fun initActivity(savedInstanceState: Bundle?) {
        viewTitle.setLeftClickListener(View.OnClickListener { finish() })
    }
}