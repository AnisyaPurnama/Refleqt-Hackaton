
import be.refleqt.h4ckaton.test.dto.ApiClient;
import be.refleqt.h4ckaton.test.dto.ApiException;
import be.refleqt.h4ckaton.test.dto.ApiResponse;
import be.refleqt.h4ckaton.test.dto.Configuration;
import be.refleqt.h4ckaton.test.dto.api.StoreApi;

import java.util.Map;

public class StoreAPI {
    ApiClient defaultClient;

    //TODO Copy paste en alles hernoemen naar pet
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
