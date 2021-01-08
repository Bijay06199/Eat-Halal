package com.example.eatthalal.utils.extentions

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eatthalal.ui.main.shop.adapter.ProductAdapter
import com.example.eatthalal.ui.main.shop.response.NewProductResponseItem

class OnScrollListener(val layoutManager: LinearLayoutManager, val adapter: RecyclerView.Adapter<ProductAdapter.ViewHolder>, val dataList: ArrayList<NewProductResponseItem>) : RecyclerView.OnScrollListener() {
    var previousTotal = 0
    var loading = true
    val visibleThreshold = 10
    var firstVisibleItem = 0
    var visibleItemCount = 0
    var totalItemCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        visibleItemCount = recyclerView.childCount
        totalItemCount = layoutManager.itemCount
        firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            val initialSize = dataList.size
            val updatedSize = dataList.size
            recyclerView.post { adapter.notifyItemRangeInserted(initialSize, updatedSize) }
            loading = true
        }
    }

    fun updateDataList(dataList: MutableList<Int>) : List<Int> {
        kotlin.repeat(30) {
            dataList.add(dataList.size + 1)
        }
        return dataList
    }


}

