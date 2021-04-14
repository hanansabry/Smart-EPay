package com.app.smartepay.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.smartepay.R;
import com.app.smartepay.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.CartItemViewHolder> {

    public interface CartCallback {
        void onAddToCartClicked(Item item, int quantity);
    }

    private List<Item> itemList;
    private Context context;
    private int count;
    private CartCallback cartCallback;

    public CartItemsAdapter(Context context, List<Item> itemList, CartCallback cartCallback) {
        this.context = context;
        this.itemList = itemList;
        this.cartCallback = cartCallback;
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_layout, parent, false);
        return new CartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.categoryNameTextView.setText(item.getCategory());
        holder.itemNameTextView.setText(item.getName());
        holder.itemPriceTextView.setText(String.valueOf(item.getPrice()));
        Picasso.with(context)
                .load(item.getImageUrl())
                .placeholder(R.drawable.welcome1)
                .into(holder.itemImageView);
        count = Integer.parseInt(holder.cartItemCountTextView.getText().toString());
        holder.increaseCartItemButton.setOnClickListener(v -> {
            holder.cartItemCountTextView.setText(++count + "");
        });

        holder.decreaseCartItemButton.setOnClickListener(v -> {
            if (count > 1) {
                holder.cartItemCountTextView.setText(--count + "");
            }
        });

        holder.mAddToCartButton.setOnClickListener(v -> {
            cartCallback.onAddToCartClicked(item, count);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class CartItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.category_name)
        TextView categoryNameTextView;
        @BindView(R.id.tv_name)
        TextView itemNameTextView;
        @BindView(R.id.tv_price)
        TextView itemPriceTextView;
        @BindView(R.id.cartItemCountTextView)
        TextView cartItemCountTextView;
        @BindView(R.id.increaseCartItemButton)
        ImageButton increaseCartItemButton;
        @BindView(R.id.decreaseCartItemButton)
        ImageButton decreaseCartItemButton;
        @BindView(R.id.mAddToCartButton)
        Button mAddToCartButton;
        @BindView(R.id.iv_img)
        ImageView itemImageView;

        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
