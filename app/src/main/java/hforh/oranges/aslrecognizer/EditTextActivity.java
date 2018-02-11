package hforh.oranges.aslrecognizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class EditTextActivity extends AppCompatActivity {

    public String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
    }

    protected void populateText() {
        EditText initialText = (EditText) findViewById(R.id.textBox);
        initialText.setText(text);

    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean doneEdit = ((CheckBox) view).isChecked();
        EditText editedText = (EditText) findViewById(R.id.textBox);
        if (doneEdit) {
            editedText.getText().toString(); // 1. feed this into translator
        }
    }
}
