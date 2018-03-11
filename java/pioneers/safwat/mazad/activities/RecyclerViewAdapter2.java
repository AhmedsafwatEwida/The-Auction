package pioneers.safwat.mazad.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import pioneers.safwat.mazad.R;

/**
 * Created by safwa on 12/7/2017.
 */

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder> {

    Context context;
    private ViewFlipper mViewFlipper;
    private float initialX;


    List<DataAdapter2> dataAdapters;

    ImageLoader imageLoader,imageLoader1,imageLoader2;

    public RecyclerViewAdapter2(List<DataAdapter2> getDataAdapter, Context context){

        super();
        this.dataAdapters = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview4itemuser, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        this.mViewFlipper= (ViewFlipper) view.findViewById(R.id.viewflip);
        mViewFlipper.setInAnimation(context,R.anim.in_from_left);
        mViewFlipper.setOutAnimation(context,R.anim.in_from_right);

        mViewFlipper.setFlipInterval(4000);
        mViewFlipper.setAutoStart(true);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder Viewholder, int position) {

        DataAdapter2 dataAdapterOBJ =  dataAdapters.get(position);

        imageLoader = ImageAdapter.getInstance(context).getImageLoader();
        imageLoader1 = ImageAdapter1.getInstance(context).getImageLoader();
        imageLoader2 = ImageAdapter2.getInstance(context).getImageLoader();


        imageLoader.get(dataAdapterOBJ.getImageUrl(),
                ImageLoader.getImageListener(
                        Viewholder.VollyImageView,//Server Image
                        R.mipmap.ic_launcher,//Before loading server image the default showing image.
                        android.R.drawable.ic_dialog_alert //Error image if requested image dose not found on server.
                )
        );
        imageLoader1.get(dataAdapterOBJ.getImageUrl1(),
                ImageLoader.getImageListener(
                        Viewholder.VollyImageView1,//Server Image
                        R.mipmap.ic_launcher,//Before loading server image the default showing image.
                        android.R.drawable.ic_dialog_alert //Error image if requested image dose not found on server.
                )
        );
        imageLoader2.get(dataAdapterOBJ.getImageUrl2(),
                ImageLoader.getImageListener(
                        Viewholder.VollyImageView2,//Server Image
                        R.mipmap.ic_launcher,//Before loading server image the default showing image.
                        android.R.drawable.ic_dialog_alert //Error image if requested image dose not found on server.
                )
        );
        Viewholder.VollyImageView.setImageUrl(dataAdapterOBJ.getImageUrl(), imageLoader);
        Viewholder.VollyImageView1.setImageUrl(dataAdapterOBJ.getImageUrl1(), imageLoader1);
        Viewholder.VollyImageView2.setImageUrl(dataAdapterOBJ.getImageUrl2(), imageLoader2);

//        Viewholder.ImageTitleTextView.setText(dataAdapterOBJ.getImageTitle());

        Viewholder.ImageIDTextView.setText(dataAdapterOBJ.getImageID());
        Viewholder.ImagepriceTextView.setText(dataAdapterOBJ.getImagePrice());
        //    Viewholder.ImageauctionTextView.setText(dataAdapterOBJ.getItemAuction());
        Viewholder.ImagebidateTextView.setText(dataAdapterOBJ.getBidDate());
        //    Viewholder.ImagebidwinTextView.setText(dataAdapterOBJ.getBidWin());
        Viewholder.ImagestatusTextView.setText(dataAdapterOBJ.getItemStatus());
        Viewholder.ImagedescrTextView.setText(dataAdapterOBJ.getItemDescr());

        Viewholder.ImagemakeTextView.setText(dataAdapterOBJ.getItemmake());
        Viewholder.ImagemodelTextView.setText(dataAdapterOBJ.getItemmodel());
        Viewholder.ImageyearTextView.setText(dataAdapterOBJ.getItemyear());
        Viewholder.ImagewarnTextView.setText(dataAdapterOBJ.getItemwarn());
        Viewholder.ImageengineTextView.setText(dataAdapterOBJ.getItemengine());
        Viewholder.ImagemillageTextView.setText(dataAdapterOBJ.getItemmillage());
        Viewholder.ImagebidtimeTextView.setText(dataAdapterOBJ.getItembidtime());
        Viewholder.ImagetypeTextView.setText(dataAdapterOBJ.getItemtype());

    }

    @Override
    public int getItemCount() {

        return dataAdapters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView ImageTitleTextView;
        public TextView ImageIDTextView;
        public NetworkImageView VollyImageView,VollyImageView1,VollyImageView2 ;
        public TextView ImagepriceTextView;
        public TextView ImagebidateTextView;
        public TextView ImagebidwinTextView;
        public TextView ImagestatusTextView;
        public TextView ImageauctionTextView;
        public TextView ImagemakeTextView;
        public TextView ImagemodelTextView;
        public TextView ImageyearTextView;
        public TextView ImagemillageTextView;
        public TextView ImagewarnTextView;
        public TextView ImagetypeTextView;
        public TextView ImagebidtimeTextView;
        public TextView ImageengineTextView;
        public TextView ImagedescrTextView;

        public ViewHolder(View itemView) {

            super(itemView);

            ImageTitleTextView = (TextView) itemView.findViewById(R.id.ImageNameTextView) ;
            ImageIDTextView = (TextView) itemView.findViewById(R.id.ImageIDTextView) ;

            VollyImageView = (NetworkImageView) itemView.findViewById(R.id.VolleyImageView) ;
            VollyImageView1 = (NetworkImageView) itemView.findViewById(R.id.VolleyImageView1) ;
            VollyImageView2 = (NetworkImageView) itemView.findViewById(R.id.VolleyImageView2) ;
            ImagepriceTextView = (TextView) itemView.findViewById(R.id.ImagepriceTextView) ;
            ImageauctionTextView = (TextView) itemView.findViewById(R.id.ImageauctionTextView) ;
            ImagebidateTextView = (TextView) itemView.findViewById(R.id.ImagebidateTextView) ;
            ImagebidwinTextView = (TextView) itemView.findViewById(R.id.ImagebidwinTextView) ;
            ImagestatusTextView = (TextView) itemView.findViewById(R.id.ImagestatusTextView) ;
            ImagedescrTextView = (TextView) itemView.findViewById(R.id.ImagedescTextView) ;
            ImagemakeTextView = (TextView) itemView.findViewById(R.id.ImagemakeTextView) ;
            ImagemodelTextView = (TextView) itemView.findViewById(R.id.ImagemodelTextView) ;
            ImageyearTextView = (TextView) itemView.findViewById(R.id.ImageyearTextView) ;
            ImagemillageTextView = (TextView) itemView.findViewById(R.id.ImagemillageTextView) ;
            ImagewarnTextView = (TextView) itemView.findViewById(R.id.ImagewarnTextView) ;
            ImagetypeTextView = (TextView) itemView.findViewById(R.id.ImagetypeTextView) ;
            ImagebidtimeTextView = (TextView) itemView.findViewById(R.id.ImagebidtimeTextView) ;
            ImageengineTextView = (TextView) itemView.findViewById(R.id.ImageengineTextView) ;
        }
    }



}