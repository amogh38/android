package com.example.mynewasyncdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class MyprogressTask extends AsyncTask<Void,Integer,String> {

    Context ctx;
    ProgressDialog pd;
    public MyprogressTask(Context ct){
        ctx=ct;
    }
    @Override
    protected void onPreExecute() {
        pd= new ProgressDialog(ctx);
        pd.setTitle("Downloading");
        pd.setMessage("please wait");
        pd.setMax(10);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setButton(ProgressDialog.BUTTON_NEGATIVE, "cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                cancel(true);
            }
        });
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            for (int i=1;i<=10;i++){
                Thread.sleep(1000);
                Log.i("Thread", "Excecute "+i);
                publishProgress(i);
            }
            return "successful";
        }
        catch (Exception e){
            Log.i("Exception",e.getMessage());
            return "failure";
        }

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int myValue = values[0];
        pd.setProgress(myValue);
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
        pd.dismiss();

    }
}
