package duc.thanhhoa.duckmarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import duc.thanhhoa.duckmarket.R;
import duc.thanhhoa.duckmarket.models.PopularProductsModel;

public class PopularProductsAdapter extends RecyclerView.Adapter<PopularProductsAdapter.ViewHolder>{

    private Context context;
    private List<PopularProductsModel> popularProductsModelList;

    public PopularProductsAdapter(Context context, List<PopularProductsModel> popularProductsModelList) {
        this.context = context;
        this.popularProductsModelList = popularProductsModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate( R.layout.popular_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(popularProductsModelList.get(position).getImg_url()).into(holder.popularProductImage);
        holder.popularProductName.setText(popularProductsModelList.get(position).getName());
        holder.popularProductPrice.setText(popularProductsModelList.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return popularProductsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popularProductImage;
        TextView popularProductName, popularProductPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            popularProductImage= itemView.findViewById(R.id.new_img);
            popularProductName= itemView.findViewById(R.id.new_product_name);
            popularProductPrice= itemView.findViewById(R.id.new_price);
        }
    }
}
