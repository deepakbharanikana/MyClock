package com.deepak.myclock;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.deepak.myclock.alarms.AlarmStateManager;
import com.deepak.myclock.provider.Alarm;
import com.deepak.myclock.R;

public class MainActivity extends ActionBarActivity implements
		LabelDialogFragment.AlarmLabelDialogHandler,
		DrawerAdapter.OnItemClickListener {

	private static FragmentManager mManager;

	private String[] mDrawerTitles;
	private TypedArray mDrawerIcons;
	private ArrayList<Items> drawerItems;
	private DrawerLayout mDrawerLayout;
	private RecyclerView mRecyclerView;
	private RecyclerView.Adapter mAdapter;
	private RecyclerView.LayoutManager mLayoutManager;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private TextView coinsView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		if (toolbar != null)
			setSupportActionBar(toolbar);

		toolbar.setTitleTextColor(getResources().getColor(R.color.white));


		mDrawerTitles = getResources().getStringArray(R.array.drawer_titles);
		mDrawerIcons = getResources().obtainTypedArray(R.array.drawer_icons);
		drawerItems = new ArrayList<Items>();
		mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning

		mRecyclerView.setHasFixedSize(true);
		mDrawerTitles = getResources().getStringArray(R.array.drawer_titles);
		mDrawerIcons = getResources().obtainTypedArray(R.array.drawer_icons);
		drawerItems = new ArrayList<Items>();
		drawerItems.add(new Items(getResources().getString(R.string.username,
				("name")), (R.drawable.ic_action_alarms)));
		for (int i = 0; i < mDrawerTitles.length; i++) {
			drawerItems.add(new Items(mDrawerTitles[i], mDrawerIcons
					.getResourceId(i, -(i + 1))));
		}
		mTitle = mDrawerTitle = getTitle();

		mAdapter = new DrawerAdapter(getApplicationContext(), drawerItems, this);

		mRecyclerView.setAdapter(mAdapter);

		mLayoutManager = new LinearLayoutManager(this);

		mRecyclerView.setLayoutManager(mLayoutManager);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
				R.string.drawer_open, R.string.drawer_close) {

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				//getSupportActionBar().setTitle(mTitle);
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				//getSupportActionBar().setTitle(mDrawerTitle);
			}

		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		//getSupportActionBar().setLogo(getResources().getDrawable(R.mipmap.actionbar_clock));

		if (savedInstanceState == null) {
			selectItem(1);
		}
		AlarmStateManager.updateNextAlarm(this);

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public void onClick(View view, int position) {
		selectItem(position);
	}

	private void selectItem(int position) {

		Fragment fragment = null;

		switch (position) {
		
		
		  case 1: 
			  fragment = new AlarmClockFragment();
		  break;
		  
		  
		  /*Uri uri = Uri.parse("market://details?id=" +
		  context.getPackageName()); Intent goToMarket = new
		  Intent(Intent.ACTION_VIEW, uri); try {
		  context.startActivity(goToMarket); } catch (ActivityNotFoundException
		  e) { UtilityClass.showAlertDialog(context, ERROR,
		  "Couldn't launch the market", null, 0); }*/
		 
		
		  case 2: 
			  //fragment = new RewardsActivity();
			  break;
		  /*Intent sharingIntent = new
		  Intent(android.content.Intent.ACTION_SEND);
		  sharingIntent.setType("text/plain"); String shareBody =
		  "Here is the share content body";
		  sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
		  "Subject Here");
		  sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
		  startActivity(Intent.createChooser(sharingIntent, "Share via"));*/
		 
		case 5:
		

			break;
		case 6:
			// Settings
			Intent intent2 = new Intent(this, SettingsActivity.class);
			startActivity(intent2);
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
	        FragmentTransaction ft = fragmentManager.beginTransaction();
	        ft.replace(R.id.main_content, fragment);
	        ft.commit();
	    	// Highlight the selected item, update the title, and close the drawer
			if(position==1){
				setTitle(mTitle);
			}
			else
	        setTitle(mDrawerTitles[position - 1]);
	        mDrawerLayout.closeDrawer(mRecyclerView);
		}

	
	}

	@Override
	public void onDialogLabelSet(Alarm alarm, String label, String tag) {
		android.app.Fragment frag = getFragmentManager().findFragmentByTag(tag);
		if (frag instanceof AlarmClockFragment) {
			((AlarmClockFragment) frag).setLabel(alarm, label);
		}
	}

}