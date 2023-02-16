package com.example.register;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_result = findViewById(R.id.tv_result);

        findViewById(R.id.btn_move).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                getSubActivityResult.launch(intent); //startActivityForResult랑 동일한 기능 수행
            }
        });
    }

    private final ActivityResultLauncher<Intent> getSubActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                //서브 액티비티로부터 돌아올 때의 서브 액티비티에서 벌어지는 어떠한 결과 값을 받아 올 수 있는 통로
                if(result.getResultCode() == RESULT_OK){
                    //서브 액티비티의 입력 값을 메인에서 받아서 텍스트뷰에 표시
                    tv_result.setText(result.getData().getStringExtra("value"));
                }
            }
    );
}