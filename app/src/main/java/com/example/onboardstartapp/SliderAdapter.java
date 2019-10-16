package com.example.onboardstartapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import org.w3c.dom.Text;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    //Arrays
    public int[] slideImages = {
            R.drawable.white_lego,
            R.drawable.black_lego,
            R.drawable.yellow_lego
    };

    public String[] slideHeadings = {
            "WHITE",
            "BLACK",
            "YELLOW"
    };

    public String[] slideDescriptions = {
            "nulla facilisi etiam dignissim diam nulla facilisi etiam dignissim diam",
            "ut tellus elementum sagittis vitaeut tellus elementum sagittis vitae",
            "aliquam malesuada bibendum arcu vitaealiquam malesuada bibendum arcu vitae"
    };



    @Override
    public int getCount() {
        return slideHeadings.length;
    }

    @Override
    public boolean isViewFromObject( View view,  Object object) {
        return view == (ConstraintLayout) object;
    }


    @Override
    public Object instantiateItem( ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageview = view.findViewById(R.id.slide_image);
        TextView slideHeader = view.findViewById(R.id.slide_heading);
        TextView slideDesc = view.findViewById(R.id.slide_desc);

        //set the resource
        slideImageview.setImageResource(slideImages[position]);
        slideHeader.setText(slideHeadings[position]);
        slideDesc.setText(slideDescriptions[position]);

        container.addView(view);


        return view;
    }

    @Override
    public void destroyItem( ViewGroup container, int position,  Object object) {

        container.removeView((ConstraintLayout) object) ;
    }
}
