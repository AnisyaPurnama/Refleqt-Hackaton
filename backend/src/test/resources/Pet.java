import org.junit.Assert;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class Pet {

    @Test
    @Tag("sanity")
    @Tag("regression")
    void getPetById() {
        Assert.assertNotNull(new PetAPI().getPetById(1));
    }
}
