package com.transparent.fleet.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.transparent.fleet.R;
import com.transparent.fleet.activity.ActivityChooseTrailer;
import com.transparent.fleet.activity.ActivityChooseTruck;
import com.transparent.fleet.activity.DocumentActivity;
import com.transparent.fleet.adapter.DocumentAdapter;
import com.transparent.fleet.basecomponent.BaseFragment;
import com.transparent.fleet.callbackClick.OnRecyclerItemClick;
import com.transparent.fleet.databinding.FragmentDocumentBinding;
import com.transparent.fleet.model.DocumentListModel;
import com.transparent.fleet.model.DocumentModel;
import com.transparent.fleet.util.AlertDialogAndIntents;

import java.util.ArrayList;

public class FragmentDriver extends BaseFragment implements View.OnClickListener {
    public static final String TAG = "Latest";
    public DocumentListModel model;
    private FragmentDocumentBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        if (null != openFrag)

            super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_document, container, false);
        model = new DocumentListModel();
        model.setArrayList(new ArrayList<DocumentModel>());
        setModelDetail();
        setOnClick();
        binding.setDocumentListModel(model);
        initView();
        setHasOptionsMenu(true);
        return binding.getRoot();

    }


    private void setOnClick() {

    }

    private void setModelDetail() {
        model.setNoWifiImg(getResources().getDrawable(R.drawable.app_icon));
        model.setNoData(getResources().getString(R.string.noInternetConnectivity));

    }


    public void initView() {
        initRecycler();
        fillArrayList();


    }


    /*
     *  initialize Reacycler view
     */
    private void initRecycler() {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.recyclerView.setAdapter(new DocumentAdapter(getActivity(),
                model.getArrayList(), new OnRecyclerItemClick() {
            @Override
            public void onClick(final int position, int type) {
                AlertDialogAndIntents.openUrl(getContext(),model.getArrayList().get(position).getLink());

            }
        }));

    }

    private void fillArrayList() {
        model.getArrayList().clear();

        DocumentModel documentModel;
        for (int i = 0; i <DocumentActivity.model.getArrayList().size() ; i++) {
            documentModel = new DocumentModel();
            documentModel.setName(DocumentActivity.model.getArrayList().get(i).getName());
            documentModel.setLink(DocumentActivity.model.getArrayList().get(i).getLink());
            model.getArrayList().add(documentModel);
        }


        model.setApiCallActive(false);
        binding.recyclerView.getAdapter().notifyDataSetChanged();
        notyFyDat();
    }

    private void notyFyDat() {
        if (model.getArrayList().size() > 0) {
            binding.recyclerView.setVisibility(View.VISIBLE);
            binding.noData.setVisibility(View.GONE);
        } else {
            binding.recyclerView.setVisibility(View.GONE);
            binding.noData.setVisibility(View.VISIBLE);
            binding.noData.setText(getString(R.string.noDataFound));

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }


}

