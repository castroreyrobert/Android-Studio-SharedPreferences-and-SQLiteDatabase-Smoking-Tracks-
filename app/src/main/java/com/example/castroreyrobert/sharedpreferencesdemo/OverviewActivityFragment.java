package com.example.castroreyrobert.sharedpreferencesdemo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;


import java.util.ArrayList;


public class OverviewActivityFragment extends ListFragment {

    private ArrayList<SmokerModel> smokerModelArrayList;
    private SmokeAdapter smokeAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DBAdapter dbAdapter = new DBAdapter(getActivity().getBaseContext());
        dbAdapter.open();
        smokerModelArrayList = dbAdapter.getAllSmoke();
        dbAdapter.close();
        smokeAdapter = new SmokeAdapter(getActivity(), smokerModelArrayList);
        setListAdapter(smokeAdapter);

        registerForContextMenu(getListView());
    }
}
