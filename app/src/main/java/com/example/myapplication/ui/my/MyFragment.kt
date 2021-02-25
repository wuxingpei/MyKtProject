package com.example.myapplication.ui.my

import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.data.PreferencesHelper
import com.example.myapplication.databinding.FragmentMyBinding
import com.example.myapplication.ui.my.activity.SettingActivity
import com.example.myapplication.ui.my.activity.UserCenterActivity
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MyFragment : BaseFragment<FragmentMyBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_my
    }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        mBinding?.run {
            holder = this@MyFragment//笔记 有这个布局中设置的点击事件才会生效
        }
        val username = PreferencesHelper.getUserName(requireContext())
        if (PreferencesHelper.hasLogin(requireContext())) {
            mBinding!!.name =
                username //笔记 "?"加在变量名后，系统在任何情况不会报它的空指针异常。 "!!"加在变量名后，如果对象为null，那么系统一定会报异常！
        } else {
            mBinding!!.name = requireContext().getString(R.string.not_login_text)
        }
    }

    fun myClick(view: View) {
        when (view.id) {
            R.id.rl_userCenter -> context?.startActivity<UserCenterActivity>()
            R.id.rl_setting -> context?.startActivity<SettingActivity>()
        }

    }
}