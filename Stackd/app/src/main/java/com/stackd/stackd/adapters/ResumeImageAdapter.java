package com.stackd.stackd.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import com.stackd.stackd.helpers.Consumer;

import java.io.File;
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
    private DataManager manager;
    private static final int NUM_DUMMY_RESUMES = 4;
    private String [] dummyResumeFilenames = {"r1.jpg", "r2.png", "r3.jpg", "r4.png"};
    private List<File> dummyResumeFiles = new ArrayList<>();
    public ResumeImageAdapter(Context c) {
        mContext = c;
        // Dummy Values
        long cId = 1;
        long rId = 21;
        // get data manager and get all data required for this activity (resumes and tags)
        manager = DataManager.getDataManager(cId, rId, mContext.getApplicationContext());
        resumes = manager.getResumes();
        tags = manager.getCompanyTags();
        filteredResumes = new ArrayList<>(resumes);
        // download dummy resume images for dummy resumes
        downloadDummyResumeImages();
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
        return filteredResumes.get(position);
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
        Resume resume = filteredResumes.get(position);
        if(resume.getUrl() != null && resume.getUrl().length() > 0) {
            holder.resumeImg.setImageURI(Uri.parse(resume.getUrl()));
        }
        else {
            // if no url, use dummy resume images from S3 bucket
            int idx = position % NUM_DUMMY_RESUMES;
            if(dummyResumeFiles.size() > idx && dummyResumeFiles.get(idx) != null)
                holder.resumeImg.setImageBitmap(decodeSampledBitmapFromFile(
                        dummyResumeFiles.get(idx), 500, 500));
        }
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

    private static Bitmap decodeSampledBitmapFromFile(File f, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(f.getPath(), options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(f.getPath(), options);
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
            if (constraint == null && activeTagNames.size() > 0) {
                filteredResumes.clear();
                for (String c: activeTagNames) {
                    for (int i = 0; i < resumes.size(); i++) {
                        List<String> tags = new ArrayList<>();
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
                if (constraint == null && activeTagNames.size() == 0) {
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

    /**
     * Downloads dummy resumes from S3 bucket and puts them into dummyResumeFiles list.
     * Tells the adapter to refresh when a file is possibly downloaded
     */
    private void downloadDummyResumeImages() {
        for(int i=0; i < NUM_DUMMY_RESUMES; i++) {
            manager.downloadFile(dummyResumeFilenames[i], new Consumer<File>() {
                @Override
                public void accept(File file) {
                    if(!dummyResumeFiles.contains(file)) {
                        dummyResumeFiles.add(file);
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }
}
