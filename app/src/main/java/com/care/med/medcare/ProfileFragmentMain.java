package com.care.med.medcare;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragmentMain extends Fragment {

    Context context;

    public ProfileFragmentMain() {
        // Required empty public constructor
    }

    public void addContext(Context context){
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile_fragment_main, null);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

        List<String> messages = new ArrayList<>();
        messages.add("What is the effect of traumatic brain injury in children");
        messages.add("Cushing's Syndrome: could analyzing hair confirm analysis");
        messages.add("Amphetamine use may speed up heart aging");

        ListAdapter listAdapter = new ListAdapter(messages);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        listAdapter.notifyDataSetChanged();



        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

        List<String> messages = new ArrayList<>();

        public class ViewHolder extends RecyclerView.ViewHolder{

            public TextView message;
            public ViewHolder(View itemView) {
                super(itemView);
                message = (TextView)itemView.findViewById(R.id.message);
            }
        }

        public ListAdapter(List<String> messages){
            this.messages = messages;
        }

        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.message_list_item, null);
            ViewHolder holder = new ViewHolder (view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.message.setText(messages.get(position));
            holder.message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ShowNews.class);
                    intent.putExtra("message", "dummy text dummy text dummy text dummy text dummy text dummy text dummy text dummy text dummy text dummy text dummy text dummy text dummy text dummy text dummy text dummy text dummy text dummy text dummy text dummy text dummy text dummy text dummy text ");
                }
            });
        }

        @Override
        public int getItemCount() {
            return messages.size();
        }
    }

}
