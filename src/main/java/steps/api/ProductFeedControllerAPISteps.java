package steps.api;

import net.thucydides.core.annotations.Step;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static net.serenitybdd.rest.SerenityRest.given;

public class ProductFeedControllerAPISteps {

    List<Integer> randInts = new ArrayList<Integer>();


    public List<Integer> getRandInts() {
        if (randInts.isEmpty()) {
            setRandInts(randInts);
        }
        return this.randInts;
    }

    public void setRandInts(List<Integer> randInts) {
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            this.randInts.add(rand.nextInt(100000));
        }
        this.randInts = randInts;
    }

//    List<Integer> randInts = new Random().ints(0,10000).boxed().collect(Collectors.toList());


    @Step
    public void FileUpload() throws IOException {
        String URL = "https://swapi.co/api/";

        boolean productsFile = false;
        File file;
        try {
            file = new File("Products.txt");
            file.createNewFile();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file.getName()));
//            for (int randi : randInts) {
            for (int randi : getRandInts()) {
                writer.write("" + randi);
                writer.newLine();
            }
            writer.close();
            System.out.println(file.getAbsolutePath());

            given().multiPart("file", file, "application/txt")
                    .when()
                    .post(URL + "/early/access/product/feed/file/upload")
                    .then().statusCode(200);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Step
    public void FileUploadUpdate() throws IOException {
        String URL = "https://swapi.co/api/";

        boolean productsFile = false;
        File file;
        try {
            file = new File("ProductsUpdate.txt");
            file.createNewFile();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file.getName()));
//            for (int randi : randInts) {
            for (int randi : getRandInts()) {
                writer.write("" + randi + ",I,9/17/2019");
                writer.newLine();
            }
            writer.close();
            System.out.println(file.getAbsolutePath());
            given().multiPart("file", file, "application/txt")
                    .when()
                    .put(URL + "/early/access/product/feed/file/upload/update")
                    .then().statusCode(200);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}