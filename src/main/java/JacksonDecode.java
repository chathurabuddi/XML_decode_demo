/**
 * Copyright (c) 2018 KPMG Technology Solutions
 *
 * @author chathurabuddi
 * @version 1.0.0
 */

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class JacksonDecode {
    public void parseResposne(String xmlFileName) throws IOException {
        JsonParser jsonParser = new JsonFactory().createJsonParser(getTestXML(xmlFileName));

        while (jsonParser.nextToken() != JsonToken.END_OBJECT && jsonParser.getCurrentToken() != null) {
            if ("EnhancedSeatMapRS".equals(jsonParser.getCurrentName())) {
                while (jsonParser.nextToken() != JsonToken.END_OBJECT && jsonParser.getCurrentToken() != null) {
                    if ("SeatMap".equals(jsonParser.getCurrentName())) {
                        while (jsonParser.nextToken() != JsonToken.END_ARRAY && jsonParser.getCurrentToken() != null) {
                            if ("changeOfGaugeInd".equals(jsonParser.getCurrentName())) {
                                jsonParser.nextToken();
                                System.out.println("changeOfGaugeInd : " + jsonParser.getText());
                            } else if("Equipment".equals(jsonParser.getCurrentName())){
                                jsonParser.nextToken();
                                System.out.println("Equipment : " + jsonParser.getText());
                            } else if("Flight".equals(jsonParser.getCurrentName())){
                                while (jsonParser.nextToken() != JsonToken.END_OBJECT && jsonParser.getCurrentToken() != null) {
                                    if("destination".equals(jsonParser.getCurrentName())){
                                        jsonParser.nextToken();
                                        System.out.println("destination : " + jsonParser.getText());
                                    }else if("origin".equals(jsonParser.getCurrentName())){
                                        jsonParser.nextToken();
                                        System.out.println("origin : " + jsonParser.getText());
                                    }else if("DepartureDate".equals(jsonParser.getCurrentName())){
                                        while (jsonParser.nextToken() != JsonToken.END_OBJECT && jsonParser.getCurrentToken() != null) {
                                            if("localTime".equals(jsonParser.getCurrentName())){
                                                jsonParser.nextToken();
                                                System.out.println("localTime : " + jsonParser.getText());
                                            }else if("content".equals(jsonParser.getCurrentName())){
                                                jsonParser.nextToken();
                                                System.out.println("DepartureDate.content : " + jsonParser.getText());
                                            }
                                        }
                                    }else if("Marketing".equals(jsonParser.getCurrentName())){
                                        while (jsonParser.nextToken() != JsonToken.END_ARRAY && jsonParser.getCurrentToken() != null) {
                                            if ("carrier".equals(jsonParser.getCurrentName())) {
                                                jsonParser.nextToken();
                                                System.out.println("carrier : " + jsonParser.getText());
                                            }else if ("content".equals(jsonParser.getCurrentName())) {
                                                jsonParser.nextToken();
                                                System.out.println("Marketing.content : " + jsonParser.getText());
                                            }
                                        }
                                    }
                                }
                            } else if("Cabin".equals(jsonParser.getCurrentName())){
                                skipElement(jsonParser);
                            } else if("PriceList".equals(jsonParser.getCurrentName())){
                                skipElement(jsonParser);
                            } else if("TaxTable".equals(jsonParser.getCurrentName())){
                                skipElement(jsonParser);
                            }
                        }
                    } if ("ApplicationResults".equals(jsonParser.getCurrentName())) {
                        skipElement(jsonParser);
                    }
                }
            }else if ("Links".equals(jsonParser.getCurrentName())) {
                skipElement(jsonParser);
            }
        }
    }


    private void skipElement(JsonParser jsonParser) throws IOException, JsonParseException {
        jsonParser.nextToken();
        jsonParser.skipChildren();
    }

    private String getTestXML(String fileName) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File contFile = new File(classLoader.getResource(fileName).getFile());
            if (contFile.exists()) {
                FileInputStream fis = new FileInputStream(contFile);
                int length = new Long(contFile.length()).intValue();
                byte[] byteSet = new byte[length];
                fis.read(byteSet);
                String response = new String(byteSet);
                return response;
            }
        } catch (Exception e) {
            System.err.println("getTestXML()." + e);
        }
        return null;
    }
}
