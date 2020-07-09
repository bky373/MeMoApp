package com.bobo.mytodolist

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController

import com.bobo.mytodolist.data.Task
import com.bobo.mytodolist.databinding.FragmentAddTaskBinding
import com.bobo.mytodolist.utilities.FunctionsUtils
import com.bobo.mytodolist.utilities.InjectorUtils
import com.bobo.mytodolist.viewModels.NoteViewModel

//const val TAG_RED = 0
//const val TAG_YELLOW = 1
//const val TAG_BLUE = 2
//const val TAG_PINK = 3

class AddTaskFragment : Fragment() {

    private val noteViewModel: NoteViewModel by viewModels {
        InjectorUtils.provideTaskViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentAddTaskBinding>(
            inflater, R.layout.fragment_add_task, container, false
        ).apply {
            viewModel = noteViewModel
            lifecycleOwner = viewLifecycleOwner
            callback = object : Callback {
                override fun onAdd() {
                    val taskName = editText.text.toString()
                    if (taskName.isNotEmpty()) {
                        viewModel?.apply {
                            val currentTask = Task(taskName, getLargestOrder() + 1)
                            addTask(currentTask)
                        }
                        editText.text.clear()
                        navigateUp(view)
                        FunctionsUtils.hideKeyboard(context, view)
                    }
                }
            }


            toolbar.setNavigationOnClickListener { view ->
                navigateUp(view)
                FunctionsUtils.hideKeyboard(context, view)
            }

            // 공유하기
            toolbar.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_share -> {
                        createShareIntent()
                        true
                    }
                    else -> false
                }
            }


        }
        setHasOptionsMenu(true)
        // 키보드
//            var position = editTaskName.length()
//            var eText = editTaskName.text
//            Selection.setSelection(eText, position)
//            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
//        FunctionsUtils.showKeyboard(context, view)
        return binding.root
    }

    private fun navigateUp(view: View?) {
        view?.findNavController()?.navigateUp()
    }

    private fun createShareIntent() {
//        val shareTask = taskDetailViewModel.task.value?.name.toString()
//        val shareIntent = activity?.let { fragmentActivity ->
//            ShareCompat.IntentBuilder.from(fragmentActivity)
//                .setText(shareTask)
//                .setType("text/plain")
//                .createChooserIntent()
//                .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
//        }
//        startActivity(shareIntent)
    }

    //    private fun selectTagColor(binding: FragmentTaskDetailBinding, id: Int?): Int {
//        return when (id) {
//            binding.red.id -> TAG_RED
//            binding.blue.id -> TAG_BLUE
//            binding.yellow.id -> TAG_YELLOW
//            binding.pink.id -> TAG_PINK
//            else -> TAG_PINK
//        }
//    }
//    private fun selectTagColor(binding: FragmentAddTaskBinding, id: Int?): Int {
//        return when (id) {
//            binding.hasty.id -> TAG_RED
//            binding.star.id -> TAG_BLUE
//            binding.cart.id -> TAG_YELLOW
//            binding.pin.id -> TAG_PINK
//            else -> TAG_PINK
//        }
//    }

    interface Callback {
        fun onAdd()
    }
}
//val builder = MaterialDatePicker.Builder.datePicker()
//val picker = builder.build()
//
//fragmentManager?.let { picker.show(it, picker.toString()) }