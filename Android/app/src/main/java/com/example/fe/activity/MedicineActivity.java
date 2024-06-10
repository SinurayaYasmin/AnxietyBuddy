package com.example.fe.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.fe.model.Adapter;
import com.google.android.material.tabs.TabLayout;
import com.example.fe.fragment.AllergyFragment;
import com.example.fe.fragment.SkinFragment;
import com.example.fe.fragment.MouthFragment;
import com.example.fe.fragment.VitaminFragment;
import com.example.fe.fragment.FeverFragment;
import com.example.fe.R;

public class MedicineActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        Adapter adapter = new Adapter(
                getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        );

        // Adding fragments to the adapter
        adapter.addFragment(new SkinFragment(), "Skin");
        adapter.addFragment(new MouthFragment(), "Mouth");
        adapter.addFragment(new FeverFragment(), "Fever");
        adapter.addFragment(new AllergyFragment(), "Allergy");
        adapter.addFragment(new VitaminFragment(), "Vitamin");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}

