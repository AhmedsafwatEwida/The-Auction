package pioneers.safwat.mazad.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import pioneers.safwat.mazad.R;


/**
 * Created by safwa on 12/7/2017.
 */

public class RecyclerViewAdapter_users2 extends RecyclerView.Adapter<RecyclerViewAdapter_users2.ViewHolder> implements Filterable {

    Context context;

    List<DataAdapter_users> dataAdapters;
    List<DataAdapter_users> dataAdaptersfiltered;

    ImageLoader imageLoader;

    public RecyclerViewAdapter_users2(List<DataAdapter_users> getDataAdapter, Context context){

        super();
        this.dataAdapters = getDataAdapter;
        this.context = context;
        this.dataAdaptersfiltered=getDataAdapter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewusers, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder Viewholder, int position) {

        DataAdapter_users dataAdapterOBJ =  dataAdapters.get(position);

        Viewholder.Username.setText(dataAdapterOBJ.getUsername());

        Viewholder.UserEmail.setText(dataAdapterOBJ.getUserEmail());
        Viewholder.UserPayment.setText(dataAdapterOBJ.getUserDownpay());
        Viewholder.UserPayment2.setText(dataAdapterOBJ.getUserDownpay2());
        Viewholder.Userid.setText(dataAdapterOBJ.getUserID());
        Viewholder.UserMobile.setText(dataAdapterOBJ.getUserMobile());


    }

    @Override
    public int getItemCount() {

        return dataAdapters.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    dataAdaptersfiltered.addAll(dataAdapters);
                } else {
                    List<DataAdapter_users> filteredList = new ArrayList<>();
                    for (DataAdapter_users row : dataAdapters) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getUserEmail().toLowerCase().contains(charString.toLowerCase()) || row.getUserMobile().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    dataAdaptersfiltered= filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = dataAdaptersfiltered;
                return filterResults;
    }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dataAdaptersfiltered= (List<DataAdapter_users>) filterResults.values;
                notifyDataSetChanged();

            }
        };
    }

            class ViewHolder extends RecyclerView.ViewHolder{

        public TextView Username;
        public TextView Userid;
        public TextView UserEmail;
        public TextView UserMobile;
        public TextView UserPayment;
        public TextView UserPayment2;

        public ViewHolder(View itemView) {

            super(itemView);

            UserEmail = (TextView) itemView.findViewById(R.id.ImageemailTextView) ;
            Userid = (TextView) itemView.findViewById(R.id.ImageIDTextView) ;
          Username = (TextView) itemView.findViewById(R.id.ImageNameTextView) ;
            UserMobile = (TextView) itemView.findViewById(R.id.ImageMobileTextView) ;
            UserPayment = (TextView) itemView.findViewById(R.id.ImagepaymenyTextView) ;
            UserPayment2 = (TextView) itemView.findViewById(R.id.Imagepaymeny2TextView) ;
        }
    }
}