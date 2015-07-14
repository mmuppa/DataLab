package edu.uw.tacoma.mmuppa.datalab;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
                        implements  LoginFragment.MyMenuListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) return;

        SharedPreferences sharedPreferences =
                getSharedPreferences(getString(R.string.SHARED_PREFS), Context.MODE_PRIVATE );
        boolean loggedIn = sharedPreferences.getBoolean(getString(R.string.LOGGEDIN),
                Boolean.parseBoolean(getString(R.string.LOGGEDIN_DEFAULT)));

        Fragment fragment = null;
        if (!loggedIn) {
            fragment = new LoginFragment();
        } else {
            fragment = new MenuFragment();
        }
        getFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            SharedPreferences sharedPreferences =
                    getSharedPreferences(
                            getString(R.string.SHARED_PREFS), Context.MODE_PRIVATE);
            sharedPreferences
                    .edit()
                    .putBoolean(getString(R.string.LOGGEDIN), false)
                    .commit();

            Fragment fragment = getFragmentManager()
                    .findFragmentById(R.id.fragment_container);
            if (fragment instanceof  MenuFragment) {
                LoginFragment loginFragment = new LoginFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, loginFragment)
                        .commit();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startMenu() {
        MenuFragment menuFragment = new MenuFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, menuFragment)
                .commit();
    }
}
