package com.bobo.mytodolist.adapters
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.databinding.DataBindingUtil
//import androidx.recyclerview.widget.RecyclerView
//import com.bobo.mytodolist.R
//import com.bobo.mytodolist.data.Task
//import com.bobo.mytodolist.databinding.ListItemTaskListingBinding
//import com.bobo.mytodolist.utilities.TaskItemTouchHelperAdapter
//
//class NoteRecyclerAdapter(
//    private var tasks: List<Task> = arrayListOf(),
//    val onClickDeleteIcon: (task: Task) -> Unit
//) : RecyclerView.Adapter<NoteRecyclerAdapter.TaskHolder>(), TaskItemTouchHelperAdapter {
//
//    class TaskHolder(
//        val binding: ListItemTaskListingBinding
//    ) : RecyclerView.ViewHolder(binding.root) {
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
//        return TaskHolder(
//            DataBindingUtil.inflate(
//                LayoutInflater.from(parent.context),
//                R.layout.item_note,
//                parent,
//                false
//            )
//        )
//    }
//
//    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
//        val currentTask= tasks.get(position)
//        holder.binding.taskName.text = currentTask.name
//    }
//
//    override fun getItemCount(): Int = tasks.size
//
//
//    fun setTasks(tasks: List<Task>) {
//        this.tasks = tasks
//        notifyDataSetChanged()
//    }
//}
//
//
//
//
//
//
//
