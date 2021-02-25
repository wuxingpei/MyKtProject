package com.example.myapplication.widget

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.core.view.isGone
import com.example.myapplication.R
import com.example.myapplication.base.ActivityStackManager
import kotlinx.android.synthetic.main.layout_view_title.view.*

class ViewTitle :
    RelativeLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        LayoutInflater.from(context).inflate(R.layout.layout_view_title, this, true)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ViewTitle)
        tvTitle.text = typedArray.getString(R.styleable.ViewTitle_title)
        tvRight.text = typedArray.getString(R.styleable.ViewTitle_rightText)
        ivRight.setImageResource(typedArray.getInt(R.styleable.ViewTitle_rightSrc, 0))
        ivLeft.visibility = typedArray.getInt(R.styleable.ViewTitle_showLeftSrc, 0)
        tvRight.visibility = typedArray.getInt(R.styleable.ViewTitle_showRightText, 2)
        ivRight.visibility = typedArray.getInt(R.styleable.ViewTitle_showRightSrc, 2)
        typedArray.recycle()
    }

    fun setTitle(title: String): ViewTitle {
        tvTitle.text = title
        return this
    }

    fun setLeftVisiable(visiable: Int): ViewTitle {
        ivLeft.visibility = visiable
        return this
    }

    fun setLeftClickListener(onClickListener: OnClickListener): ViewTitle {
        ivLeft.setOnClickListener(onClickListener)
        return this
    }

    fun setRightText(rightText: String): ViewTitle {
        tvRight.text = rightText
        return this
    }

    fun setRightTextVisiable(visiable: Int): ViewTitle {
        tvRight.visibility = visiable
        return this
    }

    fun setRightSrc(resId: Int): ViewTitle {
        ivRight.setImageResource(resId)
        return this
    }
    fun setRightSrcVisiable(visiable: Int): ViewTitle {
        ivRight.visibility = visiable
        return this
    }

    fun setRightClickListener(onClickListener: OnClickListener): ViewTitle {
        ivRight.setOnClickListener(onClickListener)
        tvRight.setOnClickListener(onClickListener)
        return this
    }

}