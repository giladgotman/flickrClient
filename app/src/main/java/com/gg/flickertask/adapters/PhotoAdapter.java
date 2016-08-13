package com.gg.flickertask.adapters;

/**
 * Created by Gilad on 8/13/2016.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gg.flickertask.R;
import com.gg.flickertask.model.Photo;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private static final String TAG = PhotoAdapter.class.getSimpleName();
    private List<Photo> mPhotoList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public ImageView mPhoto;

        public ViewHolder(View v) {
            super(v);
            mTitle = (TextView) v.findViewById(R.id.firstLine);
            mPhoto = (ImageView) v.findViewById(R.id.photo);
        }
    }

    public void add(int position, Photo item) {
        mPhotoList.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(Photo item) {
        int position = mPhotoList.indexOf(item);
        mPhotoList.remove(position);
        notifyItemRemoved(position);
    }

    public PhotoAdapter(List<Photo> myDataset) {
        mPhotoList = myDataset;
    }

    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_photo_data_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitle.setText(mPhotoList.get(position).title);
        final String imgUrl = mPhotoList.get(position).urlS;
        //TODO: set image from url using Picaso
    }

    @Override
    public int getItemCount() {
        return mPhotoList.size();
    }

}
