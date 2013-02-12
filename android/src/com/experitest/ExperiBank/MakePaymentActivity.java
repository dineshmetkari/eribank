package com.experitest.ExperiBank;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MakePaymentActivity extends Activity {
	public int COUNTRY_REQUEST_ID = 1000;
	private SharedPreferences userPreferences;
	private EditText phoneEditField, nameEditField, countryEditField;
	private SeekBar amountSeekBar;
	private TextView amountLabel;
	private Button countryButton, sendPaymentButton, cancelButton;
	private AlertDialog.Builder alertDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.makepayment);

		userPreferences = PreferenceManager.getDefaultSharedPreferences(this);

		phoneEditField = (EditText) findViewById(R.id.phoneTextField);
		nameEditField = (EditText) findViewById(R.id.nameTextField);
		amountLabel = (TextView) findViewById(R.id.amountLabel);
		amountSeekBar = (SeekBar) findViewById(R.id.amount);
		amountSeekBar.setMax(100);
		amountSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				amountLabel.setText("Amount (" + progress + "$)");
				sendPaymentButton.setEnabled(readyToSignIn());
			}
		});

		countryEditField = (EditText) findViewById(R.id.countryTextField);

		countryButton = (Button) findViewById(R.id.countryButton);
		sendPaymentButton = (Button) findViewById(R.id.sendPaymentButton);
		sendPaymentButton.setEnabled(readyToSignIn());
		cancelButton = (Button) findViewById(R.id.cancelButton);

		TextWatcher textWatcher = new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				sendPaymentButton.setEnabled(readyToSignIn());
			}
		};
		countryEditField.addTextChangedListener(textWatcher);
		phoneEditField.addTextChangedListener(textWatcher);
		nameEditField.addTextChangedListener(textWatcher);

		alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle("EriBank");
		alertDialog.setMessage("Are you sure you want to send payment?");
		alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
		alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				float balance = userPreferences.getFloat("Balance", LoginActivity.INIT_BALANCE);
				float amount = amountSeekBar.getProgress();

				SharedPreferences.Editor prefsEditr = userPreferences.edit();
				prefsEditr.putBoolean("Refilled", true);
				prefsEditr.putFloat("Balance", balance - amount);
				prefsEditr.commit();
				finish();
			}
		}).setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});

		countryButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					Intent intent = new Intent(MakePaymentActivity.this, CountryListActivity.class);
					startActivityForResult(intent, COUNTRY_REQUEST_ID);
				} catch (Exception ex) {
					Log.e(this.getClass().getName(), "Error : " + ex.getMessage(), ex);
				}
			}
		});

		sendPaymentButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					alertDialog.show();
				} catch (Exception ex) {
					Log.e(this.getClass().getName(), "Error : " + ex.getMessage(), ex);
				}
			}
		});

		cancelButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					finish();
				} catch (Exception ex) {
					Log.e(this.getClass().getName(), "Error : " + ex.getMessage(), ex);
				}
			}
		});
	}

	private boolean readyToSignIn() {
		return phoneEditField.getEditableText().toString().length() > 0 && nameEditField.getEditableText().toString().length() > 0 && amountSeekBar.getProgress() > 0 && countryEditField.getEditableText().toString().length() > 0;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && requestCode == COUNTRY_REQUEST_ID) {
			String country = data.getStringExtra("SelectedCountry");
			countryEditField.setText(country);
			sendPaymentButton.setEnabled(readyToSignIn());
		}
	}
}
