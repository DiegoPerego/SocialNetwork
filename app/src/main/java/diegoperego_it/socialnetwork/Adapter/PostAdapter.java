package diegoperego_it.socialnetwork.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import diegoperego_it.socialnetwork.Model.Post;
import diegoperego_it.socialnetwork.R;
import diegoperego_it.socialnetwork.DettaglioActivity;
import diegoperego_it.socialnetwork.Util.CardViewCL;
import diegoperego_it.socialnetwork.Util.InternalStorage;

/**
 * Created by utente3.academy on 11-Dec-17.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostView>{

    private Context context;
    private List<Post> posts;
    private Post post;
    private final CardViewCL cardViewCL;

    public PostAdapter(Context context, List<Post> posts, CardViewCL cardViewCL) {
        this.context = context;
        this.posts = posts;
        this.cardViewCL = cardViewCL;
    }

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
        this.cardViewCL = null;
    }

    @Override
    public PostAdapter.PostView onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_row, parent, false);
        PostView pV= new PostView(v, parent.getContext());
        return pV;
    }

    @Override
    public void onBindViewHolder(final PostAdapter.PostView holder, final int position) {

        post = posts.get(position);

        holder.dateVal.setText(formatDate(post.getData()));
        holder.autorVal.setText(post.getAutore());
        holder.titleVal.setText(post.getTitolo());

        holder.postCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardViewCL.onCardClick(holder.getAdapterPosition(), posts.get(position), holder.postCard);
            }
        });

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class PostView extends RecyclerView.ViewHolder{

        public TextView titleVal;
        public TextView autorVal;
        public TextView dateVal;
        public CardView postCard;

        public PostView(View itemView, final Context context) {
            super(itemView);

            titleVal = itemView.findViewById(R.id.tTitleVal);
            autorVal = itemView.findViewById(R.id.tAutorVal);
            dateVal = itemView.findViewById(R.id.tDataVal);
            postCard = itemView.findViewById(R.id.PostCard);

        }
    }

    public String formatDate(Date date){
        Format format = new SimpleDateFormat("hh:mm dd/MM/yyyy", Locale.ITALY);
        return format.format(date);
    }


}
