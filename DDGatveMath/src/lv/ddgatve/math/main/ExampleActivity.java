package lv.ddgatve.math.main;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

public class ExampleActivity extends Activity implements ConnectionCallbacks,
		OnConnectionFailedListener, OnClickListener {

	/* Request code used to invoke sign in user interactions. */
	private static final int RC_SIGN_IN = 0;

	/* Client used to interact with Google APIs. */
	private GoogleApiClient mGoogleApiClient;

	/*
	 * A flag indicating that a PendingIntent is in progress and prevents us
	 * from starting further intents.
	 */
	private boolean mIntentInProgress;

	/*
	 * Track whether the sign-in button has been clicked so that we know to
	 * resolve all issues preventing sign-in without waiting.
	 */
	private boolean mSignInClicked;

	/*
	 * Store the connection result from onConnectionFailed callbacks so that we
	 * can resolve them when the user clicks sign-in.
	 */
	private ConnectionResult mConnectionResult;

	/* A helper method to resolve the current ConnectionResult error. */
	private void resolveSignInError() {
		if (mConnectionResult != null && mConnectionResult.hasResolution()) {
			try {
				mIntentInProgress = true;
				startIntentSenderForResult(mConnectionResult.getResolution()
						.getIntentSender(), RC_SIGN_IN, null, 0, 0, 0);
			} catch (SendIntentException e) {
				// The intent was canceled before it was sent. Return to the
				// default
				// state and attempt to connect to get an updated
				// ConnectionResult.

			}
		}
		mIntentInProgress = false;
		mGoogleApiClient.connect();
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_example);

		mGoogleApiClient = new GoogleApiClient.Builder(this)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this).addApi(Plus.API)
				.addScope(Plus.SCOPE_PLUS_PROFILE).build();

		findViewById(R.id.sign_in_button).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.example, menu);
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

	protected void onStart() {
		super.onStart();
		mGoogleApiClient.connect();
	}

	protected void onStop() {
		super.onStop();

		if (mGoogleApiClient.isConnected()) {
			mGoogleApiClient.disconnect();
		}
	}

	public void onConnectionFailed(ConnectionResult result) {
		if (!mIntentInProgress && result.hasResolution()) {
			try {
				mIntentInProgress = true;
				startIntentSenderForResult(result.getResolution()
						.getIntentSender(), RC_SIGN_IN, null, 0, 0, 0);
			} catch (SendIntentException e) {
				// The intent was canceled before it was sent. Return to the
				// default
				// state and attempt to connect to get an updated
				// ConnectionResult.
				mIntentInProgress = false;
				mGoogleApiClient.connect();
			}
		}
	}

	// public void onConnectionFailed(ConnectionResult result) {
	// if (!mIntentInProgress) {
	// // Store the ConnectionResult so that we can use it later when the
	// // user clicks
	// // 'sign-in'.
	// mConnectionResult = result;
	//
	// if (mSignInClicked) {
	// // The user has already clicked 'sign-in' so we attempt to
	// // resolve all
	// // errors until the user is signed in, or they cancel.
	// resolveSignInError();
	// }
	// }
	// }

	@Override
	public void onConnected(Bundle connectionHint) {
		mSignInClicked = false;
		if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
			Person currentPerson = Plus.PeopleApi
					.getCurrentPerson(mGoogleApiClient);
			String personName = currentPerson.getDisplayName();
			// String personPhoto = currentPerson.getImage();
			// String personGooglePlusProfile = currentPerson.getUrl();
			Toast.makeText(this, personName + " connected!", Toast.LENGTH_LONG)
					.show();
		} else {
			Toast.makeText(this, "User is connected!", Toast.LENGTH_LONG)
					.show();
		}
	}

	protected void onActivityResult(int requestCode, int responseCode,
			Intent intent) {
		if (requestCode == RC_SIGN_IN) {
			mIntentInProgress = false;

			if (!mGoogleApiClient.isConnecting()) {
				mGoogleApiClient.connect();
			}
		}
	}

	public void onConnectionSuspended(int cause) {
		mGoogleApiClient.connect();
	}

	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.sign_in_button
				&& !mGoogleApiClient.isConnecting()) {
			mSignInClicked = true;
			resolveSignInError();
		}
	}

	public void signOut(View view) {
		if (view.getId() == R.id.sign_out_button) {
			if (mGoogleApiClient.isConnected()) {
				Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
				mGoogleApiClient.disconnect();
				mGoogleApiClient.connect();
			}

		}
	}
}
