package duc.thanhhoa.duckmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import duc.thanhhoa.duckmarket.activities.HomeActivity;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth= FirebaseAuth.getInstance();
        progressBar= findViewById(R.id.progressBar_splash);
        progressBar.setVisibility(View.GONE);

        if (auth.getCurrentUser()!=null){
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            Toast.makeText(this, "User already logged in", Toast.LENGTH_SHORT).show();
            finish();
        }

        //new branch develop
    }

    public void login(View view) {
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
    }

    public void register(View view) {
        startActivity(new Intent(MainActivity.this,RegisterActivity.class));
    }
}