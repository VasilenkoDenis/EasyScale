package ua.com.ics_market.easyscale;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

/**
 * Created by vasilenkoden on 01.03.2017.
 */

public class RefreshTimerTask extends TimerTask {

    Context context;
    GetDataFromUrl dataFromUrl;
    String url;

    public RefreshTimerTask(Context context) {

        dataFromUrl = new GetDataFromUrl(context);
        this.context = context;
    }

    public void setURL(String url) {
        this.url = url;

    }

    @Override
    public void run() {
        if (dataFromUrl.getStatus() != AsyncTask.Status.RUNNING) {
            try {
                dataFromUrl = new GetDataFromUrl(context);
                MainActivity.doc = dataFromUrl.execute(url).get();
            } catch (InterruptedException e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (ExecutionException e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}