package pl.turek.powiat.powiatturecki;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Autostart extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent arg1) {
        Intent intent = new Intent(context, StarterService.class);
        context.startService(intent);
    }
}