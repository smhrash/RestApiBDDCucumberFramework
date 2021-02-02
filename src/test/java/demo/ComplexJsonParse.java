package demo;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

    public static void main(String[] args) {

        JsonPath jsonPath = new JsonPath(Payload.coursePrice());
        int countCourses = jsonPath.getInt("courses.size()");

        System.out.println(countCourses);

        int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");

        System.out.println(purchaseAmount);

        String titleFirstCourse = jsonPath.getString("courses[0].title");

        System.out.println(titleFirstCourse);

        for (int i = 0; i < countCourses; i++) {

            String courseTitle = jsonPath.getString("courses[" + i + "].title");

            System.out.println(courseTitle);
            System.out.println(jsonPath.getInt("courses[" + i + "].price"));
        }
        for (int i = 0; i < countCourses; i++) {

            String courseTitle = jsonPath.getString("courses[" + i + "].title");

            if (courseTitle.equalsIgnoreCase("RPA")) {
                int copies = jsonPath.get("courses[" + i + "].copies");
                System.out.println(copies);
                break;
            }
        }
    }
}
