package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.utils.urlToBitmap
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.view.MotionEvent
import android.view.SurfaceHolder


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        mBinding?.run {
            srcImg="https://oss.zu.koodpower.com/uploads/images/pUG3zELF34f7pV8rIO68SHpl7i2SdMCVnrnK6AWe.jpeg"
        }
//        surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
//            //笔记 kotlin中实现匿名内部类
//            override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun surfaceDestroyed(p0: SurfaceHolder?) {
//            }
//
//            override fun surfaceCreated(p0: SurfaceHolder?) {
//                val paint = Paint()
//                paint.isAntiAlias = true
//                paint.style = Paint.Style.STROKE
//                GlobalScope.launch {
//                    val bitmap =
//                        urlToBitmap("https://oss.zu.koodpower.com/uploads/images/pUG3zELF34f7pV8rIO68SHpl7i2SdMCVnrnK6AWe.jpeg")
//                    val lockCanvas = p0?.lockCanvas()
//                    if (bitmap != null) {
//                        lockCanvas?.drawBitmap(bitmap, 0f, 0f, paint)
//                    }
//                    p0?.unlockCanvasAndPost(lockCanvas)
//                }
//            }
//
//        })
    }

}