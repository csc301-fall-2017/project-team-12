package com.stackd.stackd.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stackd.stackd.R;

/**
 * Adapter for GridView of resumes.
 */
public class ResumeImageAdapter extends BaseAdapter {
    private Context mContext;

    public ResumeImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder holder;
        if (convertView == null) {
            // if it's not recycled, initialize some attribute
            convertView = inflater.inflate(R.layout.resume_item, parent, false);

            holder = new ViewHolder();
            holder.resumeTitle = (TextView) convertView.findViewById(R.id.resume_title);
            holder.resumeImg = (ImageView) convertView.findViewById(R.id.resume_img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.resumeTitle.setText("Angelo Austria");
        holder.resumeImg.setImageBitmap(
                decodeSampledBitmapFromResource(mContext.getResources(), mThumbIds[position], 100, 100));
        return convertView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria,
            R.drawable.resume_angelo_gabriel_austria
    };

    private static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    private static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * ViewHolder class. Stores title and description TextViews.
     */
    private static class ViewHolder {
        TextView resumeTitle;
        ImageView resumeImg;
    }
}
