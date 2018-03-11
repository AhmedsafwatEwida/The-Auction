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
import java.util.LinkedList;
import java.util.List;

import pioneers.safwat.mazad.R;

/**
 * Created by safwa on 12/7/2017.
 */

public class RecyclerViewAdapter_users extends RecyclerView.Adapter<RecyclerViewAdapter_users.ViewHolder> implements Filterable {

    Context context;

    List<DataAdapter_users> dataAdapters;
    List<DataAdapter_users> dataAdaptersfiltered;
    private UserFilter userFilter;
    ImageLoader imageLoader;
String emailuser;
    public RecyclerViewAdapter_users(List<DataAdapter_users> getDataAdapter, Context context){

        super();
        this.dataAdapters = getDataAdapter;
        this.context = context;
        this.dataAdaptersfiltered=getDataAdapter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewusersbrief, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder Viewholder, int position) {

        DataAdapter_users dataAdapterOBJ =  dataAdapters.get(position);

      //  Viewholder.Username.setText(dataAdapterOBJ.getUsername());

        Viewholder.UserEmail.setText(dataAdapterOBJ.getUserEmail());
        Viewholder.UserPayment.setText(dataAdapterOBJ.getUserDownpay());
       Viewholder.UserPayment2.setText(dataAdapterOBJ.getUserDownpay2());
        Viewholder.Userid.setText(dataAdapterOBJ.getUserID());
        Viewholder.UserMobile.setText(dataAdapterOBJ.getUserMobile());
        Viewholder.Username.setText(dataAdapterOBJ.getUsername());

      /*  Viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //trying to get ID From card
                ViewGroup parentView = (ViewGroup)v.getParent();
                TextView emailuserr =(TextView)parentView.findViewById(R.id.ImageemailTextView);
               emailuser = emailuserr.getText().toString();
               // PreferencesHelper.save(mContext, "idPatient", idPatient);


            }
        });*/



    }

    @Override
    public int getItemCount() {

        return dataAdapters.size();
    }


    @Override
    public Filter getFilter() {
        if(userFilter == null)
            userFilter = new UserFilter(this, dataAdapters);
        return userFilter;
    }

    private static class UserFilter extends Filter {

        private final RecyclerViewAdapter_users  adapter;

        private final List<DataAdapter_users> originalList;

        private final List<DataAdapter_users> filteredList;

        private UserFilter(RecyclerViewAdapter_users adapter, List<DataAdapter_users> originalList) {
            super();
            this.adapter = adapter;
            this.originalList = new LinkedList<>(originalList);
            this.filteredList = new ArrayList<>();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filteredList.clear();
            final FilterResults results = new FilterResults();

            if (constraint.length() == 0) {
                filteredList.addAll(originalList);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();

                for (final DataAdapter_users user : originalList) {
                    if (user.getUserEmail().contains(filterPattern)||user.getUserMobile().contains(filterPattern)) {
                        filteredList.add(user);
                    }
                }
            }
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            adapter.dataAdaptersfiltered.clear();
            adapter.dataAdaptersfiltered.addAll((ArrayList<DataAdapter_users>) results.values);
            adapter.notifyDataSetChanged();
        }
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