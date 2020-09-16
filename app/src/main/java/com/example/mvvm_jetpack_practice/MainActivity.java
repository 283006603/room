package com.example.mvvm_jetpack_practice;

import android.os.Bundle;

import com.example.mvvm_jetpack_practice.adapter.MyAdapter;
import com.example.mvvm_jetpack_practice.bean.Person;
import com.example.mvvm_jetpack_practice.bean.PersonStateBean;
import com.example.mvvm_jetpack_practice.database.AppDataBase;
import com.example.mvvm_jetpack_practice.database.dao.PersonStateDao;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Person> list=new ArrayList<>();;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleview);
        LinearLayoutManager linearLayoutManage = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManage);
        adapter = new MyAdapter(list, this);
        initData();
        recyclerView.setAdapter(adapter);
        initListener();

    }



    private void initListener(){
        adapter.setListener(new MyAdapter.AdapterListener(){
            @Override
            public void onclick(int position){
                boolean eatrice =list.get(position).isEatrice();
                eatrice = !eatrice;
                list.get(position).setEatrice(eatrice);
                adapter.notifyDataSetChanged();
                //改变数据库
                Person person = list.get(position);
                int id = person.getId();
                PersonStateBean personStateBean = personStateDao.queryPersonById(id);
                if(null!=personStateBean){
                    personStateBean.isEat=eatrice;
                    personStateDao.insert(personStateBean);
                }
            }
        });
    }

    //查询数据库，数据库有数据沿用数据库，如果没有代表第一次进入用初始化的数据
    PersonStateDao personStateDao = AppDataBase.getInstance().getPersonStateDao();
    private void initData(){
        getServerData();

        //这里所有person在表里面有的共有属性都是1,区分是不是第一次进来
        List<PersonStateBean> listPersonStateBean = personStateDao.queryListPersonByType(1);
        if(null == listPersonStateBean||listPersonStateBean.size()==0){
            for(int i = 0; i < list.size(); i++){
                PersonStateBean personStateBean = new PersonStateBean();
                personStateBean.isEat = false;
                personStateBean.personId = list.get(i).getId();
                personStateBean.type = 1;
                personStateDao.insert(personStateBean);
            }
        }else{
            for(int i = 0; i < list.size(); i++){
                Person person = list.get(i);
                PersonStateBean personStateBean = personStateDao.queryPersonById(person.getId());
                if(personStateBean != null){
                    //杀掉程序后，沿用以数据库当初存的为准，服务器不愿意改状态
                    boolean isEat = personStateBean.isEat;
                    list.get(i).setEatrice(isEat);
                }
            }
        }

        adapter.notifyDataSetChanged();//数据库取出来刷新
    }

    //第一次请求的时候，服务器使用就给你这些固定的数据，本地保存数据把，卸载之后就没有,可以用做搜索历史
    private void getServerData(){
        list.add(new Person("张三", 1, false));
        list.add(new Person("李四", 2, false));
        list.add(new Person("王五", 3, false));
        list.add(new Person("哈哈1", 4, false));
        list.add(new Person("哈哈2", 5, false));
        list.add(new Person("哈哈3", 6, false));
        list.add(new Person("哈哈4", 7, false));
        list.add(new Person("哈哈5", 8, false));
        list.add(new Person("哈哈6", 9, false));
        list.add(new Person("哈哈7", 10, false));
        list.add(new Person("哈哈8", 11, false));
    }




}