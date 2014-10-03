package com.jerin.magicrecipe.fragments;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jerin.magicrecipe.R;
import com.jerin.magicrecipe.data.MagicRecipeConstants;
import com.jerin.magicrecipe.data.RecipeItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class RecipePageFragment extends Fragment {

	private ImageLoadingListener mAnimateFirstListener = new AnimateFirstDisplayListener();

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View fragmentView;
		fragmentView = inflater.inflate(R.layout.fragment_recipe_page,
				container, false);
		initView(fragmentView);
		return fragmentView;
	}

	private void initView(View fragmentView) {

		Bundle arguments = getArguments();
		RecipeItem recipeItem = arguments
				.getParcelable(MagicRecipeConstants.MAGICRECIPE_FRAGMENT_ARGS_KEY);

		if (null != recipeItem) {
			TextView titleTextView = (TextView) fragmentView
					.findViewById(R.id.titleTextView);
			titleTextView.setText(recipeItem.getTitle());

			TextView ingredientsTextView = (TextView) fragmentView
					.findViewById(R.id.ingredientsTextView);
			ingredientsTextView.setText(recipeItem.getIngredients());

			ImageView imageView = (ImageView) fragmentView
					.findViewById(R.id.imageView);

			DisplayImageOptions options = new DisplayImageOptions.Builder()
					.showImageOnLoading(R.drawable.recipe_puppy)
					.showImageForEmptyUri(R.drawable.recipe_puppy)
					.showImageOnFail(R.drawable.ic_launcher)
					.cacheInMemory(true).cacheOnDisk(false)
					.considerExifParams(true)
					.displayer(new RoundedBitmapDisplayer(2)).build();

			ImageLoader.getInstance().displayImage(recipeItem.getImageUrl(),
					imageView, options, mAnimateFirstListener);
		}

	}

	private static class AnimateFirstDisplayListener extends
			SimpleImageLoadingListener {

		static final List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				imageView.setLayoutParams(new RelativeLayout.LayoutParams(500,
						500));
				imageView.setScaleType(ScaleType.FIT_START);
				imageView.setImageBitmap(loadedImage);
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}
}
