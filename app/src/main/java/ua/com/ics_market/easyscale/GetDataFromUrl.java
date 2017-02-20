package ua.com.ics_market.easyscale;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by Denis on 20.02.2017.
 */

public class GetDataFromUrl extends AsyncTask <String,Void,Document> {

    Context mContext;

    //ProgressDialog pdialog;
    TaskCompleteListener taskCompleteListener;
    Document doc = null;


    public GetDataFromUrl(Context context){

        mContext = context;
        taskCompleteListener = (TaskCompleteListener) context;
    }

    @Override
    protected Document doInBackground(String... params) {

        try {
            doc = Jsoup.connect(params[0]).get();
            return doc;
        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Document document) {

        if (document !=null){
            taskCompleteListener.onTaskComplete(document.text());
        }
        else
            Toast.makeText(mContext,"NULL ASYNC", Toast.LENGTH_LONG).show();
    }
}
