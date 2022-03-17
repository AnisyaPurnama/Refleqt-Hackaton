import org.junit.Assert;
import org.junit.jupiter.api.Test;


public class Store {

    @Test
    void GetInventory() {
        Assert.assertNotNull(new StoreAPI().GetInventory());
    }

}
