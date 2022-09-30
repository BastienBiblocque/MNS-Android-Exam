package com.example.mns_android_exam;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SunFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SunFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SunFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SunFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SunFragment newInstance(String param1, String param2) {
        SunFragment fragment = new SunFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    ImageView imageView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sun, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        //MY CODE

        JsonObjectRequest request = new JsonObjectRequest(
                "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=43AwjPLrqjesmqA7DT2ctZ48KE4XTIIaTt5UO5ky",
                resultat -> {
                    try {
                        //nombre random
                        Random rand = new Random();
                        int int_random = rand.nextInt(855);
                        //get image
                        JSONArray listeImage = resultat.getJSONArray("photos");
                        JSONObject item = listeImage.getJSONObject(int_random);
                        String img_src = item.getString("img_src");
                        imageView = getActivity().findViewById(R.id.imageView);
                        Glide.with(this).load(img_src).into(imageView);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("test", String.valueOf(e));
                    }
                },
                error -> error.printStackTrace()
        );

        RequestManager.getInstance(getContext()).addToRequestQueue(request);

    }
}