package lv.ddgatve.math.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_activity_actions, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		// int id = item.getItemId();
		// if (id == R.id.action_settings) {
		// return true;
		// }
		// return super.onOptionsItemSelected(item);
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if (id == R.id.action_signin) {
			Intent intent = new Intent(this, ExampleActivity.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	public void displayResult(String result) {
		TextView textView = (TextView) findViewById(R.id.main_message);
		textView.setTextSize(30);
		textView.setText(result);
	}
	
	public void getJson(View view) {
		new MyAsyncTask().execute();
	}

	class MyAsyncTask extends AsyncTask<String, String, Void> {

		private ProgressDialog progressDialog = new ProgressDialog(
				MainActivity.this);
		InputStream inputStream = null;
		String result = "";

		protected void onPreExecute() {
			progressDialog.setMessage("Downloading your data...");
			progressDialog.show();
			progressDialog.setOnCancelListener(new OnCancelListener() {
				public void onCancel(DialogInterface arg0) {
					MyAsyncTask.this.cancel(true);
				}
			});
		}

		@Override
		protected Void doInBackground(String... params) {

			String url_select = "http://echo.jsontest.com/a/2/key/value";

			ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

			try {
				// Set up HTTP post

				// HttpClient is more then less deprecated. Need to change to
				// URLConnection
				HttpClient httpClient = new DefaultHttpClient();

				HttpPost httpPost = new HttpPost(url_select);
				httpPost.setEntity(new UrlEncodedFormEntity(param));
				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();

				// Read content & Log
				inputStream = httpEntity.getContent();
			} catch (UnsupportedEncodingException e1) {
				Log.e("UnsupportedEncodingException", e1.toString());
				e1.printStackTrace();
			} catch (ClientProtocolException e2) {
				Log.e("ClientProtocolException", e2.toString());
				e2.printStackTrace();
			} catch (IllegalStateException e3) {
				Log.e("IllegalStateException", e3.toString());
				e3.printStackTrace();
			} catch (IOException e4) {
				Log.e("IOException", e4.toString());
				e4.printStackTrace();
			}
			// Convert response to string using String Builder
			try {
				BufferedReader bReader = new BufferedReader(
						new InputStreamReader(inputStream, "iso-8859-1"), 8);
				StringBuilder sBuilder = new StringBuilder();

				String line = null;
				while ((line = bReader.readLine()) != null) {
					sBuilder.append(line + "\n");
				}

				inputStream.close();
				result = sBuilder.toString();

			} catch (Exception e) {
				Log.e("StringBuilding & BufferedReader",
						"Error converting result " + e.toString());
			}
			return null;
		}

		protected void onPostExecute(Void v) {
			// parse JSON data
			try {
//				JSONArray jArray = new JSONArray(result);
//				for (int i = 0; i < jArray.length(); i++) {
//
//					JSONObject jObject = jArray.getJSONObject(i);
//
//					String name = jObject.getString("ip");
//					result = name;
////					String tab1_text = jObject.getString("tab1_text");
////					int active = jObject.getInt("active");
//
//				} // End Loop
//				this.progressDialog.dismiss();
				
				JSONObject reader = new JSONObject(result);
				String key  = reader.getString("key");
				this.progressDialog.dismiss();
				result = key;

			} catch (JSONException e) {
				Log.e("JSONException", "Error: " + e.toString());
			} 
			
			
			MainActivity.this.displayResult(result);
		}
	} 
}
