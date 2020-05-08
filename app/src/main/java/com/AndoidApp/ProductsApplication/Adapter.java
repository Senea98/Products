package com.AndoidApp.ProductsApplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Produs> prods;

    public Adapter(Context context, List<Produs> prods) {
        this.layoutInflater = LayoutInflater.from(context);
        this.prods = prods;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Produs obj = prods.get(position);
        holder.title.setText(obj.denumire);
        holder.stocs.setText(obj.cantitate);
        holder.price.setText(obj.pret);
        holder.category.setText(obj.categorie);
    }

    @Override
    public int getItemCount() {
        return prods.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title, category, price, stocs;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.cardTitle);
            category = itemView.findViewById(R.id.cardCategorie);
            price = itemView.findViewById(R.id.cardPret);
            stocs = itemView.findViewById(R.id.cardCantitate);
        }
    }
}
