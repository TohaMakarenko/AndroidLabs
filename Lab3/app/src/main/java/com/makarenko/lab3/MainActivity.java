package com.makarenko.lab3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.database.sqlite.SQLiteDatabase;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    EditText passEdit;
    Switch switchPasss;
    Button okButton;
    int defInputType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("applab3.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS passwords (pass TEXT)");

        super.onCreate(savedInstanceState);
        setContentView(com.makarenko.lab3.R.layout.activity_main);


        passEdit = (EditText) findViewById(R.id.passwordEdit);
        switchPasss = (Switch) findViewById(R.id.switchPass);
        okButton = (Button) findViewById(R.id.OkButton);
        defInputType = passEdit.getInputType();

        okButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = getBaseContext().openOrCreateDatabase("applab3.db", MODE_PRIVATE, null);
                db.execSQL("CREATE TABLE IF NOT EXISTS passwords (pass TEXT)");

                db.execSQL("INSERT INTO passwords VALUES ('" + passEdit.getText() +"');");


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Password");
                builder.setMessage(passEdit.getText());
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
        Button button_get_data = findViewById(com.makarenko.lab3.R.id.button_get_data);
        button_get_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //переходим с первой на вторую активность
                Intent intent = new Intent(MainActivity.this, DataActivity.class);
                startActivity(intent);
            }
        });

        switchPasss.setOnCheckedChangeListener( new Switch.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    passEdit.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                else {
                    passEdit.setInputType(defInputType);
                }
            }
        });
    }
}
