package com.example.guestapp8126.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guestapp8126.Activities.CancelOrderActivity;
import com.example.guestapp8126.Models.RequestOrder;
import com.example.guestapp8126.R;

import java.util.List;

public class RequestOrderAdapter extends RecyclerView.Adapter<RequestOrderAdapter
        .MyViewHolder> {

    Context mContext;
    List<RequestOrder> mData;

    public RequestOrderAdapter(Context mContext, List<RequestOrder> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(mContext).inflate(R.layout.row_request_order,
                parent, false);

        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_setrika.setText(mData.get(position).getSetrika().toString());
        holder.tv_namaLayanan.setText(mData.get(position).getPaketLayanan());
        holder.tv_antarJemput.setText(mData.get(position).getAntarJemput().toString());
        holder.tv_deskripsi.setText(mData.get(position).getDeskripsi());
//        holder.tv_pewangi.setText(mData.get(position).getPewangi());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_namaLayanan, tv_deskripsi, tv_antarJemput, tv_setrika, tv_pewangi;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_antarJemput = itemView.findViewById(R.id.tv_antarJemput_rro);
            tv_deskripsi = itemView.findViewById(R.id.tv_deskripsi_rro);
            tv_namaLayanan = itemView.findViewById(R.id.tv_pakat_layanan_rro);
            tv_pewangi = itemView.findViewById(R.id.tv_pewangi_rro);
            tv_setrika = itemView.findViewById(R.id.tv_setrika_rro);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent cancelOrder = new Intent(mContext, CancelOrderActivity.class);
                    int position = getAdapterPosition();

                    cancelOrder.putExtra("orderKey", mData.get(position).getOrderKey());
                    cancelOrder.putExtra("idLaundry", mData.get(position).getIdLaundry());
                    cancelOrder.putExtra("layanan", mData.get(position).getPaketLayanan());
                    cancelOrder.putExtra("deskripsi", mData.get(position).getDeskripsi());

                    mContext.startActivity(cancelOrder);


                }
            });

        }
    }
}
