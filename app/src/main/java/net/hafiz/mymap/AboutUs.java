package net.hafiz.mymap;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        // Enable the back button in the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Find the ImageView for the home button
        ImageView homeImageView = findViewById(R.id.imageView);

        // Set OnClickListener for the home button
        homeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the main page
                startActivity(new Intent(AboutUs.this, MainActivity.class));
                finish(); // Optional: finish the current activity
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case android.R.id.home:
                // Navigate back to the main page
                startActivity(new Intent(this, MainActivity.class));
                finish(); // Optional: finish the current activity
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
