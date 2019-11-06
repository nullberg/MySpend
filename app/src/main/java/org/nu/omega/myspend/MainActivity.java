

package org.nu.omega.myspend;

import androidx.appcompat.app.AppCompatActivity;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private void myAlert(String title, String message) {

        // Alert Dialog
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(title);

        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }


    // for this to work you ***MUST***

    // (1) Enable storage permission for the app, in
    //     Settings -> Apps -> Permissions -> Storage -> [enable permission for app]

    // (2) In app/manifests/AndroidManifests.xml, include
    //     <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    //     <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />


    private void writeToFile_master(
            String timestamp, String category, String amount, String currency) {

        File root = android.os.Environment.getExternalStorageDirectory();
        File dir = new File(root.getAbsolutePath()+"/Documents/MySpend/");
        //dir.mkdirs(); // already made the dir

        try {

            File file = new File(dir, "myspend_data.txt");     // note:  doesn't actually create file

            // creates file, or leaves it alone if it exists.
            // append mode is true so no loss of data
            FileOutputStream f = new FileOutputStream(file, true);

            PrintWriter pw = new PrintWriter(f);
            pw.println(timestamp + "\t" + category + "\t" + amount + "\t" + currency);
            pw.flush();
            pw.close();
            f.close();
            myAlert("Success!", "Wrote to\n/Documents/MySpend/myspend_data.txt\n\n" +
                    "@ " + timestamp + "\n\n" +
                    "for " + amount + " " + currency + " " + category);

        } catch (Exception e) {
            myAlert("Exception was caught!!",e.toString());

        }

    }

    // after submission, call this func to reset the text of the input field,
    // and select all.
    public void emptyTheInputField() {

        EditText et = findViewById(R.id.moneyValue);
        et.setText("0.00");
        et.selectAll();

    }



    public void exportEntry(View view) {

        // amount
        EditText et = findViewById(R.id.moneyValue);
        String amount = et.getText().toString();

        // currency
        Spinner sp_currency = findViewById(R.id.mycurrency);   // casting is redundant
        String currency = sp_currency.getSelectedItem().toString();

        // category
        Spinner sp_category = findViewById(R.id.mycategory);
        String category = sp_category.getSelectedItem().toString();

        // timestamp
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss.SSS.z");
        String timestamp = sdf.format(new Date());
        String ts_date = timestamp.substring(0, 10);
        String ts_time = timestamp.substring(10, 23);
        String ts_zone = timestamp.substring(24);
        timestamp = ts_date + "\t" + ts_time + "\t" + ts_zone;


        // check that "amount" is float, and if it is, call writeToFile
        try {

            float amt = Float.parseFloat(amount);
            amount = String.format("%.2f",amt);

            // write to file
            writeToFile_master(timestamp, category, amount, currency);
            emptyTheInputField();


        } catch (Exception e) {
            myAlert("AMOUNT must be float", e.toString());
        }



    }



    private String myProbe() {

        File root = android.os.Environment.getExternalStorageDirectory();
        //File dir = new File (root.getAbsolutePath() + "/Documents/MySpend");
        File dir = new File(root.getAbsolutePath()+"/Documents/MySpend/");

        //File file = new File(dir, "myData.txt");
        try {
            //File[] l = dir.listFiles();
            //return l.toString();
            //return dir.getAbsolutePath();
            //return files.toString();

            if(dir.exists() && dir.isDirectory())
                return "dir exists";
            else
                return "dir NOT exist";


        } catch (Exception e) {
            return e.toString();
        }

        //return file.getAbsolutePath();

    }

}



// Notes
// ----------------
// you could use a Snackbar (https://developer.android.com/training/snackbar)
// but i'll try and use a dialog (https://developer.android.com/guide/topics/ui/dialogs)

// Good info:
// info on Alert Dialog: https://stackoverflow.com/questions/26097513/android-simple-alert-dialog

// All related to saving to external storage:
// https://stackoverflow.com/questions/40087355/android-mkdirs-not-working
// https://stackoverflow.com/questions/51565897/saving-files-in-android-for-beginners-internal-external-storage
// https://stackoverflow.com/questions/14376807/how-to-read-write-string-from-a-file-in-android
// https://stackoverflow.com/questions/32304616/how-to-create-files-to-a-specific-folder-in-android-application
// https://stackoverflow.com/questions/8330276/write-a-file-in-external-storage-in-android

// Note for context:  Context context = getApplicationContext();

/*
    // How to write to external memory:
    private String checkExternalMedia() {

        boolean mExternalStorageAvailable;
        boolean mExternalStorageWriteable;
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // Can read and write the media
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // Can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else {
            // Can't read or write
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }
        return "External Media: readable=" + mExternalStorageAvailable +
                " writable="+mExternalStorageWriteable;
    }

 */

// A float gives you approx. 6-7 decimal digits precision while
// a double gives you approx. 15-16. Also the range of numbers is larger for double.

    /*
float amt = Float.parseFloat(amount);
DecimalFormat df = new DecimalFormat("#.##");
//amount = String.format("%s",df.format(amt));
amount = String.format("%.2f",amt);

     */