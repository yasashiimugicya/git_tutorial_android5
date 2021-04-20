package com.example.app05;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button writeButton = (Button)findViewById(R.id.button1);
		writeButton.setOnClickListener(this);

		Button readButton = (Button)findViewById(R.id.button2);
		readButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();

		if (id == R.id.button1) {
			writeFile();
		} else if (id == R.id.button2) {
			readFile();
		}
	}

	private void writeFile() {
		EditText edit = (EditText)findViewById(R.id.editText1);
		String moji = edit.getText().toString();

		String result = "書き込めました。";

		SharedPreferences sp = getSharedPreferences("file", MODE_PRIVATE);
		SharedPreferences.Editor se = sp.edit();

		se.putString("MOJI_DATA", moji);

		if (se.commit() == false) {
			result = "書込めませんでした。";
		}

		Toast t = Toast.makeText(this, result, Toast.LENGTH_SHORT);
		t.show();

		edit.setText("");
	}

	private void readFile() {
		SharedPreferences sp = getSharedPreferences("file", MODE_PRIVATE);

		String result = sp.getString("MOJI_DATA", "登録データがありません。");

		Toast t = Toast.makeText(this, result, Toast.LENGTH_SHORT);
		t.show();
	}
}
