package com.makarenko.lab1;

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

public class MainActivity extends AppCompatActivity {

    EditText passEdit;
    Switch switchPasss;
    Button okButton;
    int defInputType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passEdit = (EditText) findViewById(R.id.passwordEdit);
        switchPasss = (Switch) findViewById(R.id.switchPass);
        okButton = (Button) findViewById(R.id.OkButton);
        defInputType = passEdit.getInputType();

        okButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
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
