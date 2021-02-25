package com.example.myapplication.ui.order.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.entity.UserCollectDetail

class DiffOrderCallBack : DiffUtil.ItemCallback<UserCollectDetail>() {
    /**
     * 判断是否是同一个item
     *
     * @param oldItem New data
     * @param newItem old Data
     * @return
     */
    override fun areItemsTheSame(oldItem: UserCollectDetail, newItem: UserCollectDetail): Boolean {
        return oldItem.id == newItem.id
    }

    /**
     * 当是同一个item时，再判断内容是否发生改变
     *
     * @param oldItem New data
     * @param newItem old Data
     * @return
     */
    override fun areContentsTheSame(
        oldItem: UserCollectDetail,
        newItem: UserCollectDetail
    ): Boolean {
        return oldItem.author == newItem.author
                && oldItem.chapterId == newItem.chapterId
                && oldItem.chapterName == newItem.chapterName
                && oldItem.courseId == newItem.courseId
                && oldItem.desc == newItem.desc
                && oldItem.envelopePic == newItem.envelopePic
                && oldItem.link == newItem.link
                && oldItem.niceDate == newItem.niceDate
                && oldItem.origin == newItem.origin
                && oldItem.originId == newItem.originId
                && oldItem.publishTime == newItem.publishTime
                && oldItem.title == newItem.title
                && oldItem.userId == newItem.userId
                && oldItem.visible == newItem.visible
                && oldItem.zan == newItem.zan
    }
}