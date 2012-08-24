package com.imaginnovate.simplecounter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class CounterActivity extends Activity implements OnClickListener {

    private TextView countView;
    private int count = 1;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        countView = (TextView) findViewById(R.id.count);
        countView.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_counter, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.count) {
			count++;
			countView.setText(Integer.toString(count));
		}
	}
	
	public void reset(View v) {
		count = 0;
		countView.setText(Integer.toString(count));
	}
	
	public void share(View v) {
		Intent sharingIntent = new Intent(Intent.ACTION_SEND);
		sharingIntent.setType("text/plain");
		sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Integer.toString(count));
		startActivity(Intent.createChooser(sharingIntent,"Share using"));
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		if(savedInstanceState.containsKey("CURR_COUNT")) {
			count = savedInstanceState.getInt("CURR_COUNT");
			countView.setText(Integer.toString(count));
		}
		super.onRestoreInstanceState(savedInstanceState);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putInt("CURR_COUNT", count);
		super.onSaveInstanceState(outState);
	}
}
