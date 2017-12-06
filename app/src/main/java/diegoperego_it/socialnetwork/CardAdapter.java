package diegoperego_it.socialnetwork;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import diegoperego_it.socialnetwork.Model.Gruppi;

/**
 * Created by utente3.academy on 06-Dec-17.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewGruppi>{

    private List<Gruppi> gruppi;
    private Context context;

    public CardAdapter(Context context, List<Gruppi> gruppi) {
        this.gruppi = gruppi;
        this.context = context;
    }

    @Override
    public CardAdapter.ViewGruppi onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        ViewGruppi grup = new ViewGruppi(v, parent.getContext());
        return grup;
    }

    @Override
    public void onBindViewHolder(ViewGruppi holder, int position) {

    }

    @Override
    public int getItemCount() {
        return gruppi.size();
    }

    public static class ViewGruppi extends RecyclerView.ViewHolder{

        public Button group;

        public ViewGruppi(View v, Context context){
            super(v);
            group = v.findViewById(R.id.bGruppi);
        }
    }
}
