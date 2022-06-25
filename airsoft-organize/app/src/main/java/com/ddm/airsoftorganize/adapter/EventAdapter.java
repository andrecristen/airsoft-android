package com.ddm.airsoftorganize.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ddm.airsoftorganize.R;
import com.ddm.airsoftorganize.models.Event;

import java.util.List;

public class EventAdapter extends BaseAdapter {

    private Context context;
    private List<Event> eventos;

    public EventAdapter(Context context, List<Event> eventos){
        super();
        this.context = context;
        this.eventos = eventos;
    }

    @Override
    public int getCount() {
        return eventos.size();
    }

    @Override
    public Object getItem(int position) {

        return eventos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        // TODO: 25/06/2022 criar layout de listagem de evento
        View view = inflater.inflate(R.layout.activity_main, parent, false);

        Event event = eventos.get(position);

//        TextView even = (TextView) view.findViewById(R.id.tNomeAluno);
//        nomeAluno.setText(event.getNome());
//
//        TextView emailAluno = (TextView) view.findViewById(R.id.tEmail);
//        emailAluno.setText(event.getEmail());
//
//        ImageView img = (ImageView) view.findViewById(R.id.imgAluno);
//
//        LinearLayout lay = (LinearLayout) view.findViewById(R.id.lay);
//        if(position%2==0)
//            lay.setBackgroundColor(Color.parseColor("#CCCCCC"));

        return view;
    }
}
