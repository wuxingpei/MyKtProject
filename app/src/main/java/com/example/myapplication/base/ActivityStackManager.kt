package com.example.myapplication.base

import android.app.Activity
import android.os.Build


object ActivityStackManager { //笔记 object声明对象
    private val activities = mutableListOf<Activity>()  //笔记 val常量   mutableListOf可变列表

    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    fun removeActivity(activity: Activity) {
        if (activities.contains(activity)) {
            activities.remove(activity)
            activity.finish()
        }
    }

    fun finishAll() =
        activities.filterNot { it.isFinishing }.forEach { it.finish() }

}
