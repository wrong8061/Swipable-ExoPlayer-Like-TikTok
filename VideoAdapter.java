package com.govind8061.practiceproject;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.StyledPlayerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    List<String> videosUrl=new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_video_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(videosUrl.get(position));
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.onDetach();
    }

    @Override
    public int getItemCount() {
        return videosUrl.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setVideosUrl(List<String> videosUrl) {
        this.videosUrl = videosUrl;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        StyledPlayerView playerView;
        ExoPlayer player;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            playerView=itemView.findViewById(R.id.playerView);
            player=new ExoPlayer.Builder(playerView.getContext()).build();

        }
        void setData(String s){
            playerView.setPlayer(player);
            MediaItem mediaItem=MediaItem.fromUri(s);
            player.addMediaItem(mediaItem);
            player.prepare();
            player.setRepeatMode(player.REPEAT_MODE_ONE);
        }

        public void onDetach(){
            player.pause();
    }

    }
}
