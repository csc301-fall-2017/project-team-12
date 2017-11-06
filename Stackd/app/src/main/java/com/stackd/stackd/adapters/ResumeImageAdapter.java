package com.stackd.stackd.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.stackd.stackd.R;

import static android.R.attr.width;

/**
 * Created by angeloaustria on 2017-10-31.
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
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(340, 340));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }


        //imageView.setImageResource(mThumbIds[position]);

        imageView.setImageBitmap(
                decodeSampledBitmapFromResource(mContext.getResources(), mThumbIds[position], 100, 100));
        return imageView;
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

    public static int calculateInSampleSize(
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

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
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
}
