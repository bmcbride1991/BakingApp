package com.mcbridebrandon.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mcbridebrandon.bakingapp.R;
import com.mcbridebrandon.bakingapp.model.Ingredient;
import com.mcbridebrandon.bakingapp.model.Recipe;

import java.util.List;


public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>{

    private static final String TAG = "INGREDIENT ADAPTER";
    private List<Ingredient> mIngredientList;
    //private final ItemClickListener mClickListener;


    public IngredientAdapter(Context context, List<Ingredient> itemList) {
        this.mIngredientList = itemList;
       // this.mClickListener = clickListener;
    }


    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.ingredient_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);

        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Log.d(TAG, "#" + position + mIngredientList);

        String qty = mIngredientList.get(position).getQuantity();
        String measure = mIngredientList.get(position).getMeasure();
        String ingredient = mIngredientList.get(position).getIngredient();

        if (holder != null) {
            if(measure.equalsIgnoreCase("UNIT")){measure = "";}
            holder.tvIngredientQuantity.setText(qty+measure);
            //holder.tvIngredientMeasure.setText(mIngredientList.get(position).getMeasure());
            holder.tvIngredientName.setText(ingredient);
        }
    }

    @Override
    public int getItemCount() {
        if(mIngredientList != null) {
            return this.mIngredientList.size();
        }else{
            return 1;
        }
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder  {
        TextView tvIngredientName;
        TextView tvIngredientQuantity;
        TextView tvIngredientMeasure;
        //final ImageView recipeImageView;


        IngredientViewHolder(View itemView) {
            super(itemView);
            //recipeImageView = itemView.findViewById();
            tvIngredientQuantity = itemView.findViewById(R.id.tv_ingredient_qty);
            //tvIngredientMeasure = itemView.findViewById(R.id.tv_ingredient_measure);
            tvIngredientName = itemView.findViewById(R.id.tv_ingredient_name);

            //set onclick
           // itemView.setOnClickListener(this);
        }

       // @Override
        //public void onClick(View v) {
           // mClickListener.onItemClick(getAdapterPosition());
       // }
    }

    //click listener for recipe
   // public interface ItemClickListener {
       // void onItemClick(int position);

   // }

    public void updateAdapter(List<Ingredient> itemList){
        this.mIngredientList = itemList;
        notifyDataSetChanged();
    }

}