package in.enggoma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.preference.PowerPreference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import in.enggoma.databinding.ActivityPdfViewBinding;
import in.enggoma.models.Network.SoleInstance;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PdfViewActivity extends AppCompatActivity implements OnPageChangeListener {


    private ActivityPdfViewBinding binding;
    private Context context;
    private String Url;
    private String   downloadedUri;
    Integer pageNumber = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_pdf_view);
        context = this;
        Url = "https://www.mohfw.gov.in/pdf/ProtectivemeasuresEng.pdf";
        


        String mfilename= Url.substring( Url.lastIndexOf('/')+1, Url.length() );


        checkFile(Url,mfilename);


     //   download(Url,mfilename);



        //   binding.pdfView.fromUri(Uri.parse("https://www.mohfw.gov.in/pdf/ProtectivemeasuresEng.pdf"));
        
        
        

    }

    private void checkFile(String url, String filename) {
        File myfile = new File(context.getExternalFilesDir(null)
                + File.separator + filename);

        if(myfile.exists())
        {
            binding.pdfView.fromFile(myfile)
                    .defaultPage(pageNumber)
                    .onPageChange(PdfViewActivity.this::onPageChanged)
                    .enableAnnotationRendering(true)
                    .scrollHandle(new DefaultScrollHandle(context))
                    .spacing(10)
                    .load();
        }else
        {
            download(url,filename);



        }

    }

    private void download(String url, String filename) {

            Call<ResponseBody> call = SoleInstance.getApiServiceInstance().downloadReports(url);
            call.enqueue(new Callback<ResponseBody>() {
                @SuppressLint("StaticFieldLeak")
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful() && response.body()!=null){
                        new AsyncTask<Void, Void, String>(){
                            @Override
                            protected String doInBackground(Void... voids) {
                                downloadedUri = writeResponseBodyToDisk(response.body(),filename);

                                return downloadedUri;
                            }

                            @Override
                            protected void onPreExecute() {
                                super.onPreExecute();
                            }

                            @Override
                            protected void onPostExecute(String s) {
                                super.onPostExecute(s);
                                if(downloadedUri!=null)
                                {


                                    File file = new File(context.getExternalFilesDir(null)
                                            + File.separator + filename);
                                    binding.pdfView.fromFile(file)
                                            .defaultPage(pageNumber)
                                            .onPageChange(PdfViewActivity.this::onPageChanged)
                                            .enableAnnotationRendering(true)
                                            .scrollHandle(new DefaultScrollHandle(context))
                                            .spacing(10)
                                            .load();
                                }

                            }
                        }.execute();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                }
            });




    }

    private String writeResponseBodyToDisk(ResponseBody body, String name) {
        try {
            // todo change the file location/name according to your needs
            File filePath = new File(
                    context.getExternalFilesDir(null)
                            + File.separator + name);

            File showPath = new File(context.getExternalFilesDir(null) + "");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(filePath);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d("file", "file download: " + fileSizeDownloaded + " of " + fileSize);
                    //Toast.makeText(context, "Downloaded", Toast.LENGTH_SHORT).show();
                }

                outputStream.flush();

                return "" + filePath;
            } catch (IOException e) {
                Log.d("file", "writeResponseBodyToDisk: " + e.getMessage());
                return "" + e.getMessage();
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            Log.d("file", "writeResponseBodyToDisk: " + e.getMessage());
            return "" + e.getMessage();
        }
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;

        setTitle(String.format("%s %s / %s", "pdf", page + 1, pageCount));
    }
}
