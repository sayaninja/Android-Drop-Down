package com.example.dropdown;

import java.util.ArrayList;

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
	private Spinner quarterSpinner;
	private Spinner gradLevelSpinner;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        addItemsToDepartmentSpinner();
        addItemsToCourseSpinner();
        addItemsToQuarterSpinner();
        addItemsToGradLevelSpinner();
        //openSearchCourses();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void addItemsToDepartmentSpinner(){
    	
    	ArrayList<String> departmentList = new ArrayList<String>();
    	departmentList.add("CS");
    	departmentList.add("ECE");
    	
    	departmentSpinner = (Spinner) findViewById(R.id.departmentSpinner);
    	
    	// Create an ArrayAdapter using the string array and a default spinner layout
    	ArrayAdapter<String> departmentAdapter = new ArrayAdapter<String>(this,
    	        android.R.layout.simple_spinner_item, departmentList);
    	
    	// Specify the layout to use when the list of choices appears
    	departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	// Apply the adapter to the spinner
    	departmentSpinner.setAdapter(departmentAdapter);
    	
    }
    
    
    public void addItemsToCourseSpinner(){
    	
    	ArrayList<String> courseList = new ArrayList<String>();
    	courseList.add("CS 48");
    	courseList.add("CS 56");
    	
    	courseSpinner = (Spinner) findViewById(R.id.courseSpinner);
    	
    	// Create an ArrayAdapter using the string array and a default spinner layout
    	ArrayAdapter<String> courseAdapter = new ArrayAdapter<String>(this,
    	       android.R.layout.simple_spinner_item, courseList);
    	
    	// Specify the layout to use when the list of choices appears
    	courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	// Apply the adapter to the spinner
    	courseSpinner.setAdapter(courseAdapter);
    	
    }
    
    public void addItemsToQuarterSpinner(){
    	ArrayList<String> quarterList = new ArrayList<String>();
    	quarterList.add("Winter 2014");
    	quarterList.add("Spring 2014");
    	
    	quarterSpinner = (Spinner) findViewById(R.id.quarterSpinner);
    	ArrayAdapter<String> quarterAdapter = new ArrayAdapter<String>(this,
    		     android.R.layout.simple_spinner_item, quarterList);
    	quarterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	quarterSpinner.setAdapter(quarterAdapter);
    	
    }
    
    public void addItemsToGradLevelSpinner(){
    	ArrayList<String> gradLevelList = new ArrayList<String>();
    	gradLevelList.add("Undegraduate");
    	gradLevelList.add("Graduate");
    	
    	gradLevelSpinner = (Spinner) findViewById(R.id.gradLevelSpinner);
    	ArrayAdapter<String> gradLevelAdapter = new ArrayAdapter<String>(this,
    		     android.R.layout.simple_spinner_item, gradLevelList);
    	gradLevelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	gradLevelSpinner.setAdapter(gradLevelAdapter);
    	
    }
    
    public void openSearchCourses(View view){
    	Intent intent = new Intent(this, SearchCourses.class);
        startActivity(intent);
    	
    	
    }
}
