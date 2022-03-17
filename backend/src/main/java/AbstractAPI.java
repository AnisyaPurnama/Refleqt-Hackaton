
import be.refleqt.h4ckaton.test.dto.ApiClient;
import okhttp3.OkHttpClient;
import utils.HttpInterceptor;

public abstract class AbstractAPI {

    protected static ApiClient configureClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpInterceptor())
                .build();
        ApiClient apiClient = new ApiClient();
        apiClient.setHttpClient(httpClient);
        apiClient.setBasePath("https://petstore3.swagger.io/api/v3");
        return apiClient;
    }

}
