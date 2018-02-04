package patelheggere.trainsearch.acivity;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import patelheggere.trainsearch.R;
import patelheggere.trainsearch.adapter.FavouriteAdpter;
import patelheggere.trainsearch.data.DBHelper;
import patelheggere.trainsearch.model.Coordinate;
import patelheggere.trainsearch.model.From;
import patelheggere.trainsearch.model.FromTo;
import patelheggere.trainsearch.model.Location;
import patelheggere.trainsearch.model.Prognosis;
import patelheggere.trainsearch.model.Station;

public class FavouriteActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private FavouriteAdpter mFavouriteAdapter;
    private List<FromTo> mFromToList;
    private Gson mGson;
    private DBHelper mDbHelper;
    private SQLiteDatabase dbWrite,dbRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        initialize();
        setTitle("Favourite List");

    }
    private void initialize()
    {
        mFromToList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.rvfavourite);
        mFavouriteAdapter = new FavouriteAdpter(this,mFromToList);
        mRecyclerView.setAdapter(mFavouriteAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        getFavouriteList();
    }



    private void getFavouriteList()
    {
        List<String> connectionList= new ArrayList<>();
        mDbHelper = new DBHelper(this);
        dbRead = mDbHelper.getReadableDatabase();
        connectionList = DBHelper.readFromSQLDB(dbRead);
        dbRead.close();
        for (int i = 0; i<connectionList.size(); i++)
        {
            try {
                parseJsonObject(new JSONObject(connectionList.get(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        mFavouriteAdapter.notifyDataSetChanged();
    }



    private void parseJsonObject(JSONObject response)
    {
        mGson = new Gson();
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
            FromTo fromTo = new FromTo();

            fromTo.setFrom(fromObj);

            toObj = mGson.fromJson(response.getJSONArray("connections").getJSONObject(0).getJSONObject("to").toString(), From.class);
            //obj.setDeparture(response.getJSONArray("connections").getJSONObject(0).getString("departure"));
            prognosis = mGson.fromJson(response.getJSONArray("connections").getJSONObject(0).getJSONObject("to").getJSONObject("prognosis").toString(), Prognosis.class);
            coordinate1 = mGson.fromJson(response.getJSONArray("connections").getJSONObject(0).getJSONObject("to").getJSONObject("station").getJSONObject("coordinate").toString(), Coordinate.class);
            station = mGson.fromJson(response.getJSONArray("connections").getJSONObject(0).getJSONObject("to").getJSONObject("station").toString(), Station.class);
            toObj.setCoordinate1(coordinate1);
            toObj.setStation(station);
            toObj.setPrognosis(prognosis);

            fromTo.setTo(toObj);
            mFromToList.add(fromTo);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
