package com.example.mediamaster;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class SqliteDisplayActivity extends AppCompatActivity {

    private EditText editTextName, editTextAddress, editTextPhone;
    private DatabaseHelper databaseHelper;

    private FrameLayout updateCardViewContainer;
    private View updateCardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_display);

        updateCardViewContainer = findViewById(R.id.update_card_view_container);


        databaseHelper = new DatabaseHelper(this);

        // Read and display the data
        readData(null);


    }

    public void readData(View view) {
        Cursor cursor = databaseHelper.readData();

        TableLayout tableLayout = findViewById(R.id.table_display_data);

        tableLayout.removeAllViews(); // Clear existing rows

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(databaseHelper.COLUMN_NAME));
            String address = cursor.getString(cursor.getColumnIndexOrThrow(databaseHelper.COLUMN_ADDRESS));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(databaseHelper.COLUMN_PHONE));


            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
            ));

            // Create TextViews to hold the data
            TextView textViewId = new TextView(this);
            textViewId.setText(String.valueOf(id));
            TextView textViewName = new TextView(this);
            textViewName.setText(name);
            TextView textViewAddress = new TextView(this);
            textViewAddress.setText(address);
            TextView textViewPhone = new TextView(this);
            textViewPhone.setText(phone);

            // Create buttons for update and delete actions
            ImageButton buttonUpdate = new ImageButton(this);
    //            buttonUpdate.setText("Update");
            buttonUpdate.setImageResource(R.drawable.baseline_edit_24);
            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle update action here
                    // You can use the 'id' to identify the row to update
                    displayUpdateCardView();

                }

                private void displayUpdateCardView() {
                    // Inflate the update card view
                    LayoutInflater inflater = LayoutInflater.from(SqliteDisplayActivity.this);
                    updateCardView = inflater.inflate(R.layout.update_card_view, updateCardViewContainer, false);

                    // Add the inflated view to the container
                    updateCardViewContainer.addView(updateCardView);

                    // Retrieve the existing data for the selected item to be updated
                    String existingId = textViewId.getText().toString();
                    String existingName = textViewName.getText().toString();  // Replace with the actual existing name
                    String existingAddress = textViewAddress.getText().toString();  // Replace with the actual existing address
                    String existingPhone = textViewPhone.getText().toString();  // Replace with the actual existing phone

                    // Update the views inside the card view with the existing data
                    EditText editTextUpdateName = updateCardView.findViewById(R.id.update_text_name);
                    EditText editTextUpdateAddress = updateCardView.findViewById(R.id.update_text_address);
                    EditText editTextUpdatePhone = updateCardView.findViewById(R.id.update_text_phone);

                    editTextUpdateName.setText(existingName);
                    editTextUpdateAddress.setText(existingAddress);
                    editTextUpdatePhone.setText(existingPhone);

                    Button save_btn = (Button) findViewById(R.id.button_save);

                    save_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Get the updated values from the EditText fields
                            String updatedName = editTextUpdateName.getText().toString();
                            String updatedAddress = editTextUpdateAddress.getText().toString();
                            String updatedPhone = editTextUpdatePhone.getText().toString();

                            // Update the values in the SQLite database
                            boolean isUpdated = databaseHelper.updateData(id, updatedName, updatedAddress, updatedPhone);

                            if (isUpdated) {
                                Toast.makeText(SqliteDisplayActivity.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                                // Perform any additional actions after updating the data
                            } else {
                                Toast.makeText(SqliteDisplayActivity.this, "Failed to update data", Toast.LENGTH_SHORT).show();
                            }

                            // Remove the update card view from the container
                            updateCardViewContainer.removeView(updateCardView);
                        }
                    });
                }
            });


            ImageButton buttonDelete = new ImageButton( SqliteDisplayActivity.this);
    //            buttonDelete.setText("Delete");
            buttonDelete.setImageResource(R.drawable.baseline_delete_24);
                buttonDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String deleteQuery = "DELETE FROM " + DatabaseHelper.TABLE_NAME + " WHERE id = " + id;

                        SQLiteDatabase db = databaseHelper.getWritableDatabase();
                        db.execSQL(deleteQuery);
                        db.close();

                        // Show a toast message indicating the delete was successful
                        Toast.makeText(SqliteDisplayActivity.this, "Data deleted successfully", Toast.LENGTH_SHORT).show();

                        // Remove the row from the table layout
                        tableLayout.removeView(row);
                    }
                });


                // Add the views to the row
            row.addView(textViewId);
            row.addView(textViewName);
            row.addView(textViewAddress);
            row.addView(textViewPhone);
            row.addView(buttonUpdate);
            row.addView(buttonDelete);

            // Add the row to the table layout
            tableLayout.addView(row);
        }
    }

}
