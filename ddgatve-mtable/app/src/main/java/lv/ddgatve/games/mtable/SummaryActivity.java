package lv.ddgatve.games.mtable;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {

    DataHolder holder = DataHolder.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TextView messageText = (TextView) findViewById(R.id.summary_message);
        messageText.setTextSize(30);
        messageText.setTextColor(Color.rgb(21, 27, 141)); 		// Cornflower blue
        messageText.setText("100 pareizi reizrēķina piemēri!");

        TextView timeText = (TextView) findViewById(R.id.time_message);
        timeText.setTextSize(20);
        timeText.setTextColor(Color.rgb(21, 27, 141)); 		// Cornflower blue

        timeText.setText("Laiks: " + holder.timeDifference());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.summary, menu);
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

    public void reset(View view) {
        DataHolder holder = DataHolder.getInstance();
        holder.reset();
        finish();
    }
}

/*
package lv.ddgatve.games.mtable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
    }
}

 */