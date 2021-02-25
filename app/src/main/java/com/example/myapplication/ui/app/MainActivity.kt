package com.example.myapplication.ui.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.home.HomeFragment
import com.example.myapplication.ui.my.MyFragment
import com.example.myapplication.ui.order.OrderFragment
import com.example.myapplication.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var currentFragment: Fragment  //笔记 lateinit这个变量会被初始化，并且不会为null，但是在声明这里，我暂时还不知道什么时候会被初始化
    private var fragmentLists = mutableListOf<Fragment>()
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initActivity(savedInstanceState: Bundle?) {
        StatusBarUtil.setTransparentForImageViewInFragment(this, null)
        StatusBarUtil.setLightMode(this)
        initFragment()
        initDefultFragment()
        radio_group.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_home -> showFragment(fragmentLists[0])
                R.id.rb_order -> showFragment(fragmentLists[1])
                R.id.rb_my -> showFragment(fragmentLists[2])
            }
        }
    }

    private fun initFragment() {
        currentFragment=Fragment()
        fragmentLists.add(HomeFragment())
        fragmentLists.add(OrderFragment())
        fragmentLists.add(MyFragment())
    }

    private fun initDefultFragment() {
        showFragment(fragmentLists[0])
    }

    //用来控制Fragment的显示隐藏
    private fun showFragment(fragment: Fragment) {
        //判断当前显示的是否是需要展示的Framgnet，可以省略不必要步骤
        if (currentFragment != fragment) {
            //隐藏当前Fragment
            val transaction = supportFragmentManager.beginTransaction()
            transaction.hide(currentFragment)
            //将fragment替换成目前传入的fragment
            currentFragment = fragment
            //判断当前fragment是否添加进事务中
            if (!fragment.isAdded) {
                //添加显示
                transaction.add(R.id.framelayout, fragment).show(fragment).commit()
            } else {
                //显示
                transaction.show(fragment).commit()
            }
        }
    }

}
