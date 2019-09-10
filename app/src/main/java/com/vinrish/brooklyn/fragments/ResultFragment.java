package com.vinrish.brooklyn.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.vinrish.brooklyn.MySingleton;
import com.vinrish.brooklyn.R;
import com.vinrish.brooklyn.SessionHandler;

import org.json.JSONException;
import org.json.JSONObject;

public class ResultFragment extends Fragment {

    private static final String KEY_IMAGE = "image";
    private static final String KEY_STATUS = "status";
    private static final String KEY_REGISTRATION_NUMBER = "registration_number";
    private static final String KEY_SEMESTER = "semester";
    private static final String KEY_ACADEMIC_YEAR = "academic_year";
    private static final String KEY_EMPTY = "";
    private TextInputEditText Registration;
    private TextInputEditText Sem;
    private TextInputEditText academic;
    private String registration;
    private String semester;
    private String academic_year;
    private ProgressDialog pDialog;
    private static final String result_url = "http://10.42.0.1/vince/gallery.php";

    private SessionHandler session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {

        Registration = view.findViewById(R.id.registration);
        Sem = view.findViewById(R.id.semester);
        academic = view.findViewById(R.id.academic_year);

        AppCompatButton btn_result = view.findViewById(R.id.btn_result);

        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registration = Registration.getText().toString().trim();
                semester = Sem.getText().toString().trim();
                academic_year = academic.getText().toString().trim();

                if (validateInputs()) {

                    results();
                }
            }
        });
    }

    private void displayLoader() {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("fetching results.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    private void results() {

        displayLoader();
        JSONObject request = new JSONObject();

        try {

            request.put(KEY_REGISTRATION_NUMBER, registration);
            request.put(KEY_SEMESTER, semester);
            request.put(KEY_ACADEMIC_YEAR, academic_year);
        } catch (JSONException e) {

            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, result_url, request, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pDialog.dismiss();
                try {

                    if (response.getInt(KEY_STATUS) == 0 ) {

                        Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();

                Toast.makeText(getContext(), "failed to fetch response", Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);

    }

    private boolean validateInputs() {

        if(KEY_EMPTY.equals(registration)){
            Registration.setError("Registration number cannot be empty");
            Registration.requestFocus();
            return false;
        }

        if(KEY_EMPTY.equals(semester)){
            Sem.setError("Semester cannot be empty");
            Sem.requestFocus();
            return false;
        }

        if(KEY_EMPTY.equals(academic_year)){
            academic.setError("Academic Year cannot be empty");
            academic.requestFocus();
            return false;
        }
        return true;
    }
}
