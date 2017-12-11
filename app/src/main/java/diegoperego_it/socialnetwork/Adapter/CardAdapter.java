package diegoperego_it.socialnetwork.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import diegoperego_it.socialnetwork.Model.Gruppi;
import diegoperego_it.socialnetwork.PostActivity;
import diegoperego_it.socialnetwork.R;
import diegoperego_it.socialnetwork.Util.InternalStorage;

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
    public void onBindViewHolder(final ViewGruppi holder, int position) {
        final Gruppi groups = gruppi.get(position);
        holder.group.setText(groups.getNome());
        holder.group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InternalStorage.writeObject(context, "gruppo", groups.getNome());
                Intent i = new Intent(context, PostActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
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
