package com.bobo.mytodolist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bobo.mytodolist.BR
import com.bobo.mytodolist.R
import com.bobo.mytodolist.data.Task
import com.bobo.mytodolist.databinding.ItemBinBinding
import kotlin.collections.ArrayList


class BinAdapter(
    private val onRestoreClickListener: (Task) -> Unit,
    private val onDeleteClickListener: (Task) -> Unit
) : RecyclerView.Adapter<BinAdapter.BinHolder>() {

    private var mBinItems = ArrayList<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinHolder {
        return BinHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_bin, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BinHolder, position: Int) {
        val currentTask = mBinItems[holder.adapterPosition]
        holder.bind(currentTask)
    }

    inner class BinHolder(
        val binding: ItemBinBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentTask: Task) {
            with(binding) {
                setVariable(BR.binItem, currentTask)
                ivRestore.setOnClickListener {
                    onRestoreClickListener.invoke(currentTask)
                }
                // TODO 삭제 후 order는??
                btnDeleteForever.setOnClickListener {
                    onDeleteClickListener.invoke(currentTask)
                }
                executePendingBindings()
            }
        }
    }
    override fun getItemCount(): Int = mBinItems.size

    fun updateList(binItems: List<Task>) {
        this.mBinItems = binItems as ArrayList<Task>
        notifyDataSetChanged()
    }

    //
//    fun updateList(newList: ArrayList<Task>) {
//        val diffResult = DiffUtil.calculateDiff(NoteListDiffCallback(this.mTasks, newList))
//
//        this.mTasks.clear()
//        this.mTasks = newList
//        diffResult.dispatchUpdatesTo(this)
//    }

//    private class NoteListDiffCallback(val oldList: ArrayList<Task>, val newList: ArrayList<Task>) :
//        DiffUtil.Callback() {
//
//        override fun getOldListSize(): Int {
//            return oldListSize
//        }
//
//        override fun getNewListSize(): Int {
//            return newListSize
//        }
//
//        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//            return oldList.get(oldItemPosition).taskId == newList.get(newItemPosition).taskId
//        }
//
//        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//            return oldList.get(oldItemPosition) == newList.get(newItemPosition)
//        }
//    }
}
//private class NoteListDiffCallback : DiffUtil.ItemCallback<Task>() {
//
//    override fun areItemsTheSame(
//        oldItem: Task,
//        newItem: Task
//    ): Boolean {
//        return oldItem.taskId == newItem.taskId
//    }
//
//    override fun areContentsTheSame(
//        oldItem: Task,
//        newItem: Task
//    ): Boolean {
//        return oldItem == newItem
//    }
//}







