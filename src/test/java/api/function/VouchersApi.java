package api.function;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static utility.Constants.*;

public class VouchersApi {

    public static Response getVouchersByPersonAndType() {
        return given().log().all()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .get("/api/v1/voucher/by-person-and-type")
                .then().log().all()
                .extract().response();
    }

    public static void verifyVouchersResponseFormat(Response response) {

        JsonPath js = new JsonPath(response.asString());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got: " + response.getStatusCode());

        // Extract "data" array
        List<Map<String, Object>> data = js.getList("data");

        // Ensure "data" is not null
        Assert.assertTrue(data != null, "Data should not be null");

        // Ensure "data" is not empty
        Assert.assertTrue(data.size() > 0, "Data list should not be empty");

        // Iterate over the list to validate each object
        for (Map<String, Object> item : data) {

            Assert.assertTrue(item.containsKey("name"), "Each item should contain 'name'");
            Assert.assertTrue(item.containsKey("voucherType"), "Each item should contain 'voucherType'");
            Assert.assertTrue(item.containsKey("count"), "Each item should contain 'count'");

            // Validate types
            Assert.assertTrue(item.get("name") instanceof String, "Name should be a string");
            Assert.assertTrue(item.get("voucherType") instanceof String, "VoucherType should be a string");
            Assert.assertTrue(item.get("count") instanceof Integer, "Count should be an integer");
        }

        System.out.println("All assertions passed successfully!");

    }

    }

