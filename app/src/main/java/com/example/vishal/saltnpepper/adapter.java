package com.example.vishal.saltnpepper;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {

    private List<itemrest> listitem;
    private Context context;

    public adapter(List<itemrest> listitem, Context context) {
        this.listitem = listitem;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(adapter.ViewHolder holder, int position) {

        itemrest ir=listitem.get(position);

        holder.tvhead.setText(ir.getHead());
        holder.tvdecs.setText(ir.getDesc());
        holder.tvoff.setText(ir.getOff());
        holder.tvrat.setText(ir.getRat());


        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return listitem.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvhead;
        public TextView tvdecs;
        public TextView tvoff;
        public TextView tvrat;
        public LinearLayout linear;

        public ViewHolder(View itemView) {
            super(itemView);

            tvhead=(TextView) itemView.findViewById(R.id.textviewitem);
            tvdecs=(TextView) itemView.findViewById(R.id.desc);
            tvoff=(TextView) itemView.findViewById(R.id.off);
            tvrat=(TextView) itemView.findViewById(R.id.rati);
            linear=(LinearLayout)itemView.findViewById(R.id.linear);


        }
    }
}
