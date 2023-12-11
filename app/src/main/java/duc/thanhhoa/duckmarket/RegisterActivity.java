package duc.thanhhoa.duckmarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import duc.thanhhoa.duckmarket.models.UserModel;

public class RegisterActivity extends AppCompatActivity {

    Button signUp;
    EditText name, email,pasword;
    TextView signIn;

    FirebaseAuth auth;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth= FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();

        signUp=findViewById(R.id.login_btn);
        name=findViewById(R.id.name_reg);
        email=findViewById(R.id.email_reg);
        pasword=findViewById(R.id.password_reg);
        signIn=findViewById(R.id.sign_in);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

            }
        });

    }

    private void createUser() {
        String userName = name.getText().toString().trim();
        String userEmail = email.getText().toString().trim();
        String userPassword = pasword.getText().toString().trim();

        if (TextUtils.isEmpty(userName)) {
            name.setError("Name is Required");
            Toast.makeText(this, "Name is Required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userEmail)) {
            email.setError("Name is Required1");
            Toast.makeText(this, "Name is Required1", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPassword)) {
            pasword.setError("Name is Required2");
            Toast.makeText(this, "Name is Required2", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userPassword.length() < 6) {
            pasword.setError("Password must be >= 6 Characters");
            Toast.makeText(this, "Password must be >= 6 Characters", Toast.LENGTH_SHORT).show();
            return;
        }

        //create user

        auth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            UserModel userModel= new UserModel(userName,userEmail,userPassword);
                            //realtime database
                            String id= task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(userModel);
                            Toast.makeText(RegisterActivity.this,
                                    "User Created.", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(RegisterActivity.this,
                                    "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}