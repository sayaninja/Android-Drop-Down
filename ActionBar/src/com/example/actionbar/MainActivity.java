package com.example.actionbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity{
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Button myButton = (Button) findViewById(R.id.ourButton);
        final TextView textView = (TextView) findViewById(R.id.textView);
        /*myButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v){
        		String text = textView.getText().toString();
        		if(text.contains("World")){
        			textView.setText("Hello Saya");
        		}
        		else{
        			textView.setText("Hello World");
        			
        		}
        			
        	}
        	
        });*/
        
    }
    
    public void buttonClick(View v){
    	final TextView textView = (TextView) findViewById(R.id.textView);
    	String text = textView.getText().toString();
		if(text.contains("World")){
			textView.setText("Hello Saya");
		}
		else{
			textView.setText("Hello World");
			
		}
    	
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
 
        return super.onCreateOptionsMenu(menu);
    }
}