package com.bobo.mytodolist.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bobo.mytodolist.*
import com.bobo.mytodolist.data.Task
import com.bobo.mytodolist.databinding.ItemNoteBinding
import com.bobo.mytodolist.utilities.ItemTouchHelperListener
import com.bobo.mytodolist.viewModels.NoteViewModel
import java.util.*
import kotlin.collections.ArrayList


class NoteAdapter(
    private val context : Context,
    private val noteViewModel: NoteViewModel,
    private val onClickListener: (Task) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteHolder>(),
    ItemTouchHelperListener, Filterable {

    var mTasks = ArrayList<Task>()
    private var searchList: List<Task> = mTasks

    open inner class NoteHolder(
        val binding: ItemNoteBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currentTask: Task) {
            with(binding) {
                setVariable(BR.task, currentTask)
                setTagColor(this, currentTask.tag)
                setClickListener { view ->
                    navigateToTask(currentTask.id, view)
                }
                bin.setOnClickListener {
                    onClickListener.invoke(currentTask)
                }
                executePendingBindings()
            }
        }

        private fun navigateToTask(taskId: Long, view: View) {
            val direction = HomeFragmentDirections
                .actionHomeFragmentToTaskDetailFragment(taskId)
            view.findNavController().navigate(direction)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.NoteHolder {
        return NoteHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_note, parent, false
            )
        )
    }

    // position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentTask = mTasks[position]
        holder.bind(currentTask) // 뷰홀더랑 currentTask랑 묶는다
    }

    override fun getItemCount(): Int = mTasks.size


    // 서치뷰 관련
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    searchList = mTasks
                } else {
                    val filteredList = ArrayList<Task>()

                    for (row in searchList) {
                        if (row.name.toLowerCase(Locale.ROOT)
                                .contains(charString.toLowerCase(Locale.ROOT))
                        ) {
                            filteredList.add(row)
                        }
                    }
                    searchList = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = searchList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
                searchList = filterResults?.values as ArrayList<Task>
                notifyDataSetChanged()
            }
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            // task 재배열
            for (i in fromPosition until toPosition) {
                Collections.swap(mTasks, i, i + 1)

                // task_order 재배열
                val order1 = mTasks.get(i).order
                val order2 = mTasks.get(i + 1).order
                mTasks.get(i).order = order2
                mTasks.get(i + 1).order = order1
            }
        } else {
            for (i in toPosition until fromPosition) {
                Collections.swap(mTasks, i + 1, i)

                val order1 = mTasks.get(i + 1).order
                val order2 = mTasks.get(i).order
                mTasks.get(i + 1).order = order2
                mTasks.get(i).order = order1
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemRemove(position: Int) {
        noteViewModel.moveTask(mTasks[position])
        Toast.makeText(context, context.getString(R.string.move_task_to_bin), Toast.LENGTH_SHORT).show()
    }

    override fun onClearView() {
        noteViewModel.updateAllTasks(mTasks)
    }

    private fun setTagColor(binding: ItemNoteBinding, color: Int) {
        when (color) {
            TAG_RED -> {
                binding.ivHasty.background = ContextCompat.getDrawable(context, R.drawable.ic_hasty_task)
                binding.tvTagBar.setBackgroundColor(ContextCompat.getColor(context,R.color.red))
            }
            TAG_BLUE -> {
                binding.ivHasty.background = ContextCompat.getDrawable(context, R.drawable.ic_shopping_cart)
                binding.tvTagBar.setBackgroundColor(ContextCompat.getColor(context,R.color.blue))
            }
            TAG_YELLOW -> {
                binding.ivHasty.background = ContextCompat.getDrawable(context, R.drawable.ic_star)
                binding.tvTagBar.setBackgroundColor(ContextCompat.getColor(context,R.color.yellow))
            }
            TAG_PINK -> {
                binding.ivHasty.background = ContextCompat.getDrawable(context, R.drawable.ic_clip)
                binding.tvTagBar.setBackgroundColor(ContextCompat.getColor(context,R.color.black))
            }
            else -> {
                binding.ivHasty.background = ContextCompat.getDrawable(context, R.drawable.ic_clip)
                binding.tvTagBar.setBackgroundColor(ContextCompat.getColor(context,R.color.black))
            }
        }
    }
//    private fun setTagColor(binding: ItemNoteBinding, color: Int) {
//        when (color) {
//            TAG_RED -> {
//                binding.tvTagBar.setBackgroundColor(ContextCompat.getColor(context,R.color.red))
//                binding.bin.setColorFilter(ContextCompat.getColor(context,R.color.red))
//            }
//            TAG_BLUE -> {
//                binding.tvTagBar.setBackgroundColor(ContextCompat.getColor(context,R.color.blue))
//                binding.bin.setColorFilter(ContextCompat.getColor(context,R.color.blue))
//            }
//            TAG_YELLOW -> {
//                binding.tvTagBar.setBackgroundColor(ContextCompat.getColor(context,R.color.yellow))
//                binding.bin.setColorFilter(ContextCompat.getColor(context,R.color.yellow))
//            }
//            TAG_PINK -> {
//                binding.tvTagBar.setBackgroundColor(ContextCompat.getColor(context,R.color.pink_custom))
//                binding.bin.setColorFilter(ContextCompat.getColor(context,R.color.pink_custom))
//            }
//            else -> {
//                binding.tvTagBar.setBackgroundColor(ContextCompat.getColor(context,R.color.pink_custom))
//                binding.bin.setColorFilter(ContextCompat.getColor(context,R.color.pink_custom))
//            }
//        }
//    }

    fun updateList(tasks: List<Task>) {
        mTasks.clear()
        mTasks.addAll(tasks)
        notifyDataSetChanged()
    }
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
//}
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







