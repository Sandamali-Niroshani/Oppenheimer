package utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class DynamicData {
    /**
     * Generates a random natid in the format "natid-number",
     * where number is between 0 and 9999999 (inclusive).
     */
    public static String generateNatid() {
        Random random = new Random();
        int number = random.nextInt(10000000); // Generates a number between 0 and 9999999
        return "natid-" + number;
    }

    public static String generateVoucherName() {
        // Get current date-time
        LocalDateTime now = LocalDateTime.now();
        // Format as YYYYMMDDHHMMSS
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = now.format(formatter);
        // Create voucher name with "TV_" prefix
        return "TV_" + timestamp;
    }

}
