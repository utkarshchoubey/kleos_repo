package com.technocracy.nitraipur.kleos2k18.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.github.florent37.depth.Depth;
import com.github.florent37.depth.DepthProvider;
import com.technocracy.nitraipur.kleos2k18.R;
import com.technocracy.nitraipur.kleos2k18.activities.LoginActivity;
import com.technocracy.nitraipur.kleos2k18.adapters.LeaderboardRecylerAdapter;
import com.technocracy.nitraipur.kleos2k18.model.User;
import com.technocracy.nitraipur.kleos2k18.restapi.ApiBase;
import com.technocracy.nitraipur.kleos2k18.restapi.ApiEndpoints;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import am.appwise.components.ni.NoInternetDialog;
import io.github.mthli.slice.Slice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderboardFragment extends Fragment {
    private Depth depth;
    public LeaderboardFragment() { }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leaderboard_page, container, false);
        AVLoadingIndicatorView indicatorView =(AVLoadingIndicatorView)view.findViewById(R.id.avi);
        indicatorView.show();
        TextView def = (TextView)view.findViewById(R.id.defaultTV);
        RecyclerView rv = (RecyclerView)view.findViewById(R.id.leaderboadRV);
        ApiEndpoints apiBase= ApiBase.getClient().create(ApiEndpoints.class);
        Call<List<User>> call = apiBase.getLeaderboard();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
              if(response.isSuccessful()){
                  indicatorView.hide();
                 response.body();
                  //if(users.size() > 9){
                      def.setVisibility(View.GONE);
                      rv.setVisibility(View.VISIBLE);
                      LinearLayoutManager layout = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                      rv.setLayoutManager(layout);
                      LeaderboardRecylerAdapter adapter = new LeaderboardRecylerAdapter(getContext(), response.body());
                      rv.setAdapter(adapter);
                  //}
                  //else{
                    //  def.setVisibility(View.Visible);
                     // rv.setVisibility(View.Gone);
                  //}
              }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                NoInternetDialog noInternetDialog = new NoInternetDialog.Builder(getContext()).build();
            }
        });

        this.depth = DepthProvider.getDepth(view);
        return  depth.setupFragment(10f, 10f, view);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        depth.onFragmentReady(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
