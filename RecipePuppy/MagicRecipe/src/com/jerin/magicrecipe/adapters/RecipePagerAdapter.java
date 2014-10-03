package com.jerin.magicrecipe.adapters;

import java.util.ArrayList;

import com.jerin.magicrecipe.data.MagicRecipeConstants;
import com.jerin.magicrecipe.data.RecipeItem;
import com.jerin.magicrecipe.fragments.RecipePageFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Pager Adapter which generates a fragment for each Recipe Item.
 * 
 * @author jerin
 * 
 */
public class RecipePagerAdapter extends FragmentStatePagerAdapter {

	private ArrayList<RecipeItem> recipeItems;

	public RecipePagerAdapter(FragmentManager fm) {
		super(fm);
	}

	/**
	 * Set ArrayList of Recipe Items to adapter.
	 * 
	 * @param recipeItems
	 */
	public void setData(ArrayList<RecipeItem> recipeItems) {
		this.recipeItems = recipeItems;
		notifyDataSetChanged();
	}

	@Override
	public Fragment getItem(int index) {
		RecipePageFragment recipePageFragment = new RecipePageFragment();
		Bundle args = new Bundle();
		args.putParcelable(MagicRecipeConstants.MAGICRECIPE_FRAGMENT_ARGS_KEY,
				recipeItems.get(index));
		recipePageFragment.setArguments(args);
		return recipePageFragment;
	}

	@Override
	public int getCount() {
		if (null == recipeItems) {
			return 0;
		}
		return recipeItems.size();
	}

}
