package com.bobo.mytodolist

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bobo.mytodolist.adapters.*
import com.bobo.mytodolist.databinding.FragmentHomeBinding

import com.bobo.mytodolist.utilities.InjectorUtils
import com.bobo.mytodolist.viewModels.BinViewModel
import com.bobo.mytodolist.viewModels.NoteViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.item_note.*

class HomeFragment : Fragment() {

    private val noteViewModel: NoteViewModel by viewModels {
        InjectorUtils.provideTaskViewModelFactory(requireContext())
    }
    private val binViewModel: BinViewModel by viewModels {
        InjectorUtils.provideBinViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = PagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
//            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        val fab = binding.fab
        fab.setOnClickListener { view->
            val action = HomeFragmentDirections.actionHomeFragmentToAddTaskFragment()
            view.findNavController().navigate(action)
        }


        setHasOptionsMenu(true)
        return binding.root
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
//
//            TASK_PAGE_INDEX -> "메모"
//            FOLDER_PAGE_INDEX -> "메모 그룹"
//            CALENDAR_PAGE_INDEX -> "달력"
//            SETTING_PAGE_INDEX -> "세팅"

//            TASK_PAGE_INDEX -> getString(R.string.task_to_do)
//            BIN_PAGE_INDEX -> getString(R.string.bin)
//            TASK_GROUP_PAGE_INDEX -> getString(R.string.task_group)
//            RESOLUTIONS_PAGE_INDEX -> getString(R.string.resolutions_of_this_year)
            else -> null
        }
    }
    private fun getTabIcon(position: Int) : Int {
        return when (position) {
            TASK_PAGE_INDEX -> R.drawable.selector_tab_note
            FOLDER_PAGE_INDEX -> R.drawable.selector_tab_folder
            CALENDAR_PAGE_INDEX -> R.drawable.selector_tab_calendar
            BIN_PAGE_INDEX -> R.drawable.selector_tab_bin
//            SETTING_PAGE_INDEX -> R.drawable.selector_tab_setting
            else -> throw IndexOutOfBoundsException()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_note, menu)
//        val adapter = NoteAdapter(noteViewModel, onClickListener = {}, 4)
//        val searchView = (menu.findItem(R.id.action_search).actionView) as SearchView
//        searchView.maxWidth = Int.MAX_VALUE
//        searchView.apply {
//            queryHint = "할 일 검색하기"
////            imeOptions = EditorInfo.IME_ACTION_DONE
//            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                override fun onQueryTextSubmit(query: String?): Boolean {
//                    adapter.filter.filter(query)
//                    return false
//                }
//
//                override fun onQueryTextChange(newText: String?): Boolean {
////                    searchView.clearFocus()
//                    adapter.filter.filter(query)
//                    return true
//                }
//            })
//        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> return true
            R.id.action_settings -> return true
            R.id.action_empty -> {
                context?.let {
                    AlertDialog.Builder(it)
                        .setTitle(getString(R.string.alert_delete_all_title))
                        .setMessage(getString(R.string.ask_delete_all_forever))
                        .setPositiveButton(getString(R.string.delete)) { _, _ ->
                            binViewModel.emptyBin()
                            Toast.makeText(
                                context,
                                getString(R.string.all_bins_deleted),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .setNeutralButton(getString(R.string.cancel)) { _, _ ->
                        }.create().show()
                }
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//
//
//        var backPressedTime: Long = 0
//        lateinit var backToast: Toast
//
//        requireActivity()
//            .onBackPressedDispatcher
//            .addCallback(this, object : OnBackPressedCallback(true) {
//
//                override fun handleOnBackPressed() {
//                    if (backPressedTime + 2000 > System.currentTimeMillis()) {
//                        backToast.cancel()
//                        requireActivity().onBackPressed()
//                        return
//                    } else {
//                        backToast = Toast.makeText(
//                            context,
//                            getString(R.string.finish_on_back_pressed),
//                            Toast.LENGTH_SHORT
//                        )
//                        backToast.show()
//                    }
//                    backPressedTime = System.currentTimeMillis()
//
//                    if (isEnabled) {
//                        isEnabled = false
//                    }
//                }
//            })
//    }
}

