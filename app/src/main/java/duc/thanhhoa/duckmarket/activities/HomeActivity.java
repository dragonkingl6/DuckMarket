package duc.thanhhoa.duckmarket.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import duc.thanhhoa.duckmarket.R;
import duc.thanhhoa.duckmarket.fragments.HomeFragment;

public class HomeActivity extends AppCompatActivity {

    Fragment homefragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homefragment = new HomeFragment();
        loadFragment(homefragment);
    }

    private void loadFragment(Fragment homefragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_container, homefragment);
        transaction.commit();
    }
}