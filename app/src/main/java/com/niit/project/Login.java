package com.niit.project;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "Crave Tap Eat";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private CallbackManager callbackManager;
    private ImageButton btRegister;
    private Button btLogin;
    private ImageView circle1;
    private static final String EMAIL = "email";
    TextView tvLogin;
    EditText ETusername,ETpassword;
    String  email, password;
    LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        callbackManager = CallbackManager.Factory.create();
        mAuth = FirebaseAuth.getInstance();

        btRegister = findViewById(R.id.btRegister);
        tvLogin    = findViewById(R.id.tvLogin);
        circle1    = findViewById(R.id.circle1);
        btLogin    = findViewById(R.id.btLogin);
        ETusername = findViewById(R.id.etUsername);
        ETpassword = findViewById(R.id.etPassword);

        btRegister.setOnClickListener(this);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = ETusername.getText().toString();
                password = ETpassword.getText().toString();
                signIn(email, password);
            }
        });



        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.d(TAG, "I have logged in for facebook stuff");
            }

            @Override
            public void onCancel() {
                // App code
                Log.d(TAG, "I couldn't log in for facebook stuff");

            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d(TAG, exception.toString());
            }
        });
    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(Login.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
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
            String name = ETusername.getText().toString();
            UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().
                    setDisplayName(name).build();
            user.updateProfile(profileChangeRequest);
        }else{
            // User is signed out
            Log.d(TAG, "onAuthStateChanged:signed_out");
        }
    }

    @Override
    public void onClick(View v) {
        if (v==btRegister){
            Intent a = new Intent(Login.this, RegisterActivity.class);
            Pair[] pairs = new Pair[1];
            pairs[0] = new Pair<View,String> (tvLogin,"login");
            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(Login.this,pairs);
            startActivity(a,activityOptions.toBundle());
        }
    }
}
