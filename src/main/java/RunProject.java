import java.io.IOException;

/**
 * Copyright (c) 2018 KPMG Technology Solutions
 *
 * @author chathurabuddi
 * @version 1.0.0
 */
public class RunProject {
    public static void main(String[] args) {
        JacksonDecode jacksonDecode = new JacksonDecode();
        try {
            jacksonDecode.parseResposne("rs.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
