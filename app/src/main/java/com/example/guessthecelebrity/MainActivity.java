package com.example.guessthecelebrity;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import static android.os.StrictMode.*;

public class MainActivity extends AppCompatActivity {
    public class download extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... urls) {
            String result="";
            URL url;
            HttpURLConnection httpURLConnection=null;
            try{

                url=new URL(urls[0]);
                httpURLConnection=(HttpURLConnection)url.openConnection();
                InputStream in=httpURLConnection.getInputStream();
                InputStreamReader reader=new InputStreamReader(in);
                int data=reader.read();
                while (data!=-1){
                    char current=(char)data;
                    result+=current;
                    data=reader.read();
                }
                return result;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }

//            return result;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView ( R.layout.activity_main );
        // online

        download task=new download();
        String result=null;
        try {
            switch (result = task.execute ( new String[]{getString ( R.string.main )} ).get ()) {
            }
            Log.i("content of url", result);
        } catch (InterruptedException e) {

            e.printStackTrace();
        } catch (ExecutionException e) {

            e.printStackTrace();
        }
    }
}
