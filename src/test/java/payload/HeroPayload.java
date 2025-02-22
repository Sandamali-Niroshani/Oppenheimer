package payload;

public class HeroPayload {

    public static String addHero(String natid, String name, String gender,
                                 String birthDate, String deathDate,
                                 double salary, double taxPaid, Integer browniePoints) {
        return "{\n" +
                "\"natid\": \"" + natid + "\",\n" +
                "\"name\": \"" + name + "\",\n" +
                "\"gender\":\"" + gender + "\",\n" +
                "\"birthDate\": \"" + birthDate + "\",\n" +
                "\"deathDate\": " + (deathDate != null ? "\"" + deathDate + "\"" : "null") + ",\n" +
                "\"salary\": " + salary + ",\n" +
                "\"taxPaid\": " + taxPaid + ",\n" +
                "\"browniePoints\": " + browniePoints + "\n" +
                "}";
    }

    public static String addHeroWithVoucher(String natid, String name, String gender,
                                 String birthDate, String deathDate, double salary, double taxPaid,
                                            Integer browniePoints, String voucherName, String voucherType) {
        return "{\n" +
                "\"natid\": \"" + natid + "\",\n" +
                "\"name\": \"" + name + "\",\n" +
                "\"gender\":\"" + gender + "\",\n" +
                "\"birthDate\": \"" + birthDate + "\",\n" +
                "\"deathDate\": " + (deathDate != null ? "\"" + deathDate + "\"" : "null") + ",\n" +
                "\"salary\": " + salary + ",\n" +
                "\"taxPaid\": " + taxPaid + ",\n" +
                "\"browniePoints\": " + browniePoints + ",\n" +
                "\"vouchers\": [\n" +
                "{\n" +
                "\"voucherName\": \"" + voucherName + "\",\n" +
                "\"voucherType\": \"" + voucherType + "\"\n" +
                "}\n" +
                "]\n" +
                "}\n";
    }

    public static String addHeroWithEmptyVoucher(String natid, String name, String gender,
                                            String birthDate, String deathDate, double salary, double taxPaid,
                                            Integer browniePoints, String voucherName, String voucherType) {
        return "{\n" +
                "\"natid\": \"" + natid + "\",\n" +
                "\"name\": \"" + name + "\",\n" +
                "\"gender\":\"" + gender + "\",\n" +
                "\"birthDate\": \"" + birthDate + "\",\n" +
                "\"deathDate\": " + (deathDate != null ? "\"" + deathDate + "\"" : "null") + ",\n" +
                "\"salary\": " + salary + ",\n" +
                "\"taxPaid\": " + taxPaid + ",\n" +
                "\"browniePoints\": " + browniePoints + ",\n" +
                "\"vouchers\": [\n" +
                "{\n" +
                "}\n" +
                "]\n" +
                "}\n";
    }


}
