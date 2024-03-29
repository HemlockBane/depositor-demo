package com.example.android.depositor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.depositor.models.AccountDetails;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class MainActivity extends AppCompatActivity {

    public final String TAG = MainActivity.class.getSimpleName();

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;

    Query accountQuery;

    EditText accountNumberEdit;
    Button acctNumberCheckButton;
    String accountNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialise all Firebase variables
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("accounts"); //reference to accounts


        //initialise all view variables
        accountNumberEdit = findViewById(R.id.account_number_edit);
        acctNumberCheckButton = findViewById(R.id.account_number_button);


        acctNumberCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidatedAccountNumber(accountNumberEdit)){
                    accountNumber = accountNumberEdit.getText().toString().trim();
                    accountQuery = mDatabaseReference.orderByChild("accountNumber").equalTo(accountNumber);

                    mChildEventListener = new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            if (dataSnapshot.exists()){
                                AccountDetails accountDetails = dataSnapshot.getValue(AccountDetails.class);
                                String name = accountDetails.getAccountName();
                                String number = accountDetails.getAccountNumber();

                                Log.e(TAG, "Account Name is  " + name);
                                Log.e(TAG, "Account Number  " + number);

                            }else{



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
                            Log.e(TAG, "Account is invalid");

                        }
                    };
                    String accountNum = "0120120190";
                    accountQuery.addChildEventListener(mChildEventListener);


//                mDatabaseReference.orderByChild("accountNumber").equalTo(accountNum)
                }



            }
        });





//        public void validateAccountNumber(/*Pass in EditText*/){
//            if (!isValidAccount(/*Pass in EditText*/)){
//                account.setError("Please, enter a valid account");
//            }else{
//                //check if account exists in database
//            }
//        }
//        public boolean isValidAccount(EditText text){
//            CharSequence string = text.getText.toString();
//            return(!TextUtils.isEmpty(string) && string.length() == 10);
//        }



    }

    private boolean isValidAccount(EditText editText){
        CharSequence string = editText.getText().toString();
        return(!TextUtils.isEmpty(string) && string.length() == 10);
    }

    private boolean isValidatedAccountNumber(EditText editText){
        if(!isValidAccount(editText)){
            editText.setError("Enter valid account number");
            return false;
        }else{
            return true;

        }
    }
}
