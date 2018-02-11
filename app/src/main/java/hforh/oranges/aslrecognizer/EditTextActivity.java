package hforh.oranges.aslrecognizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class EditTextActivity extends AppCompatActivity {

    public String textToTranslate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        getTextFromIntent();
        populateText();
    }

    protected void populateText() {
        EditText initialText = (EditText) findViewById(R.id.textBox);
        initialText.setText(textToTranslate);

    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean doneEdit = ((CheckBox) view).isChecked();
        EditText editedText = (EditText) findViewById(R.id.textBox);
        if (doneEdit) {
            textToTranslate = editedText.getText().toString(); // feed this into translator
        }
        // Intent intent = new Intent(EditTextActivity.this, )
    }

    public void getTextFromIntent() {
        Intent intent = getIntent();
        textToTranslate = intent.getStringExtra("textToTranslate");
    }
}
