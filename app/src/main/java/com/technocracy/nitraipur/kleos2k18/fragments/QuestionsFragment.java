package com.technocracy.nitraipur.kleos2k18.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.depth.Depth;
import com.github.florent37.depth.DepthProvider;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.technocracy.nitraipur.kleos2k18.R;
import com.technocracy.nitraipur.kleos2k18.adapters.QuestionRecyclerAdapter;
import com.technocracy.nitraipur.kleos2k18.utils.UserPreferences;

import io.github.mthli.slice.Slice;


public class QuestionsFragment extends Fragment {

    public QuestionsFragment() {
    }
    private Depth depth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        UserPreferences userPreferences = new UserPreferences(getContext());
        View view = inflater.inflate(R.layout.fragment_question_page, container, false);
        RecyclerViewPager mRecyclerView = (RecyclerViewPager) view.findViewById(R.id.list);
        LinearLayoutManager layout = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(layout);
        mRecyclerView.setAdapter(new QuestionRecyclerAdapter(getContext(),userPreferences.getLevel()));
        mRecyclerView.scrollToPosition(Integer.parseInt(userPreferences.getLevel())+1);
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
