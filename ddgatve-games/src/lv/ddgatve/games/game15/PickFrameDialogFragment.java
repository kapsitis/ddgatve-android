package lv.ddgatve.games.game15;

import lv.ddgatve.games.main.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class PickFrameDialogFragment extends DialogFragment {
	private Game15Activity activity;
	
	public void setActivity(Game15Activity activity) {
		this.activity = activity;
	}
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_pick_frame)
               .setItems(R.array.field_types, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {
//                	   Game15Activity.theFrame.initialize(4, 4);
                	   activity.resetGame(which+2, which+2);
               }
        });
        return builder.create();
    }
}