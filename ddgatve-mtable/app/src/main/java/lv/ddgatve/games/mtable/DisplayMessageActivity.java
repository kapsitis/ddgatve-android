package lv.ddgatve.games.mtable;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayMessageActivity extends AppCompatActivity {

    private int m1;
    private int m2;
    public final static String EXTRA_ANSWER = "lv.ddgatve.games.mtable.ANSWER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the message from the intent
        Intent intent = getIntent();
        m1 = -1;
        m2 = -1;
        try {
            m1 = Integer.parseInt(intent
                    .getStringExtra(MainActivity.EXTRA_MESSAGE));
        } catch (Exception e) {
        }
        try {
            m2 = Integer.parseInt(intent
                    .getStringExtra(MainActivity.EXTRA_ANSWER));
        } catch (Exception e) {
        }

        TextView textView = (TextView) findViewById(R.id.answer);
        textView.setTextSize(30);
        if (m1 == m2) {
            textView.setTextColor(Color.BLACK);
            textView.setText("" + m1 + "=" + m2);
        } else {
            textView.setTextColor(Color.RED);
            textView.setText("" + m1 + "\u2260" + m2);
        }

        LinearLayout myLayout = (LinearLayout) findViewById(R.id.display_message_layout);

        myLayout.setOnTouchListener(new LinearLayout.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        LinearLayout myLayout = (LinearLayout) findViewById(R.id.display_message_layout);

        myLayout.setOnTouchListener(new LinearLayout.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent m) {
                handleTouch(m);
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.display_message, menu);
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

    public void handleTouch(MotionEvent m) {
        Intent result = new Intent(this, MainActivity.class);
        result.putExtra(EXTRA_ANSWER, m1 == m2);
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    public void goBack(View view) {
        Intent result = new Intent(this, MainActivity.class);
        result.putExtra(EXTRA_ANSWER, m1 == m2);
        setResult(Activity.RESULT_OK, result);
        finish();
    }
}





/*
package lv.ddgatve.games.mtable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
    }
}
 */