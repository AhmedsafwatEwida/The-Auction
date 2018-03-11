package pioneers.safwat.mazad.activities;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import pioneers.safwat.mazad.R;

/**
 * Created by safwa on 12/7/2017.
 */

public class RecyclerViewAdapteritemsuserBid2 extends RecyclerView.Adapter<RecyclerViewAdapteritemsuserBid2.ViewHolder> {

    Context context;

    List<DataAdapter2> dataAdapters;
    private RecyclerClickListner mRecyclerClickListner;
    ImageLoader imageLoader,imageLoader1,imageLoader2;
    public RecyclerViewAdapteritemsuserBid2(List<DataAdapter2> getDataAdapter, Context context){

        super();
        this.dataAdapters = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewitemuser4, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        /*this.btn3= (Button) view.findViewById(R.id.button3);

       btn3.setClickable(true);
     btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast= Toast.makeText(context, "Wrong Price Entry or DownPayment not Paid", Toast.LENGTH_LONG);

            }
        });*/

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
        Viewholder.ImagemakeTextView.setText(dataAdapterOBJ.getItemmake());
        Viewholder.ImagemodelTextView.setText(dataAdapterOBJ.getItemmodel());
        Viewholder.ImageyearTextView.setText(dataAdapterOBJ.getItemyear());
        Viewholder.ImagewarnTextView.setText(dataAdapterOBJ.getItemwarn());
        Viewholder.ImageengineTextView.setText(dataAdapterOBJ.getItemengine());
        Viewholder.ImagemillageTextView.setText(dataAdapterOBJ.getItemmillage());
        Viewholder.ImagetypeTextView.setText(dataAdapterOBJ.getItemtype());
//        Viewholder.ImageTitleTextView.setText(dataAdapterOBJ.getImageTitle());
        Viewholder.ImagebidtimeTextView.setText(dataAdapterOBJ.getItembidtime());
        Viewholder.ImageIDTextView.setText(dataAdapterOBJ.getImageID());
        Viewholder.ImagepriceTextView.setText(dataAdapterOBJ.getImagePrice());
      //  Viewholder.ImageauctionTextView.setText(dataAdapterOBJ.getItemAuction());
        Viewholder.ImagebidateTextView.setText(dataAdapterOBJ.getBidDate());
        Viewholder.ImageHourTextView.setText(dataAdapterOBJ.getItembidtime());
     //   Viewholder.ImagebidwinTextView.setText(dataAdapterOBJ.getBidWin());
        Viewholder.ImagestatusTextView.setText(dataAdapterOBJ.getItemStatus());
        Viewholder.ImageDesrcTextView.setText(dataAdapterOBJ.getItemDescr());
        Viewholder.NewPrice.setText(dataAdapterOBJ.getImagePrice());
        Viewholder.NewPrice.setTag(position);
        Viewholder.bt.setTag(position);
        Viewholder.btpus.setTag(position);
        Viewholder.VollyImageView.setTag(position);
     /*  Viewholder.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Viewholder.ImageHourTextView.setText(dataAdapterOBJ.getItemHour());

            }
        });*/
    }

    @Override
    public int getItemCount() {

        return dataAdapters.size();
    }


    public void setRecyclerClickListner(RecyclerClickListner recyclerClickListner){
        mRecyclerClickListner = recyclerClickListner;
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView ImageTitleTextView;
        public TextView ImageIDTextView;
        public TextView ImagepriceTextView;
        public TextView ImagebidateTextView;
        public TextView ImagebidwinTextView;
        public TextView ImagestatusTextView;
        public TextView ImageauctionTextView;
        public TextView ImageDesrcTextView;
        public TextView ImageHourTextView;
        public TextView ImagemakeTextView;
        public TextView ImagemodelTextView;
        public TextView ImageyearTextView;
        public TextView ImageengineTextView;
        public TextView ImagetypeTextView;
        public TextView ImagemillageTextView;
        public TextView ImagewarnTextView;
        public EditText NewPrice;
        public TextView ImagebidtimeTextView;
        public NetworkImageView VollyImageView,VollyImageView1,VollyImageView2 ;
        public FloatingActionButton updatefast;
      public Button bt,btpus;
        public ViewHolder(View itemView) {

            super(itemView);

            ImageTitleTextView = (TextView) itemView.findViewById(R.id.ImageNameTextView) ;
            ImageIDTextView = (TextView) itemView.findViewById(R.id.ImageIDTextView) ;
            ImagepriceTextView = (TextView) itemView.findViewById(R.id.ImagepriceTextView) ;
            ImageauctionTextView = (TextView) itemView.findViewById(R.id.ImageauctionTextView) ;
            ImagebidateTextView = (TextView) itemView.findViewById(R.id.ImagebidateTextView) ;
            ImagebidwinTextView = (TextView) itemView.findViewById(R.id.ImagebidwinTextView) ;
            ImagestatusTextView = (TextView) itemView.findViewById(R.id.ImagestatusTextView) ;
            ImageDesrcTextView = (TextView) itemView.findViewById(R.id.ImagedescrTextView) ;
            ImageHourTextView = (TextView) itemView.findViewById(R.id.ImagebidhourTextView) ;
            NewPrice = (EditText) itemView.findViewById(R.id.newpriceeditable) ;
            VollyImageView = (NetworkImageView) itemView.findViewById(R.id.VolleyImageView) ;
            VollyImageView1 = (NetworkImageView) itemView.findViewById(R.id.VolleyImageView1) ;
            VollyImageView2 = (NetworkImageView) itemView.findViewById(R.id.VolleyImageView2) ;
            ImagewarnTextView = (TextView) itemView.findViewById(R.id.ImagewarnTextView) ;
            ImagemakeTextView = (TextView) itemView.findViewById(R.id.ImagemakeTextView) ;
            ImagemodelTextView = (TextView) itemView.findViewById(R.id.ImagemodelTextView) ;
            ImagemillageTextView = (TextView) itemView.findViewById(R.id.ImagemillageTextView) ;
            ImageengineTextView = (TextView) itemView.findViewById(R.id.ImageengineTextView) ;
            ImageyearTextView = (TextView) itemView.findViewById(R.id.ImageyearTextView) ;
            ImagetypeTextView = (TextView) itemView.findViewById(R.id.ImagetypeTextView) ;
            ImagebidtimeTextView = (TextView) itemView.findViewById(R.id.ImagebidhourTextView) ;
            bt = (Button) itemView.findViewById(R.id.button3);
            btpus = (Button) itemView.findViewById(R.id.button4);




        }


        @Override
        public void onClick(View view) {
            if (mRecyclerClickListner != null) {
                mRecyclerClickListner.itemClick(view, getPosition());
            }
        }
    }
    public interface RecyclerClickListner
    {
        public void itemClick(View view, int position);
    }
}