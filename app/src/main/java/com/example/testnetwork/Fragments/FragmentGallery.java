package com.example.testnetwork.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.testnetwork.Fragments.PixabayGallery.GalleryAPIService;
import com.example.testnetwork.Fragments.PixabayGallery.PictureItem;
import com.example.testnetwork.Fragments.PixabayGallery.PictureItems;
import com.example.testnetwork.Fragments.PixabayGallery.PixabayGalleryAdapter;
import com.example.testnetwork.R;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentGallery extends Fragment {
    private RecyclerView recyclerView;
    private PixabayGalleryAdapter galleryAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gallery_fragment_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Call<PictureItems> pictureItemsCall = GalleryAPIService.getInstance().getRequest().getPictures();
        pictureItemsCall.enqueue(new Callback<PictureItems>() {
            @Override
            public void onResponse(Call<PictureItems> call, Response<PictureItems> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getContext(),"FAIL",Toast.LENGTH_SHORT).show();
                    return;
                }

                recyclerView = view.findViewById(R.id.recycler_gallery_view);

                ArrayList<PictureItem> photoList = new ArrayList<>(Arrays.asList(response.body().getPictures()));

                galleryAdapter = new PixabayGalleryAdapter(getContext(),photoList);
                recyclerView.setAdapter(galleryAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<PictureItems> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
