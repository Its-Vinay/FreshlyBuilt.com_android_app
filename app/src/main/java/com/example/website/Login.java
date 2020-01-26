package com.example.website;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.website.Api.RetrofitClient;
import com.example.website.Storage.SharedPrefManager;
import com.example.website.model.LoginResponse;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private TextInputEditText user_name,pass;
    private Button login;
    private ImageButton google,facebook;
    private TextView signup;
    private String nonce;
    private String generated_cookie;
    private GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN=0;
    private String s = "http://23269809514-lvnlm8mamft9quibvj08tuqbu8ve78ps.apps.googleusercontent.com/";
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        spinner = findViewById(R.id.progress_login);
        user_name = findViewById(R.id.username_login);
        pass = findViewById(R.id.pass_login);
        login = findViewById(R.id.login_login);
        signup = findViewById(R.id.signup_login);
        google = findViewById(R.id.google_login);
        facebook = findViewById(R.id.facebook_login);
        spinner.setVisibility(View.GONE);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 signIn();

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                userLogin();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Sign_Up.class));
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
               .requestServerAuthCode(s)
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(SharedPrefManager.getInstance(this).isLoggedIn()||account!=null){
            Intent intent = new Intent(this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);


            }
        }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            if(account!=null) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }

        }catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error : ", "signInResult:failed code=" + e.getStatusCode());

        }
    }

    public void userLogin(){
        String username,password;
        username = user_name.getText().toString().trim();
        password = pass.getText().toString().trim();
        if (username.isEmpty()) {
            user_name.setError("Empty field");
            user_name.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            pass.setError("Empty Field");
            pass.requestFocus();
            return;
        }
        Call<LoginResponse> call1 = RetrofitClient
                .getInstance()
                .getApi()
                .Nonce();
        call1.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call1, Response<LoginResponse> response) {
                LoginResponse lr1 = response.body();
                nonce=lr1.getNonce().trim();
                Toast.makeText(Login.this,nonce, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<LoginResponse> call1, Throwable t) {
                Toast.makeText(Login.this,"nonce not fetched", Toast.LENGTH_SHORT).show();
            }
        });
        
        Call<LoginResponse> call2 = RetrofitClient
                .getInstance()
                .getApi()
                .cookie(nonce,username,password);
  call2.enqueue(new Callback<LoginResponse>() {
      @Override
      public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
          try {
              spinner.setVisibility(View.GONE);
              LoginResponse loginResponse = response.body();
              String status = loginResponse.getStatus();
              Toast.makeText(Login.this,status, Toast.LENGTH_SHORT).show();
          } catch (Exception e) {
              Toast.makeText(Login.this, "exception error", Toast.LENGTH_SHORT).show();
          }
      }

      @Override
      public void onFailure(Call<LoginResponse> call, Throwable t) {
          Toast.makeText(Login.this, "onFailure", Toast.LENGTH_SHORT).show();

      }
  });

    /*    Call<LoginResponse> call3 = RetrofitClient
                .getInstance()
                .getApi()
                .Valid(generated_cookie);
        call3.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
               try {
                   spinner.setVisibility(View.GONE);
                   LoginResponse lr3 = response.body();
                   boolean condition = lr3.getValid();
                   if(condition==true) {
                       Toast.makeText(Login.this, "true", Toast.LENGTH_SHORT).show();
                   }else{
                       Toast.makeText(Login.this, "false", Toast.LENGTH_SHORT).show();
                   }
                   
                   if (lr3.getValid()) {

                       SharedPrefManager.getInstance(Login.this)
                               .saveUser(username, password);
                       Intent intent = new Intent(Login.this, MainActivity.class);
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                       startActivity(intent);
                   } else {
                       Toast.makeText(Login.this, "Invalid Credential", Toast.LENGTH_SHORT).show();
                   }

               }
               catch(Exception e){
                   Toast.makeText(Login.this, "valid is false", Toast.LENGTH_SHORT).show();
               }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(Login.this, "Please try after sometime", Toast.LENGTH_SHORT).show();
            }
        }); */

    }

}
