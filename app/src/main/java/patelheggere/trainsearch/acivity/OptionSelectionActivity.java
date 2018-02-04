package patelheggere.trainsearch.acivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import patelheggere.trainsearch.R;

public class OptionSelectionActivity extends AppCompatActivity {

    private Button mSearchBtn, mFavBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_selection);
        initializeSearchButton();
        initializeFavouriteButton();
    }
    private void initializeSearchButton()
    {
        mSearchBtn = findViewById(R.id.search_button);
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
            }
        });
    }
    private void initializeFavouriteButton()
    {
        mFavBtn = findViewById(R.id.favourite);
        mFavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FavouriteActivity.class));
            }
        });
    }
}
