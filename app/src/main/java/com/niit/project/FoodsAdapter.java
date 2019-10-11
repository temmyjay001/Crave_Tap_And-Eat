package com.niit.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.like.LikeButton;
import com.like.OnLikeListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
* RecyclerView.Adapter
 * RecyclerView.ViewHolder
* */
public class FoodsAdapter extends RecyclerView.Adapter<FoodsAdapter.FoodsViewHolder> {

    private Context context;
    private List<Foods> foodsList;


    public FoodsAdapter(Context context, List<Foods> foodsList) {
        this.context = context;
        this.foodsList = foodsList;
    }

    @NonNull
    @Override
    public FoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.foods_list, null);
        return new FoodsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodsViewHolder holder, int position) {
        Foods foods = foodsList.get(position);

        holder.textViewTitle.setText(foods.getName());
        holder.textViewDesc.setText(foods.getShortDesc());
        holder.textViewRating.setText(String.valueOf(foods.getRating()));

        holder.imageView.setImageDrawable(context.getResources().getDrawable(foods.getImage()));
//        holder.heart.setImageDrawable(context.getResources().getDrawable(foods.getHeart()));
    }

    @Override
    public int getItemCount() {
        return foodsList.size();
    }

    class FoodsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        private LikeButton likeButton;
        TextView textViewTitle, textViewDesc, textViewRating;

        public FoodsViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            likeButton = itemView.findViewById(R.id.heart_button);
            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.title);
            textViewDesc = itemView.findViewById(R.id.shortDesc);
            textViewRating = itemView.findViewById(R.id.rating);

            likeButton.setOnLikeListener(new OnLikeListener() {
                @Override
                public void liked(LikeButton likeButton) {
                    Toast.makeText(context,"I was Liked",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void unLiked(LikeButton likeButton) {
                    Toast.makeText(context,"I was UnLiked",Toast.LENGTH_SHORT).show();
                }
            });

        }

        @Override
        public void onClick(View view) {

        }
    }
}
