import be.refleqt.apitesting.test.dto.ApiClient;
import be.refleqt.apitesting.test.dto.ApiException;
import be.refleqt.apitesting.test.dto.ApiResponse;
import be.refleqt.apitesting.test.dto.Configuration;
import be.refleqt.apitesting.test.dto.api.StoreApi;

import java.util.Map;

public class StoreAPI {
    ApiClient defaultClient;

    public StoreAPI() {
        defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://petstore3.swagger.io/api/v3");

    }

    /**
     * Get the Inventory
     * @return getInventoryWithHttpInfo response
     */
    public ApiResponse<Map<String, Integer>> GetInventory(){
        StoreApi apiInstance = new StoreApi(defaultClient);
        ApiResponse<Map<String, Integer>> response = null;
        try
        {
            response = apiInstance.getInventoryWithHttpInfo();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return response;
    }
}
