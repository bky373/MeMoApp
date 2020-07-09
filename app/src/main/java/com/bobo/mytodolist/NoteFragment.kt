package com.bobo.mytodolist

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import com.bobo.mytodolist.adapters.NoteAdapter
import com.bobo.mytodolist.data.Task
import com.bobo.mytodolist.databinding.FragmentNote2Binding
import com.bobo.mytodolist.databinding.FragmentNoteBinding
import com.bobo.mytodolist.utilities.FunctionsUtils
import com.bobo.mytodolist.utilities.InjectorUtils
import com.bobo.mytodolist.utilities.TaskItemTouchHelper
import com.bobo.mytodolist.viewModels.NoteViewModel


// TODO edittext 글자수 길어지면 버튼 삭제됨..
// TODO 뒤로가기 했을 때 대화창 띄우기

class NoteFragment : Fragment() {
    private val noteViewModel: NoteViewModel by viewModels {
        InjectorUtils.provideTaskViewModelFactory(requireContext())
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentNote2Binding>(
            inflater, R.layout.fragment_note2, container, false
        )

        val adapter = NoteAdapter(
            requireContext(),
            noteViewModel,
            onClickListener = { task ->
                noteViewModel.moveTask(task)
                Toast.makeText(context, getString(R.string.move_task_to_bin), Toast.LENGTH_SHORT)
                    .show()
            }
        )

        binding.apply {
            recyclerView.adapter = adapter
            val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            context?.getDrawable(R.drawable.rv_divider)?.let {
                dividerItemDecoration.setDrawable(
                    it
                )
            }
            recyclerView.addItemDecoration(dividerItemDecoration)
//            editText.addTextChangedListener { onTextChange(this, it) }
//            editText.addTextChangedListener { text ->
//                if(text.toString().trim().isEmpty()){
//                    editText.clearFocus()
//                }
//
//            }
//            editText.setOnTouchListener(View.OnTouchListener { _, event ->
//
//                val DRAWABLE_LEFT = 0
//                val DRAWABLE_RIGHT = 2
//
//                if (event.action == MotionEvent.ACTION_UP) {
//                    if (event.x <= editText.compoundDrawables[DRAWABLE_LEFT].bounds.width() + (2 * editText.paddingStart)) {
//                        editText.text = null
//                        editText.requestFocus()
//                        return@OnTouchListener true
//                    }
//                    if (event.x >= editText.right - editText.compoundDrawables[DRAWABLE_RIGHT].bounds.width() - (2 * editText.paddingEnd)) {
//                        val taskName = editText.text.toString()
//                        if (taskName.isNotEmpty()) {
//                            viewModel?.apply {
//                                val currentTask = Task(taskName,getLargestOrder() + 1)
//                                addTask(currentTask)
//                            }
//                            editText.text.clear()
//                            FunctionsUtils.hideKeyboard(context, view)
//                            recyclerView.scrollToPosition(adapter.itemCount - 1)
//                        }
//                        return@OnTouchListener true
//                    }
//                }
//                false
//            })



            lifecycleOwner = viewLifecycleOwner
            viewModel = noteViewModel
//            addCallback = object : Callback {
//                override fun onAdd() {
//                    val taskName = editText.text.toString()
//                    if (taskName.isNotEmpty()) {
//                        viewModel?.apply {
//                            val currentTask = Task(taskName, getLargestOrder() + 1)
//                            addTask(currentTask)
//                        }
//                        editText.text.clear()
//                        FunctionsUtils.hideKeyboard(context, view)
//                        recyclerView.scrollToPosition(adapter.itemCount - 1)
//                    }
//                }
//            }

            ItemTouchHelper(TaskItemTouchHelper(adapter)).attachToRecyclerView(this.recyclerView)
//            requireActivity()
//                .onBackPressedDispatcher
//                .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
//                    override fun handleOnBackPressed() {
//                        subContainer.requestFocus()
//                        if(subContainer.isFocused) {
////                            remove()
//                        }
//                    }
//                })
        }

        updateUI(adapter)
        return binding.root
    }

    private fun updateUI(adapter: NoteAdapter) {
        noteViewModel.noteTasks.observe(viewLifecycleOwner) { result ->
            adapter.updateList(result)
        }
    }
    private fun onTextChange(binding: FragmentNoteBinding, s: Editable?) {
//        binding.apply {
//            if (s.toString().trim().isEmpty()) {
//                addBtn.apply {
//                    text = context.getString(R.string.input_btn)
//                    setBackgroundColor(
//                        ContextCompat.getColor(
//                            requireContext(),
//                            R.color.pink_custom
//                        )
//                    )
//                }
//            } else {
//                addBtn.apply {
//                    text = context.getString(R.string.add_btn)
//                    setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
//                }
//            }
//        }
    }

    interface Callback {
        fun onAdd()
    }

}





