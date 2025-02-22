package testScripts;

import api.function.HeroApi;
import api.queryHelper.DBQueryHelper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payload.HeroPayload;
import utility.DBUtility;
import utility.DynamicData;
import utility.JsonUtils;

import java.sql.Connection;
import java.util.Map;

public class UserStory4 {

    // Read test data from JSON file
    Map<String, Object> testData = JsonUtils.readJsonFile("src/test/java/resources/UserStory4.json");
    // Establish DB connection
    Connection conn = DBUtility.getConnection();
        @Test
        public void createWorkingHeroWithVoucher() {

            // Generate a dynamic natid
           String natid = DynamicData.generateNatid();
            System.out.println("Generated natid: " + natid);

            String voucherName = DynamicData.generateVoucherName();
            System.out.println("Generated voucherName: " + voucherName);

            // Extract values dynamically from JSON
            String name = (String) testData.get("name");
            String gender =(String) testData.get("gender");
            String birthDate = (String) testData.get("birthDate");
            String deathDate =(String) testData.get("deathDate");
            double salary = (double)testData.get("salary");
            double taxPaid = (double)(testData.get("taxPaid"));
            Integer browniePoints = testData.get("browniePoints") != null ?  (Integer) testData.get("browniePoints") : null;
            String voucherType = (String) testData.get("voucherType");

            // Generate dynamic payload
            String jsonPayload = HeroPayload.addHeroWithVoucher(natid, name, gender, birthDate, deathDate, salary, taxPaid, browniePoints, voucherName, voucherType);

            Response response = HeroApi.createHeroWithVoucher(jsonPayload);
            HeroApi.verifyCreatingHeroWithVoucher(response, natid);
            HeroApi.verifyVoucherCreatedInVoucherTable(response, voucherName);
        }

        @Test(dependsOnMethods = "createWorkingHeroWithVoucher")
        public void createWorkingHeroEmptyVoucher() {

            // Generate a dynamic natid
            String natid = DynamicData.generateNatid();
            System.out.println("Generated natid: " + natid);

            // Second attempt (duplicate)
            // Extract values dynamically from JSON
            String name = (String) testData.get("name");
            String gender =(String) testData.get("gender");
            String birthDate = (String) testData.get("birthDate");
            String deathDate =(String) testData.get("deathDate");
            double salary = (double)testData.get("salary");
            double taxPaid = (double)(testData.get("taxPaid"));
            Integer browniePoints = testData.get("browniePoints") != null ?  (Integer) testData.get("browniePoints") : null;
            String voucherName = "";
            String voucherType = "";

            // Generate dynamic payload
            String jsonPayload = HeroPayload.addHeroWithEmptyVoucher(natid, name, gender, birthDate, deathDate, salary, taxPaid, browniePoints, voucherName, voucherType);

            Response response = HeroApi.createHeroWithVoucher(jsonPayload);
            HeroApi.verifyCreatingHeroWithEmptyVoucher(response);
            DBQueryHelper.verifyRecordNotCreated(natid);
        }
    }


