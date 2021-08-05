package com.example.multiscreenswithlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DescriptionActivity extends AppCompatActivity {
    Button back, btnAddToCart;
    ImageView bigImage;
    TextView prodDesc,title;
    EditText edtQuanity;

    public static ArrayList<Cart> cartItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        back=findViewById(R.id.btnBack);
        bigImage=findViewById(R.id.ivBigImage);
        prodDesc=findViewById(R.id.txvDesc);
        title=findViewById(R.id.txvProdTitle);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        edtQuanity = findViewById(R.id.edtQuantity);

        title.setText(MainActivity.products.get(MainActivity.index).getName());
        bigImage.setImageResource(MainActivity.products.get(MainActivity.index).getImg());
        prodDesc.setText(MainActivity.products.get(MainActivity.index).getDescription());


        back.setOnClickListener(v -> {
            Intent intent=new Intent(DescriptionActivity.this,MainActivity.class);
            startActivity(intent);
        });

        btnAddToCart.setOnClickListener(v -> {
            String quantity = edtQuanity.getText().toString();
            if(quantity.isEmpty()){
                Toast.makeText(this, "Please provide the quantity.", Toast.LENGTH_SHORT).show();
            }else{
                int addQuantity = Integer.parseInt(quantity);
                System.out.println(MainActivity.index);
                Cart updateCartItem = checkCart();
                if(updateCartItem == null){
                    cartItems.add(new Cart(LoginActivity.UserName,MainActivity.index,addQuantity));
                }else{
                    int updatedQuantity = updateCartItem.getQuantity();
                    updatedQuantity += addQuantity;
                    updateCartItem.setQuantity(updatedQuantity);
                }
                Toast.makeText(this, "Item has been added to the cart.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DescriptionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private Cart checkCart(){
        for(Cart ct: cartItems){
            if(MainActivity.index == ct.getProductID()){
                return ct;
            }
        }
        return null;
    }
}