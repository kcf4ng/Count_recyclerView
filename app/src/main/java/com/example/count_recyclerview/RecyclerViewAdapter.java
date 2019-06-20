package com.example.count_recyclerview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";
    Context mContext ;
    ArrayList<String> mName =  new ArrayList<>();
    ArrayList<String> mNum =new ArrayList<>();
    ArrayList<String> mSum = new ArrayList<>();
    ArrayList<String> mHistory = new ArrayList<>();
    ArrayList<String> mChange = new ArrayList<>();

    public RecyclerViewAdapter(Context mContext,
                               ArrayList<String> mName,
                               ArrayList<String> mNum,
                               ArrayList<String> mSum,
                               ArrayList<String> mHistory,
                               ArrayList<String> mChange) {
        this.mContext = mContext;
        this.mName = mName;
        this.mNum = mNum;
        this.mSum = mSum;
        this.mHistory = mHistory;
        this.mChange = mChange;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
    viewHolder .lblName.setText(mName.get(i));
    viewHolder.lblSum.setText(mSum.get(i));
    viewHolder.lblSum.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setMessage(mHistory.get(i));
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }
    });
    viewHolder.btnPlus.setText("+"+mChange.get(i));
    viewHolder.btnPlus.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int intSum = Integer.valueOf(mSum.get(i));
            String strHis = mHistory.get(i);
            int intChange = Integer.parseInt(mChange.get(i));
            intSum += intChange;
            String sum = Integer.toString(intSum);
            strHis +="+"+mChange.get(i);
            mSum.set(i,sum);
            mHistory.set(i,strHis);
            notifyItemChanged(i);
        }
    });

    viewHolder.btnMinus.setText("-"+mChange.get(i));
    viewHolder.btnMinus.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int intSum = Integer.valueOf(mSum.get(i));
            String strHis = mHistory.get(i);
            int intChange = Integer.parseInt(mChange.get(i));

            if (intSum > 0) {
                intSum -= intChange;
                strHis +="-"+mChange.get(i);
                String sum = Integer.toString(intSum);
                mSum.set(i,sum);
                mHistory.set(i,strHis);
                notifyItemChanged(i);
            }
        }
    });
    }

    @Override
    public int getItemCount() {
        return mName.size();
    }


    public void addData(int position,String str) {
        mName .add(position, str);
        int i = mNum.size();
        String strNum = String.valueOf(i);
        mNum .add(position, strNum);
        mSum .add(position, "0");
        mHistory.add(position,"1");
        mChange.add(position,"1");
        //更新recyclerview頁面
        notifyItemInserted(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
            LinearLayout Linearlayout_parent;
            TextView lblName,lblSum;
            Button btnPlus, btnMinus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Linearlayout_parent = itemView.findViewById(R.id.LinearLayout_parent);
            lblName = itemView.findViewById(R.id.lblName);
            lblSum = itemView.findViewById(R.id.lblSum);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);
        }
    }
}
