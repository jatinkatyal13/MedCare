package com.care.med.medcare;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentMain extends Fragment {

    Context context;

    public HomeFragmentMain() {
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
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_home_fragment_main, container, false);

        GridView gridView = (GridView)rootView.findViewById(R.id.gridView);

        //List of all the griditems
        final List<GridItem> gridItems = new ArrayList<>();
        gridItems.add(new GridItem("Patient Tracking", getResources().getDrawable(R.drawable.icon), new Intent(context, PatientTracking.class)));
        gridItems.add(new GridItem("Yur Pharmacy", getResources().getDrawable(R.drawable.icon), new Intent(context, Pharmacy.class)));
        gridItems.add(new GridItem("blood Donation", getResources().getDrawable(R.drawable.icon), new Intent(context, BloodDonation.class)));
        gridItems.add(new GridItem("Healthinator", getResources().getDrawable(R.drawable.icon), new Intent()));

        GridViewAdapter gridViewAdapter = new GridViewAdapter(gridItems, context);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Log.e("jatin", "clicked");
                startActivity(gridItems.get(position).NextActivity);
            }
        });

        gridView.setAdapter(gridViewAdapter);

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

    //grid list item class
    private class GridItem {
        String Title;
        Drawable Icon;
        Intent NextActivity;

        public GridItem(String Title, Drawable Icon, Intent NextActivity){
            this.Title = Title;
            this.Icon = Icon;
            this.NextActivity = NextActivity;

        }


        //getter methods
        public String getTitle() {
            return Title;
        }

        public Drawable getIcon() {
            return Icon;
        }

        public Intent getNextActivity() {
            return NextActivity;
        }

        //setter methods
        public void setIcon(Drawable icon) {
            Icon = icon;
        }

        public void setNextActivity(Intent nextActivity) {
            NextActivity = nextActivity;
        }

        public void setTitle(String title) {
            Title = title;
        }
    }

    //adapter class for grid view
    private class GridViewAdapter extends BaseAdapter {

        List<GridItem> gridItems = new ArrayList<>();
        Context context;

        public GridViewAdapter(List<GridItem> gridItems, Context context){
            this.gridItems = gridItems;
            this.context = context;
        }

        @Override
        public int getCount() {
            return gridItems.size();
        }

        @Override
        public Object getItem(int i) {
            return gridItems.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            final GridItem gridItem = gridItems.get(i);

            if (view == null)
                view = LayoutInflater.from(context).inflate(R.layout.fragment_home_fragment_main_grid_item, null);

            ((TextView)view.findViewById(R.id.title)).setText(gridItem.getTitle());

            return view;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return true;
        }

        @Override
        public boolean isEnabled(int position) {
            return true;
        }
    }
}
