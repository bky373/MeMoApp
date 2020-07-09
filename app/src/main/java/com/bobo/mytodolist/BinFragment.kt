package com.bobo.mytodolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.bobo.mytodolist.adapters.BinAdapter
import com.bobo.mytodolist.adapters.NoteAdapter
import com.bobo.mytodolist.data.Task
import com.bobo.mytodolist.databinding.FragmentBinBinding
import com.bobo.mytodolist.utilities.InjectorUtils
import com.bobo.mytodolist.viewModels.BinViewModel
import com.bobo.mytodolist.viewModels.NoteViewModel


class BinFragment : Fragment() {

    private val noteViewModel: NoteViewModel by viewModels {
        InjectorUtils.provideTaskViewModelFactory(requireContext())
    }
    private val binViewModel: BinViewModel by viewModels {
        InjectorUtils.provideBinViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentBinBinding>(
            inflater, R.layout.fragment_bin, container, false
        )
        val adapter = BinAdapter(
            onRestoreClickListener = { task ->
                noteViewModel.moveTask(task)
                Toast.makeText(context, getString(R.string.move_task_to_note), Toast.LENGTH_SHORT).show()
            },
            onDeleteClickListener = { task ->
                context?.let {
                    AlertDialog.Builder(it)
                        .setTitle(getString(R.string.alert_delete_title))
                        .setMessage(getString(R.string.ask_delete_forever))
                        .setPositiveButton(getString(R.string.delete)){ _, _ ->
                            binViewModel.deleteTask(task)
                            Toast.makeText(context,getString(R.string.task_deleted), Toast.LENGTH_SHORT).show()
                        }
                        .setNeutralButton(getString(R.string.cancel)){ _, _ ->
                        }.create().show()
                }
            }
        )
        binding.apply {
            recyclerView.adapter = adapter
            lifecycleOwner = viewLifecycleOwner

            viewModel = binViewModel
        }
        updateUI(adapter)
        return binding.root
    }

    private fun updateUI(adapter: BinAdapter) {
        binViewModel.binItems.observe(viewLifecycleOwner) { result ->
            val list = result as ArrayList<Task>
            adapter.updateList(list)
        }
    }
}