package com.example.lilialobato.pawstagramV3.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.lilialobato.pawstagramV3.CommentsActivity;
import com.example.lilialobato.pawstagramV3.R;
import com.example.lilialobato.pawstagramV3.helper.SharedPrefrenceManger;
import com.example.lilialobato.pawstagramV3.helper.SquareImageView;
import com.example.lilialobato.pawstagramV3.models.Story;
import com.example.lilialobato.pawstagramV3.models.User;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class StoryListAdapter extends ArrayAdapter<Story> {


    private Context mContext;

    public StoryListAdapter(@NonNull Context context, int resource,ArrayList<Story> list) {
        super(context,resource,list);
        this.mContext = context;

    }



    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public Story getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(@Nullable Story item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;

        if(view == null){

            LayoutInflater li = LayoutInflater.from(mContext);
            view = li.inflate(R.layout.feed_single_item,null);
        }
        Story story = getItem(position);
                if(story != null){
                    CircleImageView profile_photo = (CircleImageView) view.findViewById(R.id.profile_photo);
                    SquareImageView story_image = view.findViewById(R.id.story_image);
                    TextView username = view.findViewById(R.id.username_tv);
                    TextView number_of_likes = view.findViewById(R.id.num_of_likes);
                    TextView tage = view.findViewById(R.id.image_tags);
                    TextView date = view.findViewById(R.id.image_time);
                    TextView view_all_comments = view.findViewById(R.id.view_all_comments);



                    if(story.getProfile_image().isEmpty()){
                      profile_photo.setImageResource(R.drawable.user);
                    }else{
                        Picasso.get().load(story.getProfile_image()).error(R.drawable.user).into(profile_photo);
                    }
                   if(story.getStory_image().isEmpty()) {
                       profile_photo.setImageResource(R.drawable.user);
                   }else{
                       Picasso.get().load(story.getStory_image()).error(R.drawable.user).into(story_image);
                   }

                    username.setText(story.getUsername());
                    number_of_likes.setText(story.getLike() + " likes");
                    tage.setText(story.getTitle());
                    date.setText(story.getTime());


                    viewAllComments(view_all_comments,story.getId());


                }


        return view;
    }


    private void viewAllComments(TextView view_all_comments,final int id){



        view_all_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent viewAllCommentsIntent = new Intent(getContext(),CommentsActivity.class);
                viewAllCommentsIntent.putExtra("story_id",id);
                getContext().startActivity(viewAllCommentsIntent);


            }
        });
    }
}
