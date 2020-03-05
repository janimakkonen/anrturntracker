package fi.karretti.anrturntracker;


import java.lang.reflect.Field;

import com.google.analytics.tracking.android.EasyTracker;

import fi.karretti.anrturntracker.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameActivity extends Activity {
	
	static final String TURN_CLICKS = "fi.karretti.anrturntracker.turnClicks";
	static final String CLICKS = "fi.karretti.anrturntracker.Clicks";
	static final String BRAIN_DAMAGE = "fi.karretti.anrturntracker.brainDamge";
	static final String TAG_OR_BAD_PUB = "fi.karretti.anrturntracker.tagOrBadPub";
	static final String CREDITS = "fi.karretti.anrturntracker.credits";
	static final String CLICK_LOST = "fi.karretti.anrturntracker.clickLost";
	static final String SIDE = "fi.karretti.anrturntracker.side";
	
	public float[] corpX;
	public float[] corpY;
	public float[] runnerX;
	public float[] runnerY;
	
	private int side;
	private int clicks;
	private int turnClicks;
	private int brainDamage;
	private int tagOrBadPub;
	private int credits;
	private boolean clickLost;
	public boolean justLost;
	
	
	public boolean isClickLost() {
		return clickLost;
	}

	public void setClickLost(boolean clickLost) {
		this.clickLost = clickLost;
	}

	public int getBrainDamage() {
		return brainDamage;
	}

	public void setBrainDamage(int brainDamage) {
		this.brainDamage = brainDamage;
	}

	public int getTagOrBadPub() {
		return tagOrBadPub;
	}

	public void setTagOrBadPub(int tagOrBadPub) {
		this.tagOrBadPub = tagOrBadPub;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public int getTurnClicks() {
		return turnClicks;
	}

	public void setTurnClicks(int turnClics) {
		this.turnClicks = turnClics;
	}

	public int getClicks() {
		return clicks;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
    
	     // Probably initialize members with default values for a new instance
	    Intent intent = getIntent();
		this.side = intent.getIntExtra(MainActivity.EXTRA_SIDE, 0);

		newGame(this.side);

		getMenu();
	    // Show the Up button in the action bar.
		setupActionBar();
		setCoordinates();
	}
	
	
	public void getMenu(){
		try {
	        ViewConfiguration config = ViewConfiguration.get(this);
	        Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
	        if(menuKeyField != null) {
	            menuKeyField.setAccessible(true);
	            menuKeyField.setBoolean(config, false);
	        }
	    } catch (Exception ex) {
	        // Ignore
	    }
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {
		// Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        	getActionBar().setDisplayHomeAsUpEnabled(true);
        	//getActionBar().setDisplayShowHomeEnabled(false);
        }

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present. 
		getMenuInflater().inflate(R.menu.game, menu);
		return super.onCreateOptionsMenu(menu); 
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.start_new_game:
			askNewGame();
			return true;
		case R.id.lose_click_permanently:
			if(side == 0){
   				if(clicks > 2){
   					this.clicks -= 1;
   					clickLost = true;
   					justLost = true;
   				}
   				else{
   					AlertDialog.Builder builder = new AlertDialog.Builder(this);

   	       			// 2. Chain together various setter methods to set the dialog characteristics
   	       			builder.setMessage("You have " + this.clicks + " clicks. You can't lose them any more.").setTitle(R.string.dialog_cant_lose);
   	       			
   	       			// Add the buttons
   	       			builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
   	       			           public void onClick(DialogInterface dialog, int id) {      			             			   
   	       			        	     
   	       			           }
   	       			       });
   	       			AlertDialog dialog = builder.create();
   	       			dialog.show();
   				}
   			}
   			else{
   				if(clicks > 3){
   					this.clicks -= 1;
   					clickLost = true;
   					justLost = true;
   				}
   				else{
   					AlertDialog.Builder builder = new AlertDialog.Builder(this);

   	       			// 2. Chain together various setter methods to set the dialog characteristics
   	       			builder.setMessage("You have " + this.clicks + " clicks. You can't lose them any more.").setTitle(R.string.dialog_cant_lose);
   	       			
   	       			// Add the buttons
   	       			builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
   	       			           public void onClick(DialogInterface dialog, int id) {      			             			   
   	       			        	     
   	       			           }
   	       			       });
   	       			AlertDialog dialog = builder.create();
   	       			dialog.show();
   				}
   			}
			return true;
		case R.id.gain_click_permanently:
			if(side == 0){
   				if(clicks < 3){
   					this.clicks += 1;
   					if(this.turnClicks == 2){
   						this.turnClicks +=1;
   					}
   					clickLost = false;
   				}else{
   					AlertDialog.Builder builder = new AlertDialog.Builder(this);

   	       			// 2. Chain together various setter methods to set the dialog characteristics
   	       			builder.setMessage("You have " + this.clicks + " clicks. You can't gain them any more.").setTitle(R.string.dialog_cant_gain);
   	       			
   	       			// Add the buttons
   	       			builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
   	       			           public void onClick(DialogInterface dialog, int id) {      			             			   
   	       			        	     
   	       			           }
   	       			       });
   	       			AlertDialog dialog = builder.create();
   	       			dialog.show();
   				}
   			}
   			else{
   				if(clicks < 4){
   					if(this.turnClicks == 3){
   						this.turnClicks +=1;
   					}
   					this.clicks += 1;
   					clickLost = false;
   				}
   				else{
   					AlertDialog.Builder builder = new AlertDialog.Builder(this);

   	       			// 2. Chain together various setter methods to set the dialog characteristics
   	       			builder.setMessage("You have " + this.clicks + " clicks. You can't gain them any more.").setTitle(R.string.dialog_cant_gain);
   	       			
   	       			// Add the buttons
   	       			builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
   	       			           public void onClick(DialogInterface dialog, int id) {      			             			   
   	       			        	     
   	       			           }
   	       			       });
   	       			AlertDialog dialog = builder.create();
   	       			dialog.show();
   				}
   			}
			return true;
			
			
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void askNewGame() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

  		// 2. Chain together various setter methods to set the dialog characteristics
  		builder.setMessage("Choose your side.").setTitle("New game");
  			
  		// Add the buttons
  		builder.setPositiveButton("Corporation",
  	            new DialogInterface.OnClickListener() {
  	                public void onClick(DialogInterface dialog, int id) {
  	                    newGame(0);

  	                }
  	            });

  	    builder.setNeutralButton("Runner",
  	            new DialogInterface.OnClickListener() {
  	                public void onClick(DialogInterface dialog, int id) {
  	                	newGame(1);
  	                   
  	                    //dialog.cancel();

  	                }

  	            });

  	    builder.setNegativeButton("Cancel",
  	            new DialogInterface.OnClickListener() {
  	                public void onClick(DialogInterface dialog, int id) {
  	                    dialog.cancel();

  	                }
  	            });
  		AlertDialog dialog = builder.create();
  		dialog.show();
	}
	
	private void newGame(int side) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_game);
		this.side = side;
		this.brainDamage = 0;
		this.credits = 5;
		this.tagOrBadPub = 0;
		this.clickLost = false;
		this.justLost = false;
		
		if(this.side == 0){
			this.clicks = 3;
			this.turnClicks = this.clicks;
			
		}
		else if(this.side == 1){
			this.clicks = 4;
			this.turnClicks = this.clicks;
			
		}
		showGameState();
		showTrackerCard();
	}
	
	@Override
	public void onStart() {
		super.onStart();
		// The rest of your onStart() code.
	    EasyTracker.getInstance(getApplicationContext()).activityStart(this); // Add this method.
	}
	
	@Override
	protected void onStop() {
	    super.onStop();  // Always call the superclass method first

	    SharedPreferences preferences = getPreferences(MODE_PRIVATE);
	    SharedPreferences.Editor editor = preferences.edit();
	    editor.putInt(TURN_CLICKS, this.turnClicks); // value to store
	    editor.putInt(CLICKS, this.clicks); // value to store
	    editor.putInt(BRAIN_DAMAGE, this.brainDamage);
	    editor.putInt(CREDITS, this.credits);
	    editor.putInt(TAG_OR_BAD_PUB, tagOrBadPub);
	    editor.putInt(SIDE, side);
	    editor.putBoolean(CLICK_LOST, clickLost);
	    editor.commit();
	    EasyTracker.getInstance(getApplicationContext()).activityStop(this);
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		String start;
		Intent intent = getIntent();
		start = (String)intent.getStringExtra(MainActivity.EXTRA_START);
		
		if (start.equals("start")){
			
		}
		else if(start.equals("continue")){
			int value;
			value = this.side+3;
			SharedPreferences preferences = getPreferences(MODE_PRIVATE);
			this.turnClicks = preferences.getInt(TURN_CLICKS, value);
			this.clicks = preferences.getInt(CLICKS, value);
			this.brainDamage = preferences.getInt(BRAIN_DAMAGE, 0);
			this.credits = preferences.getInt(CREDITS, 5);
			this.tagOrBadPub = preferences.getInt(TAG_OR_BAD_PUB, 0);
			this.clickLost = preferences.getBoolean(CLICK_LOST, false);
			this.side = preferences.getInt(SIDE, 0);
			showGameState();
			showTrackerCard();
			setCoordinates();
			animateOnResume();
		}
		else{
		
		}
		
	}
	
	public void buttonPushed(View view){
		switch(view.getId()) {
       		case R.id.button_lose_click:
       			if (this.turnClicks > 0){
       				animate(0);
       				this.turnClicks -= 1;
       				
       			}
                break;
       		case R.id.button_gain_click:
       			if(side == 0 && clicks <= 3){
       				if(clicks > turnClicks || justLost){
       					
       					this.turnClicks += 1;
       					
           				animate(1);
           				if(this.turnClicks > clicks){
       						this.turnClicks = clicks;
       					}
                        
           			}
       			}
       			else if(side == 1 && clicks <= 4){
       				if(clicks > turnClicks || justLost){
       					this.turnClicks += 1;
       					
           				animate(1);
           				if(this.turnClicks > clicks){
       						this.turnClicks = clicks;
       					}
                        
           			}
       			}
       			
                break;
       		case R.id.button_start_new_turn:
       			AlertDialog.Builder builder = new AlertDialog.Builder(this);

       			// 2. Chain together various setter methods to set the dialog characteristics
       			builder.setMessage("Are you sure? You still have " + this.turnClicks + "/" + this.clicks + " clicks left.").setTitle(R.string.dialog_title);
       			
       			// Add the buttons
       			builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
       			           public void onClick(DialogInterface dialog, int id) {      			             			   
       			        	   animateStartTurn();
       			        	   turnClicks = clicks;   
       			           }
       			       });
       			builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
       			           public void onClick(DialogInterface dialog, int id) {
       			               // User cancelled the dialog
       			           }
       			       });

       			// 3. Get the AlertDialog from create()
       			if (this.turnClicks > 0){
       				AlertDialog dialog = builder.create();
       				dialog.show();
       			}       			
       			else{
       				animate(0);
       				this.turnClicks = this.clicks;
       			}
       			break;
       		case R.id.button_gain_brain_damage:
       			this.brainDamage += 1;
       			break;
       		case R.id.button_lose_brain_damage:
       			if(this.brainDamage > 0){
       				this.brainDamage -= 1;
       			}
       			break;
       		case R.id.button_gain_credit:
       			
       			this.credits += 1;
       			
       			break;
       		case R.id.button_lose_credit:
       			if(this.credits > 0){
       				this.credits -= 1;
       			}
       			break;
       		case R.id.button_gain_tag_or_bad_publicity:
       			this.tagOrBadPub += 1;
       			break;
       		case R.id.button_lose_tag_or_bad_publicity:
       			if(this.tagOrBadPub > 0){
       				this.tagOrBadPub -= 1;
       			}
       			break;
		}
		showGameState();
		
	}
	
	public void showGameState(){
		//Show brain damage
		TextView textView = (TextView)findViewById(R.id.brain_damage);
		textView.setText(Integer.toString(this.brainDamage));
		//Show credits
		textView = (TextView)findViewById(R.id.credits);
		textView.setText(Integer.toString(this.credits));
		textView = (TextView)findViewById(R.id.creditsToOpponent);
		textView.setText(Integer.toString(this.credits));
		//Show tags or bad pubs
		textView = (TextView)findViewById(R.id.tags_or_bad_publicity);
		textView.setText(Integer.toString(this.tagOrBadPub));
		
		ImageView imageView = (ImageView)findViewById(R.id.tag_or_bad_pub_image);
		if(this.side == 0){
			imageView.setImageResource(R.drawable.bad_publicity_symbol);
		}
		else if(this.side == 1){
			imageView.setImageResource(R.drawable.tag_symbol);
		}
		
		
		
	}
	public void showTrackerCard(){
		LinearLayout layout = (LinearLayout)findViewById(R.id.tracker_background);
		if(this.side == 0){
			layout.setBackgroundResource(R.drawable.corp_click_tracker);
		}
		else if(this.side == 1){
			layout.setBackgroundResource(R.drawable.runner_click_tracker);
		}
	}
	
	public void animateOnResume(){
		ImageView clickTrackerImage = (ImageView) findViewById(R.id.click_tracker);
		
		TranslateAnimation moveAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
		if(this.side == 1){
			if (this.turnClicks == 0){
				moveAnimation = new TranslateAnimation(0.0f, runnerX[0], 0.0f, runnerY[0]);
			}
			else if (this.turnClicks == 1){
				moveAnimation = transAnim(0.0f, runnerX[1], 0.0f, runnerY[1]);
			}
			else if(this.turnClicks == 2){
				moveAnimation = transAnim(0.0f, runnerX[2], 0.0f, runnerY[2]);
			}
			else if(this.turnClicks == 3){
				moveAnimation = transAnim(0.0f, runnerX[3], 0.0f, runnerY[3]);
			}
			else if(this.turnClicks == 4){
				moveAnimation = transAnim(0.0f, 0.0f, 0.0f, 0.0f);
			}
		}
		else if(this.side == 0){
			if (this.turnClicks == 0){
				moveAnimation = transAnim(0.0f, corpX[0], 0.0f, corpY[0]);
			}
			else if (this.turnClicks == 1){
				moveAnimation = transAnim(0.0f, corpX[1], 0.0f, corpY[1]);
			}
			else if(this.turnClicks == 2){
				moveAnimation = transAnim(0.0f, corpX[2], 0.0f, corpY[2]);
			}
			else if(this.turnClicks == 3){
				moveAnimation = transAnim(0.0f, 0.0f, 0.0f, 0.0f);
			}
		}
		moveAnimation.setFillAfter(true);
		moveAnimation.setDuration(400);
		
		clickTrackerImage.startAnimation(moveAnimation);
	}
	public void animateStartTurn(){
		ImageView clickTrackerImage = (ImageView) findViewById(R.id.click_tracker);
		
		TranslateAnimation moveAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
		if(this.side == 1){
			if (this.turnClicks == 0){
				moveAnimation = new TranslateAnimation(runnerX[0], 0.0f, runnerY[0], 0.0f);
			}
			else if (this.turnClicks == 1){
				moveAnimation = transAnim(runnerX[1], 0.0f, runnerY[1], 0.0f);
			}
			else if(this.turnClicks == 2){
				moveAnimation = transAnim(runnerX[2], 0.0f, runnerY[2], 0.0f);
			}
			else if(this.turnClicks == 3){
				moveAnimation = transAnim(runnerX[3], 0.0f, runnerY[3], 0.0f);
			}
			else if(this.turnClicks == 4){
				moveAnimation = transAnim(0.0f, 0.0f, 0.0f, 0.0f);
			}
		}
		else if(this.side == 0){
			if (this.turnClicks == 0){
				moveAnimation = transAnim(corpX[0], 0.0f, corpY[0], 0.0f);
			}
			else if (this.turnClicks == 1){
				moveAnimation = transAnim(corpX[1], 0.0f, corpY[1], 0.0f);
			}
			else if(this.turnClicks == 2){
				moveAnimation = transAnim(corpX[2], 0.0f, corpY[2], 0.0f);
			}
			else if(this.turnClicks == 3){
				moveAnimation = transAnim(0.0f, 0.0f, 0.0f, 0.0f);
			}
		}
		moveAnimation.setFillAfter(true);
		moveAnimation.setDuration(400);
		
		clickTrackerImage.startAnimation(moveAnimation);
	}
	
	public void setCoordinates(){
		corpX = new float[3];
		corpY = new float[3];
		runnerX = new float[4];
		runnerY = new float[4];
		float density;
		density = getResources().getDisplayMetrics().density;
		
		corpX[2] = 52.0f*density;
		corpX[1] = 91.0f*density;
		corpX[0] = 130.5f*density;
		corpY[2] = 54.0f*density;
		corpY[1] = 76.0f*density;
		corpY[0] = 54.0f*density;
		runnerX[3] = 31.5f*density;
		runnerX[2] = 70.5f*density;
		runnerX[1] = 109.5f*density;
		runnerX[0] = 149.0f*density;
		runnerY[3] = 51.0f*density;
		runnerY[2] = 73.0f*density;
		runnerY[1] = 51.0f*density;
		runnerY[0] = 72.0f*density;
		
	}
	
	public void animate(int suunta){
		ImageView clickTrackerImage = (ImageView) findViewById(R.id.click_tracker);
		TranslateAnimation moveAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
	
		if(this.side == 1){
			if (this.turnClicks == 0){
				moveAnimation = new TranslateAnimation(runnerX[0], 0.0f, runnerY[0], 0.0f);
			}
			else if (this.turnClicks == 1){
				moveAnimation = transAnim(runnerX[1], runnerX[0], runnerY[1], runnerY[0]);
			}
			else if(this.turnClicks == 2){
				moveAnimation = transAnim(runnerX[2], runnerX[1], runnerY[2], runnerY[1]);
			}
			else if(this.turnClicks == 3){
				if(clickLost && !justLost || suunta == 1 && clickLost) {
					moveAnimation = transAnim(0.0f, runnerX[2], 0.0f, runnerY[2]);
				}
				else{
					moveAnimation = transAnim(runnerX[3], runnerX[2], runnerY[3], runnerY[2]);
				}
				
			}
			else if(this.turnClicks == 4){
				if(clickLost && suunta == 0){
					moveAnimation = transAnim(0.0f, runnerX[2], 0.0f, runnerY[2]);
					turnClicks -= 1;
				}
				else{					
					moveAnimation = transAnim(0.0f, runnerX[3], 0.0f, runnerY[3]);
				}
				
			}
		}
		else if(this.side == 0){
			if (this.turnClicks == 0){
				moveAnimation = transAnim(corpX[0], 0.0f, corpY[2], 0.0f);
			}
			else if (this.turnClicks == 1){
				moveAnimation = transAnim(corpX[1], corpX[0], corpY[1], corpY[2]);
			}
			else if(this.turnClicks == 2){
				if(clickLost && !justLost || suunta == 1 && clickLost){
					moveAnimation = transAnim(0.0f, corpX[1], 0.0f, corpY[1]);
				}
				else{
					moveAnimation = transAnim(corpX[2], corpX[1], corpY[2], corpY[1]);
				}
				
			}
			else if(this.turnClicks == 3){
				if(clickLost && suunta == 0){
					moveAnimation = transAnim(0.0f, corpX[1], 0.0f, corpY[1]);
					turnClicks -= 1;
				}
				else {
					moveAnimation = transAnim(0.0f, corpX[2], 0.0f, corpY[2]);
				}
			}
		}
		moveAnimation.setFillAfter(true);
		moveAnimation.setDuration(400);
		justLost = false;
		if(suunta == 0){
		
		}
		else if(suunta == 1){
			moveAnimation.setInterpolator(new ReverseInterpolator());
		}
		clickTrackerImage.startAnimation(moveAnimation);
	}
		
	public TranslateAnimation transAnim(float fromX, float toX, float fromY, float toY){
		TranslateAnimation moveAnimation = new TranslateAnimation(TranslateAnimation.ABSOLUTE, fromX,
				TranslateAnimation.ABSOLUTE, toX, TranslateAnimation.ABSOLUTE,
				fromY, TranslateAnimation.ABSOLUTE, toY);
		return moveAnimation;
	}

	

}
