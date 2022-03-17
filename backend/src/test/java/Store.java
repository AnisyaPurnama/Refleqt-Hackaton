import org.junit.Assert;
import org.junit.jupiter.api.Test;


public class Store {

    //TODO Tagging om meerdere suites uit te pluizen
    @Test
    void GetInventory() {
        Assert.assertNotNull(new StoreAPI().GetInventory());
    }

}
