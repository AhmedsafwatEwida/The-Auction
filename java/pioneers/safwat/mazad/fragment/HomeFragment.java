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
import android.widget.TextView;

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
import pioneers.safwat.mazad.activities.DataAdapter2;
import pioneers.safwat.mazad.activities.RecyclerViewAdapter4itemusers;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private SwipeRefreshLayout swipeRefreshLayout;

    List<DataAdapter2> ListOfdataAdapter;

    // RecyclerView recyclerView;

    String HTTP_JSON_URL = "http://192.168.100.8/mazad/AuctionList.php";

    // String HTTP_JSON_URL = "http://192.168.43.215/mazad/AuctionList.php";
    // String HTTP_JSON_URL = "http://192.168.168.43/mazad/ItemJsonData.php";
    //  String HTTP_JSON_URL = "http://192.168.43.215/android_login_upload_images/ImageJsonData2.php";

    String Image_Auction_JSON = "auction";
    String Bid_Date_JSON = "biddate";
    String Bid_Hour_JSON = "bidhour";
    String Bid_Type_JSON = "type";
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
    String Image_Descr_JSON = "descr";
    List<String> IdList = new ArrayList<>();
    String Image_URL_JSON = "image_path1";
    String Image_URL_JSON1 = "image_path2";
    String Image_URL_JSON2 = "image_path3";

    String yearu,modelu,makeu,warnu,bdateu,btimeu,priceu,engineu,codeu,descu,statusu,millageu,typeu,image1,image2,image3;


    JsonArrayRequest RequestOfJSonArray ;

    RequestQueue requestQueue ;

    View view ;

    int RecyclerViewItemPosition ;

    RecyclerView.LayoutManager layoutManagerOfrecyclerView;

    RecyclerView.Adapter recyclerViewadapter;

    ArrayList<String> ImageTitleNameArrayListForClick;
    ArrayList<String> Image1;
    ArrayList<String> Image2;
    ArrayList<String> Image3;

    private HomeFragment.OnFragmentInteractionListener mListener;

    public HomeFragment() {
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

        Image1 = new ArrayList<>();
        Image2 = new ArrayList<>();
        Image3 = new ArrayList<>();

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
                    View vview = recyclerView.findContainingItemView( view);
                    TextView carmake = (TextView) vview.findViewById(R.id.ImagemakeTextView);
                    TextView carmodel = (TextView) vview.findViewById(R.id.ImagemodelTextView);
                    TextView carwaranty = (TextView) vview.findViewById(R.id.ImagewarnTextView);
                    TextView carmillage = (TextView) vview.findViewById(R.id.ImagemillageTextView);
                    TextView caryear = (TextView) vview.findViewById(R.id.ImageyearTextView);
                    TextView cartype = (TextView) vview.findViewById(R.id.ImagetypeTextView);
                    TextView biddate = (TextView) vview.findViewById(R.id.ImagebidateTextView);
                    TextView bidtime = (TextView) vview.findViewById(R.id.ImagebidhourTextView);
                    TextView itemcode = (TextView) vview.findViewById(R.id.ImageIDTextView);
                    TextView price = (TextView) vview.findViewById(R.id.ImagepriceTextView);
                    TextView carengine = (TextView) vview.findViewById(R.id.ImageengineTextView);
                    TextView descr = (TextView) vview.findViewById(R.id.ImagedescTextView);
                    TextView status = (TextView) vview.findViewById(R.id.ImagestatusTextView);
                    //Getting RecyclerView Clicked Item value.
                    RecyclerViewItemPosition = Recyclerview.getChildAdapterPosition(view);
                    Intent i = new Intent(getContext(), Bidone.class);
              //      i.putExtra("ListViewValue", IdList.get(RecyclerViewItemPosition).toString());

              //      makeu=carmake.getText().toString();
             //       modelu=carmodel.getText().toString();
             //       yearu=caryear.getText().toString();
             //       warnu=carwaranty.getText().toString();
                    bdateu=biddate.getText().toString();
                    btimeu=bidtime.getText().toString();
              //      millageu=carmillage.getText().toString();
                    statusu=status.getText().toString();
             //       descu=descr.getText().toString();
                    typeu=cartype.getText().toString();
            //        engineu=carengine.getText().toString();
                    priceu=price.getText().toString();
                    codeu=itemcode.getText().toString();
                    image1=Image1.get(RecyclerViewItemPosition);
                    image2=Image2.get(RecyclerViewItemPosition);
                    image3=Image3.get(RecyclerViewItemPosition);
                 /*   i.putExtra("makeValue", makeu);
                    i.putExtra("modelValue", modelu);
                    i.putExtra("yearValue", yearu);
                    i.putExtra("typeValue", typeu);
                    i.putExtra("stausValue", statusu);
                    i.putExtra("idValue", codeu);
                    i.putExtra("destValue", descu);
                    i.putExtra("dateValue", bdateu);
                    i.putExtra("timeValue", btimeu);
                    i.putExtra("engineValue", engineu);
                    i.putExtra("warnValue", warnu);
                    i.putExtra("millagValue", millageu);
                    i.putExtra("priceValue", priceu);
                    i.putExtra("image1", image1);
                    i.putExtra("image2", image2);
                    i.putExtra("image3", image3);*/


                   // startActivity(i);
                    // Showing RecyclerView Clicked Item value using Toast.
                 //   Toast.makeText(getContext(), ImageTitleNameArrayListForClick.get(RecyclerViewItemPosition), Toast.LENGTH_LONG).show();
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
        RequestOfJSonArray = new JsonArrayRequest(AppConfig.HTTP_JSON_URL_list,

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

            DataAdapter2 GetDataAdapter2 = new DataAdapter2();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);
                //   if (json.getString((Image_Auction_JSON)).equalsIgnoreCase("YES"))


                //  GetDataAdapter2.setImageTitle("Name:"+json.getString(Image_Name_JSON));
                GetDataAdapter2.setImageTitle(json.getString(Image_Name_JSON));
                GetDataAdapter2.setImagePrice(json.getString(Image_Price_JSON));
                GetDataAdapter2.setImagePrice1(json.getString(Image_Price_JSON1));
                GetDataAdapter2.setImagePrice2(json.getString(Image_Price_JSON2));
                GetDataAdapter2.setImagePrice3(json.getString(Image_Price_JSON3));
                GetDataAdapter2.setImagePrice4(json.getString(Image_Price_JSON4));
                GetDataAdapter2.setBidDate(json.getString(Bid_Date_JSON));
                GetDataAdapter2.setItemtype(json.getString(Bid_Type_JSON));
                GetDataAdapter2.setNoBid(json.getString(Image_NoBid_JSON));
                GetDataAdapter2.setItemStatus(json.getString(Bid_Status_JSON));
                GetDataAdapter2.setBidWin("Win1:"+json.getString(Bid_Winner_JSON));
                GetDataAdapter2.setBidWin1("Win2:"+json.getString(Bid_Winner_JSON1));
                GetDataAdapter2.setBidWin2("Win3:"+json.getString(Bid_Winner_JSON2));
                GetDataAdapter2.setBidWin3("Win4:"+json.getString(Bid_Winner_JSON3));
                GetDataAdapter2.setBidWin4("Win5:"+json.getString(Bid_Winner_JSON4));
                GetDataAdapter2.setItembidtime(json.getString(Bid_Hour_JSON));
                GetDataAdapter2.setItemAuction(json.getString(Image_Auction_JSON));
                GetDataAdapter2.setImageID(json.getString(Image_ID_JSON));
                GetDataAdapter2.setItemDescr(json.getString(Image_Descr_JSON));
                GetDataAdapter2.setNoBid(json.getString(Image_NoBid_JSON));
                if (Integer.parseInt(json.getString(Image_NoBid_JSON))==1)
                    GetDataAdapter2.setImagePrice(json.getString(Image_Price_JSON));
                else if (Integer.parseInt(json.getString(Image_NoBid_JSON))==2)
                    GetDataAdapter2.setImagePrice(json.getString(Image_Price_JSON1));
                else if (Integer.parseInt(json.getString(Image_NoBid_JSON))==3)
                    GetDataAdapter2.setImagePrice(json.getString(Image_Price_JSON2));
                else if (Integer.parseInt(json.getString(Image_NoBid_JSON))==4)
                    GetDataAdapter2.setImagePrice(json.getString(Image_Price_JSON3));
                else if (Integer.parseInt(json.getString(Image_NoBid_JSON))==5)
                    GetDataAdapter2.setImagePrice(json.getString(Image_Price_JSON4));

                IdList.add(json.getString("id").toString());

                // Adding image title name in array to display on RecyclerView click event.
                ImageTitleNameArrayListForClick.add(json.getString(Image_Name_JSON));

                //ImageTitleNameArrayListForClick.add(json.getString(Image_Name_JSON));
                GetDataAdapter2.setImageUrl(json.getString(Image_URL_JSON));
                GetDataAdapter2.setImageUrl1(json.getString(Image_URL_JSON1));
                GetDataAdapter2.setImageUrl2(json.getString(Image_URL_JSON2));
                Image1.add(json.getString(Image_URL_JSON));
                Image2.add(json.getString(Image_URL_JSON1));
                Image3.add(json.getString(Image_URL_JSON2));
                ListOfdataAdapter.add(GetDataAdapter2);

            } catch (JSONException e) {

                e.printStackTrace();
            }

            // ListOfdataAdapter.add(GetDataAdapter2);
            swipeRefreshLayout.setRefreshing(false);
        }

        recyclerViewadapter = new RecyclerViewAdapter4itemusers(ListOfdataAdapter, getContext());

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

