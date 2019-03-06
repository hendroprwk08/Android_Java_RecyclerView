package com.example.cilodong_latihan02_recyclerview;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface SiswaDao {
    @Query("SELECT * FROM siswa")
    List<Siswa> getAll();

    @Query("SELECT * FROM siswa WHERE nama LIKE :nama")
    List<Siswa> findByName(String nama);

    @Insert
    void insertAll(Siswa siswa); //pake class tanpa id-nya

    @Update
    void update(Siswa siswa); //pake class yang ada id-nya

    @Delete
    void delete(Siswa siswa); //pake class yang ada id-nya
}
