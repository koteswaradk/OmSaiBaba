package com.skyries.saibaba;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	private static final int SCREEN_ORIENTATION_PORTRAIT = 0;
	int mPosition = -1;
	String mTitle = "";
	private MediaPlayer mp = null;
	// Array of strings storing country names
	String[] mCountries;
	private InterstitialAd interstitial;
	// Array of integers points to images stored in /res/drawable-ldpi/
	int[] mFlags = new int[] { R.drawable.sai_baba, R.drawable.sai_baba,
			R.drawable.sai_baba, R.drawable.sai_baba, R.drawable.sai_baba,
			R.drawable.sai_baba, R.drawable.sai_baba, R.drawable.sai_baba,
			R.drawable.sai_baba, R.drawable.sai_baba, R.drawable.sai_baba,R.drawable.sai_baba,
			R.drawable.sai_baba, R.drawable.sai_baba,R.drawable.sai_baba };

	// Array of strings to initial counts
	//String[] mCount = new String[] { "", "", "", "", "", "", "", "", "", "" };

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private LinearLayout mDrawer;
	private List<HashMap<String, String>> mList;
	private SimpleAdapter mAdapter;
	final private String TEXT = "country";
	final private String ICON = "flag";

	// final private String COUNT = "count";

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mp = MediaPlayer.create(this, R.raw.sada);
		// Getting an array of country names
		mCountries = getResources().getStringArray(R.array.nav_drawer_items);

		// Title of the activity
		mTitle = (String) getTitle();

		// Getting a reference to the drawer listview
		mDrawerList = (ListView) findViewById(R.id.drawer_list);

		// Getting a reference to the sidebar drawer ( Title + ListView )
		mDrawer = (LinearLayout) findViewById(R.id.drawer);

		// Each row in the list stores country name, count and flag
		mList = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < 15; i++) {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(TEXT, mCountries[i]);
			// hm.put(COUNT, mCount[i]);
			hm.put(ICON, Integer.toString(mFlags[i]));
			mList.add(hm);
		}

		// Keys used in Hashmap
		String[] from = { ICON, TEXT };

		// Ids of views in listview_layout
		int[] to = { R.id.icon, R.id.text };

		// Instantiating an adapter to store each items
		// R.layout.drawer_layout defines the layout of each item
		mAdapter = new SimpleAdapter(this, mList, R.layout.drawer_layout, from,to);

		// Getting reference to DrawerLayout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		// Creating a ToggleButton for NavigationDrawer with drawer event
		// listener
		
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.string.app_name, R.string.drawer_open) {

			/** Called when drawer is closed */
			public void onDrawerClosed(View view) {
				highlightSelectedCountry();
				supportInvalidateOptionsMenu();
				
				
				
			}

			/** Called when a drawer is opened */
			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle("Select Your Options");
				supportInvalidateOptionsMenu();
			}
		};

		// Setting event listener for the drawer
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		// ItemClick event handler for the drawer items
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				// Increment hit count of the drawer list item
				// incrementHitCount(position);

				showFragment(position);

				/*
				 * if(position < 5) { // Show fragment for countries : 0 to 4
				 * showFragment(position); }else{ // Show message box for
				 * countries : 5 to 9 Toast.makeText(getApplicationContext(),
				 * mCountries[position], Toast.LENGTH_LONG).show(); }
				 */

				// Closing the drawer
				mDrawerLayout.closeDrawer(mDrawer);
			}
		});

		// Enabling Up navigation
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		getSupportActionBar().setDisplayShowHomeEnabled(true);

		// Setting the adapter to the listView
		mDrawerList.setAdapter(mAdapter);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			showFragment(0);
		}
		// playsong();
	}

	private void playsong() {
		// TODO Auto-generated method stub
		// System.out.println("Starting song...");
		mp.start();
		// System.out.println("Playing music...");
	}

	private void stopsong() {
		// System.out.println("Stopping...");
		mp.stop();
		mp.reset();
		mp.release();
		mp = null;

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if (mp != null && mp.isPlaying()) {
			stopsong();
		}

		// finish();
		super.onPause();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

	/*
	 * public void incrementHitCount(int position){ HashMap<String, String> item
	 * = mList.get(position); String count = item.get(COUNT);
	 * item.remove(COUNT); if(count.equals("")){ count = "  1  "; }else{ int cnt
	 * = Integer.parseInt(count.trim()); cnt ++; count = "  " + cnt + "  "; }
	 * item.put(COUNT, count); mAdapter.notifyDataSetChanged(); }
	 */

	public void showFragment(int position) {

		Fragment fragment = null;

		switch (position) {
		case 0:
			fragment = new ManthraFragment();
			break;
		case 1:
			fragment = new AarathiFragment();
			break;
		case 2:
			fragment = new BhajanaFragment();
			break;
		case 3:
			fragment = new OmSaiNathayaNamahaFragment();
			break;
		case 4:
			fragment = new AshtotharaNamavaliFragment();
			break;
		case 5:
			fragment = new KakadaAaratiFragment();
			break;
		case 6:
			fragment = new QuotesAndSayingFragment();
			break;
		case 7:
			fragment = new SacharithaFragment();
			break;
		case 8:
			fragment = new AssuranceFragment();
			break;
		case 9:
			fragment = new OtherMantraFragment();
			break;
		case 10:
			fragment = new MiracleFragment();
			break;
		case 11:
			fragment = new WallPapersFragments();
			break;
		case 12:
			fragment = new BabaRarepicsFragment();
			break;
		case 13:
			fragment = new MusiumFragments();
			break;
		case 14:
			fragment = new AboutAppFragment();
			break;

		}

		if (fragment != null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment).commit();

			// update selected item and title, then close the drawer
			// mDrawerList.setItemChecked(position, true);
			// mDrawerList.setSelection(position);
			mTitle = mCountries[position];
			// mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			// Log.e("MainActivity", "Error in creating fragment");
		}
		/*
		 * //Currently selected country mTitle = mCountries[position];
		 * 
		 * // Creating a fragment object CountryFragment cFragment = new
		 * CountryFragment();
		 * 
		 * // Creating a Bundle object Bundle data = new Bundle();
		 * 
		 * // Setting the index of the currently selected item of mDrawerList
		 * data.putInt("position", position);
		 * 
		 * // Setting the position to the fragment cFragment.setArguments(data);
		 * 
		 * // Getting reference to the FragmentManager FragmentManager
		 * fragmentManager = getSupportFragmentManager();
		 * 
		 * // Creating a fragment transaction FragmentTransaction ft =
		 * fragmentManager.beginTransaction();
		 * 
		 * // Adding a fragment to the fragment transaction
		 * ft.replace(R.id.content_frame, fragment);
		 * 
		 * // Committing the transaction ft.commit();
		 */
	}

	// Highlight the selected country : 0 to 4
	public void highlightSelectedCountry() {
		int selectedItem = mDrawerList.getCheckedItemPosition();

		/*
		 * if(selectedItem > 4) mDrawerList.setItemChecked(mPosition, true);
		 * else
		 */
		mPosition = selectedItem;

		if (mPosition != -1)
			getSupportActionBar().setTitle(mCountries[mPosition]);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	/*
	 * @Override public boolean onPrepareOptionsMenu(Menu menu) { // if nav
	 * drawer is opened, hide the action items boolean drawerOpen =
	 * mDrawerLayout.isDrawerOpen(mDrawerList);
	 * menu.findItem(R.id.action_settings).setVisible(!drawerOpen); return
	 * super.onPrepareOptionsMenu(menu); }
	 */
}