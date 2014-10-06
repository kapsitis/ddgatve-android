package lv.kapsitis.myfirstapp;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class CheckAnswerActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_answer);

		Intent intent = getIntent();
		int answer = Integer.parseInt(intent
				.getStringExtra(MainActivity.EXTRA_ANSWER));

		TextView textView = (TextView) findViewById(R.id.check_message);
		textView.setTextSize(30);
		if (answer == 45) {
			textView.setTextColor(Color.BLACK);
			textView.setText(answer + " = 45");
		} else {
			textView.setTextColor(Color.RED);
			textView.setText(answer +  "\u2260" + " 45");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.check_answer, menu);
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
	
//	public void checkAnswer(View view) {
//	Intent intent = new Intent(this, CheckAnswerActivity.class);
//	EditText editText = (EditText) findViewById(R.id.answer);
//	String message = editText.getText().toString();
//	intent.putExtra(EXTRA_ANSWER, message);
//	startActivity(intent);
//}
}
