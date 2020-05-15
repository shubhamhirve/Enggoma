package in.enggoma;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.james602152002.floatinglabelspinner.FloatingLabelSpinner;
import com.preference.PowerPreference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import in.enggoma.models.MaterialAdapter;
import in.enggoma.models.SQuery;

public class SelectActivity extends AppCompatActivity {

    FloatingLabelSpinner stream, semester, subject;
    MaterialAdapter adapter_stream, adapter_semester, adapter_subject;
    List<String> dataset_stream, dataset_semester, dataset_subject;
    SQuery query;
    private int current_node = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        getSupportActionBar().hide();
        PowerPreference.init(this);

        stream = (FloatingLabelSpinner) findViewById(R.id.spinner_stream);
        semester = (FloatingLabelSpinner) findViewById(R.id.spinner_semester);
        subject = (FloatingLabelSpinner) findViewById(R.id.spinner_subject);
        query = new SQuery();

        dataset_stream = new ArrayList<>(Arrays.asList("Computer Engineering", "Computer Technology", "Information Technology", "Electronics & Telecommunication"));

        dataset_semester = new LinkedList<>(Arrays.asList("1st Semester", "2nd Semester", "3rd Semester", "4th Semester", "5th Semester", "6th Semester"));

        dataset_subject = new LinkedList<>(Arrays.asList("Computer Engineering", "Computer Technology", "Information Technology", "Electronics & Telecommunication"));

        adapter_stream = new MaterialAdapter(this, dataset_stream);
        adapter_semester = new MaterialAdapter(this, dataset_semester);
        adapter_subject = new MaterialAdapter(this, dataset_subject);

        stream.setAdapter(adapter_stream);
        semester.setAdapter(adapter_semester);
        subject.setAdapter(adapter_subject);



        stream.setDropDownHintView(getHintView());
        semester.setDropDownHintView(getHintView());
        subject.setDropDownHintView(getHintView());

        ////TODO verify code again. after click spinner not dismiss. or change to regular spinner

        stream.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        semester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    public View getHintView() {
        final int screen_width = getResources().getDisplayMetrics().widthPixels;
        final float label_text_size = screen_width / 25;
        final short margins = (short) (screen_width / 40);
        final short rect_height = (short) (screen_width / 60);
        View hintView = getLayoutInflater().inflate(R.layout.spinner_header, null, false);
        TextView title = hintView.findViewById(R.id.title);
        ImageView back = hintView.findViewById(R.id.back);
        View rect = hintView.findViewById(R.id.rect);

        ((RelativeLayout.LayoutParams) rect.getLayoutParams()).height = rect_height;
        rect.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        ((RelativeLayout.LayoutParams) title.getLayoutParams()).setMargins(margins, margins, margins, margins);
        hintView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        title.setTextSize(TypedValue.COMPLEX_UNIT_PX, label_text_size);
        ((RelativeLayout.LayoutParams) back.getLayoutParams()).setMargins(margins, 0, margins, 0);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        switch (current_node) {
            case 0:
                back.setVisibility(View.GONE);
                break;
            default:
                back.setVisibility(View.VISIBLE);
                break;
        }
        return hintView;
    }


}
