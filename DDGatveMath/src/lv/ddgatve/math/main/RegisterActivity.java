package lv.ddgatve.math.main;

import android.support.v7.app.ActionBarActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
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
	
	public void sendRegister(View view) {
		EditText editFirstName = (EditText) findViewById(R.id.first_name);
		String firstName = editFirstName.getText().toString();
		EditText editLastName = (EditText) findViewById(R.id.last_name);
		String lastName = editLastName.getText().toString();
		EditText editEmail = (EditText) findViewById(R.id.email);
		String email = editEmail.getText().toString();
		EditText editPassword = (EditText) findViewById(R.id.password1);
		String password = editPassword.getText().toString();
		PostAsyncTask task = new PostAsyncTask(this);
		task.execute(firstName, lastName, email, password);
	}
	
	public void displayResult(String result) {
		TextView textView = (TextView) findViewById(R.id.register_success_message);
		textView.setTextColor(Color.RED);
		textView.setText(result);
	}
}
