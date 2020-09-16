package com.example.mvvm_jetpack_practice.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PersonStateBean{

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "person_id")
    public int personId;

    @ColumnInfo(name = "is_eat")
    public boolean isEat;

    @ColumnInfo(name = "type")
    public int type;



}
