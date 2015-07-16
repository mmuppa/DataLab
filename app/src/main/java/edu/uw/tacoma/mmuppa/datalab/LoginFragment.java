package edu.uw.tacoma.mmuppa.datalab;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.OutputStreamWriter;

import edu.uw.tacoma.mmuppa.datalab.data.UserInfoDB;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    public interface MyMenuListener {
        public void startMenu();
    }

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        Button loginButton = (Button) v.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateAndStore()) {
                    SharedPreferences sharedPreferences =
                            getActivity().getSharedPreferences(
                                    getString(R.string.SHARED_PREFS), Context.MODE_PRIVATE);
                    sharedPreferences
                            .edit()
                            .putBoolean(getString(R.string.LOGGEDIN), true)
                            .commit();

                    ((MyMenuListener) getActivity()).startMenu();
                }
            }
        });

        return v;
    }


    private boolean validateAndStore() {
        Activity activity = getActivity();
        EditText email = (EditText) activity.findViewById(R.id.email); if (email.getText().length() == 0) {
            Toast.makeText(activity, "Please enter email", Toast.LENGTH_LONG)
                    .show(); email.requestFocus(); return false;
        }
        EditText pwd = (EditText) activity.findViewById(R.id.pwd); if (pwd.getText().length() == 0) {
            Toast.makeText(activity, "Please enter password", Toast.LENGTH_LONG)
                    .show(); pwd.requestFocus(); return false;
        }
        //Store in file
        try {
            OutputStreamWriter outputStreamWriter = new
                    OutputStreamWriter(
                    activity.openFileOutput(getString(R.string.ACCT_FILE) , Context.MODE_PRIVATE));
            outputStreamWriter.write("email = " + email.getText().toString() + ";");
            outputStreamWriter.write("password = " + pwd.getText().toString());
            outputStreamWriter.close();
            Toast.makeText(activity,"Stored in File Successfully!", Toast.LENGTH_LONG)
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        // Store using SQLite
        UserInfoDB userInfoDB = new UserInfoDB(activity);
        boolean inserted =
                userInfoDB.insertUser(email.getText().toString(), pwd.getText().toString());
        if (inserted) {
            Toast.makeText(activity, "Stored user information successfully!", Toast.LENGTH_SHORT)
                        .show();
        } else {
            Toast.makeText(activity, "Storing user information failed!", Toast.LENGTH_SHORT)
                    .show();
        }
        userInfoDB.closeDB();

        return true;
    }
}
