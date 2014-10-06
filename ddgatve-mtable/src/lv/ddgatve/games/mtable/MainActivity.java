package lv.ddgatve.games.mtable;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class MainActivity extends ActionBarActivity {

	public final static String EXTRA_MESSAGE = "lv.ddgatve.games.mtable.MESSAGE";
	public final static String EXTRA_ANSWER = "lv.ddgatve.games.mtable.ANSWER";
	public final static int ANSWER_CORRECT = 1;

	DataHolder app = DataHolder.getInstance();

	private final int START_STATE = 0;
	private final int TRUE_STATE = 1;
	private final int FALSE_STATE = 2;
	private int STATE = START_STATE;

	private int width;
	private int height;

	public String getDimensions() {
		return "" + height + "*" + width;
	}

	public void setWidth(int arg) {
		width = arg;
	}

	public void setHeight(int arg) {
		height = arg;
	}

	private void changeQuestion(TextView textView) {
		textView.setTextSize(30);
		int[] col = new int[] { Color.BLUE, Color.rgb(76, 196, 23),
				Color.rgb(255, 102, 0), Color.BLUE, Color.RED };
		int questionType = app.getQuestionType();
		if (questionType >= 1 && questionType <= 4) {
			textView.setTextColor(col[questionType]);
		} else {
			textView.setTextColor(Color.rgb(152, 175, 199));
		}
		textView.setText(app.getQuestion());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		EditText editText = (EditText) findViewById(R.id.edit_message);
		editText.setImeActionLabel(getResources().getString(R.string.enter),
				KeyEvent.KEYCODE_ENTER);
		editText.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				sendMessage(v);
				return true;
			}

		});

		final SurfaceView surface = (SurfaceView) findViewById(R.id.surface);

		surface.getHolder().addCallback(new Callback() {

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {

			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				setWidth(width);
				setHeight(height);

				Paint whitePaint = new Paint();
				whitePaint.setColor(Color.WHITE);
				whitePaint.setStrokeWidth(1);
				Paint bluePaint = new Paint();
				bluePaint.setColor(Color.rgb(152, 175, 199));
				bluePaint.setStrokeWidth(1);
				Paint greenPaint = new Paint();
				greenPaint.setColor(Color.rgb(76, 196, 23));
				greenPaint.setStrokeWidth(1);
				Paint redPaint = new Paint();
				redPaint.setColor(Color.rgb(255, 0, 0));
				redPaint.setStrokeWidth(1);

				int cellSize = 30;
				if (width > 50 && height > 50) {
					cellSize = Math.min((int) Math.floor((width - 1) / 20),
							(int) Math.floor((height - 1) / 5));
				}

				Canvas canvas = holder.lockCanvas();
				canvas.drawColor(Color.GRAY);
				int count = app.getTotalCorrect();
				for (int jj = 0; jj < 5; jj++) {
					for (int ii = 0; ii < 20; ii++) {
						int ll = cellSize * ii + 3;
						int tt = cellSize * jj + 3;
						int rr = cellSize * (ii + 1);
						int bb = cellSize * (jj + 1);
						if (count == 1 && STATE == TRUE_STATE) {
							canvas.drawRect(ll, tt, rr, bb, greenPaint);
						} else if (count == 0 && STATE == FALSE_STATE) {
							canvas.drawRect(ll, tt, rr, bb, redPaint);
						} else if (count <= 0) {
							canvas.drawRect(ll, tt, rr, bb, whitePaint);
						} else {
							canvas.drawRect(ll, tt, rr, bb, bluePaint);
						}
						count--;
					}
				}

				if (width > 50 && height > 50) {
					canvas.drawRect(0, cellSize * 5 + 3, width, height,
							whitePaint);
					canvas.drawRect(cellSize * 20 + 3, 0, width, height,
							whitePaint);
				}

				holder.unlockCanvasAndPost(canvas);

			}
		});
		changeQuestion((TextView) findViewById(R.id.name));

		final View view = getWindow().getDecorView().getRootView();
		view.getViewTreeObserver().addOnGlobalLayoutListener(
				new ViewTreeObserver.OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
						surface.post(new Runnable() {
							public void run() {
								setWidth(surface.getWidth());
								setHeight(surface.getHeight());
								changeQuestion((TextView) findViewById(R.id.name));
							}
						});
					}
				});
	}

	@Override
	protected void onStart() {
		super.onStart();
		SurfaceView surface = (SurfaceView) findViewById(R.id.surface);
		setWidth(surface.getWidth());
		setHeight(surface.getHeight());
		changeQuestion((TextView) findViewById(R.id.name));

		// Window win = getWindow();
		// if (win != null) {
		// win.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		// }
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

	public void sendMessage(View view) {
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		intent.putExtra(EXTRA_ANSWER, "" + app.getAnswer());
		startActivityForResult(intent, ANSWER_CORRECT);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == ANSWER_CORRECT) {
			EditText editText = (EditText) findViewById(R.id.edit_message);
			editText.setText("");

			if (resultCode == RESULT_OK) {
				if (data.getBooleanExtra(DisplayMessageActivity.EXTRA_ANSWER,
						true)) {
					app.nextQuestion();
					app.incrementTotalCorrect();
					if (app.getTotalCorrect() >= 100) {
						Intent intent = new Intent(this, SummaryActivity.class);
						startActivity(intent);
					}
					STATE = TRUE_STATE;

				} else {
					app.nextQuestion();
					STATE = FALSE_STATE;
				}

			}
			changeQuestion((TextView) findViewById(R.id.name));
		}
		(new Handler()).postDelayed(new Runnable() {

			public void run() {
				EditText yourEditText = (EditText) findViewById(R.id.edit_message);
				yourEditText.dispatchTouchEvent(MotionEvent.obtain(
						SystemClock.uptimeMillis(), SystemClock.uptimeMillis(),
						MotionEvent.ACTION_DOWN, 0, 0, 0));
				yourEditText.dispatchTouchEvent(MotionEvent.obtain(
						SystemClock.uptimeMillis(), SystemClock.uptimeMillis(),
						MotionEvent.ACTION_UP, 0, 0, 0));

			}
		}, 200);

	}

}
