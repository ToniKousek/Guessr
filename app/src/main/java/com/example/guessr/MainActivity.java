package com.example.guessr;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvQuestion;
    private TextView tvAnswer;
    private Button btnShowAnswer;
    private Button btnNext;

    private List<Flashcard> flashcards;
    private int currentCardIndex = 0;
    private boolean showingAnswer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvQuestion = findViewById(R.id.tvQuestion);
        btnShowAnswer = findViewById(R.id.btnShowAnswer);
        tvAnswer = findViewById(R.id.tvAnswer);
        // btnNext = findViewById(R.id.btnNext);

        flashcards = XMLParser.parseFlashcards(this);

        showQuestion();

        btnShowAnswer.setOnClickListener(view -> {
            if (showingAnswer) {
                currentCardIndex = (currentCardIndex + 1) % flashcards.size();
                showQuestion();
            } else {
                showAnswer();
            }
            showingAnswer = !showingAnswer;
        });
    }

    private void showQuestion() {
        tvQuestion.setText(flashcards.get(currentCardIndex).getQuestion());
        tvAnswer.setText("");
        btnShowAnswer.setText("Show Answer");
    }

    private void showAnswer() {
        tvAnswer.setText(flashcards.get(currentCardIndex).getAnswer());
        btnShowAnswer.setText("Show Next Question");
    }
}
