package lv.ddgatve.games.game15;

import lv.ddgatve.games.main.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	private int rows;
	private int cols;

	public ImageAdapter(Context c) {
		mContext = c;
	}

	public void setDimensions(int rows, int cols) {
		if (rows * cols > 16) {
			rows = 4;
			cols = 4;
		} else {
			this.rows = rows;
			this.cols = cols;
		}
	}

	public int getCount() {
		// return tiles.length;
		return rows * cols;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// Game15Frame theFrame = Game15Frame.getInstance();
		ImageView imageView;
		if (convertView == null) {

			imageView = new ImageView(mContext);

			imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(2, 2, 2, 2);
		} else {
			imageView = (ImageView) convertView;
		}

		imageView.setImageResource(tiles[Game15Activity.theFrame
				.getSlotByNum(position)]);
		return imageView;
	}

	private Integer[] tiles = { R.drawable.tile_0, R.drawable.tile_1,
			R.drawable.tile_2, R.drawable.tile_3, R.drawable.tile_4,
			R.drawable.tile_5, R.drawable.tile_6, R.drawable.tile_7,
			R.drawable.tile_8, R.drawable.tile_9, R.drawable.tile_10,
			R.drawable.tile_11, R.drawable.tile_12, R.drawable.tile_13,
			R.drawable.tile_14, R.drawable.tile_15 };
}