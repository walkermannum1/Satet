package com.example.user.satet;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by user on 2016/11/21.
 */

public class LoginActivity extends Activity{
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private ImageView log;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox remembPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        log = (ImageView) findViewById(R.id.imageView);
        accountEdit = (EditText)findViewById(R.id.account);
        passwordEdit = (EditText)findViewById(R.id.password);
        remembPass = (CheckBox)findViewById(R.id.remember_pass);
        login = (Button) findViewById(R.id.login);

        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember) {
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            remembPass.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                boolean result = LoginService.check(name, password);
                if (result){
                    Toast.makeText(getApplicationContext(), R.strings.success)
                }
            }
        });
    }

}
