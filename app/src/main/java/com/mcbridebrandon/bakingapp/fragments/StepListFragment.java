package com.mcbridebrandon.bakingapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mcbridebrandon.bakingapp.R;
import com.mcbridebrandon.bakingapp.StepDetailActivity;
import com.mcbridebrandon.bakingapp.adapters.StepAdapter;
import com.mcbridebrandon.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;

public class StepListFragment extends Fragment implements StepAdapter.ItemClickListener{

    private View rootView;
    private List<Step> stepList;
    private boolean mTwoPane;
    private onStepClickListener mCallback;
    private static final String STEPLIST_KEY = "steplist";
    private static final String POSITION_KEY = "position";

    //mandatory constructor for instantiating the fragment
    public StepListFragment(){

    }
    //interface for onclick
    public interface onStepClickListener{
      void onStepItemClick(int position);
    }

    //override onAttach to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try {
            mCallback = (onStepClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement onStepClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if(savedInstanceState != null){
            stepList = savedInstanceState.getParcelableArrayList("stepList");
        }
        //inflate the ingredient list layout
        View rootView = inflater.inflate(R.layout.fragment_step_list,container,false);

        //get a reference to the recyclerview
        RecyclerView mStepRecyclerView = rootView.findViewById(R.id.rv_step_list);

        //set the layout manager
        mStepRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //setup ingredient adapter
        StepAdapter mStepAdapter = new StepAdapter(getContext(), stepList, this);

        //set the adapter
        mStepRecyclerView.setAdapter(mStepAdapter);


        return rootView;
    }

    public void setStepList(List<Step> stepList) {
        this.stepList = stepList;
    }
    public void setTwoPane(boolean mTwoPane){this.mTwoPane = mTwoPane;}

    @Override
    public void onItemClick(int position) {
        if(!mTwoPane) {
            Step stepToSend;
            //int stepPosition = position;
            stepToSend = stepList.get(position);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(STEPLIST_KEY, (ArrayList<? extends Parcelable>) stepList);
            bundle.putInt(POSITION_KEY, position);
            Intent intent = new Intent(getContext(), StepDetailActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }else {
            mCallback.onStepItemClick(position);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STEPLIST_KEY, (ArrayList<? extends Parcelable>) stepList);
    }
}
