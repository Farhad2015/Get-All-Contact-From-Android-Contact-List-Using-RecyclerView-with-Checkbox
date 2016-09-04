package com.ezzetech.getallcontactsrnd;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Android Development on 9/3/2016.
 */
public class SelectUserAdapter extends RecyclerView.Adapter<SelectUserAdapter.MyContactListViewHolder> {

    List<SelectUser> mainInfo;
    private ArrayList<SelectUser> arraylist;
    Context context;


    public SelectUserAdapter(Context context, List<SelectUser> mainInfo) {
        this.mainInfo = mainInfo;
        this.context = context;
        this.arraylist = new ArrayList<SelectUser>();
        this.arraylist.addAll(mainInfo);
    }

    public class MyContactListViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewUserImage;
        TextView textViewShowName;
        TextView textViewPhoneNumber;
        CheckBox checkBoxSelectItem;

        public MyContactListViewHolder(View itemView) {
            super(itemView);

            textViewShowName = (TextView) itemView.findViewById(R.id.name);
            checkBoxSelectItem = (CheckBox) itemView.findViewById(R.id.check);
            textViewPhoneNumber = (TextView) itemView.findViewById(R.id.no);
            imageViewUserImage = (ImageView) itemView.findViewById(R.id.pic);
            checkBoxSelectItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final SelectUser selectUser = mainInfo.get(getAdapterPosition());
                    CheckBox checkBox = (CheckBox) view;
                    if (checkBox.isChecked()) {
                        selectUser.setCheckedBox(true);
                    } else {
                        selectUser.setCheckedBox(false);
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public MyContactListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowview, parent, false);
        MyContactListViewHolder holder = new MyContactListViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyContactListViewHolder holder, int position) {
        String imagepath = mainInfo.get(position).getImagepath();
        if (imagepath == null) {
            Picasso.with(context).load(R.drawable.image).into(holder.imageViewUserImage);
        }else {
            Picasso.with(context).load(imagepath).into(holder.imageViewUserImage);
        }
        holder.textViewShowName.setText(mainInfo.get(position).getName());
        holder.textViewPhoneNumber.setText(mainInfo.get(position).getPhone());
        holder.checkBoxSelectItem.setChecked(mainInfo.get(position).getCheckedBox());
    }

    @Override
    public int getItemCount() {
        return mainInfo.size();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mainInfo.clear();
        if (charText.length() == 0) {
            mainInfo.addAll(arraylist);
        } else {
            for (SelectUser wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    mainInfo.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
