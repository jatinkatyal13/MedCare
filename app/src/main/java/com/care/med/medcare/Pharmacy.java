package com.care.med.medcare;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Pharmacy extends AppCompatActivity implements SearchView.OnQueryTextListener {

    List<Medicine> medicines = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        //populate medicines list
        medicines.add(new Medicine(1, "Paracetamol", "Crocin"));
        medicines.add(new Medicine(2, "Moxiking", "Amoxcyllin"));
        medicines.add(new Medicine(3, "Limcee", "Calpol"));

        ListAdapter listAdapter = new ListAdapter(medicines);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        SearchView searchView = (SearchView) findViewById(R.id.search);
        searchView.setOnQueryTextListener(this);


    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        //make a post request to the server for the data relatd to the query
        //and update the medicines list
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    public class Medicine{
        int id;
        String salt;
        String name;
        Medicine(int id, String salt, String name){
            this.id = id;
            this.salt = salt;
            this.name = name;
        }

        //getter methods

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getSalt() {
            return salt;
        }

        //setter methods

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }
    }

    public void addToCart(Medicine medicine){
        Toast.makeText(this, "1 item added to cart", Toast.LENGTH_SHORT).show();
    }


    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

        List<Medicine> medicines = new ArrayList<>();

        public ListAdapter(List<Medicine> medicines){
            this.medicines = medicines;
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            TextView name, salt;
            Button buy;

            public ViewHolder(View itemView) {
                super(itemView);
                name = (TextView)itemView.findViewById(R.id.name);
                salt = (TextView)itemView.findViewById(R.id.salts);
                buy = (Button)itemView.findViewById(R.id.buy);
            }
        }

        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(Pharmacy.this).inflate(R.layout.pharmacy_list_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ListAdapter.ViewHolder holder, final int position) {
            holder.name.setText(medicines.get(position).getName());
            holder.salt.setText(medicines.get(position).getSalt());
            holder.buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addToCart(medicines.get(position));
                }
            });
            holder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        @Override
        public int getItemCount() {
            return medicines.size();
        }
    }

}
