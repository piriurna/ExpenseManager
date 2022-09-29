package com.blankwhite.expensemanager.ui.main.expenses.adapters

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FabButtonAutoHideListener(private val fabButton: FloatingActionButton) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if(recyclerView.scrollState == RecyclerView.SCROLL_STATE_IDLE) return

        if(dy > 0) {
            fabButton.hide()
        }else if(dy < 0){
            fabButton.show()
        }
    }
}