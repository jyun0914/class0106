package com.example.simpleui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class MessageActivity extends Activity {

	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);

		textView = (TextView) findViewById(R.id.textView1);

		String text = getIntent().getStringExtra("text");
		writeFile(text);
		
		textView.setText(readFile());
	}

	
	private String readFile() {
		try {
			FileInputStream fis = openFileInput("message");
			byte[] buffer = new byte[1024];
			fis.read(buffer);
			
			return new String(buffer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private void writeFile(String text) {
		try {
			FileOutputStream fos = openFileOutput("message",
					Context.MODE_APPEND);
			text += "\n";
			fos.write(text.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
