package com.tymtorneos.modulotymuser1.retrofit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.JsonDeserializer;
import com.tymtorneos.modulotymuser1.R;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> dataList;

    public UserAdapter(List<User> dataList) {
        this.dataList = dataList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_row_view, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.txtUserNombre.setText(dataList.get(position).getNombre());
        holder.txtUserEmail.setText(dataList.get(position).getCorreo());
        holder.txtUserPsp.setText(dataList.get(position).getUsuario_psp());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView txtUserNombre, txtUserEmail, txtUserPsp;

        UserViewHolder(View itemView) {
            super(itemView);
            txtUserNombre =  itemView.findViewById(R.id.txt_user_nombre);
            txtUserEmail =  itemView.findViewById(R.id.txt_user_email);
            txtUserPsp =  itemView.findViewById(R.id.txt_user_psp);
        }
    }
}
