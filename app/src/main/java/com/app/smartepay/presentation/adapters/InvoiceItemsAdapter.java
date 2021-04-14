package com.app.smartepay.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.smartepay.R;
import com.app.smartepay.model.InvoiceItem;
import com.app.smartepay.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class InvoiceItemsAdapter extends RecyclerView.Adapter<InvoiceItemsAdapter.InvoiceItemViewHolder> {
    private List<InvoiceItem> invoiceItemsList;
    private Context context;

    public InvoiceItemsAdapter(Context context, List<InvoiceItem> invoiceItemsList) {
        this.context = context;
        this.invoiceItemsList = invoiceItemsList;
    }

    @NonNull
    @Override
    public InvoiceItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_checkout, parent, false);
        return new InvoiceItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceItemViewHolder holder, int position) {
        InvoiceItem invoiceItem = invoiceItemsList.get(position);
        holder.categoryNameTextView.setText(invoiceItem.getItem().getCategory());
        holder.nameTextView.setText(invoiceItem.getItem().getName());
        holder.priceTotalTextView.setText(String.valueOf(invoiceItem.getTotalPrice()));
        holder.quantityTextView.setText(invoiceItem.getQuantity()+"");
        Picasso.with(context)
                .load(invoiceItem.getItem().getImageUrl())
                .placeholder(R.drawable.welcome1)
                .into(holder.itemImageView);
    }

    @Override
    public int getItemCount() {
        return invoiceItemsList.size();
    }

    public class InvoiceItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.category_name)
        TextView categoryNameTextView;
        @BindView(R.id.tv_name)
        TextView nameTextView;
        @BindView(R.id.tv_price_total)
        TextView priceTotalTextView;
        @BindView(R.id.quantityTextView)
        TextView quantityTextView;
        @BindView(R.id.iv_img)
        ImageView itemImageView;

        public InvoiceItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
