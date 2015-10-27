package com.mycompany.filemanager.General;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mycompany.filemanager.R;

import java.util.List;

/**
 * Created by Diego Avila on 25-10-2015.
 */
public class CustomGridFiles extends BaseAdapter {
    private Context mContext;
    private List<FileM> files;

    public CustomGridFiles(Context c, List<FileM> objects) {
        mContext = c;
        this.files = objects;

    }

    public int getCount() {
        return this.files.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            //imageView = new ImageView(mContext);
            //grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_single, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            grid.setLayoutParams(new GridView.LayoutParams(170, 170));
            //grid.setScaleType(ImageView.ScaleType.CENTER_CROP);
            grid.setPadding(8, 8, 8, 8);
            imageView.setImageResource(R.drawable.image_icon);
            final FileM fileM = files.get(position);
            textView.setText(fileM.getName());
        } else {
            grid = convertView;
        }

        //imageView.setImageResource(mThumbIds[position]);
        return grid;
    }

    // references to our images
    /*private Integer[] mThumbIds = {
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            //mainActivity.getAppContext().getResources().getDrawable(android.R.drawable.dialog_frame)

    };*/
}
