package com.example.greenflag;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    TextView tv_name, tv_username, tv_birthdate, tv_country, tv_gender, tv_address;
    ImageView ivAvatar;
    Contacts item;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        initUI();
    }
    private void initUI(){
        tv_name = itemView.findViewById(R.id.name);
        tv_username = itemView.findViewById(R.id.user_name);
        tv_birthdate = itemView.findViewById(R.id.birth_date);
        tv_country = itemView.findViewById(R.id.country);
        tv_gender = itemView.findViewById(R.id.gender);
        tv_address = itemView.findViewById(R.id.address);
        ivAvatar = itemView.findViewById(R.id.avatar);
        ivAvatar.setImageResource(R.drawable.ic_android_black_24dp);
    }
    public void bindView(Contacts contact){
        if(contact != null){
            tv_name.setText(contact.getName());
            tv_username.setText(contact.getUserName());
            tv_birthdate.setText(contact.getBirthDate());
            tv_country.setText(contact.getCountry());
            tv_gender.setText(contact.getGender());
            tv_address.setText(contact.getAddress());
            ivAvatar.setImageResource(contact.getAvatar());
        }
    }
}
