package com.example.worldcup2026app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.worldcup2026app.data.local.dao.MundialDao
import com.example.worldcup2026app.data.local.entity.TeamEntity
import com.example.worldcup2026app.data.local.entity.PlayerEntity


@Database(
    entities = [TeamEntity::class, PlayerEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MundialDatabase : RoomDatabase(){
    abstract val dao: MundialDao
}