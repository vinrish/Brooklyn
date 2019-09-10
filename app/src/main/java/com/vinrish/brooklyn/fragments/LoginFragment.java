package com.vinrish.brooklyn.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vinrish.brooklyn.MainActivity;
import com.vinrish.brooklyn.MySingleton;
import com.vinrish.brooklyn.R;
import com.vinrish.brooklyn.SessionHandler;
import com.vinrish.brooklyn.Utils.Constants;
import com.vinrish.brooklyn.model.ApiRequest;
import com.vinrish.brooklyn.model.ApiResponse;
import com.vinrish.brooklyn.model.User;
import com.vinrish.brooklyn.rest.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.Toast.LENGTH_LONG;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_OPERATION = "login";
    private TextInputEditText txtemail;
    private TextInputEditText txtpassword;
    private String email;
    private String password;
    private String login;
    private ProgressDialog pDialog;
    private AppCompatButton btn_login;
    private SessionHandler session;

    private static final String login_url = "http://10.42.0.1/php/login.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {

        session = new SessionHandler(getContext());

        if(session.isLoggedIn()){
            loadDashboard();
        }

        txtemail = view.findViewById(R.id.email);
        txtpassword = view.findViewById(R.id.password);
        btn_login = view.findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this);
    }

    private void loadDashboard() {
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_login:

                String email = txtemail.getText().toString().trim();
                String password = txtpassword.getText().toString().trim();

                if (!email.isEmpty() && !password.isEmpty()) {

                    if (!Constants.isValidEmail(email)) {
                        Toast.makeText(getActivity(),"enter a valid email", Toast.LENGTH_LONG).show();
                    }else  {
                        try {
                            loginProcess();
                        } catch (Exception e) {

                            e.printStackTrace();

                        }
                    }
                } else {
                    Toast.makeText(getActivity(),"input fields", Toast.LENGTH_LONG).show();
                }
        }

    }

    private void displayLoader() {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Logging In.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    private void loginProcess() {
//
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Constants.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .client(getUnsafeOkHttpClient().build())
//                .build();
//
//        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
//
//        User user = new User();
//        user.setEmail(email);
//        user.setPassword(password);
//        ApiRequest request = new ApiRequest();
//        request.setOperation(Constants.LOGIN_OPERATION);
//        request.setUser(user);
//
//        Call<ApiResponse> call = apiInterface.operation(request);
//
//        call.enqueue(new Callback<ApiResponse>() {
//            @Override
//            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//
//                ApiResponse resp = response.body();
//
//                if (resp != null) {
//                    Snackbar.make(getView(), "status: " + response.code() + "\t" + resp.getMessage(), Snackbar.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ApiResponse> call, Throwable t) {
//
//                Log.d(Constants.TAG, "failed");
//                Toast.makeText(getActivity(), t.getLocalizedMessage(), LENGTH_LONG).show();
//
//            }
//        });

        displayLoader();

        JSONObject request = new JSONObject();

        try {

            request.put(KEY_OPERATION, login);
            request.put(KEY_EMAIL, txtemail);
            request.put(KEY_PASSWORD, password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, login_url, request, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pDialog.dismiss();

                try {
                    if (response.getString("message").equalsIgnoreCase("login successful")){

                        Toast.makeText(getContext(), "welcome rish", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {

                    Toast.makeText(getActivity(),"something went wrong !!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

//                try {
//                    Toast.makeText(getContext(), "rest" + response.getJSONObject("user"), Toast.LENGTH_SHORT).show();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

//                GsonBuilder gsonBuilder = new GsonBuilder();
//                Gson gson = gsonBuilder.create();
//                 try {
//                     if (response.getInt(KEY_STATUS) == 0) {
//                         response.toString();
//                         session.loginUser(txtemail, (String) response.get(KEY_NAME));
//                         loadDashboard();
//                     } else {
//                         Toast.makeText(getContext(),
//                                 response.getString(KEY_MESSAGE), Toast.LENGTH_SHORT).show();
//                     }
//                 } catch (JSONException e) {
//                     e.printStackTrace();
//                 }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();

                //Display error message whenever an error occurs
                Toast.makeText(getContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);


    }

    public static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
