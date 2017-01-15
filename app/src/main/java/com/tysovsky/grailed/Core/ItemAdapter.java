package com.tysovsky.grailed.Core;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tysovsky.grailed.Core.Item;
import com.tysovsky.grailed.R;

import java.util.ArrayList;

/**
 * Created by tysovsky-mac on 1/14/17.
 */

public class ItemAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<Item> items;

    public ItemAdapter(Context context, ArrayList<Item> items){
        this.context = context;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return items.get(i).id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Item item = items.get(i);

        if(view == null){
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.item_view, null);

            final ImageView cover_photo = (ImageView)view.findViewById(R.id.item_image);
            final TextView designer = (TextView)view.findViewById(R.id.item_designer);
            final TextView size = (TextView)view.findViewById(R.id.item_size);
            final TextView title = (TextView)view.findViewById(R.id.item_title);
            final TextView price = (TextView)view.findViewById(R.id.item_price);
            final ImageView saved = (ImageView) view.findViewById(R.id.item_saved);

            ViewHolder viewHolder = new ViewHolder(cover_photo, designer, size, title, price, saved);

            view.setTag(viewHolder);
        }

        final ViewHolder viewHolder = (ViewHolder)view.getTag();

        viewHolder.designer.setText(item.designer);
        viewHolder.size.setText(item.size);
        viewHolder.title.setText(item.title);
        viewHolder.price.setText("$"+item.price);

        Picasso.with(context).load(item.cover_photo_url).resize(300, 0).into(viewHolder.cover_photo);

        viewHolder.saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.saved){
                    viewHolder.saved.setImageResource(R.drawable.icon_not_saved);
                    item.saved = false;
                }
                else{
                    viewHolder.saved.setImageResource(R.drawable.icon_saved);
                    item.saved = true;
                }
            }
        });

        return view;
    }

    private class ViewHolder{
        final ImageView cover_photo;
        final TextView designer;
        final TextView size;
        final TextView title;
        final TextView price;
        final ImageView saved;

        public ViewHolder(ImageView cover_photo, TextView designer, TextView size, TextView title, TextView price, ImageView saved){
            this.cover_photo = cover_photo;
            this.designer = designer;
            this.size = size;
            this.title = title;
            this.price = price;
            this.saved = saved;
        }
    }

}
