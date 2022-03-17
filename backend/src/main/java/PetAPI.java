import be.refleqt.h4ckaton.test.dto.ApiException;
import be.refleqt.h4ckaton.test.dto.ApiResponse;
import be.refleqt.h4ckaton.test.dto.api.PetApi;
import be.refleqt.h4ckaton.test.dto.model.Pet;

public class PetAPI extends AbstractAPI  {

    public PetAPI() {
    }

    /**
     * Get the pet with Id
     * @return getInventoryWithHttpInfo response
     */
    public ApiResponse<Pet> getPetById(long id){
        PetApi apiInstance = new PetApi(configureClient());
        ApiResponse<Pet> response = null;
        try
        {
            response = apiInstance.getPetByIdWithHttpInfo(id);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return response;
    }
}
