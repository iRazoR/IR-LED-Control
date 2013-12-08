package com.projects.irledcontrol2;

import java.lang.reflect.Method;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	
	static final int NUM_PAGES = 2;
	private String ircode = "";
    private ViewPager mViewPager;  
    private MyFragmentPagerAdapter mMyFragmentPagerAdapter;  
    private int type = 1;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);  
        mMyFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());  
        mViewPager.setAdapter(mMyFragmentPagerAdapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; Shows different types of remote controls.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	 public void setType(int typ){ // Set type, chosen from menu
		type = typ;
	}
		
	public int getType(){ // Get type, chosen from menu
		return type;
	}
	
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
		 // menu method to assign numbers to each type; sets "type" 1 for type A, 2 for type B and 3 for type C
	     switch(item.getItemId()) {
	     case R.id.item1:
	    	 setType(1);
	         break;
	         
	     case R.id.item2:
	    	 setType(2);
	         break;
	         
	     case R.id.item3:
	    	 setType(3);
	         break;
	     }
	     return true;
	 }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {  
    	// Fragment Pager Adapter Class creates Viewpager   
        public MyFragmentPagerAdapter(android.support.v4.app.FragmentManager fm) {  
             super(fm);  
        }

        public android.support.v4.app.Fragment getItem(int pos) {
        		FragmentRemote f = new FragmentRemote(pos);
            	return f;}

		@Override
        public int getCount() {
            return NUM_PAGES;
		}  
		
	    @Override
	    public CharSequence getPageTitle(int position) {
	    	// show title of current remote
	    	String currentRemote = "";
	    	switch (position) {
	        case 0:
	            currentRemote = "24 Keys";
	        break;
	        case 1:
	        	currentRemote = "44 Keys";
	        break;
	    	}
			return currentRemote;

	    }

    }
 
	public void setIRCode(String code){ // "Set" for variable "ircode"
		ircode = code;
	}
	
	public String getIRCode(){ // "Get" for variable "ircode"
		return ircode;
	}
	
	Handler RepeatActionHandler = new Handler() // RepeatActionHandler handles all IR codes and transfer it to IR interface
	{	
		
		public void handleMessage(Message msg)
		{
		
			try {
				Object irdaService = MainActivity.this.getSystemService("irda");
				Class c = irdaService.getClass();
				Class p[] = {String.class};
				Method write = c.getMethod("write_irsend", p);
				write.invoke(irdaService, getIRCode());
			} catch (Exception e) {
				Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
			}
		}
	};

	// onClickButton Methods for each button; method name consists of button function + remote type (44 keys or 24 keys)
	
	public void power44(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
		switch(getType()){
			case 1:
				setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,63,21");
				break;
			case 2:
				setIRCode("");
				break;
			case 3:
				setIRCode("");				
				break;
		}
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void brightUp44(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
		switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,21,21,63,21,21,21,63,21,63,21,21,21,21,21,21,21,63,21,21,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
		}
		RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void brightDown44(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,63,21,63,21,21,21,63,21,21,21,21,21,63,21,21,21,21,21,21,21,63,21,21,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
		}
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void red44(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,21,21,63,21,21,21,63,21,63,21,63,21,21,21,21,21,63,21,21,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void red244(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,21,21,63,21,21,21,63,21,63,21,21,21,63,21,21,21,63,21,21,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void red344(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,21,21,63,21,63,21,63,21,63,21,21,21,63,21,21,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
		}
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void red444(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,21,21,21,21,21,21,63,21,63,21,21,21,21,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void red544(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,21,21,21,21,21,21,63,21,63,21,63,21,21,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void green44(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,63,21,63,21,21,21,63,21,21,21,21,21,63,21,63,21,21,21,21,21,63,21,21,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void green244(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,21,21,63,21,21,21,63,21,21,21,21,21,63,21,21,21,63,21,21,21,63,21,21,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void green344(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,21,21,63,21,21,21,63,21,21,21,21,21,63,21,63,21,63,21,21,21,63,21,21,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void green444(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
		}
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void green544(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,63,21,63,21,21,21,21,21,21,21,21,21,63,21,63,21,21,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void blue44(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,21,21,21,21,21,21,63,21,21,21,21,21,63,21,21,21,63,21,63,21,63,21,21,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void blue244(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,63,21,21,21,21,21,63,21,21,21,21,21,63,21,63,21,21,21,63,21,63,21,21,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void blue344(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,63,21,21,21,21,21,63,21,21,21,21,21,63,21,21,21,21,21,63,21,63,21,21,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void blue444(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,63,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void blue544(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,63,21,21,21,21,21,21,21,63,21,21,21,63,21,21,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void white44(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,21,21,63,21,21,21,63,21,63,21,21,21,63,21,63,21,63,21,21,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void white244(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,63,21,21,21,63,21,63,21,63,21,21,21,63,21,63,21,21,21,63,21");
		switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,63,21,21,21,63,21,63,21,63,21,21,21,63,21,63,21,21,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
		}
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void white344(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,21,21,21,21,63,21,21,21,63,21,63,21,21,21,21,21,63,21,63,21,21,21,63,21");
		switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,21,21,21,21,63,21,21,21,63,21,63,21,21,21,21,21,63,21,63,21,21,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
		}
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void white444(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void white544(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,21,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void redUp44(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,21,21,21,21,21,21,63,21,63,21,21,21,63,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void redDown44(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void greenUp44(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,21,21,63,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void greenDown44(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,21,21,63,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void blueUp44(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,21,21,63,21,21,21,21,21,21,21,63,21,21,21,21,21,63,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void blueDown44(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,63,21,21,21,21,21,21,21,63,21,21,21,63,21,63,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void diy144(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,21,21,21,21,21,21,21,21,63,21,63,21,21,21,21,21,63,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void diy244(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,63,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void diy344(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void diy444(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,21,21,63,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void diy544(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,63,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,21,21,63,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void diy644(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,21,21,63,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void jump344(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,21,21,63,21,63,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void jump744(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,63,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void fade344(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void fade744(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void quick44(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,21,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void slow44(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,21,21,21,21,63,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void auto44(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void flash44(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,21,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void play44(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,21,21,63,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void powerOn24(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("37500,338,169,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,21,21");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void powerOff24(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("37500,338,169,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,63,21,63,21,21,21,21,21,21,21,21,21,63,21,63,21,21,21,21,21,63,21,63,21,63,21,63,21,21,21");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void brightUp24(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,63,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("37500,338,169,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,63,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,21,21,63,21,63,21,63,21,63,21,21,21");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void brightDown24(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,21,21,63,21,63,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("37500,338,169,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,63,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,21,21,63,21,63,21,63,21,63,21,21,21");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void strobe24(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,21,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("37500,338,169,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,63,21,21,21,21,21,63,21,21,21,21,21,21,21,63,21,21,21,63,21,63,21,21,21");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void flash24(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("37500,338,169,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,63,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,21,21");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void smooth24(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,21,21,21,21,63,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("37500,338,169,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,63,21,21,21,21,21,63,21,21,21,21,21,63,21,63,21,21,21,63,21,63,21,21,21");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void fade24(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,21,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("37500,338,169,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,63,21,63,21,21,21,21,21,63,21,21,21,21,21,63,21,21,21,21,21,63,21,63,21,21,21");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void white24(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,21,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("37500,338,169,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,63,21,21,21,21,21,21,21,63,21,21,21,21,21,63,21,21,21,63,21,63,21,63,21,21,21");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void red24(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,63,21,21,21,21,21,21,21,63,21,21,21,63,21,63,21,21,21,63,21,63,21,63,21,21,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void red224(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,63,21,63,21,21,21,21,21,21,21,63,21,21,21,63,21,21,21,21,21,63,21,63,21,63,21,21,21");
			break;
		case 2:
			setIRCode("");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void red324(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,21,21,63,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("37500,338,169,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,63,21,21,21,63,21,21,21,21,21,63,21,21,21,63,21,21,21,63,21,21,21,63,21,63,21,21,21");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void red424(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,63,21,63,21,21,21,21,21,21,21,21,21,63,21,63,21,21,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("37500,338,169,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,63,21,21,21,63,21,21,21,21,21,63,21,21,21,63,21,21,21,63,21,21,21,63,21,63,21,21,21");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void red524(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,21,21,63,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("37500,338,169,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,63,21,21,21,21,21,63,21,21,21,63,21,63,21,63,21,21,21,63,21,63,21,21,21");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void green24(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,21,21,63,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("37500,338,169,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,63,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,21,21,63,21,63,21,63,21,21,21");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void green224(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,21,21,21,21,21,21,21,21,63,21,63,21,21,21,21,21,63,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("37500,338,169,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,63,21,63,21,21,21,21,21,21,21,63,21,63,21,63,21,21,21,21,21,63,21,63,21,63,21,21,21");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void green324(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,21,21,21,21,21,21,63,21,63,21,21,21,63,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("37500,338,169,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,63,21,21,21,63,21,21,21,21,21,63,21,63,21,63,21,21,21,63,21,21,21,63,21,63,21,21,21");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void green424(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,21,21,21,21,21,21,63,21,63,21,63,21,21,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("37500,338,169,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,63,21,63,21,21,21,21,21,63,21,63,21,63,21,63,21,21,21,21,21,63,21,63,21,21,21");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void green524(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("37500,338,169,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,21,21,63,21,63,21,21,21");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void blue24(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,21,21,63,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("37500,338,169,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,63,21,21,21,63,21,21,21,21,21,21,21,63,21,63,21,21,21,63,21,21,21,63,21,63,21,63,21,21,21");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void blue224(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("37500,338,169,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,63,21,63,21,63,21,21,21,21,21,21,21,63,21,63,21,21,21,21,21,21,21,63,21,63,21,63,21,21,21");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void blue324(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,63,21,21,21,21,21,21,21,63,21,21,21,63,21,21,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("37500,338,169,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,63,21,63,21,21,21,63,21,21,21,21,21,63,21,63,21,21,21,21,21,63,21,21,21,63,21,63,21,21,21");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void blue424(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,63,21,63,21,21,21,21,21,21,21,63,21,21,21,63,21,21,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("37500,338,169,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,63,21,21,21,63,21,63,21,21,21,21,21,63,21,63,21,21,21,63,21,21,21,21,21,63,21,63,21,21,21");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}
	
	public void blue524(View view){
        Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(80);
        switch(getType()){
		case 1:
			setIRCode("37500,338,169,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,63,21,21,21,21,21,21,21,63,21,21,21,63,21,63,21,21,21,63,21,63,21,63,21");
			break;
		case 2:
			setIRCode("37500,338,169,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,63,21,63,21,63,21,63,21,21,21,63,21,21,21,21,21,63,21,21,21,21,21,63,21,63,21,21,21,63,21,63,21,21,21,63,21,63,21,21,21");
			break;
		case 3:
			setIRCode("");				
			break;
        }
        RepeatActionHandler.sendEmptyMessage(0);
	}

}
