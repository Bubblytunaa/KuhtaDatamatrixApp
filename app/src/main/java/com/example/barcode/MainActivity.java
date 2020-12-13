package com.example.barcode;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.barcode.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.DataMatrixWriter;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button_ok;
    private ImageView barCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        barCode = findViewById(R.id.barCode);
        button_ok = findViewById(R.id.save);

    }

    @SuppressLint("SetTextI18n")
    public void generate(View view) {
        String inputText = editText.getText().toString();
        if( !inputText.isEmpty() ) {
            DataMatrixWriter dataMatrixWriter = new DataMatrixWriter();
            BitMatrix bitMatrix = dataMatrixWriter.encode(inputText, BarcodeFormat.DATA_MATRIX, 300, 300, null);
            int[] pixels = new int[300 * 300];
            System.out.println("getHeight " + bitMatrix.getHeight());
            System.out.println("getWeight " + bitMatrix.getWidth());
            for (int i = 0; i < bitMatrix.getHeight(); i++) {

                for (int j = 0; j < bitMatrix.getWidth(); j++) {

                    int offset = i * 100;
                    pixels[offset + j] = bitMatrix.get(j, i) ? Color.BLACK : Color.WHITE;
                    System.out.println("offset - " + offset);
                }
            }

            Bitmap bitmap = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, 100, 5, 0, 20, 20);
            barCode.setImageBitmap(bitmap);
        }
        else {
            editText.setText("string is empty");
        }

    }
}


