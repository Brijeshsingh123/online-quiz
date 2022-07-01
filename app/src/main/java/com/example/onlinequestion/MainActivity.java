package com.example.onlinequestion;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import com.example.onlinequestion.databinding.ActivityMainBinding;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences.Editor editor;
    ActivityMainBinding binding;
    String timer;
    private CountDownTimer countDownTimer;
    private static final long long_Countdown_timer=30000;
    private static final long long_Countdown_interval=1000;
    private long time_left;
    private int correct=0,wrong=0,score=0,updated=0;
    String question[]={
            "Q.1. If a Computer has more then one processor then it is konw  as ?",
            "Q.2. Full form of URL ?",
            "Q.3. One HiloByte (KB) is equals to ?"
    };
    int img[]={
            R.drawable.download1,
            R.drawable.download2,
            R.drawable.download3
    };

    String answer[]={
            "Multiprocessor",
            "Uniform Resource Locator",
            "1,024 bytes"
    };
    String opstion[]={
            "Uniprocess","Multiprocessor","MultiThread","Multiprograming",
            "Uniform Resource Locator","Uniform Resource Location","gysdyg","ydadygad",
            "1,000 bytes","1,024 bytes","1,064 bytes","1,394 bytes"
    };
    int qIndex=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editor=getApplicationContext().getSharedPreferences("loginpref", MODE_PRIVATE).edit();
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
//        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        editor.putInt("correct",correct);
        editor.putInt("wrong",wrong);
        editor.apply();
        time_left=long_Countdown_timer;
        startCountDown();
        binding.noQoestion.setText("0"+"/"+question.length);
        binding.question.setText(question[qIndex]);
        binding.img.setImageResource(img[qIndex]);
        binding.radio1.setText(opstion[0]);
        binding.radio2.setText(opstion[1]);
        binding.radio3.setText(opstion[2]);
        binding.radio4.setText(opstion[3]);
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.radigroup.getCheckedRadioButtonId()==-1)
                {
                    alertbox("Please Select an Option");
                }else {
                    showNextQuestion();
                }
            }
        });
    }

    private void startCountDown() {
        countDownTimer=new CountDownTimer(time_left,long_Countdown_interval) {
            @Override
            public void onTick(long l) {

                time_left=l;
                int second=(int) TimeUnit.MILLISECONDS.toSeconds(time_left);
                timer=String.format(Locale.getDefault(),"%02d",second);
                binding.tvtime.setText(timer);
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private void showNextQuestion() {
        chechAnswer();
        updated=updated+1;
        binding.noQoestion.setText(updated+"/"+question.length);
        editor.putInt("updated",updated);
        editor.apply();

//        else {
//            startActivity(new Intent(MainActivity.this,Result.class));
//        }
    }

    private void chechAnswer() {

        if (binding.radigroup.getCheckedRadioButtonId()==-1)
        {
            alertbox("Please Select an Option");
        }else {
            RadioButton button=findViewById(binding.radigroup.getCheckedRadioButtonId());
            String checkAnswer=button.getText().toString();

            if (checkAnswer.equals(answer[qIndex]))
            {
                correct=correct+1;
                binding.score.setText(""+correct);
                editor.putInt("correct",correct);
                editor.apply();
                countDownTimer.cancel();
                alertbox("Correct Answer");
            }else {
                wrong=wrong+1;
                editor.putInt("wrong",wrong);
                editor.apply();
                countDownTimer.cancel();
                alertbox("Wrong Answer");
            }
        }
        qIndex++;
        binding.radigroup.clearCheck();
    }
    private void alertbox(String data) {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.alert_dialog);
        TextView view=dialog.findViewById(R.id.text_dialog);
        GifImageView gif=dialog.findViewById(R.id.gif);
        view.setText(data);
        Button  button=dialog.findViewById(R.id.btn_ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (data.equals("Please Select an Option"))
                {
                    dialog.dismiss();
                }else {
                    time_left=long_Countdown_timer;
                    startCountDown();
                    if (qIndex < question.length) {
                        ((GifDrawable) gif.getDrawable()).start();
                        binding.question.setText(question[qIndex]);
                        binding.img.setImageResource(img[qIndex]);
                        binding.radio1.setText(opstion[qIndex * 4]);
                        binding.radio2.setText(opstion[qIndex * 4 + 1]);
                        binding.radio3.setText(opstion[qIndex * 4 + 2]);
                        binding.radio4.setText(opstion[qIndex * 4 + 3]);
                        dialog.dismiss();
                    } else {
                        startActivity(new Intent(MainActivity.this, Result.class));
                    }
                }
            }
        });
        dialog.show();
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(MainActivity.this,MainActivity.class));
//        showDialog(this, getString(R.string.exit), true, true, AppConstants.DIALOG_LOGIN_BACK_ID);
    }
}