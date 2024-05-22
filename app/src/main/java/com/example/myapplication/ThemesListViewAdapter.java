package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.example.myapplication.Model.Theme;

import java.util.ArrayList;

public class ThemesListViewAdapter extends BaseAdapter {

    Context context;
    ArrayList<Theme> themes;
    LayoutInflater inflater;


    public ThemesListViewAdapter(Context ctx, ArrayList<Theme> themes){
        this.context = ctx;
        this.themes = themes;
        inflater = LayoutInflater.from(ctx);

    }

    @Override
    public int getCount() {
        return themes.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint({"InflateParams", "ViewHolder", "SetTextI18n"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.themes_activity_list_view, null);
        TextView txtViewName =  convertView.findViewById(R.id.txtViewName);
        txtViewName.setText(themes.get(position).getName());
        TextView txtViewDescription = convertView.findViewById(R.id.txtViewDescription);
        txtViewDescription.setText(themes.get(position).getDescription());
        Button button = convertView.findViewById(R.id.button);
        button.setOnClickListener(v->{
            Intent intent = new Intent(context, PacketsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            intent.putExtra("themeId",themes.get(position).getId());
            context.startActivity(intent);
        });
        return convertView;
    }





}


