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
import com.gg.flickertask.model.Photos;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private static final int IMAGE_WIDTH = 250;
    private static final int IMAGE_HEIGHT = 250;
    private static final String TAG = PhotoAdapter.class.getSimpleName();
    private static final int ITEMS_BEFORE_NEXT_PAGE_REQUEST = 10;
    private static final int FIRST_PAGE = 1;
    private Photos mPhotos;
    private int mCurrentPage;
    private final PhotoAdapterListener mPhotoAdapterListener;
    private boolean mRequesting;

    public PhotoAdapter(Photos photos, final PhotoAdapterListener itemClickListener) {
        mPhotoAdapterListener = itemClickListener;
        setPhotos(photos);
    }

    /**
     * set new dataset of photos and paging information.
     * @param photos the new dataset
     */
    public final void setPhotos(final Photos photos) {
        mPhotos = photos;
        mCurrentPage = FIRST_PAGE;
        notifyDataSetChanged();
        mRequesting = false;
    }

    /**
     * add a new list of photos to the existing dataset.
     * if the current dataset is null, it will replace it
     * @param photos photos objects containing the photo list to add
     */
    public final void addPhotos(final Photos photos) {
        if (mPhotos != null) {
            if (photos != null
                    && photos.getPhotoList() != null
                    && mPhotos.getPhotoList() != null) {
                mPhotos.getPhotoList().addAll(photos.getPhotoList());
            }
        } else {
            mPhotos = photos;
        }
        notifyDataSetChanged();
        mRequesting = false;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mViews;
        public ImageView mPhoto;

        public ViewHolder(final View v) {
            super(v);
            mTitle = (TextView) v.findViewById(R.id.firstLine);
            mViews = (TextView) v.findViewById(R.id.secondLine);
            mPhoto = (ImageView) v.findViewById(R.id.photo);
        }
    }

    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent,
                                                      final int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_photo_data_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (getPhotoList() != null) {
            mCurrentPage = (position / mPhotos.getPerpageInt()) + 1;
            holder.mTitle.setText(getPhotoList().get(position).getTitle());
            holder.mViews.setText("Views: " + getPhotoList().get(position).getViews());
            holder.mPhoto.setImageResource(R.drawable.default_photo_image);
            final String imgUrl = getPhotoList().get(position).getUrlSmallSize();
            if (imgUrl != null) {
                Picasso.with(holder.itemView.getContext())
                        .load(imgUrl)
                        .resize(IMAGE_WIDTH, IMAGE_HEIGHT)
                        .centerCrop()
                        .placeholder(R.drawable.default_photo_image)
                        .into(holder.mPhoto);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    mPhotoAdapterListener.onItemClick(getPhotoList().get(position));
                }
            });

            if (position == getPhotoList().size() - ITEMS_BEFORE_NEXT_PAGE_REQUEST) {
                if (mCurrentPage < mPhotos.getPageseInt()) {
                    final int nextPage = mCurrentPage + 1;
                    if (!mRequesting) {
                        Log.d(TAG, "onBindViewHolder: getting next page");
                        Log.d(TAG, "onBindViewHolder: size: " + mPhotos.getPhotoList().size());
                        mRequesting = true;
                        mPhotoAdapterListener.getNextSearchPage(nextPage);
                    } else {
                        Log.d(TAG, "onBindViewHolder: no request : size" + (mPhotos.getPhotoList().size()));
                    }
                }
            }
        }
    }

    @Override
    public final int getItemCount() {
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

    /**
     * set is requesting flag.
     * set to false when there is no request pending
     * used to not sending multiple same requests
     * @param isRequesting
     */

    public final void setIsRequesting(final boolean isRequesting) {
        mRequesting = isRequesting;
    }

    public interface PhotoAdapterListener {
        void onItemClick(Photo item);
        void getNextSearchPage(int page);
    }
}
