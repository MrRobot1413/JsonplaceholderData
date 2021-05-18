package ru.mrrobot1413.test.storage.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.mrrobot1413.test.App

@Entity(tableName = App.TABLE_NAME)
data class AlbumDb(
    @ColumnInfo(name = "userId")
    val userId: Int,
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
)