package com.makarenko.lab2;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.app.Activity;

public class PasswordFragment extends Fragment {
    EditText passEdit;
    Switch switchPasss;
    Button okButton;
    int defInputType;
    String message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_password, container, false);

        passEdit = view.findViewById(R.id.passwordEdit);
        switchPasss = view.findViewById(R.id.switchPass);
        okButton = view.findViewById(R.id.OkButton);
        defInputType = passEdit.getInputType();
        final Context context = view.getContext();

        okButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetail("Пароль: " + passEdit.getText());
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

        return view;
    }

    public OnFragmentInteractionListener mListener;
    interface OnFragmentInteractionListener {

        void onFragmentInteraction(String link);
    }
    public void updateDetail(String curDate) {
        // генерируем некоторые данные

        // Посылаем данные Activity
        mListener.onFragmentInteraction(curDate);
    }
    @Override
    public void onAttach(Activity MainActivity) {
        super.onAttach(MainActivity);
        mListener = (OnFragmentInteractionListener) MainActivity;}
}
