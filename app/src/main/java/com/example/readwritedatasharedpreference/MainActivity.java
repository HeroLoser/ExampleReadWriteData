package com.example.readwritedatasharedpreference;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    EditText edtTitle, edtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtTitle = findViewById(R.id.edit_title);
        edtDescription = findViewById(R.id.edit_file);
    }

    public void btnNewFile(View view) {
        newFile();
    }

    public void btnFileOpen(View view) {
        readData();
    }

    public void btnSaveFile(View view) {
        saveFile();
    }

    private void newFile(){
        edtTitle.setText("");
        edtDescription.setText("");
    }

    private void saveFile(){
        if (edtTitle.getText().toString().isEmpty() && edtDescription.getText().toString().isEmpty()){
            Toast.makeText(this, "Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }
        else {
            String title = edtTitle.getText().toString();
            String content = edtDescription.getText().toString();
            DataModels dataModels = new DataModels();
            dataModels.setTitle(title);
            dataModels.setDescription(content);

            Helper.writeFile(dataModels, this);
        }
    }

    private void readData(){
        ArrayList<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, getFilesDir().list());
        final CharSequence[] items = arrayList.toArray(new CharSequence[arrayList.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pilih File");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                loadData(items[i].toString());
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void loadData(String title) {
        DataModels dataModels = Helper.readData(this, title);
        edtTitle.setText(dataModels.getDescription());
        edtDescription.setText(dataModels.getTitle());
    }

}