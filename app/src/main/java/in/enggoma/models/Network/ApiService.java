package in.enggoma.models.Network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface ApiService {

    @Streaming
    @GET
    Call<ResponseBody> downloadReports(@Url String downloadUrl);
}
