package testScripts;

import api.function.HeroApi;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payload.HeroPayload;
import utility.DynamicData;
import utility.JsonUtils;

import java.util.Map;

public class UserStory1 {

    String natid;

    // Read test data from JSON file
    Map<String, Object> testData = JsonUtils.readJsonFile("src/test/java/resources/UserStory1.json");

        @Test
        public void createHeroSuccess() {

            // Generate a dynamic natid
            natid = DynamicData.generateNatid();
            System.out.println("Generated natid: " + natid);

            // Extract values dynamically from JSON
            String name = (String) testData.get("name");
            String gender =(String) testData.get("gender");
            String birthDate = (String) testData.get("birthDate");
            String deathDate =(String) testData.get("deathDate");
            double salary = (double)testData.get("salary");
            double taxPaid = (double)(testData.get("taxPaid"));
            Integer browniePoints = testData.get("browniePoints") != null ?  (Integer) testData.get("browniePoints") : null;

            // Generate dynamic payload
            String jsonPayload = HeroPayload.addHero(natid, name, gender, birthDate, deathDate, salary, taxPaid, browniePoints);

            Response response = HeroApi.createHero(jsonPayload);
            HeroApi.verifyCreatingHero(response, natid);

        }

        @Test(dependsOnMethods = "createHeroSuccess")
        public void createHeroDuplicateNatid() {

            // Second attempt (duplicate)
            // Extract values dynamically from JSON
            String name = (String) testData.get("name");
            String gender =(String) testData.get("gender");
            String birthDate = (String) testData.get("birthDate");
            String deathDate =(String) testData.get("deathDate");
            double salary = (double)testData.get("salary");
            double taxPaid = (double)(testData.get("taxPaid"));
            Integer browniePoints = testData.get("browniePoints") != null ?  (Integer) testData.get("browniePoints") : null;

            // Generate dynamic payload
            String jsonPayload = HeroPayload.addHero(natid, name, gender, birthDate, deathDate, salary, taxPaid, browniePoints);

            //create hero with duplicate natid
            Response response = HeroApi.createHero(jsonPayload);

            //Verify response
            HeroApi.verifyCreatingHeroDuplicate(response, natid);
        }
    }


