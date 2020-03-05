package fi.karretti.anrturntracker;

import com.google.analytics.tracking.android.EasyTracker;

import fi.karretti.anrturntracker.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;


public class MainActivity extends Activity {
	private int side;
	public final static String EXTRA_SIDE = "fi.karretti.anrturntracker.SIDE";
	public final static String EXTRA_START = "fi.karretti.anrturntracker.START";
	
	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.side = 0;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new AppEULA(this).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
		
	public void startGame(View view){
    	//Do something
		Intent intent = new Intent(this, GameActivity.class);
		intent.putExtra(EXTRA_SIDE, this.side);
		intent.putExtra(EXTRA_START, "start");
		startActivity(intent);
	}
	
	public void continueGame(View view){
    	//Do something
		Intent intent = new Intent(this, GameActivity.class);
		intent.putExtra(EXTRA_SIDE, this.side);
		intent.putExtra(EXTRA_START, "continue");
		startActivity(intent);	
	}
	
	public void onRadioButtonClicked(View view){
		boolean checked = ((RadioButton) view).isChecked();
	    
	    // Check which radio button was clicked
	    switch(view.getId()) {
	        case R.id.radio_corp:
	            if (checked)
	                this.side = 0;
	            break;
	        case R.id.radio_runner:
	            if (checked)
	                this.side = 1;
	            break;
	    }

		
	}
	
	@Override
	public void onStart() {
		super.onStart();
	    // The rest of your onStart() code.
	    EasyTracker.getInstance(getApplicationContext()).activityStart(this); // Add this method.
	}

	@Override
	public void onStop() {
		super.onStop();
	    // The rest of your onStop() code.
	    EasyTracker.getInstance(getApplicationContext()).activityStop(this); // Add this method.
	}

}
