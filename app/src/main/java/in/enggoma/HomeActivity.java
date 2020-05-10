package in.enggoma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.preference.PowerPreference;

import in.enggoma.models.SQuery;

public class HomeActivity extends AppCompatActivity {

    View notes, questions, question_papers, answer_sheets, concepts, aboutus;
    SQuery query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        PowerPreference.init(this);
        query = new SQuery();

        notes = (View) findViewById(R.id.notes);
        questions = (View) findViewById(R.id.questions);
        question_papers = (View) findViewById(R.id.question_papers);
        answer_sheets = (View) findViewById(R.id.answer_sheets);
        concepts = (View) findViewById(R.id.concepts);
        aboutus = (View) findViewById(R.id.aboutus);

        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query.setType("notes");
                Intent i = new Intent(HomeActivity.this, SelectActivity.class);
                PowerPreference.getDefaultFile().setObject("query", query);
                startActivity(i);
            }
        });

        questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query.setType("questions");
                Intent i = new Intent(HomeActivity.this, SelectActivity.class);
                PowerPreference.getDefaultFile().setObject("query", query);
                startActivity(i);
            }
        });

        question_papers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query.setType("question_papers");
                Intent i = new Intent(HomeActivity.this, SelectActivity.class);
                PowerPreference.getDefaultFile().setObject("query", query);
                startActivity(i);
            }
        });

        answer_sheets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query.setType("answer_sheets");
                Intent i = new Intent(HomeActivity.this, SelectActivity.class);
                PowerPreference.getDefaultFile().setObject("query", query);
                startActivity(i);
            }
        });

        concepts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query.setType("concepts");
                Intent i = new Intent(HomeActivity.this, SelectActivity.class);
                PowerPreference.getDefaultFile().setObject("query", query);
                startActivity(i);
            }
        });

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, AboutUsActivity.class);
                PowerPreference.getDefaultFile().setObject("query", query);
                startActivity(i);
            }
        });


    }
}
