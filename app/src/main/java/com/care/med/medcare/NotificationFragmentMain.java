package com.care.med.medcare;

import android.content.Context;
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

public class NotificationFragmentMain extends Fragment {

    Context context;

    public NotificationFragmentMain() {
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
        View rootView = inflater.inflate(R.layout.fragment_notification_fragment_main, null);

        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);

        List<String> notifications = new ArrayList<>();
        //populating the notifications list
        notifications.add("Hello");
        notifications.add("How");
        notifications.add("Are");
        notifications.add("You");
        ListAdapter listAdapter = new ListAdapter(notifications);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listAdapter);
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

    //adapter for recycler view
    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

        List<String> notifications = new ArrayList<>();

        public ListAdapter(List<String> notifications){
            this.notifications = notifications;
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            public TextView message;

            public ViewHolder(View itemView) {
                super(itemView);
                message = (TextView)itemView.findViewById(R.id.message);
            }
        }

        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_list_item, null);
            ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.message.setText(notifications.get(position));
        }


        @Override
        public int getItemCount() {
            return notifications.size();
        }
    }

}
