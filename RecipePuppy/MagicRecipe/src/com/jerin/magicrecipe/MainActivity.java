package com.jerin.magicrecipe;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.jerin.magicrecipe.data.MagicRecipeConstants;
import com.jerin.magicrecipe.fragments.RecipeViewPagerFragment;
import com.jerin.magicrecipe.fragments.RecipeSearchFragment;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class MainActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #updateActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		initImageLoader(this);

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));

	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments

		Fragment contentFragment = null;

		switch (position) {
		case MagicRecipeConstants.MAGICRECIPE_TAB_TODAYS_SPECIAL:
			contentFragment = new RecipeViewPagerFragment();
			break;

		case MagicRecipeConstants.MAGICRECIPE_TAB_SEARCH_RECIPES:
			contentFragment = new RecipeSearchFragment();

		default:
			break;
		}

		attachContentFragment(position, contentFragment);
	}

	public void attachContentFragment(int position, Fragment contentFragment) {
		Bundle args = contentFragment.getArguments();

		if (null == args) {
			args = new Bundle();
		}
		args.putInt(MagicRecipeConstants.MAGICRECIPE_TAB_KEY, position);
		contentFragment.setArguments(args);

		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.container, contentFragment).commit();
	}

	/**
	 * Callback from frgaments indicating tab number. This can be used to
	 * identify what title to be shown on Actionbar.
	 * 
	 * @param number
	 */
	public void onSectionAttached(int number) {
		switch (number) {
		case MagicRecipeConstants.MAGICRECIPE_TAB_TODAYS_SPECIAL:
			mTitle = getString(R.string.title_section1);
			break;
		case MagicRecipeConstants.MAGICRECIPE_TAB_SEARCH_RECIPES:
			mTitle = getString(R.string.title_section2);
			break;
		case MagicRecipeConstants.MAGICRECIPE_TAB_SEARCH_RESULTS:
			mTitle = getString(R.string.title_section3);
			break;
		}
		updateActionBar();
	}

	public void updateActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	/**
	 * Initialize Universal Image Loader.
	 * 
	 * @param context
	 */
	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.diskCacheSize(50 * 1024 * 1024)
				// 50 Mb
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}

}
