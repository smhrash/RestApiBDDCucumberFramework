package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    public  RequestSpecification requestSpecification;

    public RequestSpecification requestSpecification() throws IOException {


        PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

        requestSpecification = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                .addQueryParam("key","qaclick123")
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .setContentType(ContentType.JSON).build();

        return requestSpecification;


    }
    public String getGlobalValue(String url) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("/Users/user/IdeaProjects/RestApiBDDCucumberFramework/src/test/java/resources/global.properties");
        properties.load(fileInputStream);


        return properties.getProperty(url);
    }
}
