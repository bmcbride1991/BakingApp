package com.mcbridebrandon.bakingapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.mcbridebrandon.bakingapp.fragments.VideoPlayerFragment;
import com.mcbridebrandon.bakingapp.model.Step;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class StepDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<Step> stepList;
    private Step currentStep;
    private int stepPosition;
    private SimpleExoPlayer player;
    private Long playerPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        //get the bundle from StepDetailActivity
        Bundle bundle = getIntent().getExtras();
        //get the step list
        stepList = bundle.getParcelableArrayList("steplist");
        //get the position clicked on
        stepPosition = bundle.getInt("position");
        //set the current step
        currentStep = stepList.get(stepPosition);

            //create a new instance of the ingredient fragment
            VideoPlayerFragment videoFragment = new VideoPlayerFragment();

            //update ingredient list
            videoFragment.setVideoUrl(currentStep.getVideoURL());
            videoFragment.setThumbnailUrl(currentStep.getThumbnailURL());
            videoFragment.setRecipeDescription(currentStep.getDescription());

            //use the fragment manager and transaction to add the fragment to the screen
            FragmentManager fragmentManager = getSupportFragmentManager();

            //fragment transaction
            fragmentManager.beginTransaction()
                    .add(R.id.video_container,videoFragment)
                    .commit();


        Button prevButton = findViewById(R.id.btn_prev);
        Button nextButton = findViewById(R.id.btn_next);

        prevButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int lastPosition = stepList.size()-1;

        switch (v.getId()) {
            case R.id.btn_prev:
                //previous button was pressed
                //load previous step
            if(currentStep.getId() > 0)
            {
                if (player!=null){
                    player.stop();
                }
                //update
                //create a new instance of the ingredient fragment
                VideoPlayerFragment videoFragment = new VideoPlayerFragment();

                currentStep = stepList.get(stepPosition-1);
                stepPosition-=1;
                //update ingredient list
                videoFragment.setVideoUrl(currentStep.getVideoURL());
                videoFragment.setThumbnailUrl(currentStep.getThumbnailURL());
                videoFragment.setRecipeDescription(currentStep.getDescription());

                //use the fragment manager and transaction to add the fragment to the screen
                FragmentManager fragmentManager = getSupportFragmentManager();

                //fragment transaction
                fragmentManager.beginTransaction()
                        .replace(R.id.video_container,videoFragment)
                        .addToBackStack(null)
                        .commit();

                
            }
            else {
                Toast.makeText(this,"Already at the First Step", Toast.LENGTH_SHORT).show();
            }
             break;

            case R.id.btn_next:
                //next button was pressed
                //load next step
                if(currentStep.getId() < stepList.get(lastPosition).getId()) {
                    if (player != null) {
                        player.stop();
                    }
                    //update
                    //create a new instance of the ingredient fragment
                    VideoPlayerFragment videoFragment = new VideoPlayerFragment();

                    currentStep = stepList.get(stepPosition+1);
                    stepPosition+=1;
                    //update ingredient list
                    videoFragment.setVideoUrl(currentStep.getVideoURL());
                    videoFragment.setThumbnailUrl(currentStep.getThumbnailURL());
                    videoFragment.setRecipeDescription(currentStep.getDescription());

                    //use the fragment manager and transaction to add the fragment to the screen
                    FragmentManager fragmentManager = getSupportFragmentManager();

                    //fragment transaction
                    fragmentManager.beginTransaction()
                            .replace(R.id.video_container,videoFragment)
                            .commit();


                }else
                {
                    Toast.makeText(this,"Already at the Final Step", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }










    }