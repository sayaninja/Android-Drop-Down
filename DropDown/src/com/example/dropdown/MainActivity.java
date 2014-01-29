package com.example.dropdown;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity {

	
	private Spinner departmentSpinner;
	private Spinner courseSpinner;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        addItemsToDepartmentSpinner();
        addItemsToCourseSpinner();
        //openSearchCourses();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void addItemsToDepartmentSpinner(){
    	departmentSpinner = (Spinner) findViewById(R.id.departmentSpinner);
    	
    	// Create an ArrayAdapter using the string array and a default spinner layout
    	ArrayAdapter<CharSequence> departmentAdapter = ArrayAdapter.createFromResource(this,
    	        R.array.department_array, android.R.layout.simple_spinner_item);
    	
    	// Specify the layout to use when the list of choices appears
    	departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	// Apply the adapter to the spinner
    	departmentSpinner.setAdapter(departmentAdapter);
    	
    }
    
    public void addItemsToCourseSpinner(){
    	courseSpinner = (Spinner) findViewById(R.id.courseSpinner);
    	
    	// Create an ArrayAdapter using the string array and a default spinner layout
    	ArrayAdapter<CharSequence> courseAdapter = ArrayAdapter.createFromResource(this,
    	        R.array.course_array, android.R.layout.simple_spinner_item);
    	
    	// Specify the layout to use when the list of choices appears
    	courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	// Apply the adapter to the spinner
    	courseSpinner.setAdapter(courseAdapter);
    	
    }
    
    public void openSearchCourses(View view){
    	Intent intent = new Intent(this, SearchCourses.class);
        startActivity(intent);
    	
    	
    }
}
