package duc.thanhhoa.duckmarket.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import duc.thanhhoa.duckmarket.R;
import duc.thanhhoa.duckmarket.adapter.CategoryAdapter;
import duc.thanhhoa.duckmarket.adapter.NewProductsAdapter;
import duc.thanhhoa.duckmarket.adapter.PopularProductsAdapter;
import duc.thanhhoa.duckmarket.models.CategoryModel;
import duc.thanhhoa.duckmarket.models.NewProductsModel;
import duc.thanhhoa.duckmarket.models.PopularProductsModel;

public class HomeFragment extends Fragment {


    CategoryAdapter categoryAdapter;
    PopularProductsAdapter popularProductsAdapter;
    List<PopularProductsModel> popularProductsModelList;
    List<CategoryModel> categoryModelList;
    RecyclerView categoryRecycler, newProductRecycler, popularRecycler;
    NewProductsAdapter newProductsAdapter;
    List<NewProductsModel> newProductsModelList;
    FirebaseFirestore db;
    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_home, container, false);



        categoryRecycler = root.findViewById(R.id.rec_category);
        newProductRecycler= root.findViewById(R.id.new_product_rec);
        popularRecycler= root.findViewById(R.id.popular_rec);
        db= FirebaseFirestore.getInstance();


        ImageSlider imageSlider = root.findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.banner1,"Discount 50%", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner2,"Discount 40%", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner3,"Discount 30%", ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels);

        //category

        categoryRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryModelList = new ArrayList<>();
        categoryAdapter= new CategoryAdapter(getContext(),categoryModelList);
        categoryRecycler.setAdapter(categoryAdapter);

        db.collection("Category").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if ((task.isSuccessful())){
                    for(QueryDocumentSnapshot documentSnapshot: task.getResult()){
                        CategoryModel categoryModel = documentSnapshot.toObject(CategoryModel.class);
                        categoryModelList.add(categoryModel);
                        categoryAdapter.notifyDataSetChanged();
                    }
                }else{
                    //error
                }
            }
        });

        //new product

        newProductRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        newProductsModelList = new ArrayList<>();
        newProductsAdapter= new NewProductsAdapter(getContext(),newProductsModelList);
        newProductRecycler.setAdapter(newProductsAdapter);

        db.collection("NewProducts").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
               if ((task.isSuccessful())){
                   for(QueryDocumentSnapshot documentSnapshot: task.getResult()){
                       NewProductsModel newProductsModel = documentSnapshot.toObject(NewProductsModel.class);
                       newProductsModelList.add(newProductsModel);
                       newProductsAdapter.notifyDataSetChanged();
                   }
               }else{
                   //error
               }
            }
        });

        //popular product

        popularRecycler.setLayoutManager(new GridLayoutManager(getActivity(),2));
        popularProductsModelList= new ArrayList<>();
        popularProductsAdapter= new PopularProductsAdapter(getContext(),popularProductsModelList);
        popularRecycler.setAdapter(popularProductsAdapter);

        db.collection("AllProducts").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if ((task.isSuccessful())){
                    for(QueryDocumentSnapshot documentSnapshot: task.getResult()){
                        PopularProductsModel popularProductsModel  = documentSnapshot.toObject(PopularProductsModel.class);
                        popularProductsModelList.add(popularProductsModel);
                        popularProductsAdapter.notifyDataSetChanged();
                    }
                }else{
                    //error
                }
            }
        });
        return root;
    }
}