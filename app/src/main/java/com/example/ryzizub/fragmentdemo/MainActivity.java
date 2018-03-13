package com.example.ryzizub.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Fragment2.OnFragmentInteractionListener {
    private static final String MAIN_FRAGMENT_TAG = "main_fragment";
    private static final String SECOND_FRAGMENT_TAG = "second_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button b = (Button) findViewById(R.id.f1);
        Button b2 = (Button) findViewById(R.id.f2);
        Button b3 = (Button) findViewById(R.id.f3);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(1);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(2);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(3);
            }
        });
    }

    private void switchFragment(int id) {
        FragmentManager fm = getSupportFragmentManager();

        switch (id) {
            case 1: {
                Fragment secondFragment = fm.findFragmentByTag(SECOND_FRAGMENT_TAG);
                FragmentTransaction ft = fm.beginTransaction()
                        .replace(R.id.container, new Fragment1(), MAIN_FRAGMENT_TAG);

                if (secondFragment != null) {
                    ft.remove(secondFragment);
                }

                ft.commit();
                break;
            }
            case 2: {
                Fragment secondFragment = fm.findFragmentByTag(SECOND_FRAGMENT_TAG);
                FragmentTransaction ft = fm.beginTransaction()
                        .replace(R.id.container, Fragment2.newInstance("First", "Second"), MAIN_FRAGMENT_TAG);

                if (secondFragment != null) {
                    ft.remove(secondFragment);
                }

                ft.commit();
                break;
            }
            case 3: {
                Fragment3A fa = new Fragment3A();
                fa.setString("Text");

                Fragment3B fb = new Fragment3B();
                fb.setString("Text");

                fm.beginTransaction()
                        .replace(R.id.container, fa, MAIN_FRAGMENT_TAG)
                        .replace(R.id.container2, fb, SECOND_FRAGMENT_TAG)
                        .commit();
                break;
            }
            default:
                throw new RuntimeException("Unknown ID");
        }
    }

    @Override
    public void onFragmentInteraction() {
        Toast.makeText(this, "Fragment Callback", Toast.LENGTH_SHORT).show();
    }
}
