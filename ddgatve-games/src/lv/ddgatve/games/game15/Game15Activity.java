package lv.ddgatve.games.game15;

import lv.ddgatve.games.main.R;
import android.app.FragmentManager;
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

	public static int ROWS = 4;
	public static int COLS = 4;
	public static Game15Frame theFrame = Game15Frame.getInstance();
	public int sqSize = 120; 
	public ImageAdapter theAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game15);
		
		PickFrameDialogFragment dFragment = new PickFrameDialogFragment();
		dFragment.setActivity(this);
		FragmentManager fm = getFragmentManager();
		dFragment.show(fm, "MyDF");

		GridView gridView = (GridView) findViewById(R.id.gridview);
		
		sqSize = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 280/COLS, getResources()
				.getDisplayMetrics());
		
		theAdapter = new ImageAdapter(this);
//		theAdapter.setDimensions(ROWS, COLS);
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
