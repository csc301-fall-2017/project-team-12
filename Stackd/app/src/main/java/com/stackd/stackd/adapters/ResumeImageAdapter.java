package com.stackd.stackd.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.stackd.stackd.R;
import com.stackd.stackd.db.DataManager;
import com.stackd.stackd.db.entities.Resume;
import com.stackd.stackd.db.entities.Tag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Adapter for GridView of resumes.
 */
public class ResumeImageAdapter extends BaseAdapter implements Filterable {
    private Context mContext;
    private List<Resume> resumes;
    private List<Resume> filteredResumes;
    private List<Tag> tags; // the set of all company tags
    private Set<String> activeTagNames = new HashSet<>(); // the set of active tag names

    public ResumeImageAdapter(Context c) {
        mContext = c;
        // Dummy Values
        long cId = 1;
        long rId = 21;
        // get data manager and get all data required for this activity (resumes and tags)
        DataManager manager = DataManager.getDataManager(cId, rId);
        resumes = manager.getResumes();
        tags = manager.getCompanyTags();
        filteredResumes = new ArrayList<>(resumes);
    }

    public List<Tag> getTags() { return this.tags; }
    public Set<String> getActiveTagNames() {
        return this.activeTagNames;
    }

    public void setActiveTagNames(Set<String> activeTagNames) {
        this.activeTagNames = activeTagNames;
    }

    public void addConstraint(String constraint) {
        this.activeTagNames.add(constraint.toLowerCase());
    }

    public void removeConstraint(String constraint) {
        this.activeTagNames.remove(constraint.toLowerCase());
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
            // Show all resumes
            if(constraint == null && activeTagNames.size() == 0) {
                results.values = resumes;
                results.count = resumes.size();
                return results;
            }
            // Filter candidates by name
            if(constraint != null) {
                filteredResumes.clear();
                String strConstraint = (String) constraint;
                strConstraint = strConstraint.toLowerCase();
                for (int i = 0; i < resumes.size(); i++) {
                    // put resumes into the adapter whose candidate's name starts with the query
                    String name = resumes.get(i).getCandidateName().toLowerCase();
                    if(name.startsWith(strConstraint)) {
                        filteredResumes.add(resumes.get(i));
                    }
                }
            }
            else // no name constraint, show all resumes
                filteredResumes = new ArrayList<>(resumes);

            // Filter candidates by tag, only look at those left after name search
            if (activeTagNames.size() > 0) {
                for (Resume resume : filteredResumes) {
                    List<Tag> tagList = resume.getTagList();
                    if (tagList != null) {
                        int num_active_tags = activeTagNames.size();
                        int num_tags = 0;
                        for(String c: activeTagNames)
                            for (Tag t : tagList) {
                                if (t.getName().toLowerCase().equals(c.toLowerCase())) {
                                    num_tags++;
                                    break;
                                }
                            }
                        // not all active tags present in the resume, remove it from filtered
                        if(num_tags != num_active_tags && filteredResumes.contains(resume))
                            filteredResumes.remove(resume);
                    }
                }
            }
            results.values = filteredResumes;
            results.count = filteredResumes.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // update GridView
            notifyDataSetChanged();
        }
    }
}
