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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView welcome;
    ListView prodList;
    Button logout, btnToCart;
    public static ArrayList<Product> products=new ArrayList<>();
    public static int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillProducts();

        welcome=findViewById(R.id.txvWelcome);
        prodList=findViewById(R.id.lvProducts);
        logout=findViewById(R.id.btnLogOut);
        btnToCart = findViewById(R.id.btnToCart);

        String clientName=getClient(LoginActivity.UserName,LoginActivity.clients);
        welcome.setText("Welcome "+clientName);
        prodList.setAdapter(new ProductsAdapter(this,products));

        logout.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        });

        prodList.setOnItemClickListener((parent, view, position, id) -> {
            index=position;
            Intent intent=new Intent(MainActivity.this,DescriptionActivity.class);
            startActivity(intent);
        });

        btnToCart.setOnClickListener(v -> {
            if(DescriptionActivity.cartItems.size() == 0){
                Toast.makeText(this, "Your cart is empty, Please add items to view cart.", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }
    //method receives the user name and returns the client name
    public String getClient(String user,ArrayList<Client>clnts)
    {
        for(Client cln:clnts)
            if(cln.getUsername().equalsIgnoreCase(user))
                return cln.getName();
        return null;
    }
    public void fillProducts()
    {
        products.clear();
        products.add(new Product("Camping Chair",59.99,R.drawable.chair,"Brand New Upgraded Design with Your COMFORT in Mind! The new YIZI GO design is 1cm higher and 15% lighter and prior version. Experience superior lower back support from the new deeper seat fabric"));
        products.add(new Product("Sleeping Pad",86.99,R.drawable.sleeping,"UPGRADE BUILT-IN PUMP : No additional air pump is needed, and avoid the embarrassment of blowing with your mouth. Just press the pump with your hands or feet multiple times,"));
        products.add(new Product("Travel Towel",11.99,R.drawable.towel,"Microfiber&Friendly to Skin: This microfiber travel towel is made of high quality microfiber,which is smooth and compact,more soft,friendly to skin,no faded"));
        products.add(new Product("Solar Power Bank ",45.95,R.drawable.power,"【26800mAh Ultra High Capacity Solar Charger】:Built-in 26800mAh high capacity polymer battery equipped with a compact solar panel, which could recharge the battery itself under sunlight, eco-friendly and ideal for outdoor activity ."));
        products.add(new Product("Survival Kit",27.99,R.drawable.kit,"ULTRA BRIGHT: This lantern Includes 30 individual low consumption LED bulbs carrying 360° of luminous light while saving energy\n" +
                "LONG-LASTING: Light up at least to 30 hours of regular, continuous use with enough battery capacity (batteries pre-installed in the lantern)"));
    }

}