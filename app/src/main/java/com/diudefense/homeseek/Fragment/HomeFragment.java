package com.diudefense.homeseek.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.diudefense.homeseek.Activity.AddPropertyActivity;
import com.diudefense.homeseek.Activity.AllPopularActivity;
import com.diudefense.homeseek.Activity.AllPropActivity;
import com.diudefense.homeseek.Activity.LoginFormActivity;
import com.diudefense.homeseek.Constants.BaseApp;
import com.diudefense.homeseek.Constants.Constants;
import com.diudefense.homeseek.Item.CategoryItem;
import com.diudefense.homeseek.Item.CityItem;
import com.diudefense.homeseek.Item.GridItem;
import com.diudefense.homeseek.Item.SliderItem;
import com.diudefense.homeseek.Models.CategoryModels;
import com.diudefense.homeseek.Models.CityModels;
import com.diudefense.homeseek.Models.PropertyModels;
import com.diudefense.homeseek.R;
import com.diudefense.homeseek.Utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;


public class HomeFragment extends Fragment {

    ArrayList<PropertyModels> mSliderList, mLatestList, mPopularList;
    ArrayList<CategoryModels> mCategoryList;
    ArrayList<CityModels> mCityList;
    SliderItem sliderItem;
    ScrollView mScrollView;
    ProgressBar mProgressBar, progresspopular, progresscity, progresslatest;
    ViewPager mViewPager;
    CircleIndicator circleIndicator;
    RecyclerView rvLatest, rvCategory, rvCity, rvPopular;
    Button addProperty;
    CategoryItem categoryItem;
    TextView moreLatest, morePopular;
    CityItem cityItem;
    BaseApp baseApp;
    GridItem popularItem, latestItem;
    RelativeLayout nofound;
    LinearLayout rlslider;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mSliderList = new ArrayList<>();
        mLatestList = new ArrayList<>();
        mPopularList = new ArrayList<>();
        mCategoryList = new ArrayList<>();
        mCityList = new ArrayList<>();
        baseApp = BaseApp.getInstance();
        mScrollView = rootView.findViewById(R.id.scrollView);
        mProgressBar = rootView.findViewById(R.id.progressBar);
        progresscity = rootView.findViewById(R.id.progressCity);
        progresspopular = rootView.findViewById(R.id.progresspopular);
        progresslatest = rootView.findViewById(R.id.progresslatest);
        mViewPager = rootView.findViewById(R.id.viewPager);
        rlslider = rootView.findViewById(R.id.rlslider);
        circleIndicator = rootView.findViewById(R.id.indicator_unselected_background);
        rvLatest = rootView.findViewById(R.id.latest);
        rvCity = rootView.findViewById(R.id.city);
        rvCategory = rootView.findViewById(R.id.category);
        rvPopular = rootView.findViewById(R.id.popular);
        moreLatest = rootView.findViewById(R.id.morelatest);
        morePopular = rootView.findViewById(R.id.morepopular);
        addProperty = rootView.findViewById(R.id.addproperty);
        nofound = rootView.findViewById(R.id.nofound);

        progresspopular.setVisibility(View.VISIBLE);
        progresscity.setVisibility(View.VISIBLE);
        progresslatest.setVisibility(View.VISIBLE);

        rvLatest.setHasFixedSize(true);
        rvLatest.setNestedScrollingEnabled(false);
        rvLatest.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        rvCity.setHasFixedSize(true);
        rvCity.setNestedScrollingEnabled(false);
        rvCity.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        rvCategory.setHasFixedSize(true);
        rvCategory.setLayoutManager(new GridLayoutManager(getActivity(), 5));

        rvPopular.setHasFixedSize(true);
        rvPopular.setNestedScrollingEnabled(false);
        rvPopular.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        if (NetworkUtils.isConnected(getActivity())) {
            getFeaturedItem();
            getCategory();
            getPopularItem();
            getLatestItem();
            getCity();
        } else {
            nofound.setVisibility(View.VISIBLE);
        }

        morePopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AllPopularActivity.class);
                getActivity().startActivity(intent);
            }
        });
        moreLatest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), AllPropActivity.class);
                    getActivity().startActivity(intent);
            }
        });

        addProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baseApp.getIsLogin()) {
                    Intent intent = new Intent(getActivity(), AddPropertyActivity.class);
                    getActivity().startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), LoginFormActivity.class);
                    getActivity().startActivity(intent);
                }

            }
        });


            return rootView;

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(HomeFragment.this).attach(HomeFragment.this).commit();
        }
    }

    private void getFeaturedItem() {

        JSONObject parameters = new JSONObject();
        RequestQueue rq = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, Constants.FEATURED, parameters, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String respo=response.toString();
                        Log.d("responce",respo);
                        getData(respo);
                        mScrollView.setVisibility(View.VISIBLE);
                        addProperty.setVisibility(View.VISIBLE);
                        mProgressBar.setVisibility(View.GONE);
                        rlslider.setVisibility(View.VISIBLE);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d("respo",error.toString());
                    }
                });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rq.getCache().clear();
        rq.add(jsonObjectRequest);
    }

    public void getData(String loginData){

        try {
            JSONObject jsonObject=new JSONObject(loginData);
            String code=jsonObject.optString("code");
            if(code.equals("200")) {
                JSONArray msg = jsonObject.getJSONArray("msg");
                for (int i = 0; i < msg.length(); i++) {
                    JSONObject userdata = msg.getJSONObject(i);
                    PropertyModels item = new PropertyModels();
                    item.setPropid(userdata.getString("propid"));
                    item.setName(userdata.getString("name"));
                    item.setAddress(userdata.getString("address"));
                    item.setPrice(userdata.getString("price"));
                    item.setImage(userdata.getString("image"));
                    item.setRateAvg(userdata.getString("rate"));
                    mSliderList.add(item);

                }

            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        displayData();
    }

    private void getCategory() {

        JSONObject parameters = new JSONObject();
        RequestQueue rq = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, Constants.CATEGORY, parameters, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String respo=response.toString();
                        Log.d("responce",respo);
                        getDataCategory(respo);
                        mScrollView.setVisibility(View.VISIBLE);
                        addProperty.setVisibility(View.VISIBLE);
                        mProgressBar.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d("respo",error.toString());
                    }
                });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rq.getCache().clear();
        rq.add(jsonObjectRequest);
    }

    public void getDataCategory(String loginData){
        try {
            JSONObject jsonObject=new JSONObject(loginData);
            String code=jsonObject.optString("code");
            if(code.equals("200")) {
                JSONArray msg = jsonObject.getJSONArray("msg");
                for (int i = 0; i < msg.length(); i++) {
                    JSONObject userdata = msg.getJSONObject(i);
                    CategoryModels item = new CategoryModels();
                    item.setCategoryId(userdata.getString("cid"));
                    item.setCategoryName(userdata.getString("cname"));
                    item.setCategoryImage(userdata.getString("cimage"));
                    mCategoryList.add(item);

                }
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        displayData();
    }

    private void getCity() {

        JSONObject parameters = new JSONObject();
        RequestQueue rq = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, Constants.CITY, parameters, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String respo=response.toString();
                        Log.d("responce",respo);
                        getDataCity(respo);
                        addProperty.setVisibility(View.VISIBLE);
                        mScrollView.setVisibility(View.VISIBLE);
                        progresscity.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d("respo",error.toString());
                    }
                });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rq.getCache().clear();
        rq.add(jsonObjectRequest);
    }

    public void getDataCity(String loginData){
        try {
            JSONObject jsonObject=new JSONObject(loginData);
            String code=jsonObject.optString("code");
            if(code.equals("200")) {
                JSONArray msg = jsonObject.getJSONArray("msg");
                for (int i = 0; i < msg.length(); i++) {
                    JSONObject userdata = msg.getJSONObject(i);
                    CityModels item = new CityModels();
                    item.setCityId(userdata.getString("cityid"));
                    item.setCityName(userdata.getString("cityname"));
                    item.setCityImage(userdata.getString("cityimage"));
                    mCityList.add(item);

                }
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        displayData();
    }

    private void getPopularItem() {

        JSONObject parameters = new JSONObject();
        RequestQueue rq = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, Constants.POPULAR, parameters, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String respo=response.toString();
                        Log.d("responce",respo);
                        getDataPopular(respo);
                        addProperty.setVisibility(View.VISIBLE);
                        mScrollView.setVisibility(View.VISIBLE);
                        progresspopular.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d("respo",error.toString());
                    }
                });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rq.getCache().clear();
        rq.add(jsonObjectRequest);
    }

    public void getDataPopular(String loginData){
        try {
            JSONObject jsonObject=new JSONObject(loginData);
            String code=jsonObject.optString("code");
            if(code.equals("200")) {
                JSONArray msg = jsonObject.getJSONArray("msg");
                for (int i = 0; i < msg.length(); i++) {
                    JSONObject userdata = msg.getJSONObject(i);
                    PropertyModels item = new PropertyModels();
                    item.setPropid(userdata.getString("propid"));
                    item.setName(userdata.getString("name"));
                    item.setImage(userdata.getString("image"));
                    item.setAddress(userdata.getString("address"));
                    item.setRateAvg(userdata.getString("rate"));
                    item.setPrice(userdata.getString("price"));
                    item.setPurpose(userdata.getString("purpose"));
                    item.setBed(userdata.getString("bed"));
                    item.setBath(userdata.getString("bath"));
                    item.setArea(userdata.getString("area"));

                    mPopularList.add(item);

                }
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        displayData();
    }

    private void getLatestItem() {

        JSONObject parameters = new JSONObject();
        RequestQueue rq = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, Constants.LATEST, parameters, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String respo=response.toString();
                        Log.d("responce",respo);
                        getDataLatest(respo);
                        addProperty.setVisibility(View.VISIBLE);
                        mScrollView.setVisibility(View.VISIBLE);
                        progresslatest.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d("respo",error.toString());
                    }
                });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rq.getCache().clear();
        rq.add(jsonObjectRequest);
    }

    public void getDataLatest(String loginData){
        try {
            JSONObject jsonObject=new JSONObject(loginData);
            String code=jsonObject.optString("code");
            if(code.equals("200")) {
                JSONArray msg = jsonObject.getJSONArray("msg");
                for (int i = 0; i < msg.length(); i++) {
                    JSONObject userdata = msg.getJSONObject(i);
                    PropertyModels item = new PropertyModels();
                    item.setPropid(userdata.getString("propid"));
                    item.setName(userdata.getString("name"));
                    item.setImage(userdata.getString("image"));
                    item.setAddress(userdata.getString("address"));
                    item.setPrice(userdata.getString("price"));
                    item.setRateAvg(userdata.getString("rate"));
                    item.setPurpose(userdata.getString("purpose"));
                    item.setBed(userdata.getString("bed"));
                    item.setBath(userdata.getString("bath"));
                    item.setArea(userdata.getString("area"));
                    mLatestList.add(item);

                }
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        displayData();
    }

    private void displayData() {
        sliderItem = new SliderItem(getActivity(), mSliderList);
        mViewPager.setAdapter(sliderItem);
        circleIndicator.setViewPager(mViewPager);

        categoryItem = new CategoryItem(getActivity(), mCategoryList, R.layout.item_category);
        rvCategory.setAdapter(categoryItem);

        popularItem = new GridItem(getActivity(), mPopularList, R.layout.item_grid);
        rvPopular.setAdapter(popularItem);

        latestItem = new GridItem(getActivity(), mLatestList, R.layout.item_grid);
        rvLatest.setAdapter(latestItem);

        cityItem = new CityItem(getActivity(), mCityList, R.layout.item_square);
        rvCity.setAdapter(cityItem);

        if(mSliderList.isEmpty()){
            rlslider.setVisibility(View.GONE);
        }
    }



}