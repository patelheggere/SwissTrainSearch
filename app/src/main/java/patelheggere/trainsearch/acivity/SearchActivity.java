package patelheggere.trainsearch.acivity;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.Date;

import patelheggere.trainsearch.R;
import patelheggere.trainsearch.data.DBHelper;
import patelheggere.trainsearch.model.Coordinate;
import patelheggere.trainsearch.model.From;
import patelheggere.trainsearch.model.Prognosis;
import patelheggere.trainsearch.model.Station;
import patelheggere.trainsearch.singleton.MySingleton;
import patelheggere.trainsearch.utils.AppConstants;

public class SearchActivity extends AppCompatActivity {

    private EditText mEtFrom, mEtTo;
    private Button mSubmit,mkFavourite;
    private TextView mTvDepart, mTvArr;
    private Gson mGson;
    private DBHelper mDbHelper;
    private SQLiteDatabase dbWrite;
    private String ConnectionResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initializeUIComponents();
    }

    private void initializeUIComponents()
    {
        mEtFrom = findViewById(R.id.etFrom);
        mEtTo = findViewById(R.id.etTo);
        mSubmit = findViewById(R.id.submit);
        mkFavourite = findViewById(R.id.mkFavourite);
        mTvArr = findViewById(R.id.tvArrival);
        mTvDepart = findViewById(R.id.tvDepart);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchTrains();
            }
        });

        mkFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToFavouriteList();
            }
        });
    }

    private void SearchTrains()
    {
        if(!(mEtFrom.getText().toString().equals("")&& mEtTo.getText().toString().equals(""))  )
        {
            mGson =  new Gson();
            String URL= AppConstants.url + "from=" + mEtFrom.getText().toString().trim() + "&to=" + mEtTo.getText().toString().trim();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    ConnectionResult = response.toString();
                    From fromObj = new From();
                    From toObj = new From();
                    Coordinate coordinate1 = new Coordinate();
                    Prognosis prognosis = new Prognosis();
                    Station station = new Station();
                    try {
                        fromObj = mGson.fromJson(response.getJSONArray("connections").getJSONObject(0).getJSONObject("from").toString(), From.class);
                        //obj.setDeparture(response.getJSONArray("connections").getJSONObject(0).getString("departure"));
                        prognosis = mGson.fromJson(response.getJSONArray("connections").getJSONObject(0).getJSONObject("from").getJSONObject("prognosis").toString(), Prognosis.class);
                        coordinate1 = mGson.fromJson(response.getJSONArray("connections").getJSONObject(0).getJSONObject("from").getJSONObject("station").getJSONObject("coordinate").toString(), Coordinate.class);
                        station = mGson.fromJson(response.getJSONArray("connections").getJSONObject(0).getJSONObject("from").getJSONObject("station").toString(), Station.class);
                        fromObj.setCoordinate1(coordinate1);
                        fromObj.setStation(station);
                        fromObj.setPrognosis(prognosis);


                        toObj = mGson.fromJson(response.getJSONArray("connections").getJSONObject(0).getJSONObject("to").toString(), From.class);
                        //obj.setDeparture(response.getJSONArray("connections").getJSONObject(0).getString("departure"));
                        prognosis = mGson.fromJson(response.getJSONArray("connections").getJSONObject(0).getJSONObject("to").getJSONObject("prognosis").toString(), Prognosis.class);
                        coordinate1 = mGson.fromJson(response.getJSONArray("connections").getJSONObject(0).getJSONObject("to").getJSONObject("station").getJSONObject("coordinate").toString(), Coordinate.class);
                        station = mGson.fromJson(response.getJSONArray("connections").getJSONObject(0).getJSONObject("to").getJSONObject("station").toString(), Station.class);
                        toObj.setCoordinate1(coordinate1);
                        toObj.setStation(station);
                        toObj.setPrognosis(prognosis);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String mDepart= null;
                    String mArr= null;
                    if (fromObj.getStation()!=null || toObj.getStation()!=null) {
                        mDepart = "Platform: "+fromObj.getPlatform()+"\n\n Station Name: "+fromObj.getStation().getName()+"\n\n Departure Time: "+ timeStampToDate(fromObj.getDepartureTimestamp());
                        mArr = "Platform: "+toObj.getPlatform()+"\n\n Station Name: "+toObj.getStation().getName()+"\n\n  Arrival Time: "+ timeStampToDate(toObj.getArrivalTimestamp());
                        mDepart=mDepart.replace("null","Not Available");
                        mArr=mArr.replace("null","Not Available");
                        mTvDepart.setText(mDepart);
                        mTvArr.setText(mArr);
                    }
                    else{
                        Toast.makeText(SearchActivity.this, "Please enter Station names correctly", Toast.LENGTH_SHORT).show();

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            new MySingleton().getInstance().addToRequestQueue(jsonObjectRequest);

        }
        else {
            Toast.makeText(this, "from or To address cann't be empty", Toast.LENGTH_SHORT).show();

        }
    }

    private String timeStampToDate(Long ts)
    {
        Timestamp timestamp = new Timestamp(ts);
        Date dt = new Date(timestamp.getTime());
        String date=dt.toString().replace("GMT+5:30 1970","");
        return dt.toString();
    }

    private void addToFavouriteList()
    {
        mDbHelper = new DBHelper(this);
        dbWrite = mDbHelper.getWritableDatabase();
        if(ConnectionResult!=null)
            DBHelper.writeToSQLDB(dbWrite, ConnectionResult);
        dbWrite.close();
    }
}
