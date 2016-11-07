package com.example.castroreyrobert.sharedpreferencesdemo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;


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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.long_press, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {


        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int rowPosition = info.position;

        SmokerModel smokerModel = (SmokerModel) getListAdapter().getItem(rowPosition);

        switch (item.getItemId()){

            case R.id.delete_menu:
                DBAdapter dbAdapter = new DBAdapter(getActivity().getBaseContext());
                dbAdapter.open();
                dbAdapter.deleteSmoker(smokerModel.getId());

                smokerModelArrayList.clear();
                smokerModelArrayList.addAll(dbAdapter.getAllSmoke());
                smokeAdapter.notifyDataSetChanged();

                dbAdapter.close();
        }

        return super.onContextItemSelected(item);
    }
}
