package com.gg.flickertask.adapters;

/**
 * Created by Gilad on 8/13/2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gg.flickertask.R;
import com.gg.flickertask.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    public static final int IMAGE_WIDTH = 250;
    public static final int IMAGE_HEIGHT = 250;
    private List<Photo> mPhotoList;
    private Context mContext;
    private final OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Photo item);
    }

    public PhotoAdapter(Context context, List<Photo> photoList, OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
        mContext = context;
        setPhotoList(photoList);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public ImageView mPhoto;

        public ViewHolder(View v) {
            super(v);
            mTitle = (TextView) v.findViewById(R.id.firstLine);
            mPhoto = (ImageView) v.findViewById(R.id.photo);
        }
    }



    public void setPhotoList(List<Photo> photoList) {
        mPhotoList = photoList;
    }

    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_photo_data_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTitle.setText(mPhotoList.get(position).title);
        final String imgUrl = mPhotoList.get(position).url_s;
        if (imgUrl != null) {
            Picasso.with(mContext)
                    .load(imgUrl)
                    .resize(IMAGE_WIDTH, IMAGE_HEIGHT)
                    .centerCrop()
                    .into(holder.mPhoto);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mItemClickListener.onItemClick(mPhotoList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mPhotoList == null) {
            return 0;
        }
        return mPhotoList.size();
    }
}
