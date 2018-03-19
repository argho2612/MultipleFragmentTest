package com.example.archakraborty.multiplefragmenttest;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by archakraborty on 14-03-2018.
 */

public class TitlesFragment extends ListFragment {

    boolean mDualPane;
    int mCurCheckPosition = 0;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> connectArrayToListView = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_activated_1,SuperHeroInfo.NAMES);
        setListAdapter(connectArrayToListView);

        View detailsFrame = getActivity().findViewById(R.id.details);

        mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        if(savedInstanceState != null){
            mCurCheckPosition = savedInstanceState.getInt("curChoice",0);        }

            if(mDualPane){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showDetails(mCurCheckPosition);
            }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("curChoice",mCurCheckPosition);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position);
    }

    public void showDetails(int index){
        mCurCheckPosition = index;

        if(mDualPane){
            getListView().setItemChecked(index,true);

            DetailsFragment details = (DetailsFragment) getFragmentManager().findFragmentById(R.id.details);

            if(details == null || details.getShowIndex() != index){
                details = DetailsFragment.newInstance(index);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.details,details);

                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
            }
        }
        else {
            Intent intent = new Intent();
            intent.setClass(getActivity(),DetailsActivity.class);
            intent.putExtra("index",index);
            startActivity(intent);
        }
    }
}
