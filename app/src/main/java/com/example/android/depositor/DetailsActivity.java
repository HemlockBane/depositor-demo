package com.example.android.depositor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.depositor.models.AccountDetails;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DetailsActivity extends AppCompatActivity {

    public final String TAG = DetailsActivity.class.getSimpleName();
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    Query accountQuery;

    private TextView accountNameText;
    private TextView accountNumberText;
    private EditText depositAmountEdit;
    private EditText depositorNameEdit;
    private EditText depositorPhoneEdit;
    private EditText depositorEmailEdit;
    private Button nextButton;
    private ImageView chevronright;

    String passedAccountNumber;
    String accountName;
    String accountNumber;
    String depositAmount;
    String depositorName;
    String depositorPhone;
    String depositorEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        accountNameText = findViewById(R.id.account_name_text);
        accountNumberText = findViewById(R.id.account_number_text);
        depositAmountEdit = findViewById(R.id.deposit_amount_edit);
        depositorNameEdit = findViewById(R.id.depositor_name_edit);
        depositorPhoneEdit = findViewById(R.id.deposit_phone_edit);
        depositorEmailEdit = findViewById(R.id.deposit_email_edit);
//        nextButton = findViewById(R.id.next_button);
        chevronright = findViewById(R.id.right_button);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("accounts");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            passedAccountNumber = bundle.getString("accountNumber");
            Log.e(TAG, "Passed account number" + passedAccountNumber);
        }

        accountQuery = mDatabaseReference.orderByChild("accountNumber").equalTo(passedAccountNumber);

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.exists()){
                    //pass snapshot to class for deserialization
                    AccountDetails accountDetails = dataSnapshot.getValue(AccountDetails.class);
                    accountName = accountDetails.getAccountName();
                    accountNumber = accountDetails.getAccountNumber();
                    //Set account name and number TextViews account name and number
                    accountNameText.setText(accountName);
                    accountNumberText.setText(accountNumber);

                    Log.e(TAG, "accountName: " + accountName);
                    Log.e(TAG, "accountNumber: " + accountNumber);

                }


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        };
        accountQuery.addChildEventListener(mChildEventListener);
        chevronright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if data entries are invalid, print toast. Else, pass details to next activity
                if(!validateEntries()){
                    Toast.makeText(DetailsActivity.this, "Please, fill the required fields", Toast.LENGTH_SHORT).show();

                }else{
                    Log.e(TAG, "accountName: " + accountName);
                    Log.e(TAG, "accountNumber: " + accountNumber);

                    depositAmount = depositAmountEdit.getText().toString();
                    depositorName = depositorNameEdit.getText().toString();
                    depositorPhone = depositorPhoneEdit.getText().toString();
                    depositorEmail = depositorEmailEdit.getText().toString();

                    Log.e(TAG, "accountName: " + accountName);
                    Log.e(TAG, "accountNumber: " + accountNumber);
                    Log.e(TAG, "depositAmount: " + depositAmount);
                    Log.e(TAG, "depositorName: " + depositorName);
                    Log.e(TAG, "depositorPhone: " + depositorPhone);
                    Log.e(TAG, "depositorEmail: " + depositorEmail);


                    Intent intent = new Intent(DetailsActivity.this, PostActivity.class);
                    intent.putExtra("accountName", accountName);
                    intent.putExtra("accountNumber", accountNumber);
                    intent.putExtra("depositAmount", depositAmount);
                    intent.putExtra("depositorName", depositorName);
                    intent.putExtra("depositorPhone", depositorPhone);
                    intent.putExtra("depositorEmail", depositorEmail);
                    startActivity(intent);
                }
            }
        });


    }

    private boolean validateEntries(){
        boolean valid = true;
        if (isEmpty(depositAmountEdit)){
            depositAmountEdit.setError("Please, enter cash amount to deposit");
            valid = false;
        }

        if (isEmpty(depositorNameEdit)){
            depositorNameEdit.setError("Please, enter depositor's name");
            valid = false;
        }

        //check if number is not valid (i.e. if at least of the test conditions is false)
        if (!isValidPhoneNumber(depositorPhoneEdit)){
            depositorPhoneEdit.setError("Please, enter depositor's phone number");
            valid = false;

        }

        if (!validateOptionalEmail(depositorEmailEdit)){
            depositorEmailEdit.setError("Please enter a valid email");
            valid = false;
        }
        return valid;
    }

    private boolean isValidPhoneNumber(EditText text){
        CharSequence string = text.getText().toString();
        //check if the number is equal to 11 digits and if number is not empty
        //both conditions should return true
        return(!TextUtils.isEmpty(string) && string.length() == 11);
    }

    private boolean validateOptionalEmail(EditText text){
        CharSequence string = text.getText().toString();
        //check if email matches standard email pattern
        //should return true
        return (Patterns.EMAIL_ADDRESS.matcher(string).matches());
    }

    private boolean isEmpty(EditText text){
        CharSequence string = text.getText().toString();
        return TextUtils.isEmpty(string);
    }
}
