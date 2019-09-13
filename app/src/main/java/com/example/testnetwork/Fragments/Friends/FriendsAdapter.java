package com.example.testnetwork.Fragments.Friends;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.testnetwork.Fragments.PixabayGallery.PictureItem;
import com.example.testnetwork.Person.Person;
import com.example.testnetwork.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendViewHolder> {

    private List<Person> list;
    private Context context;
    private onItemClickListener mListener;

    public interface onItemClickListener {
        void onItemClick(int position,int type);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        mListener = listener;
    }

    public FriendsAdapter(Context context, List<Person> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.friend_item_layout,viewGroup,false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder friendViewHolder, int position) {
        Picasso.with(context).load(R.mipmap.ic_launcher).into(friendViewHolder.ivIconPerson);
        friendViewHolder.txtMainInfoPerson.setText("User: "+list.get(position).login);
        friendViewHolder.txtOtherInfoPerson.setText("Id: "+list.get(position).id+", Salary: "+list.get(position).salary);
    }

    @Override
    public int getItemCount() { return list.size(); }

    class FriendViewHolder extends RecyclerView.ViewHolder{
        TextView txtMainInfoPerson;
        TextView txtOtherInfoPerson;
        ImageView ivIconPerson;
        LinearLayout layout;
        Button btnAccept;


        FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("MyLog","ViewCreated");
            txtMainInfoPerson = itemView.findViewById(R.id.mainPersonInfo);
            txtOtherInfoPerson = itemView.findViewById(R.id.otherPersonInfo);
            ivIconPerson = itemView.findViewById(R.id.iconPerson);
            layout = itemView.findViewById(R.id.frame_friend);
            btnAccept = itemView.findViewById(R.id.accept_button);


            btnAccept.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position!=RecyclerView.NO_POSITION)
                    mListener.onItemClick(position,0);
                    //list.remove(position);
                    notifyDataSetChanged();
            });
            itemView.setOnClickListener(v -> {
                        int position = getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION)
                            mListener.onItemClick(position,1);
            });

        }
    }

}
