package com.example.retrofit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.retrofit.R;
import com.example.retrofit.dto.User;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UserAdapterReyclerView extends RecyclerView.Adapter<UserAdapterReyclerView.UserViewHolder> {

    private final List<User> users;

    public UserAdapterReyclerView(List<User> users) {
        this.users = users;
    }

    @NonNull
    @NotNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);

        return new UserAdapterReyclerView.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UserViewHolder holder, int position) {
        User user = users.get(position);

        holder.firstName.setText(user.getFirstName());
        holder.lastName.setText(user.getLastName());
        holder.id.setText(user.getId());

//        holder.sent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(), "Hola: " + user.getFirstName(), Toast.LENGTH_LONG).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        TextView firstName;
        TextView lastName;
        TextView id;


        public UserViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.firstName);
            lastName = itemView.findViewById(R.id.lastName);
            id = itemView.findViewById(R.id.id);
        }
    }
}
