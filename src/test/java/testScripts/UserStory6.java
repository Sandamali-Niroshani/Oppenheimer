package testScripts;

import api.function.VouchersApi;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class UserStory6 {

    @Test
    public void verifyVouchersByPersonAndType() {

        Response response = VouchersApi.getVouchersByPersonAndType();
        VouchersApi.verifyVouchersResponseFormat(response);
    }
}


