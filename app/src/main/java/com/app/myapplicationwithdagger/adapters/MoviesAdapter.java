package com.app.myapplicationwithdagger.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.myapplicationwithdagger.MainActivity;
import com.app.myapplicationwithdagger.R;
import com.app.myapplicationwithdagger.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by harsh on 23/3/18.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private static final String TAG = MoviesAdapter.class.getSimpleName();

    public static final String TMDB_IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185//";

    List<Movie> moviesList = new ArrayList<>(0);;

    Picasso picasso;

    Context context;

    @Inject
    public MoviesAdapter(MainActivity context, Picasso picasso) {
        this.context = context;
        this.picasso = picasso;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        holder.txtId.setText("" + (position + 1));
        holder.txtRating.setText(movie.getVoteAverage() + "/10.0");
        holder.txtTitle.setText("" + movie.getTitle());
        holder.txtOverview.setText("" + movie.getOverview());

        picasso.load(TMDB_IMAGE_BASE_URL + "" + movie.getPosterPath())
                .into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public void setMovieList(List<Movie> movieListData) {
        moviesList.clear();
        if (movieListData != null) {
            moviesList.addAll(movieListData);
        }
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtId, txtRating, txtTitle, txtOverview;
        public ImageView imgPoster;

        public MyViewHolder(View view) {
            super(view);
            txtId = (TextView) view.findViewById(R.id.txtId);
            txtRating = (TextView) view.findViewById(R.id.txtRating);
            txtTitle = (TextView) view.findViewById(R.id.txtTitle);
            txtOverview = (TextView) view.findViewById(R.id.txtOverview);
            imgPoster = (ImageView) view.findViewById(R.id.imgPoster);
        }
    }
}
