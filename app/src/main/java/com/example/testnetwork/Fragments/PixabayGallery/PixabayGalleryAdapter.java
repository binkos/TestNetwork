package com.example.testnetwork.Fragments.PixabayGallery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testnetwork.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PixabayGalleryAdapter extends RecyclerView.Adapter<PixabayGalleryAdapter.PixabayViewHolder> {

    private ArrayList<PictureItem> arrayList;
    private Context context;

    public PixabayGalleryAdapter(Context context,ArrayList<PictureItem> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public PixabayViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PixabayViewHolder( LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.picture_item_layout,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PixabayViewHolder pixabayViewHolder, int position) {
        Picasso.with(context).load(arrayList.get(position).getLargeImageURL()).fit().centerInside().into(pixabayViewHolder.imageItem);
        pixabayViewHolder.txtUserName.setText("User: "+arrayList.get(position).getUser());
        pixabayViewHolder.txtLikes.setText("Likes: "+arrayList.get(position).getLikes());
    }

    @Override
    public int getItemCount() { return arrayList.size(); }

    class PixabayViewHolder extends RecyclerView.ViewHolder{
        TextView txtUserName;
        TextView txtLikes;
        ImageView imageItem;

        PixabayViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUserName = itemView.findViewById(R.id.username_view);
            txtLikes = itemView.findViewById(R.id.likes_view);
            imageItem = itemView.findViewById(R.id.picture_view);
        }
    }

}
