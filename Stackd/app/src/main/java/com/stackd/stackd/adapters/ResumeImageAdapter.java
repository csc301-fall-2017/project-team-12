package com.stackd.stackd.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.stackd.stackd.R;
import com.stackd.stackd.model.Resume;
import com.stackd.stackd.model.Tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Adapter for GridView of resumes.
 */
public class ResumeImageAdapter extends BaseAdapter implements Filterable {
    private Context mContext;
    private ArrayList<Resume> resumes = new ArrayList<>();
    private ArrayList<Resume> filteredResumes;

    private Set<String> constraints = new HashSet<String>();

    public ResumeImageAdapter(Context c) {
        mContext = c;
        long rid = 0;
        Resume r = new Resume(rid);
        r.setCandidateName("John Smith");

        // test resumes with python tag and c tag
        List<Tag> tags = new ArrayList<Tag>();
        tags.add(new Tag(1, "python"));
        tags.add(new Tag(3, "pey"));
        Resume r2 = new Resume(rid + 1);
        r2.setCandidateName("Angelo Austria");
        r2.setTagList(tags);

        List<Tag> tags2 = new ArrayList<Tag>();
        tags2.add(new Tag(2, "c"));
        Resume r3 = new Resume(rid + 2);
        r3.setCandidateName("Dmitry Ten");
        r3.setTagList(tags2);

        for(int i=0; i < 2; i++)
            resumes.add(r);
        resumes.add(r2);
        resumes.add(r3);
        filteredResumes = new ArrayList<>(resumes);
    }

    public Set<String> getContraints() {
        return this.constraints;
    }

    public void setConstraints(Set<String> constraints) {
        this.constraints = constraints;
    }

    public void addConstraint(String constraint) {
        this.constraints.add(constraint.toLowerCase());
    }

    public void removeConstraint(String constraint) {
        this.constraints.remove(constraint.toLowerCase());
    }

    public int getCount() {
        return filteredResumes.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // return a view for the item at the position given.
    // Returns a view containing an ImageView and TextvView. ImageView is the picture of the resume
    // TextView is the title of the resume.
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder holder;
        if (convertView == null) {
            // if it's not recycled, initialize a new view holder
            convertView = inflater.inflate(R.layout.resume_item, parent, false);

            holder = new ViewHolder();
            holder.resumeTitle = (TextView) convertView.findViewById(R.id.resume_title);
            holder.resumeImg = (ImageView) convertView.findViewById(R.id.resume_img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.resumeTitle.setText(filteredResumes.get(position).getCandidateName());
        holder.resumeImg.setImageBitmap(
                decodeSampledBitmapFromResource(mContext.getResources(), R.drawable.resume_template, 100, 100));
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new ResumeFilter();
    }

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

    private class ResumeFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            filteredResumes = new ArrayList<>(resumes);
            // Filter candidates by tag
            if (constraint == null && constraints.size() > 0) {
                filteredResumes.clear();
                for (String c: constraints) {
                    for (int i = 0; i < resumes.size(); i++) {
                        List<String> tags = new ArrayList<String>();
                        if (resumes.get(i).getTagList() != null) {
                            for (Tag t : resumes.get(i).getTagList()) {
                                tags.add(t.getName().toLowerCase());
                            }

                            if (tags.contains(c) && !filteredResumes.contains(resumes.get(i))) {
                                filteredResumes.add(resumes.get(i));
                            }
                        }
                    }
                }

                results.values = filteredResumes;
                results.count = filteredResumes.size();
                return results;
            }
            // Filter candidates by name
            else {
                ArrayList<Resume> filteredResumesCopy = new ArrayList<>(filteredResumes);
                filteredResumes.clear();
                // No tags and empty search query means we must be able to view all candidates
                if (constraint == null && constraints.size() == 0) {
                    results.values = resumes;
                    results.count = resumes.size();
                    filteredResumes = new ArrayList<>(resumes);
                    return results;
                }
                String strConstraint = (String) constraint;
                strConstraint = strConstraint.toLowerCase();
                for (int i = 0; i < filteredResumesCopy.size(); i++) {
                    // put resumes into the adapter whose candidate's name starts with the query
                    String name = filteredResumesCopy.get(i).getCandidateName().toLowerCase();
                    if(name.startsWith(strConstraint)) {
                        filteredResumes.add(filteredResumesCopy.get(i));
                    }
                }
                results.values = filteredResumes;
                results.count = filteredResumes.size();
                return results;
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // update GridView
            notifyDataSetChanged();
        }
    }
}
