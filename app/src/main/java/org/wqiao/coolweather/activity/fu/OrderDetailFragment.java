package org.wqiao.coolweather.activity.fu;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.wqiao.coolweather.R;
import org.wqiao.coolweather.databinding.FragmentOrderDetailBinding;
import org.wqiao.coolweather.model.Order;


public class OrderDetailFragment extends Fragment {

    private final static  String TAG ="OrderDetailFragment";

    private OnFragmentInteractionListener mListener;

    private  FragmentOrderDetailBinding binding;

    public OrderDetailFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_order_detail, container, false);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_order_detail, container, false);
        binding.setOrder((Order) getArguments().getSerializable("order"));
        binding.btnOrderSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onFragmentInteraction(binding.getOrder());
                }
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d(TAG, "onHiddenChanged: "+hidden);
        if(!hidden) {
            binding.setOrder((Order) getArguments().getSerializable("order"));
        }
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
//        Order order = (Order) getArguments().getSerializable("order");
//        binding.setOrder(order);
    }


    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach");
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach");
        super.onDetach();
        mListener = null;
        binding = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Order item);
    }

}
