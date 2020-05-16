package in.enggoma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import in.enggoma.databinding.ActivityPdfViewBinding;

public class PdfViewActivity extends AppCompatActivity {


    private ActivityPdfViewBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_pdf_view);
        context = this;


        binding.pdfView.fromUri(Uri.parse("https://www.mohfw.gov.in/pdf/ProtectivemeasuresEng.pdf"));

    }
}
