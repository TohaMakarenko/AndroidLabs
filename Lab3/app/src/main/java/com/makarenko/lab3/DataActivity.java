package com.makarenko.lab3;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class DataActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.makarenko.lab3.R.layout.activity_data);

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("applab3.db", MODE_PRIVATE, null);

        Cursor query = db.rawQuery("SELECT * FROM passwords;", null);

        TextView textView = (TextView) findViewById(com.makarenko.lab3.R.id.textView);
        textView.setText("");
        if(query.moveToFirst()){
            do{
                String pass = query.getString(0);
                textView.append(pass);
                textView.append("\n\n");
            }
            while(query.moveToNext());
        }
        else{
            textView.setText("");
            textView.append("БД пуста");
        }
        query.close();
        db.close();
    }

    public void onClick(View view) {
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("applab3.db", MODE_PRIVATE, null);
        db.execSQL("DELETE FROM passwords");

        TextView textView = (TextView) findViewById(com.makarenko.lab3.R.id.textView);
        textView.setText("");
        textView.append("БД пуста");

        db.close();



    }



}
