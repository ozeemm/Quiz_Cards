package com.example.myapplication.View.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.myapplication.Data.Repository;
import com.example.myapplication.Model.CardsPacket;
import com.example.myapplication.R;
import com.example.myapplication.View.Activities.CardsActivity;

import java.util.ArrayList;
import java.util.HashMap;


public class PacketsListViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CardsPacket> packets;
    private LayoutInflater inflater;
    private HashMap<Integer, ArrayList<Integer>> knownCardsCount;

    public PacketsListViewAdapter(Context ctx, ArrayList<CardsPacket> packets, HashMap<Integer, ArrayList<Integer>> knownCardsCount){
        this.context = ctx;
        this.packets = packets;
        inflater = LayoutInflater.from(ctx);
        this.knownCardsCount = knownCardsCount;
    }

    @Override
    public int getCount() {
        return packets.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    @SuppressLint({"InflateParams", "ViewHolder"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.packets_activity_list_view, null);
        TextView txtViewName =  convertView.findViewById(R.id.txtViewName);
        txtViewName.setText(packets.get(position).getName());
        TextView txtViewDescription = convertView.findViewById(R.id.txtViewDescription);
        txtViewDescription.setText(packets.get(position).getDescription());
        Button buttonOpen = convertView.findViewById(R.id.buttonOpen);
        Button buttonRestart = convertView.findViewById(R.id.buttonRestart);
        TextView textViewProgress = convertView.findViewById(R.id.textViewProgress);
        ProgressBar progressBar = convertView.findViewById(R.id.progressBar);
        Integer know = 0;
        Integer all = packets.get(position).getCardsCount();
        if (knownCardsCount.containsKey(packets.get(position).getId())){
            know = knownCardsCount.get(packets.get(position).getId()).size();
        }
        if (know>0)
            buttonRestart.setEnabled(true);
        if (know.equals(all))
            buttonOpen.setEnabled(false);
        textViewProgress.setText((int)(Math.round(know*(100./all)))+"%");
        progressBar.setProgress((100/all)*know);
        buttonOpen.setOnClickListener(v->{
            Intent intent = new Intent(context, CardsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            intent.putExtra("packetId", packets.get(position).getId());
            context.startActivity(intent);
        });
        buttonRestart.setOnClickListener(v->{
            Repository.restartCards(packets.get(position).getId());
            Intent intent = new Intent(context, CardsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            intent.putExtra("packetId", packets.get(position).getId());
            context.startActivity(intent);
        });
        return convertView;
    }







}


