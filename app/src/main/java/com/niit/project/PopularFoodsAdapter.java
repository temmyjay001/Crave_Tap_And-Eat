package com.niit.project;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PopularFoodsAdapter extends RecyclerView.Adapter<PopularFoodsAdapter.PopularFoodsViewHolder>{

    Context context;
    List<PopularFoods> popularFoodsList;

    public PopularFoodsAdapter(Context context, List<PopularFoods> popularFoodsList) {
        this.context = context;
        this.popularFoodsList = popularFoodsList;
    }

    @NonNull
    @Override
    public PopularFoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.popular_list,null);
        return new PopularFoodsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularFoodsViewHolder holder, int position) {
        PopularFoods popularFoods = popularFoodsList.get(position);

        holder.textViewTitle.setText(popularFoods.getName());
        holder.textViewRating.setText(String.valueOf(popularFoods.getRating()));
        holder.imageView.setImageDrawable(context.getResources().getDrawable(popularFoods.getImage()));
    }

    @Override
    public int getItemCount() {
        return popularFoodsList.size();
    }

    class PopularFoodsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView textViewTitle, textViewRating;

        public PopularFoodsViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.title);
            textViewRating = itemView.findViewById(R.id.rating);

            imageView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context,orderActivity.class);
            if (view.getId()== imageView.getId()) {

                Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();

                intent.putExtra("Bitmap", bitmap);
                context.startActivity(intent);
            }
        }
    }
}
