package lv.kapsitis.myfirstapp;

import java.util.Random;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	public static String EXTRA_ANSWER = "lv.kapsitis.myfirstapp.EXTRA_ANSWER";

	private int first;
	private int second;

	
	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.activity_main);
	// TextView textView = (TextView) findViewById(R.id.question);
	// textView.setTextSize(30);
	// Random r = new Random();
	// first = r.nextInt(10); // uzmin nejaušu pirmo skaitli no 0 līdz 9
	// second = r.nextInt(10); // uzmin nejaušu otro skaitli no 0 līdz 9
	// String jautajums = "Cik ir " + first + "*" + second + "?";
	// textView.setText(jautajums);
	// }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView textView = (TextView) findViewById(R.id.question);
		textView.setTextSize(30);
		textView.setTextColor(Color.RED);
		textView.setTextColor(Color.rgb(255, 0, 255));
		textView.setText("Cik ir 3*15?");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	public void checkAnswer(View view) {
		EditText editText = (EditText) findViewById(R.id.answer);
		String message = editText.getText().toString();
		String result = "Nepareizi!";
		if (message.equals("45")) {
			result = "Pareizi!";
		}
		TextView textView = (TextView) findViewById(R.id.question);
		textView.setTextSize(30);
		textView.setText(result);
	}
}
