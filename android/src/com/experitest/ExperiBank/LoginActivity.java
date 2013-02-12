package com.experitest.ExperiBank;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends Activity {
    /** Called when the activity is first created. */
	public static float INIT_BALANCE = 100;
	private SharedPreferences userPreferences;
	private EditText userNameEditField, passwordEditField;
	private Button loginButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        userPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        
        userNameEditField = (EditText) findViewById(R.id.usernameTextField);
		passwordEditField = (EditText) findViewById(R.id.passwordTextField);
        loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					if (userNameEditField.getEditableText().toString().trim().equalsIgnoreCase("company") &&
							passwordEditField.getEditableText().toString().trim().equalsIgnoreCase("company")) {
						
						Boolean isRefilled = userPreferences.getBoolean("Refilled", false);
						if (!isRefilled) {
							SharedPreferences.Editor prefsEditr = userPreferences.edit();
							prefsEditr.putBoolean("Refilled", true);
							prefsEditr.putFloat("Balance", INIT_BALANCE);
							prefsEditr.commit();
						}
						finish();
						startActivity(new Intent(LoginActivity.this, PaymentHomeActivity.class));
					}
					else {
						Toast.makeText(LoginActivity.this, "Invalid username or password!", Toast.LENGTH_LONG).show();
					}
				} catch (Exception ex) {
					Log.e(this.getClass().getName(), "Error : " + ex.getMessage(), ex);
				}
			}
		});
    }
}