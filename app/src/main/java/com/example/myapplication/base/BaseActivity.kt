package com.example.myapplication.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.myapplication.R
import com.example.myapplication.utils.StatusBarUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity(),
    CoroutineScope by MainScope() { //笔记 abstract抽象类即基类（封装常用方法、以及处理一些共有的逻辑）
// CoroutineScope by MainScope()能够无缝将异步任务切回主线程在activity中就能直接在launch中开启ui线程

    protected val mBinding: VB by lazy {
        DataBindingUtil.setContentView(this, getLayoutId()) as VB
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityStackManager.addActivity(this)
        setStatusBar()
        mBinding.lifecycleOwner =
            this  //笔记 DataBinding需调用setLifecycleOwner(LifecycleOwner lifecycleOwner)之后，绑定了LiveData数据源的xml控件才会随着数据变化而改变
        initActivity(savedInstanceState)
    }

    /** 透明状态栏 */
    open fun setStatusBar() {//笔记 为方法增加open，那么方法就可以被重写了
        StatusBarUtil.setColorNoTranslucent(this, resources.getColor(R.color.colorWhite))
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityStackManager.removeActivity(this)
        mBinding.unbind()
    }

    abstract fun getLayoutId(): Int
    abstract fun initActivity(savedInstanceState: Bundle?)
}