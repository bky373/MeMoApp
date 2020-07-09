package com.bobo.mytodolist.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bobo.mytodolist.*
import java.lang.IndexOutOfBoundsException

//const val TASK_PAGE_INDEX = 0
//const val BIN_PAGE_INDEX = 1
//const val TASK_GROUP_PAGE_INDEX = 2
//const val RESOLUTIONS_PAGE_INDEX = 3

const val TASK_PAGE_INDEX = 0
const val FOLDER_PAGE_INDEX = 1
const val CALENDAR_PAGE_INDEX = 2
const val BIN_PAGE_INDEX = 3
//const val SETTING_PAGE_INDEX = 3
//const val RESOLUTIONS_PAGE_INDEX = 3

class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        TASK_PAGE_INDEX to { NoteFragment() },
        CALENDAR_PAGE_INDEX to { CalendarFragment() },
        FOLDER_PAGE_INDEX to { FolderFragment() },
        BIN_PAGE_INDEX to { BinFragment() }

//        RESOLUTIONS_PAGE_INDEX to { ResolutionsOfThisYearFragment() }
    )

    override fun getItemCount(): Int = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}
