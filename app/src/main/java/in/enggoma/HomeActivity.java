package in.enggoma;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.preference.PowerPreference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import in.enggoma.models.SQuery;

public class HomeActivity extends AppCompatActivity {

    View notes, questions, question_papers, answer_sheets, concepts, aboutus;
    TextView helloUser;
    SQuery query;
    CircleImageView circleImageView;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        PowerPreference.init(this);
        mAuth = FirebaseAuth.getInstance();
        query = new SQuery();

        notes = findViewById(R.id.notes);
        questions = findViewById(R.id.questions);
        question_papers = findViewById(R.id.question_papers);
        answer_sheets = findViewById(R.id.answer_sheets);
        concepts = findViewById(R.id.concepts);
        aboutus = findViewById(R.id.aboutus);
        helloUser = findViewById(R.id.tv_hello_user);
        circleImageView = findViewById(R.id.profile_pic);


        PowerPreference.init(this);




    helloUser.setText("Hello "+PowerPreference.getDefaultFile().getString("name"));
    Picasso.get().load(PowerPreference.getDefaultFile().getString("photo")).into(circleImageView);




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
