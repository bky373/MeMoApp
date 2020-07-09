package com.bobo.mytodolist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.util.TableInfo

const val TAG_PINK = 3
//Entity: Represents a table within the database.
@Entity(tableName = "task_table")
data class Task(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "order") var order: Long,
    @ColumnInfo(name = "is_deleted") var isDeleted: Boolean = false,
    @ColumnInfo(name = "tag") var tag: Int = TAG_PINK

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long = 0

    override fun toString() = name
}