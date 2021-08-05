package com.example.multiscreenswithlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CartAdaptor extends BaseAdapter {

    private ArrayList<Cart> cartItems;
    private LayoutInflater inflater;

    public CartAdaptor(Context context , ArrayList<Cart> cartItems) {
        this.cartItems = cartItems;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cartItems.size();
    }

    @Override
    public Object getItem(int position) {
        return cartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.cart_row,null);
            holder = new ViewHolder();
            holder.txvCartProductName = convertView.findViewById(R.id.txvCartProductName);
            holder.txvCartQuantity = convertView.findViewById(R.id.txvCartQuantity);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.txvCartProductName.setText(MainActivity.products.get(position).getName());
        holder.txvCartQuantity.setText("x  " + cartItems.get(position).getQuantity());
        return convertView;
    }

    static class ViewHolder{
        private TextView txvCartProductName, txvCartQuantity;
    }
}
