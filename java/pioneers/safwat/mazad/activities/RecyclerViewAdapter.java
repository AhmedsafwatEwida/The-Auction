package pioneers.safwat.mazad.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import pioneers.safwat.mazad.R;

/**
 * Created by safwa on 12/7/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;

    List<DataAdapter> dataAdapters;

    ImageLoader imageLoader;

    public RecyclerViewAdapter(List<DataAdapter> getDataAdapter, Context context){

        super();
        this.dataAdapters = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder Viewholder, int position) {

        DataAdapter dataAdapterOBJ =  dataAdapters.get(position);

        imageLoader = ImageAdapter.getInstance(context).getImageLoader();

        imageLoader.get(dataAdapterOBJ.getImageUrl(),
                ImageLoader.getImageListener(
                        Viewholder.VollyImageView,//Server Image
                        R.mipmap.ic_launcher,//Before loading server image the default showing image.
                        android.R.drawable.ic_dialog_alert //Error image if requested image dose not found on server.
                )
        );

        Viewholder.VollyImageView.setImageUrl(dataAdapterOBJ.getImageUrl(), imageLoader);

        Viewholder.ImageTitleTextView.setText(dataAdapterOBJ.getImageTitle());

        Viewholder.ImageIDTextView.setText(dataAdapterOBJ.getImageID());
        Viewholder.ImagepriceTextView.setText(dataAdapterOBJ.getImagePrice());
        Viewholder.ImageauctionTextView.setText(dataAdapterOBJ.getItemAuction());
        Viewholder.ImagebidateTextView.setText(dataAdapterOBJ.getBidDate());
        Viewholder.ImagebidwinTextView.setText(dataAdapterOBJ.getBidWin());
        Viewholder.ImagestatusTextView.setText(dataAdapterOBJ.getItemStatus());
        Viewholder.ImageDesrcTextView.setText(dataAdapterOBJ.getItemDescr());

    }

    @Override
    public int getItemCount() {

        return dataAdapters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView ImageTitleTextView;
        public TextView ImageIDTextView;
        public TextView ImagepriceTextView;
        public TextView ImagebidateTextView;
        public TextView ImagebidwinTextView;
        public TextView ImagestatusTextView;
        public TextView ImageauctionTextView;
        public TextView ImageDesrcTextView;
        public NetworkImageView VollyImageView ;

        public ViewHolder(View itemView) {

            super(itemView);

            ImageTitleTextView = (TextView) itemView.findViewById(R.id.ImageNameTextView) ;
            ImageIDTextView = (TextView) itemView.findViewById(R.id.ImageIDTextView) ;
            ImagepriceTextView = (TextView) itemView.findViewById(R.id.ImagepriceTextView) ;
            ImageauctionTextView = (TextView) itemView.findViewById(R.id.ImageauctionTextView) ;
            ImagebidateTextView = (TextView) itemView.findViewById(R.id.ImagebidateTextView) ;
            ImagebidwinTextView = (TextView) itemView.findViewById(R.id.ImagebidwinTextView) ;
            ImagestatusTextView = (TextView) itemView.findViewById(R.id.ImagestatusTextView) ;
            ImageDesrcTextView = (TextView) itemView.findViewById(R.id.ImagedescTextView) ;

            VollyImageView = (NetworkImageView) itemView.findViewById(R.id.VolleyImageView) ;

        }
    }

}