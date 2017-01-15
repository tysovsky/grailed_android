package com.tysovsky.grailed.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tysovsky.grailed.Core.Item;
import com.tysovsky.grailed.R;

/**
 * Created by tysovsky-mac on 1/15/17.
 */

public class ItemViewFragment extends Fragment {

    private Item currentItem = null;
    private TextView price = null;
    private ImageView image = null;


    public static final String TAG = "ItemViewFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_view, container, false);

        image = (ImageView)view.findViewById(R.id.item_picture);
        Picasso.with(getActivity()).load(currentItem.cover_photo_url).resize(600, 0).into(image);

        return view;
    }

    public void setCurrentItem(Item item){
        currentItem = item;
    }
}
