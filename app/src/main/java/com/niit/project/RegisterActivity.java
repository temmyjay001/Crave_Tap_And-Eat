package com.niit.project;

import android.content.Intent;

import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "Crave Tap Eat";
    private FirebaseAuth mAuth;
    private RelativeLayout rlayout;
    private Animation animation;
    private Button mRegister;
    private String email, password, passwordVerification;
    private EditText username, pass, passVerification, etEmail;
    private boolean isRegistrationClicked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        mAuth = FirebaseAuth.getInstance();


        username = (EditText) findViewById(R.id.etUsername);
        pass = (EditText) findViewById(R.id.etPassword);
        passVerification = (EditText) findViewById(R.id.etRePassword);
        etEmail = (EditText) findViewById(R.id.etEmail);

        rlayout     = findViewById(R.id.rlayout);
        animation   = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        rlayout.setAnimation(animation);

        mRegister = (Button) findViewById(R.id.btRegister);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = etEmail.getText().toString();
                password = pass.getText().toString();
                passwordVerification = passVerification.getText().toString();
                if (password.equals(passwordVerification) && !password.equals("") && !passwordVerification.equals("")) {
                    createAccount();
                } else {
                    Snackbar.make(findViewById(R.id.newUserPage), "Passwords don't match", Snackbar.LENGTH_SHORT).show();
                    pass.setText("");
                    passVerification.setText("");
                }
            }
        });
    }

    private void createAccount() {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                        isRegistrationClicked = true;
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
//                            Toast.makeText(newUser.this, R.string.auth_failed, Toast.LENGTH_SHORT).show();
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            isRegistrationClicked = false;
                            updateUI(null);
                        }
                        // ...
                    }
                });
    }

    private void updateUI(FirebaseUser user){
        if (user != null) {
            // User is signed in
            Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
            String name = username.getText().toString();
            UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().
                    setDisplayName(name).build();
            user.updateProfile(profileChangeRequest);
        }else{
            // User is signed out
            Log.d(TAG, "onAuthStateChanged:signed_out");
        }

        sendVerificationEmail();
    }

    private void sendVerificationEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.sendEmailVerification()
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        // Email sent
                        finish();
                    } else {
                        // overridePendingTransition(0, 0);
                        // finish();
                        // overridePendingTransition(0, 0);
                        // startActivity(getIntent());
                        sendVerificationEmail();
                    }
                }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        FirebaseAuth.getInstance().signOut();

        if (isRegistrationClicked) {
            Toast.makeText(getApplicationContext(), "Verify Email and Login", Toast.LENGTH_LONG).show();
        }
        startActivity(new Intent(getApplicationContext(), Login.class));
        super.finish();
    }
}

