package github.didikee.aes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private Button decrypt;
    private Button encrypt;
    private Button copyText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        decrypt = (Button) findViewById(R.id.decrypt);
        encrypt = (Button) findViewById(R.id.encypt);
        copyText = (Button) findViewById(R.id.copy);
        textView = (TextView) findViewById(R.id.textView);

        decrypt.setOnClickListener(this);
        encrypt.setOnClickListener(this);
        copyText.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.decrypt:
                decrypt();
                break;
            case R.id.encypt:
                encrypt();
                break;
            case R.id.copy:
                copyText();
                break;
        }
    }

    private void encrypt() {
        String text = editText.getText().toString();
        if (TextUtils.isEmpty(text)) {
            toast("无内容可加密");
            return;
        }
        String encrypt = MyNetUtil.encrypt(text);
        textView.setText(encrypt);
    }

    private void decrypt() {
        String text = editText.getText().toString();
        if (TextUtils.isEmpty(text)) {
            toast("无内容可解密");
            return;
        }
        String decrypt = MyNetUtil.decrypt(text);
        textView.setText(decrypt);
    }

    private void copyText() {
        String text = textView.getText().toString();
        if (TextUtils.isEmpty(text)) {
            toast("无内容可复制");
            return;
        }
        editText.setText(text);
        toast("内容已经写入文本框中");
    }

    private void toast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
