package com.app.shahrdarirouls;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.Collections;
import java.util.List;

public class Recycler_View_Adapter extends RecyclerView.Adapter<View_Holder> {
    List<Data> list = Collections.emptyList();
    Context context;
    public static boolean visibleProgress = false;
    View v;

    public Recycler_View_Adapter(List<Data> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @Override
    public View_Holder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        View_Holder holder = new View_Holder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(final View_Holder holder, final int position) {

        visibleProgress = true;
        holder.title.setText(list.get(position).title);


//-----------------------------title
        String title_lst = list.get(position).title;
        try {
            if (title_lst.length() > 4) {
                String[] splited_title = title_lst.split("\\s+");
                String title_RVA = "";
                if (!(splited_title.length < 4)) {
                    for (int i = 0; i < 3; i++) {
                        title_RVA += " " + splited_title[i];
                    }
                    holder.title.setText(title_RVA + "...");
                } else {
                    holder.title.setText(title_lst + "...");
                }
            } else {
                holder.title.setText(title_lst + "...");
            }
        } catch (Exception e) {
            Log.d("Err", e.getMessage());
        }

//-----------------------------description
        String description_lst = list.get(position).description;

        try {
            if (description_lst.length() > 10) {
                String[] splited_description = description_lst.split("\\s+");
                String description_RVA = "";
                if (!(splited_description.length < 10)) {
                    for (int i = 0; i < 9; i++) {
                        description_RVA += " " + splited_description[i];
                    }
                    holder.description.setText(description_RVA + "...");
                } else {
                    holder.description.setText(description_lst + "...");
                }
            } else {
                holder.description.setText(description_lst + "...");
            }
        } catch (Exception e) {
            Log.d("Err", e.getMessage());
        }


        holder.id_at_RecycleView.setText(list.get(position).id_at_RecycleView + "");
        //      animate(holder);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = holder.id_at_RecycleView.getText().toString();
                Intent intent = new Intent(context, Activity_Show.class);
                intent.putExtra("KEY", a);
                context.startActivity(intent);
            }
        });
    }

    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }

    @Override
    public int getItemCount() {

        //returns the number of elements the RecyclerView will display

        return list.size();

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, Data data) {
        list.add(position, data);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(Data data) {
        int position = list.indexOf(data);
        list.remove(position);
        notifyItemRemoved(position);

    }


}
