package pioneers.safwat.mazad.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pioneers.safwat.mazad.R;
import pioneers.safwat.mazad.activities.AppConfig;
import pioneers.safwat.mazad.activities.Bidone;
import pioneers.safwat.mazad.activities.DataAdapter;
import pioneers.safwat.mazad.activities.RecyclerViewAdapteritemsuser;

/**
 * Created by safwa on 12/11/2017.
 */

public class CarsFragment2 extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SwipeRefreshLayout swipeRefreshLayout;

    List<DataAdapter> ListOfdataAdapter;

    // RecyclerView recyclerView;

   // String HTTP_JSON_URL = "http://192.168.100.8/mazad/AuctionListcar.php";
   // String HTTP_JSON_URL = "http://192.168.43.215/mazad/AuctionListcar.php";
    //  String HTTP_JSON_URL = "http://192.168.43.215/mazad/AuctionList.php";
    // String HTTP_JSON_URL = "http://192.168.168.43/mazad/ItemJsonData.php";
    //  String HTTP_JSON_URL = "http://192.168.43.215/android_login_upload_images/ImageJsonData2.php";

    String Image_Auction_JSON = "auction";
    String Bid_Date_JSON = "biddate";
    String Bid_Winner_JSON = "bidwin";
    String Bid_Winner_JSON1 = "bidwin1";
    String Bid_Winner_JSON2 = "bidwin2";
    String Bid_Winner_JSON3 = "bidwin3";
    String Bid_Winner_JSON4 = "bidwin4";
    String Bid_Status_JSON = "status";
    String Image_Price_JSON = "price";
    String Image_Price_JSON1 = "price1";
    String Image_Price_JSON2 = "price2";
    String Image_Price_JSON3 = "price3";
    String Image_Price_JSON4 = "price4";
    String Image_NoBid_JSON = "nobid";
    String Image_Name_JSON = "email";
    String Image_ID_JSON = "id";
    String Bid_Hour_JSON = "bidhour";
    String Bid_Type_JSON = "type";
    String Image_Make_JSON = "carmake";
    String Image_Model_JSON = "carmodel";
    String Image_Year_JSON = "caryear";
    String Image_Warn_JSON = "carwaranty";
    String Image_Millage_JSON = "carmillage";
    String Image_Cartypr_JSON = "cartype";
    String Image_Engine_JSON = "carengine";
    String Image_Bidtime_JSON = "bidhour";
    String Image_Descr_JSON = "descr";
    List<String> IdList = new ArrayList<>();
    String Image_URL_JSON = "image_path1";

    JsonArrayRequest RequestOfJSonArray ;

    RequestQueue requestQueue ;

    View view ;

    int RecyclerViewItemPosition ;

    RecyclerView.LayoutManager layoutManagerOfrecyclerView;

    RecyclerView.Adapter recyclerViewadapter;

    ArrayList<String> ImageTitleNameArrayListForClick;

    private HomeFragment.OnFragmentInteractionListener mListener;

    public CarsFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_home, container, false);
        ImageTitleNameArrayListForClick = new ArrayList<>();

        ListOfdataAdapter = new ArrayList<>();
        this.recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        JSON_HTTP_CALL();
                                    }
                                }
        );


        //  recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);

        recyclerView.setHasFixedSize(true);

        layoutManagerOfrecyclerView = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManagerOfrecyclerView);

        // JSON_HTTP_CALL();

        // Implementing Click Listener on RecyclerView.
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

                view = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if(view != null && gestureDetector.onTouchEvent(motionEvent)) {

                    //Getting RecyclerView Clicked Item value.
                    RecyclerViewItemPosition = Recyclerview.getChildAdapterPosition(view);
                    Intent i = new Intent(getContext(), Bidone.class);
                    i.putExtra("ListViewValue", IdList.get(RecyclerViewItemPosition).toString());
                    startActivity(i);
                    // Showing RecyclerView Clicked Item value using Toast.
               //     Toast.makeText(getContext(), ImageTitleNameArrayListForClick.get(RecyclerViewItemPosition), Toast.LENGTH_LONG).show();
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });


        return v;

    }
    public void JSON_HTTP_CALL(){
        swipeRefreshLayout.setRefreshing(true);
        RequestOfJSonArray = new JsonArrayRequest(AppConfig.HTTP_JSON_URL_listcar,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response == null )
                            return;
                        ListOfdataAdapter.clear();//to resset adapter each time
                        ParseJSonResponse(response);
                        swipeRefreshLayout.setRefreshing(false);

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

        requestQueue = Volley.newRequestQueue(getContext());

        requestQueue.add(RequestOfJSonArray);

    }
    public void ParseJSonResponse(JSONArray array){
        //  swipeRefreshLayout.setRefreshing(false);
        for(int i = 0; i<array.length(); i++) {

            DataAdapter GetDataAdapter2 = new DataAdapter();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);
                //   if (json.getString((Image_Auction_JSON)).equalsIgnoreCase("YES"))


                GetDataAdapter2.setImageTitle("Name:"+json.getString(Image_Name_JSON));
                GetDataAdapter2.setImagePrice("Price:"+json.getString(Image_Price_JSON));
                GetDataAdapter2.setImagePrice1("Price:"+json.getString(Image_Price_JSON1));
                GetDataAdapter2.setImagePrice2("Price:"+json.getString(Image_Price_JSON2));
                GetDataAdapter2.setImagePrice3("Price:"+json.getString(Image_Price_JSON3));
                GetDataAdapter2.setImagePrice4("Price:"+json.getString(Image_Price_JSON4));
                GetDataAdapter2.setBidDate("Bid Date:"+json.getString(Bid_Date_JSON));
                GetDataAdapter2.setItemType("Item Type:"+json.getString(Bid_Type_JSON));
                GetDataAdapter2.setNoBid("Bid:"+json.getString(Image_NoBid_JSON));
                GetDataAdapter2.setItemStatus("Status:"+json.getString(Bid_Status_JSON));
                GetDataAdapter2.setBidWin("Win1:"+json.getString(Bid_Winner_JSON));
                GetDataAdapter2.setBidWin1("Win2:"+json.getString(Bid_Winner_JSON1));
                GetDataAdapter2.setBidWin2("Win3:"+json.getString(Bid_Winner_JSON2));
                GetDataAdapter2.setBidWin3("Win4:"+json.getString(Bid_Winner_JSON3));
                GetDataAdapter2.setBidWin4("Win5:"+json.getString(Bid_Winner_JSON4));
                GetDataAdapter2.setItemHour("Bid Time:"+json.getString(Bid_Hour_JSON));
                GetDataAdapter2.setItemAuction("Auction:"+json.getString(Image_Auction_JSON));
                GetDataAdapter2.setImageID("Item Code#:"+json.getString(Image_ID_JSON));
                GetDataAdapter2.setItemDescr("Description:"+json.getString(Image_Descr_JSON));
                GetDataAdapter2.setNoBid("NO.of Bids:"+json.getString(Image_NoBid_JSON));
                if (Integer.parseInt(json.getString(Image_NoBid_JSON))==1)
                    GetDataAdapter2.setImagePrice("Price:"+json.getString(Image_Price_JSON));
                else if (Integer.parseInt(json.getString(Image_NoBid_JSON))==2)
                    GetDataAdapter2.setImagePrice("Price:"+json.getString(Image_Price_JSON1));
                else if (Integer.parseInt(json.getString(Image_NoBid_JSON))==3)
                    GetDataAdapter2.setImagePrice("Price:"+json.getString(Image_Price_JSON2));
                else if (Integer.parseInt(json.getString(Image_NoBid_JSON))==4)
                    GetDataAdapter2.setImagePrice("Price:"+json.getString(Image_Price_JSON3));
                else if (Integer.parseInt(json.getString(Image_NoBid_JSON))==5)
                    GetDataAdapter2.setImagePrice("Price:"+json.getString(Image_Price_JSON4));

                IdList.add(json.getString("id").toString());
                // Adding image title name in array to display on RecyclerView click event.
                ImageTitleNameArrayListForClick.add(json.getString(Image_Name_JSON));
                //ImageTitleNameArrayListForClick.add(json.getString(Image_Name_JSON));
                GetDataAdapter2.setImageUrl(json.getString(Image_URL_JSON));
                ListOfdataAdapter.add(GetDataAdapter2);

            } catch (JSONException e) {

                e.printStackTrace();
            }

            // ListOfdataAdapter.add(GetDataAdapter2);
            swipeRefreshLayout.setRefreshing(false);
        }

        recyclerViewadapter = new RecyclerViewAdapteritemsuser(ListOfdataAdapter, getContext());

        recyclerView.setAdapter(recyclerViewadapter);

    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomeFragment.OnFragmentInteractionListener) {
            mListener = (HomeFragment.OnFragmentInteractionListener) context;
        } else {
          /*  throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");*/
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onRefresh() {
        JSON_HTTP_CALL();
        swipeRefreshLayout.setRefreshing(false);
    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
