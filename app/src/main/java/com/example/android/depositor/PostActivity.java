package com.example.android.depositor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.depositor.models.Payment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostActivity extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mSubDatabaseReference;
    private ChildEventListener mChildEventListener;


    final String TAG = PostActivity.class.getSimpleName();

    private TextView accountNameText;
    private TextView accountNumberText;
    private TextView depositAmountText;
    private TextView depositorNameText;
    private TextView depositorPhoneNumberText;
    private TextView depositorEmailText;
    private ImageView postPaymentButton;
    private ImageView editPaymentButton;

    String pushID;
    String accountName;
    String accountNumber;
    String depositAmount;
    String depositorName;
    String depositorPhoneNumber;
    String depositorEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();

        accountNameText = findViewById(R.id.account_name_details);
        accountNumberText = findViewById(R.id.account_number_details);
        depositAmountText = findViewById(R.id.deposit_amount_details);
        depositorNameText = findViewById(R.id.depositor_name_details);
        depositorPhoneNumberText = findViewById(R.id.depositor_phone_details);
        depositorEmailText = findViewById(R.id.depositor_email_details);
        postPaymentButton = findViewById(R.id.post_button_details);
        editPaymentButton = findViewById(R.id.edit_button_details);

        //Get intent from previous activity
        Intent intent = getIntent();
        //Get bundle (i.e. passed data) from previous activity
        Bundle bundle = intent.getExtras();

        //Get content from bundle if bundle is not null
        if(bundle != null ){
            accountName = bundle.getString("accountName");
            accountNumber = bundle.getString("accountNumber");
            depositAmount = bundle.getString("depositAmount");
            depositorName = bundle.getString("depositorName");
            depositorPhoneNumber = bundle.getString("depositorPhone");

            Log.e(TAG, "depositorPhone" + depositorPhoneNumber );
            //Get depositor from bundle if depositor email bundle exists
            if(!TextUtils.isEmpty(bundle.getString("depositorEmail"))){
                depositorEmail = bundle.getString("depositorEmail");
            }

        }
        //Set the textviews
            accountNameText.setText(accountName);
            accountNumberText.setText(accountNumber);
            depositAmountText.setText(depositAmount);
            depositorNameText.setText(depositorName);
            depositorPhoneNumberText.setText(depositorPhoneNumber);
            depositorEmailText.setText(depositorEmail);


        postPaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSubDatabaseReference = mDatabaseReference.child("depositQueue").push();
                pushID = mSubDatabaseReference.getKey();
                Payment post = new Payment(pushID,
                        accountName,
                        accountNumber,
                        depositAmount,
                        depositorName,
                        depositorPhoneNumber,
                        depositorEmail);

                mSubDatabaseReference.setValue(post)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Write was successful!

                                Toast.makeText(PostActivity.this, "Successfully added to queue", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Write failed
                                Toast.makeText(PostActivity.this, "Failed to add to queue", Toast.LENGTH_SHORT).show();
                            }
                        });

                Intent intent = new Intent(PostActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        editPaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostActivity.this, DetailsActivity.class);
                startActivity(intent);


            }
        });


    }

    public void hasEmail(){

    }

    public void hasNoEmail(){

    }



}
