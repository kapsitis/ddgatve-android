package lv.ddgatve.games.game15;

import lv.ddgatve.games.main.R;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class Game15Activity extends ActionBarActivity {

	public static final int ROWS = 3;
	public static final int COLS = 3;
	public static Game15Frame theFrame = Game15Frame.getInstance(ROWS, COLS);
	public int sqSize = 120; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game15);

		GridView gridView = (GridView) findViewById(R.id.gridview);
		
		sqSize = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 280/COLS, getResources()
				.getDisplayMetrics());
		
		final ImageAdapter theAdapter = new ImageAdapter(this);
		theAdapter.setDimensions(ROWS, COLS);
		gridView.setNumColumns(COLS);
		gridView.setColumnWidth(sqSize);
		ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
		layoutParams.height = ROWS*sqSize;
		layoutParams.width = COLS*sqSize;
		gridView.setAdapter(theAdapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				theFrame.move(position);
				theAdapter.notifyDataSetChanged();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.game15, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
