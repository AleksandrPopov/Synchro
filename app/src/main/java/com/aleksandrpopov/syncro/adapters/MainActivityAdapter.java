package com.aleksandrpopov.syncro.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ScanViewHolder> {

    @Override
    public MainActivityAdapter.ScanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MainActivityAdapter.ScanViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ScanViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageItem;
        TextView mTextResult;
        public ScanViewHolder(View itemView) {
            super(itemView);
//            mImageItem = itemView.findViewById(R.id.imageItemMainActivityAdapter);
//            mTextResult = itemView.findViewById(R.id.textItemMainActivityAdapter);
        }
    }
}
