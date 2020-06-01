package com.fdepedis.myretroproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.fdepedis.myretroproject.R;
import com.fdepedis.myretroproject.model.RetroPhoto;
import com.fdepedis.myretroproject.model.RetroPost;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<RetroPhoto> dataList;
    private Context context;

    public PostAdapter(Context context, List<RetroPhoto> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        TextView txtTitle;

        PostViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtTitle = mView.findViewById(R.id.title);

        }
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.post_row, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.txtTitle.setText(dataList.get(position).getTitle());


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
