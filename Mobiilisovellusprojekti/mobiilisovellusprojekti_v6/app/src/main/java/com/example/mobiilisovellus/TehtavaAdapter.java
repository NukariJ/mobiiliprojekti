package com.example.mobiilisovellus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.List;

public class TehtavaAdapter extends ArrayAdapter<Tehtava> {

    //adapteri Listviewille tehtäväluokkaa varten

    private Context mContext;
    private List<Tehtava> tehtavaList;

    public TehtavaAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Tehtava> list) {
        super(context,0,list);
        mContext = context;
        tehtavaList = list;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        Tehtava current = tehtavaList.get(position);

        //Tehtäväoliosta otetaan tarpeelliset tiedot ja niiden perusteella luodaan näkymä listviewille

        TextView nimi = (TextView) listItem.findViewById(R.id.textView_nimi);
        nimi.setText(current.getNimi());

        TextView prosentti = (TextView) listItem.findViewById(R.id.textView_prosentti);
        prosentti.setText("" + current.getSuoritettu()+" %");

        if(current.getVanhentunut() == true) {
            TextView paivamaara = (TextView) listItem.findViewById(R.id.textView_paivamaara);
            paivamaara.setText("Tehtävä vanhentunut");
        }else {

            TextView paivamaara = (TextView) listItem.findViewById(R.id.textView_paivamaara);
            paivamaara.setText(current.getPaivamaara());
        }
        return listItem;
    }



}