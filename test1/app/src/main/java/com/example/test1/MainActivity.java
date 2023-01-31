package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Course>courseList = new ArrayList<>();
    ArrayList<selectedCourse>selectedCourseList = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    EditText name;
    Spinner sp;
    TextView fees,hours,totalFees,totalHours;
    RadioButton graduated, ungraduated;
    CheckBox accommodation,medical;
    Button addCourse;
    boolean graduation;

    int finalHours = 0;
    int finalFees = 0;
    int expense = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.etName);
        sp = findViewById(R.id.spinner);
        fees = findViewById(R.id.tvFees);
        hours = findViewById(R.id.tvHours);
        totalFees = findViewById(R.id.tvTotalFeesLabel);
        totalHours = findViewById(R.id.tvTotalHoursLabel);
        graduated = findViewById(R.id.rdBtnGrad);
        ungraduated = findViewById(R.id.rdBtnUnGrad);
        medical = findViewById(R.id.chMedical);
        accommodation = findViewById(R.id.chAccommodation);
        addCourse = findViewById(R.id.btnAddCourse);
        filldata();




        //spinner for select course
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, names);
        sp.setAdapter(aa);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fees.setText(String.valueOf(courseList.get(i).getFees()));
                hours.setText(String.valueOf(courseList.get(i).getHours()));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //graduated radio button selection
        graduated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                graduation = true;
            }
        });

        //Ungraduated radio button selection
        ungraduated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                graduation = false;
            }
        });

        //medical checkbox selection
        medical.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.getId()==R.id.chMedical) {
                    expense=0;
                    if (medical.isChecked())
                        expense += 700;
                    else if (!medical.isSelected())
                        expense -= 700;
                }

                finalFees += expense;



            }
        });
        //accommodation checkbox selection
        accommodation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.getId()==R.id.chAccommodation) {
                    expense=0;
                    if (accommodation.isChecked())
                        expense += 1000;
                    else if (!accommodation.isSelected())
                        expense -= 1000;
                }

                finalFees += expense;
            }
        });


        //add course button
        addCourse.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View view) {
                int currentFees = Integer.parseInt(fees.getText().toString());
                int currentHours = Integer.parseInt(hours.getText().toString());
                int tempHours = finalHours+currentHours;

                if (graduation)
                {
                    if (tempHours<=21)
                    {
                        String selectedCourseName = sp.getSelectedItem().toString();
                        boolean add = true;
                        for (selectedCourse sc : selectedCourseList)
                        {
                            if (sc.getCourseName()==selectedCourseName)
                            {
                                add = false;
                            }
                        }

                        if (add)
                        {
                            selectedCourseList.add(new selectedCourse(selectedCourseName,currentHours,currentFees));
                            finalHours += currentHours;
                            finalFees += currentFees;
                            totalFees.setText(String.valueOf(finalFees));
                            totalHours.setText(String.valueOf(finalHours));
                        }

                        else
                        {
                            Toast.makeText(getBaseContext(),"course is already added",Toast.LENGTH_LONG).show();
                        }


                    }
                    else
                    {
                        Toast.makeText(getBaseContext(),"you can’t add this course",Toast.LENGTH_LONG).show();
                    }
                }

                else {
                    if (tempHours<=19)
                    {
                        String selectedCourseName = sp.getSelectedItem().toString();
                        boolean add = true;
                        for (selectedCourse sc : selectedCourseList)
                        {
                            if (sc.getCourseName()==selectedCourseName)
                            {
                                add = false;
                            }
                        }

                        if (add)
                        {
                            selectedCourseList.add(new selectedCourse(selectedCourseName,currentHours,currentFees));
                            finalHours += currentHours;
                            finalFees += currentFees;
                            totalFees.setText(String.valueOf(finalFees));
                            totalHours.setText(String.valueOf(finalHours));
                        }
                        else
                        {
                            Toast.makeText(getBaseContext(),"course is already added",Toast.LENGTH_LONG).show();
                        }


                    }
                    else
                    {
                        Toast.makeText(getBaseContext(),"you can’t add this course",Toast.LENGTH_LONG).show();
                    }

                }

            }
        });
    }

    public void filldata()
    {
        courseList.add(new Course("Java",6,1300));
        courseList.add(new Course("Swift",5,1500));
        courseList.add(new Course("iOS",5,1350));
        courseList.add(new Course("Android",7,1400));
        courseList.add(new Course("Database",4,1000));

        for (Course cfe : courseList)
        {
            names.add(cfe.getCourseName());
        }

    }


}