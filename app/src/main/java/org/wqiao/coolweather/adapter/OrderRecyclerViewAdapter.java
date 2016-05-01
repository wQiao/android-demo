package org.wqiao.coolweather.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.wqiao.coolweather.R;
import org.wqiao.coolweather.activity.fu.OrderListFragment.OnListFragmentInteractionListener;
import org.wqiao.coolweather.databinding.FragmentOrderItemBinding;
import org.wqiao.coolweather.model.Order;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Order} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class OrderRecyclerViewAdapter extends RecyclerView.Adapter<OrderRecyclerViewAdapter.BindingHolder> {

    private final List<Order> mValues;
    private final OnListFragmentInteractionListener mListener;

    public OrderRecyclerViewAdapter(List<Order> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentOrderItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.fragment_order_item,parent,false);
        return new BindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(final BindingHolder holder, int position) {
        holder.binding.setOrder(mValues.get(position));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.binding.getOrder());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final FragmentOrderItemBinding binding;

        public BindingHolder(FragmentOrderItemBinding binding) {
            super(binding.getRoot());
            this.binding =binding;
            mView = binding.getRoot();
        }

    }
}
