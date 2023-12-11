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
import duc.thanhhoa.duckmarket.models.CategoryModel;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{

    private Context context;
    private List<CategoryModel> categoryModelList;
    public CategoryAdapter(Context context, List<CategoryModel> categoryModelList) {
        this.context = context;
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(categoryModelList.get(position).getImg_url()).into(holder.categoryImage);
        holder.categoryName.setText(categoryModelList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView categoryImage;
        TextView categoryName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage=itemView.findViewById(R.id.cat_img);
            categoryName=itemView.findViewById(R.id.cat_name);

        }
    }
}
