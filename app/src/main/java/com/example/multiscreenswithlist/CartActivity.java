package com.example.multiscreenswithlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CartActivity extends AppCompatActivity {

    ListView lvCartItems;
    TextView txvTotalAmount;
    Button btnRemove, btnBuy;

    private int selectedItemIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        lvCartItems = findViewById(R.id.lvCartItems);
        txvTotalAmount = findViewById(R.id.txvTotalAmount);
        btnBuy = findViewById(R.id.btnBuy);
        btnRemove = findViewById(R.id.btnRemove);

        lvCartItems.setAdapter(new CartAdaptor(this, DescriptionActivity.cartItems));
        displayTotal();

        lvCartItems.setOnItemClickListener((parent, view, position, id) -> selectedItemIndex = position);

        btnRemove.setOnClickListener(v -> {
            if(selectedItemIndex == -1){
                Toast.makeText(this, "Please select a item to remove from the cart.", Toast.LENGTH_SHORT).show();
            }else{
                DescriptionActivity.cartItems.remove(selectedItemIndex);
                lvCartItems.setAdapter(new CartAdaptor(this, DescriptionActivity.cartItems));
                Toast.makeText(this, "Item has been removed from the cart.", Toast.LENGTH_SHORT).show();
                if(DescriptionActivity.cartItems.size() == 0){
                    Toast.makeText(this, "Your cart is empty, you are being redirected to Products Page.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(CartActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                displayTotal();
            }
        });

        btnBuy.setOnClickListener(v -> {
            if(DescriptionActivity.cartItems.size() > 0){
                Toast.makeText(this, "Your total amount is: "+ displayTotal()+" $. Your Items will be deliverd to: "+ getUserAddress() +". And you are being redirected to Products Page.", Toast.LENGTH_LONG).show();
                DescriptionActivity.cartItems.clear();
                Intent intent = new Intent(CartActivity.this, MainActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Please add Items to the cart", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getUserAddress(){
        String address = "";
        String userName = LoginActivity.UserName;
        for(Client ct: LoginActivity.clients){
            if(ct.getUsername().equals(userName)){
                address = ct.getAddress();
            }
        }
        return address;
    }

    private double displayTotal(){
        double totalAmount = 0;
        for(Cart ct: DescriptionActivity.cartItems){
            double productPrice = MainActivity.products.get(ct.getProductID()).getPrice();
            totalAmount += productPrice;
        }
        txvTotalAmount.setText(String.format("%.2f",totalAmount)+" $");
        return totalAmount;
    }
}