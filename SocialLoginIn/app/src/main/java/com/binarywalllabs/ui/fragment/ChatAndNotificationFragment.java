package com.binarywalllabs.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binarywalllabs.socialintegration.R;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;

import com.google.api.services.youtube.YouTubeScopes;
import com.google.api.services.youtube.model.*;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.CommentSnippet;
import com.google.api.services.youtube.model.CommentThread;
import com.google.api.services.youtube.model.CommentThreadListResponse;

import java.util.HashMap;
import java.util.List;

public class ChatAndNotificationFragment extends Fragment {
  YouTube youTube ;
    public static Fragment newInstance() {
        ChatAndNotificationFragment fragment = new ChatAndNotificationFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chatlist_fragmnet, container, false);
        ReadAllComments("5xVh-7ywKpE");
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void ReadAllComments(String videoId){

       // YouTube youtube = getYouTubeService();

        try {
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("part", "snippet,replies");
            parameters.put("videoId", "m4Jtj2lCMAA");

            YouTube.CommentThreads.List commentThreadsListByVideoIdRequest = youTube.commentThreads().list(parameters.get("part").toString());
            if (parameters.containsKey("videoId") && parameters.get("videoId") != "") {
                commentThreadsListByVideoIdRequest.setVideoId(parameters.get("videoId").toString());
            }

            CommentThreadListResponse response = commentThreadsListByVideoIdRequest.execute();
            System.out.println(response);


        } catch (GoogleJsonResponseException e) {
            e.printStackTrace();
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }

}
