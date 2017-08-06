package com.example.shree.wlug;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feedback extends AppCompatActivity {

    EditText Name;
    EditText feedback;
    Button send;
    TextInputLayout errorName;
    FirebaseAuth mAuth;
    DatabaseReference mref;
    FirebaseUser user;
    String name,feed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Name =(EditText) findViewById(R.id.name);
        feedback=(EditText)findViewById(R.id.givefeedback);
        errorName = (TextInputLayout) findViewById(R.id.errorName);
        send =(Button) findViewById(R.id.send);
        mAuth=FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mref= FirebaseDatabase.getInstance().getReference("WlugUsersInfo");

    }

    public void sendfeedback(View view)
    {
        name = Name.getText().toString();
        feed = feedback.getText().toString();

        if(name.isEmpty() || feed.isEmpty())
        {
            if(name.isEmpty()) {
                errorName.setError("This field is required");
            }
            if(feed.isEmpty()) {
                feedback.setError("This field is required");
            }

        }
        else
        {
            mref.child(user.getUid()).child("Feedback").setValue(feed);
            Toast.makeText(getApplicationContext(),"Thank you for giving your valuable feedback",Toast.LENGTH_SHORT).show();

        }

    }
}
