
import be.refleqt.h4ckaton.test.dto.ApiException;
import be.refleqt.h4ckaton.test.dto.ApiResponse;
import be.refleqt.h4ckaton.test.dto.api.StoreApi;

import java.util.Map;

public class StoreAPI extends AbstractAPI{

    /**
     * Get the Inventory
     * @return getInventoryWithHttpInfo response
     */
    public ApiResponse<Map<String, Integer>> GetInventory(){
        StoreApi apiInstance = new StoreApi(configureClient());
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
