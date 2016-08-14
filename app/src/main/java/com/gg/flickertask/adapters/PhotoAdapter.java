package com.gg.flickertask.adapters;

/**
 * Created by Gilad on 8/13/2016.
 */

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gg.flickertask.R;
import com.gg.flickertask.model.Photo;
import com.gg.flickertask.model.PhotoSearchResult;
import com.gg.flickertask.model.Photos;
import com.gg.flickertask.network.NetworkHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    public static final int IMAGE_WIDTH = 250;
    public static final int IMAGE_HEIGHT = 250;
    private static final String TAG = PhotoAdapter.class.getSimpleName();
    public static final int ITEMS_BEFORE_NEXT_PAGE_REQUEST = 10;
    private Photos mPhotos;
    private int mCurrentPage;
    private final OnItemClickListener mItemClickListener;
    private boolean mRequesting;

    public interface OnItemClickListener {
        void onItemClick(Photo item);
    }

    public PhotoAdapter(Photos photos, OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
        setPhotos(photos);
    }

    public void setPhotos(Photos photos) {
        mPhotos = photos;
        mCurrentPage = 1;
        notifyDataSetChanged();
    }

    public void addPhotos(Photos photos) {
        if (mPhotos != null) {
            if (photos != null && photos.getPhotoList() != null &&
                    mPhotos.getPhotoList() != null) {
                mPhotos.getPhotoList().addAll(photos.getPhotoList());
            }
        } else {
            mPhotos = photos;
        }
        notifyDataSetChanged();
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

    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_photo_data_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (getPhotoList() != null) {
            mCurrentPage = (position / mPhotos.getPerpageInt()) + 1;
            holder.mTitle.setText(getPhotoList().get(position).title);
            final String imgUrl = getPhotoList().get(position).url_s;
            if (imgUrl != null) {
                Picasso.with(holder.itemView.getContext())
                        .load(imgUrl)
                        .resize(IMAGE_WIDTH, IMAGE_HEIGHT)
                        .centerCrop()
                        .into(holder.mPhoto);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(getPhotoList().get(position));
                }
            });

            if (position == getPhotoList().size() - ITEMS_BEFORE_NEXT_PAGE_REQUEST) {
                if (mCurrentPage < mPhotos.getPageseInt()) {
                    final int nextPage = mCurrentPage + 1;
                    if (!mRequesting) {
                        Log.d(TAG, "onBindViewHolder: getting next page");
                        Log.d(TAG, "onBindViewHolder: size: " + mPhotos.getPhotoList().size());
                        mRequesting = true;
                        NetworkHelper.getInstance().getPhotoSearchResult("food", new Callback<PhotoSearchResult>() {
                            @Override
                            public void onResponse(Call<PhotoSearchResult> call, Response<PhotoSearchResult> response) {
                                Log.d(TAG, "onResponse: page(" + nextPage + "), :" + response.body().toString());
                                if (response != null) {
                                    addPhotos(response.body().photos);
                                }
                                mRequesting = false;
                            }

                            @Override
                            public void onFailure(Call<PhotoSearchResult> call, Throwable t) {
                                mRequesting = false;
                            }
                        }, nextPage);
                    } else {
                        Log.d(TAG, "onBindViewHolder: no request : size" + (mPhotos.getPhotoList().size()));
                    }
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mPhotos == null || mPhotos.getPhotoList() == null) {
            return 0;
        }
        return mPhotos.getPhotoList().size();
    }

    private List<Photo> getPhotoList() {
        if (mPhotos == null) {
            return null;
        }
        return mPhotos.getPhotoList();
    }
}