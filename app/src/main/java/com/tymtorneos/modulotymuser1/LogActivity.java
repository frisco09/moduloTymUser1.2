package com.tymtorneos.modulotymuser1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LogActivity extends AppCompatActivity implements View.OnClickListener {


    // UI references.
    private EditText Name;
    private EditText Password;
    private Button Login;
    private Button Registro;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        Name = (EditText)findViewById(R.id.txtUser);
        Password = (EditText)findViewById(R.id.txtPass);
        Login = (Button)findViewById(R.id.btnIngresar);
        Registro = (Button)findViewById(R.id.btCrearCuenta);
    }

    private void validate(final String user_name, final String user_password){

        //Login.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LogActivity.this, R.style.dialogLogin);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Autenticando...");
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess(user_name,user_password);
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 5000);
    }

    public void onLoginSuccess(String user_name, String user_password) {
        if((user_name.equals("user")) && (user_password.equals("1234"))){
            Intent intent = new Intent(LogActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }else{
            counter--;
            Login.setEnabled(true);

            if(counter == 0){
                Login.setEnabled(false);
            }
        }
        finish();
    }

    @Override
    public void onClick(View v) {
        validate(Name.getText().toString(), Password.getText().toString());
    }

 public void register(View v) {
   Intent intent = new Intent(LogActivity.this, RegistroActivity.class);
   startActivity(intent);
  overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
 }

}
/**
 * Callback received when a permissions request has been completed.

 @Override
 public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
 @NonNull int[] grantResults) {
 if (requestCode == REQUEST_READ_CONTACTS) {
 if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
 populateAutoComplete();
 }
 }
 }



 private void attemptLogin() {
 if (mAuthTask != null) {
 return;
 }

 // Reset errors.
 mEmailView.setError(null);
 mPasswordView.setError(null);

 // Store values at the time of the login attempt.
 String email = mEmailView.getText().toString();
 String password = mPasswordView.getText().toString();

 boolean cancel = false;
 View focusView = null;

 // Check for a valid password, if the user entered one.
 if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
 mPasswordView.setError(getString(R.string.error_invalid_password));
 focusView = mPasswordView;
 cancel = true;
 }

 // Check for a valid email address.
 if (TextUtils.isEmpty(email)) {
 mEmailView.setError(getString(R.string.error_field_required));
 focusView = mEmailView;
 cancel = true;
 } else if (!isEmailValid(email)) {
 mEmailView.setError(getString(R.string.error_invalid_email));
 focusView = mEmailView;
 cancel = true;
 }

 if (cancel) {
 // There was an error; don't attempt login and focus the first
 // form field with an error.
 focusView.requestFocus();
 } else {
 // Show a progress spinner, and kick off a background task to
 // perform the user login attempt.
 showProgress(true);
 mAuthTask = new UserLoginTask(email, password);
 mAuthTask.execute((Void) null);
 }
 }

 private boolean isEmailValid(String email) {
 //TODO: Replace this with your own logic
 return email.contains("@");
 }

 private boolean isPasswordValid(String password) {
 //TODO: Replace this with your own logic
 return password.length() > 4;
 }


 @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
 private void showProgress(final boolean show) {
 // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
 // for very easy animations. If available, use these APIs to fade-in
 // the progress spinner.
 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
 int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

 mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
 mLoginFormView.animate().setDuration(shortAnimTime).alpha(
 show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
 @Override
 public void onAnimationEnd(Animator animation) {
 mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
 }
 });

 mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
 mProgressView.animate().setDuration(shortAnimTime).alpha(
 show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
 @Override
 public void onAnimationEnd(Animator animation) {
 mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
 }
 });
 } else {
 // The ViewPropertyAnimator APIs are not available, so simply show
 // and hide the relevant UI components.
 mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
 mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
 }
 }

 @Override
 public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
 return new CursorLoader(this,
 // Retrieve data rows for the device user's 'profile' contact.
 Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
 ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

 // Select only email addresses.
 ContactsContract.Contacts.Data.MIMETYPE +
 " = ?", new String[]{ContactsContract.CommonDataKinds.Email
 .CONTENT_ITEM_TYPE},

 // Show primary email addresses first. Note that there won't be
 // a primary email address if the user hasn't specified one.
 ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
 }

 @Override
 public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
 List<String> emails = new ArrayList<>();
 cursor.moveToFirst();
 while (!cursor.isAfterLast()) {
 emails.add(cursor.getString(ProfileQuery.ADDRESS));
 cursor.moveToNext();
 }

 addEmailsToAutoComplete(emails);
 }

 @Override
 public void onLoaderReset(Loader<Cursor> cursorLoader) {

 }

 private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
 //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
 ArrayAdapter<String> adapter =
 new ArrayAdapter<>(LogActivity.this,
 android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

 mEmailView.setAdapter(adapter);
 }


 private interface ProfileQuery {
 String[] PROJECTION = {
 ContactsContract.CommonDataKinds.Email.ADDRESS,
 ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
 };

 int ADDRESS = 0;
 int IS_PRIMARY = 1;
 }


 public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

 private final String mEmail;
 private final String mPassword;

 UserLoginTask(String email, String password) {
 mEmail = email;
 mPassword = password;
 }

 @Override
 protected Boolean doInBackground(Void... params) {
 //TODO: attempt authentication against a network service.

 try {
 // Simulate network access.
 Thread.sleep(2000);
 } catch (InterruptedException e) {
 return false;
 }

 for (String credential : DUMMY_CREDENTIALS) {
 String[] pieces = credential.split(":");
 if (pieces[0].equals(mEmail)) {
 // Account exists, return true if the password matches.
 return pieces[1].equals(mPassword);
 }
 }

 // TODO: register the new account here.
 return true;
 }

 @Override
 protected void onPostExecute(final Boolean success) {
 mAuthTask = null;
 showProgress(false);

 if (success) {
 finish();
 } else {
 mPasswordView.setError(getString(R.string.error_incorrect_password));
 mPasswordView.requestFocus();
 }
 }

 @Override
 protected void onCancelled() {
 mAuthTask = null;
 showProgress(false);
 }
 }
 @Override
 public void onPointerCaptureChanged(boolean hasCapture) {

 }

 private void populateAutoComplete() {
 if (!mayRequestContacts()) {
 return;
 }

 getLoaderManager().initLoader(0, null, this);
 }

 private boolean mayRequestContacts() {
 if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
 return true;
 }
 if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
 return true;
 }
 if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
 Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
 .setAction(android.R.string.ok, new View.OnClickListener() {
 @Override
 @TargetApi(Build.VERSION_CODES.M)
 public void onClick(View v) {
 requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
 }
 });
 } else {
 requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
 }
 return false;
 }

 @Override
 public Loader<Cursor> onCreateLoader(int id, Bundle args) {
 return null;
 }

 @Override
 public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

 }

 @Override
 public void onLoaderReset(Loader<Cursor> loader) {

 }
 */
