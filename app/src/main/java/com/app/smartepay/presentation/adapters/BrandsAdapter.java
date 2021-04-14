package com.app.smartepay.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.smartepay.R;
import com.app.smartepay.model.Brand;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BrandsAdapter extends RecyclerView.Adapter<BrandsAdapter.BrandViewHolder> {

    public interface BrandsListener {
        void onBrandClicked(Brand brand);
    }

    private Context context;
    private List<Brand> brandsList;
    private BrandsListener brandsListener;

    public BrandsAdapter(Context context, List<Brand> brandsList, BrandsListener brandsListener) {
        this.context = context;
        this.brandsList = brandsList;
        this.brandsListener = brandsListener;
    }

    @NonNull
    @Override
    public BrandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_item_layout, parent, false);
        return new BrandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandViewHolder holder, int position) {
        Brand brand = brandsList.get(position);
        holder.brandNameTextView.setText(brand.getName());
        holder.brandDescTextView.setText(brand.getDesc());
        Picasso.with(context)
                .load(brand.getImageUrl())
                .placeholder(R.drawable.welcome1)
                .into(holder.brandImageView);
        holder.brandsView.setOnClickListener(v -> brandsListener.onBrandClicked(brand));
    }

    @Override
    public int getItemCount() {
        return brandsList.size();
    }

    class BrandViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.brandImageView)
        ImageView brandImageView;
        @BindView(R.id.brandNameTextView)
        TextView brandNameTextView;
        @BindView(R.id.brandDescTextView)
        TextView brandDescTextView;
        @BindView(R.id.brandsView)
        View brandsView;

        public BrandViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
