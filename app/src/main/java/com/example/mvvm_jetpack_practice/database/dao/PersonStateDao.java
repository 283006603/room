package com.example.mvvm_jetpack_practice.database.dao;

import com.example.mvvm_jetpack_practice.bean.PersonStateBean;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface PersonStateDao extends BaseDao<PersonStateBean>{

    //根据id精确查找某一个persion
    @Query("select *from personstatebean where person_id=(:personId)")
    PersonStateBean queryPersonById(int personId);

    //把吃了饭的人全部找出来
    @Query("select * from personstatebean where is_eat=(:isEat)")
    PersonStateBean queryListPersonByEat(boolean isEat);

    //把所有人找出来(他们的共有属性，是自己定的type=1)
    @Query("select * from personstatebean where type=(:type)")
    List<PersonStateBean> queryListPersonByType(int type);
}
