package com.paraskcd.notes.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.time.Instant
import java.util.Date
import java.util.UUID

class DateConverter {
    @TypeConverter
    fun timeStampFromDate(date: Date): Long {
        return date.time

    }
    @TypeConverter
    fun dateFromTimestamp(timestamp: Long): Date? {
        return Date(timestamp)
    }
}

class UUIDConverter {
    @TypeConverter
    fun fromUUID(uuid: UUID): String? {
        return uuid.toString()
    }

    @TypeConverter
    fun uuidFromString(string: String?): UUID? {
        return UUID.fromString(string)
    }
}

@Entity(tableName = "notes_tbl")
data class Note(
    @PrimaryKey
        val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "note_title")
        val title: String,

    @ColumnInfo(name = "note_description")
        val description: String,

    @ColumnInfo(name = "note_entry_date")
        val entryDate: Date = Date.from(Instant.now())
)

