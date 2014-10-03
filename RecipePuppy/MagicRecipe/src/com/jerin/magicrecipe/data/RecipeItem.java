package com.jerin.magicrecipe.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class representing a Recipe Item.
 * 
 * @author jerin
 * 
 */
public class RecipeItem implements Parcelable {

	/**
	 * The title of the recipe.
	 */
	private String mRecipeTitle;

	/**
	 * The thumbnail url of the recipe.
	 */
	private String mRecipeImageUrl;

	/**
	 * The recipe's description.
	 */
	private String mRecipeIngredients;

	public RecipeItem() {
	}

	public String getTitle() {
		return mRecipeTitle;
	}

	public void setTitle(String mTitle) {
		this.mRecipeTitle = mTitle;
	}

	public String getImageUrl() {
		return mRecipeImageUrl;
	}

	public void setImageUrl(String mUrl) {
		this.mRecipeImageUrl = mUrl;
	}

	public String getIngredients() {
		return mRecipeIngredients;
	}

	public void setIngredients(String ingredients) {
		this.mRecipeIngredients = ingredients;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mRecipeTitle);
		dest.writeString(mRecipeIngredients);
		dest.writeString(mRecipeImageUrl);
	}

}
