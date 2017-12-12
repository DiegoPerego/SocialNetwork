package diegoperego_it.socialnetwork.Util;

import android.support.v7.widget.CardView;

import java.util.List;

import diegoperego_it.socialnetwork.Model.Post;

/**
 * Created by utente3.academy on 12-Dec-17.
 */

public interface CardViewCL {
    void onCardClick (int pos, Post postList, CardView cardView);
}
