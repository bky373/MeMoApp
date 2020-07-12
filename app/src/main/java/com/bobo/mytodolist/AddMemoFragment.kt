package com.bobo.mytodolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController

import com.bobo.mytodolist.data.Task
import com.bobo.mytodolist.databinding.FragmentAddMemoBinding
import com.bobo.mytodolist.utilities.FunctionsUtils
import com.bobo.mytodolist.utilities.InjectorUtils
import com.bobo.mytodolist.viewModels.NoteViewModel

//const val TAG_RED = 0
//const val TAG_YELLOW = 1
//const val TAG_BLUE = 2
//const val TAG_PINK = 3

class AddMemoFragment : Fragment() {

    private val noteViewModel: NoteViewModel by viewModels {
        InjectorUtils.provideTaskViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentAddMemoBinding>(
            inflater, R.layout.fragment_add_memo, container, false
        ).apply {
            viewModel = noteViewModel
            lifecycleOwner = viewLifecycleOwner
            callback = object : Callback {
                override fun onAdd() {
                    val taskName = writeEditText.text.toString()
                    if (taskName.isNotEmpty()) {
                        viewModel?.apply {
                            val currentTask = Task(taskName, getLargestOrder() + 1)
                            addTask(currentTask)
                        }
                        writeEditText.text.clear()
                        navigateUp(view)
                        FunctionsUtils.hideKeyboard(context, view)
                    }
                }
            }
            // navigationController 사용
            toolbar.setNavigationOnClickListener { view ->
                navigateUp(view)
                FunctionsUtils.hideKeyboard(context, view)
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

    interface Callback {
        fun onAdd()
    }
}
//val builder = MaterialDatePicker.Builder.datePicker()
//val picker = builder.build()
//
//fragmentManager?.let { picker.show(it, picker.toString()) }