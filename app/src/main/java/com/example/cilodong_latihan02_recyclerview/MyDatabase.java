package com.example.cilodong_latihan02_recyclerview;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = Siswa.class, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract SiswaDao siswaDao();
}
