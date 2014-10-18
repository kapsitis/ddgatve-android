package lv.ddgatve.games.game15;

import lv.ddgatve.games.main.R;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class Game15Activity extends ActionBarActivity {

	public static Game15Frame theFrame = Game15Frame.getInstance(2,2);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game15);

		GridView gridView = (GridView) findViewById(R.id.gridview);
		final ImageAdapter theAdapter =  new ImageAdapter(this);
		theAdapter.setDimensions(2, 2);
		gridView.setNumColumns(2);
		gridView.setAdapter(theAdapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				theFrame.move(position);
				theAdapter.notifyDataSetChanged();
				// Toast.makeText(Game15Activity.this, "" + position,
				// Toast.LENGTH_SHORT).show();
			}
		}); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game15, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
