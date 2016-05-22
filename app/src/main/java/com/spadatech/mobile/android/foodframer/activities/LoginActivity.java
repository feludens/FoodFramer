package com.spadatech.mobile.android.foodframer.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.spadatech.mobile.android.foodframer.R;
import com.spadatech.mobile.android.foodframer.dbtables.UserTable;
import com.spadatech.mobile.android.foodframer.managers.SessionManager;
import com.spadatech.mobile.android.foodframer.models.User;

/**
 * Created by Felipe S. Pereira on 4/30/16.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_login);
    }

    public void login(View v){
        EditText username = (EditText) findViewById(R.id.et_login_username);
        EditText password = (EditText) findViewById(R.id.et_login_password);

        UserTable userTable = new UserTable();
        User user = null;

        if(password != null) {
            if (username != null) {
                if (!username.getText().toString().isEmpty()) {
                    if (username.getText().toString().contains("@")) {
                        user = userTable.findUserByEmailAndPassword(username.getText().toString(), password.getText().toString());
                    }else{
                        user = userTable.findUserByUsernameAndPassword(username.getText().toString(), password.getText().toString());
                    }
                }
            }
        }

        if(user != null){
            SessionManager sessionManager = new SessionManager(this);
            if(sessionManager.createSession(user.getUsername(), user.getEmail())){
                Intent intent = new Intent(this, PlanListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }else{
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle(getString(R.string.oooopsie));
            alertDialog.setMessage(getString(R.string.alert_wrong_credentials));
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.got_it),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }

    public void createAccount(View v){
        Intent registrationIntent = new Intent(this, RegistrationActivity.class);
        startActivity(registrationIntent);
    }
}
