package com.example.mobiilisovellus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TehtavaAdapter extends ArrayAdapter<Tehtava> {

    private Context mContext;
    private List<Tehtava> tehtavaList;

    public TehtavaAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Tehtava> list) {
        super(context,0,list);
        mContext = context;
        tehtavaList = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        Tehtava current = tehtavaList.get(position);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM HH:mm");



        TextView nimi = (TextView) listItem.findViewById(R.id.textView_nimi);
        nimi.setText(current.getNimi());

        TextView prosentti = (TextView) listItem.findViewById(R.id.textView_prosentti);
        prosentti.setText("" + current.getSuoritettu()+" %");

        if(current.getVanhentunut() == true) {
            TextView paivamaara = (TextView) listItem.findViewById(R.id.textView_paivamaara);
            paivamaara.setText("Tehtävä vanhentunut");
        }else {

            TextView paivamaara = (TextView) listItem.findViewById(R.id.textView_paivamaara);
            paivamaara.setText("" + current.getPaivamaara().format(formatter));

        }
        return listItem;
    }


}
