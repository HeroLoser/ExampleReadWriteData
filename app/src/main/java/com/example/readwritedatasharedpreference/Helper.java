package com.example.readwritedatasharedpreference;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Helper {

    private static final String TAG_TITLE = Helper.class.getName();

    static void writeFile(DataModels dataModels, Context context){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(dataModels.getTitle(), Context.MODE_PRIVATE));
            outputStreamWriter.write(dataModels.getDescription());
            outputStreamWriter.close();

        } catch (Exception e) {
            Log.e(TAG_TITLE, "Write Failed");
        }
    }

    static DataModels readData(Context context, String title){
        DataModels dataModels = new DataModels();
        try {
            InputStream inputStream = context.openFileInput(title);

            if (inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receive;
                StringBuilder stringBuilder = new StringBuilder();

                while ((receive = bufferedReader.readLine()) != null){
                    stringBuilder.append(receive);
                }

                inputStream.close();
                dataModels.setTitle(stringBuilder.toString());
                dataModels.setDescription(title);
            }

        } catch (FileNotFoundException e) {
            Log.e(TAG_TITLE, "File not found : ", e);
        } catch (IOException e){
            Log.e(TAG_TITLE, "Fail Read", e);
        }
        return dataModels;
    }
}
