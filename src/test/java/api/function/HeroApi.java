package api.function;

import api.queryHelper.DBQueryHelper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utility.Constants.*;

public class HeroApi {

    /**
     * This method is used to create a hero
     *
     * @param payload - json payload
     * @return response
     * @author niroshani
     * @completed 2025-02-21
     */
    public static Response createHero(String payload) {
        return given().log().all()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post("/api/v1/hero")
                .then().log().all()
                .extract().response();
    }

    /**
     * This method is used to verify the response of creating a hero
     *
     * @param response - response
     * @param natid    - natid
     * @return void
     * @author niroshani
     * @completed 2025-02-21
     */

    public static void verifyCreatingHero(Response response, String natid) {

        JsonPath js = new JsonPath(response.toString());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got: " + response.getStatusCode());

        //Verify record is created in database table WORKING_CLASS_HEROES
        String dbNatId = DBQueryHelper.retrieveDBRecordFromNatid(natid);
        Assert.assertEquals(dbNatId, natid, "Record is found in DB");
    }

    /**
     * This method is used to verify the response of creating a hero with duplicate natid
     *
     * @param response - response
     * @param natid    - natid
     * @return void
     * @author niroshani
     * @completed 2025-02-21
     */
    public static void verifyCreatingHeroDuplicate(Response response, String natid) {

        JsonPath js = new JsonPath(response.asString());

        Assert.assertEquals(response.getStatusCode(), 400, "Expected status code 400 for duplicate natid");//pass json response body
        String errorMessage = js.getString("errorMsg");
        System.out.print(errorMessage);
        Assert.assertEquals(errorMessage, "Working Class Hero of natid: " + natid + " already exists!", "Error message is as expected");
    }

    /**
     * This method is used to create a hero with voucher
     *
     * @param payload - json payload
     * @return response
     * @author niroshani
     * @completed 2025-02-21
     */
    public static Response createHeroWithVoucher(String payload) {
        return given().log().all()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post("/api/v1/hero/vouchers")
                .then().log().all()
                .extract().response();
    }

    /**
     * This method is used to verify the response of creating a hero with voucher
     *
     * @param response - response
     * @param natid    - natid
     * @return void
     */
    public static void verifyCreatingHeroWithVoucher(Response response, String natid) {

        JsonPath js = new JsonPath(response.toString());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got: " + response.getStatusCode());

        //Verify record is created in database table WORKING_CLASS_HEROES
        String dbNatId = DBQueryHelper.retrieveDBRecordFromNatid(natid);
        Assert.assertEquals(dbNatId, natid, "Record is found in DB");

        //Verify record is created in database table VOUCHERS
        String dbVoucherName = DBQueryHelper.retrieveDBRecordFromVoucherName(natid);
    }

    /**
     * This method is used to verify the voucher created in VOUCHERS table
     * @param response - response
     * @param voucherName   - voucherName
     *
     */
    public static void verifyVoucherCreatedInVoucherTable(Response response, String voucherName) {

        JsonPath js = new JsonPath(response.toString());

        //Verify record is created in database table VOUCHERS
        String dbVoucherName = DBQueryHelper.retrieveDBRecordFromVoucherName(voucherName);
        Assert.assertEquals(dbVoucherName, voucherName, "Record is found in DB");
    }

    public static void verifyCreatingHeroWithEmptyVoucher(Response response) {

        JsonPath js = new JsonPath(response.asString());

        Assert.assertEquals(response.getStatusCode(), 400, "Expected status code 400 for duplicate natid");//pass json response body
        String errorMessage = js.getString("errorMsg");
        System.out.print(errorMessage);
        Assert.assertEquals(errorMessage, VOUCHERNAMECANNOTBEBLANK, "Error message is as expected");
    }


    public static Response retrieveWorkingClassHeroOwe(int natid) {
        return given().log().all()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .queryParam("natid", natid)
                .get("/api/v1/hero/owe-money")
                .then().log().all()
                .extract().response();
    }

    public static void verifyWorkingClassHeroOwe(Response response, String natid) {

        JsonPath js = new JsonPath(response.asString());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got: " + response.getStatusCode());

        String actualNatId = js.getString("message.data");
        String actualStatus = js.getString("message.status");
        Assert.assertEquals(actualNatId, natid, "Response matches the expected natid");
        Assert.assertTrue(actualStatus.equals(OWESTATUS) || actualStatus.equals(NILSTATUS), "Response matches the expected status");
    }

    public static void verifyHeroOweAcceptOnlyNumeric(String natid) {
        given().log().all()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .queryParam("natid", natid)
                .get("/api/v1/hero/owe-money")
                .then().log().all()
                .assertThat()
                .statusCode(500)
                .body("error", equalTo("Internal Server Error"));
    }

}
