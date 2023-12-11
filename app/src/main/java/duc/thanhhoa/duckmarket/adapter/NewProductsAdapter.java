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
import duc.thanhhoa.duckmarket.models.NewProductsModel;

public class NewProductsAdapter extends RecyclerView.Adapter<NewProductsAdapter.ViewHolder> {

    private Context context;
    private List<NewProductsModel> newProductsModelList;

    public NewProductsAdapter(Context context, List<NewProductsModel> newProductsModelList) {
        this.context = context;
        this.newProductsModelList = newProductsModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate( R.layout.new_products,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(newProductsModelList.get(position).getImg_url()).into(holder.newProductImage);
        holder.newProductName.setText(newProductsModelList.get(position).getName());
        holder.newProductPrice.setText(newProductsModelList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return newProductsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView newProductImage;
        TextView newProductName, newProductPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            newProductImage= itemView.findViewById(R.id.new_img);
            newProductName= itemView.findViewById(R.id.new_product_name);
            newProductPrice= itemView.findViewById(R.id.new_price);
        }
    }
}
