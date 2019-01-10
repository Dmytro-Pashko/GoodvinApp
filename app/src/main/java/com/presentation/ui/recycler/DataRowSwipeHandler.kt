package com.presentation.ui.recycler

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION

/**
 * Created by Dmytro Pashko on 1/10/2019.
 */

class DataRowSwipeHandler(private val adapter: DataRowAdapter) :
    ItemTouchHelper.SimpleCallback(0, AVAILABLE_SWIPE_DIRS) {

    companion object {
        private const val AVAILABLE_SWIPE_DIRS = ItemTouchHelper.LEFT
    }

    override fun onMove(view: RecyclerView, holder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) = false

    override fun onSwiped(holder: RecyclerView.ViewHolder, direction: Int) {
        val position = holder.adapterPosition
        if (position != NO_POSITION) {
            adapter.removeItemAt(position)
        }
    }
}
