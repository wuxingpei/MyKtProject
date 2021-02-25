package com.example.myapplication.ui.order.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.example.myapplication.databinding.ItemRecyclerOrderBinding
import com.example.myapplication.entity.UserCollectDetail


class OrderAdapter(layoutResId: Int) :
    BaseQuickAdapter<UserCollectDetail, BaseDataBindingHolder<ItemRecyclerOrderBinding>>(
        layoutResId
    ), LoadMoreModule {//笔记 Adapter类实现LoadMoreModule接口即可
    override fun convert(
        holder: BaseDataBindingHolder<ItemRecyclerOrderBinding>,
        item: UserCollectDetail
    ) {
        val binding: ItemRecyclerOrderBinding? = holder.dataBinding
        if (binding != null) {
            binding.data = item
        }
    }
}