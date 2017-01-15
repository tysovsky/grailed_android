package com.tysovsky.grailed.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.tysovsky.grailed.Core.Item;
import com.tysovsky.grailed.Core.ItemAdapter;
import com.tysovsky.grailed.Core.NetworkManager;
import com.tysovsky.grailed.Interfaces.OnFeedItemClickedListener;
import com.tysovsky.grailed.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tysovsky-mac on 1/15/17.
 */

public class FeedFragment extends Fragment{
    public static final String TAG = "FeedFragment";
    private OnFeedItemClickedListener onFeedItemClickedListener = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragmed_feed, container, false);

        String url = "https://mnrwefss2q-dsn.algolia.net/1/indexes/Listing_by_date_added_production/query?x-algolia-agent=Algolia%20for%20vanilla%20JavaScript%203.19.0&x-algolia-application-id=MNRWEFSS2Q&x-algolia-api-key=a3a4de2e05d9e9b463911705fb6323ad";

        Map<String, String> params = new HashMap();
        params.put("params", "query=&hitsPerPage=40&facets=%5B%22strata%22%2C%22size%22%2C%22category%22%2C%22category_size%22%2C%22category_path%22%2C%22category_path_size%22%2C%22category_path_root_size%22%2C%22price_i%22%2C%22designers.id%22%2C%22location%22%5D&filters=(strata%3A'grailed')&page=0");


        final ArrayList<Item> items = new ArrayList<>();
        final ItemAdapter itemAdapter = new ItemAdapter(getActivity(), items);
        JSONObject parameters = new JSONObject(params);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray arr = response.getJSONArray("hits");

                            for(int i = 0; i < arr.length(); i++){
                                items.add(new Item(
                                        arr.getJSONObject(i).getInt("id"),
                                        arr.getJSONObject(i).getString("title"),
                                        arr.getJSONObject(i).getString("designer_names"),
                                        arr.getJSONObject(i).getInt("price"),
                                        arr.getJSONObject(i).getString("description"),
                                        arr.getJSONObject(i).getString("size"),
                                        arr.getJSONObject(i).getString("category"),
                                        arr.getJSONObject(i).getString("strata"),
                                        arr.getJSONObject(i).getJSONObject("cover_photo").getString("url"),
                                        false

                                ));
                            }

                            itemAdapter.notifyDataSetChanged();
                            Log.i("Tag", arr.length() + " items found");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });
        NetworkManager.getInstance(getActivity()).addToRequestQueue(jsObjRequest);

        GridView gridView = (GridView)view.findViewById(R.id.main_feed_grid);

        gridView.setAdapter(itemAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i(TAG, "Clicked");
                if(onFeedItemClickedListener != null){
                    onFeedItemClickedListener.OnFeedItemCliked(items.get(i));
                }
            }
        });

        return view;
    }

    public void setOnFeedItemClickedListener(OnFeedItemClickedListener listener){
        this.onFeedItemClickedListener = listener;
    }
}
