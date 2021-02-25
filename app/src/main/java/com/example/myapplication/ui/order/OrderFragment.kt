package com.example.myapplication.ui.order

import android.os.Bundle
import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.databinding.FragmentOrderBinding
import com.example.myapplication.entity.UserCollectDetail
import com.example.myapplication.ui.order.activity.DetailActivity
import com.example.myapplication.ui.order.adapter.DiffOrderCallBack
import com.example.myapplication.ui.order.adapter.OrderAdapter
import com.example.myapplication.viewmodel.OrderViewModel
import kotlinx.android.synthetic.main.fragment_order.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel


class OrderFragment : BaseFragment<FragmentOrderBinding>() {
    private var page = 0
    private val mOrderViewModel by viewModel<OrderViewModel>()
    private val mAdapter = OrderAdapter(R.layout.item_recycler_order)
    override fun getLayoutId(): Int {
        return R.layout.fragment_order
    }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        mBinding?.run {
            orderAdapter = mAdapter
            refreshColor=R.color.colorMain
            refreshListener= SwipeRefreshLayout.OnRefreshListener {
                // 这里的作用是防止下拉刷新的时候还可以上拉加载
                mAdapter.loadMoreModule.isEnableLoadMore = false
                page = 0
                getOrderData(page)
            }
        }
        page = 0
        getOrderData(page)
        mAdapter.setOnItemClickListener { adapter, view, position ->
            val userCollectDetail: UserCollectDetail =
                adapter.getItem(position) as UserCollectDetail
            context?.startActivity<DetailActivity>(
                "title" to userCollectDetail.title,
                "link" to userCollectDetail.link
            )
        }
        // 是否自定加载下一页（默认为true）
//        mAdapter.loadMoreModule.isAutoLoadMore = false
        mAdapter.setDiffCallback(DiffOrderCallBack())
        // 当数据不满一页时，是否继续自动加载（默认为true）
//        mAdapter.loadMoreModule.isEnableLoadMoreIfNotFullPage = false
        // 设置加载更多监听事件
        mAdapter.loadMoreModule.setOnLoadMoreListener {
            page++
            getOrderData(page)
        }
    }

    private fun getOrderData(page: Int) {
        launch {
            mOrderViewModel.order(page).catch {
                swipeRefresh.isRefreshing = false

                mAdapter.loadMoreModule.isEnableLoadMore = true
                // 当前这次数据加载错误，调用此方法
                mAdapter.loadMoreModule.loadMoreFail()
                context?.toast(R.string.no_network)
            }.onStart {
                //笔记 可进行加载框的显示
            }.onCompletion {
                //笔记 可进行加载框的隐藏
            }.collectLatest {
                swipeRefresh.isRefreshing = false

                mAdapter.loadMoreModule.isEnableLoadMore = true
                if (it.data.curPage < it.data.pageCount) {
                    mAdapter.loadMoreModule.loadMoreComplete()
                } else if (it.data.curPage == it.data.pageCount) {
                    mAdapter.loadMoreModule.loadMoreEnd()
                }
                if (it.errorCode == 0) {
                    if (page == 0) {
                        mAdapter.setDiffNewData(it.data.datas.toMutableList(), Runnable {
                            //平滑滚动
                            recyclerview.layoutManager?.scrollToPosition(0)
                        })
                    } else {
                        mAdapter.addData(it.data.datas)
                    }

                }
            }
        }
    }
}