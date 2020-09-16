package com.example.mvvm_jetpack_practice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvvm_jetpack_practice.R;
import com.example.mvvm_jetpack_practice.bean.Person;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    List<Person>list;
    Context context;

    public MyAdapter(List<Person> list, Context context){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view= LayoutInflater.from(context).inflate(R.layout.item_persion,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position){
        holder.tv_name.setText(list.get(position).getName());
        holder.tv_id.setText("工牌:"+list.get(position).getId());
        holder.tv_iseat.setText(list.get(position).isEatrice()?"吃饭了":"没吃饭");

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(adapterListener!=null){
                    adapterListener.onclick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount(){
        return list.size();
    }


    AdapterListener adapterListener;
    public void setListener(AdapterListener listener){
        adapterListener=listener;
    }

    public interface AdapterListener{
        void onclick(int position);

    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name;
        TextView tv_id;
        TextView tv_iseat;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            tv_name=itemView.findViewById(R.id.tv_name);
            tv_id=itemView.findViewById(R.id.tv_id);
            tv_iseat=itemView.findViewById(R.id.tv_iseat);
        }
    }
}
