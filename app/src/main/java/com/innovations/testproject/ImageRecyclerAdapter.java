package com.innovations.testproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.myviewholder> {
    List<ImageModel> imageModelSubs;
    Context mContext;
    int number;


    public ImageRecyclerAdapter(List<ImageModel> imageModelSubs, Context mContext ) {
        this.imageModelSubs = imageModelSubs;
        this.mContext = mContext;

    }

    @NonNull
    @NotNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.postrecycler, parent,false);
        return new ImageRecyclerAdapter.myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull myviewholder holder, int position) {
      if (imageModelSubs.get(position).getBitmap()  != null){
          holder.image.setImageBitmap(imageModelSubs.get(position).getBitmap());

      }else{
          Glide.with(holder.image.getContext())
                  .load(Uri.parse(imageModelSubs.get(position).getPicture()))
                  .error(R.drawable.ic_launcher_background)
                  .into(holder.image);

      }


        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ImageDetailActivity.class);
                intent.putExtra("image", imageModelSubs.get(position).getPicture());
                intent.putExtra("comment", imageModelSubs.get(position).getComment());
                intent.putExtra("bitmap", imageModelSubs.get(position).getBitmap());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return imageModelSubs.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        ImageView image;
        public myviewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
        }
    }
}
