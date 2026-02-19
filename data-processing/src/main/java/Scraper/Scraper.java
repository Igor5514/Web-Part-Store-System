package Scraper;

import Writter.Writter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class Scraper {

    Writter writter = new Writter();

    public Scraper(){

    }

    public void scrapeInitialiser() {
        try{
            Document doc = Jsoup.connect("https://www.cars-data.com/en/").userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36").ignoreContentType(true).timeout(60_000).get();;
            Element rootDiv = doc.select("section.brands_plus_most > div > div > div.center").first();
            if(rootDiv != null){
                Elements divs = rootDiv.select("div");
                for(int i=0; i< 32; i++){
                    String secondPageLink = divs.get(i).select("a").attr("href");
                    String carBrand = divs.get(i).select("a").attr("title");
                    if(!secondPageLink.isEmpty()){
                        scrapeSecondPage(secondPageLink,carBrand);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void scrapeSecondPage(String url, String carBrand){
        try {
            Element doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36").ignoreContentType(true).get();
            Elements rootElements = doc.select("section.models");
            for(Element element : rootElements){
                Elements carModelElements = element.select("div.col-4");
                for(Element modelElement : carModelElements){
                    String thirdPageLink = modelElement.select("a").attr("href");
                    String unchangedCarGeneration = modelElement.select("a").attr("title");
                    String carGeneration = modelElement.select("a").attr("title").substring(carBrand.length()).trim();
                    if(!thirdPageLink.isEmpty()){
                        scrapeThirdPage(unchangedCarGeneration, thirdPageLink, carBrand, carGeneration);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void scrapeThirdPage(String unchangedCarGeneration, String thirdPageLink, String carBrand, String carGeneration){
        try {
            Element doc = Jsoup.connect(thirdPageLink).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36").ignoreContentType(true).timeout(60_000).get();
            Elements rootElements = doc.select("section.models");
            for(int i = 0; i<rootElements.size()-1; i++){
                Elements carModelElements = rootElements.get(i).select("div.col-4");
                for(Element modelElement : carModelElements){
                    String fourthPageLink = modelElement.select("a").attr("href");
                    Element p = modelElement.selectFirst("p");
                    String carModel = p.text().trim().substring(unchangedCarGeneration.length());
                    if(!fourthPageLink.isEmpty()){
                        scrapeFourthPage(unchangedCarGeneration, fourthPageLink, carBrand, carGeneration, carModel);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void scrapeFourthPage(String unchangedCarGeneration, String fourthPageLink, String carBrand, String carGeneration, String carModel){
        try {
            Element doc = Jsoup.connect(fourthPageLink).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36").ignoreContentType(true).timeout(60_000).get();
            Element rootCarEngineElements = doc.selectFirst("div.col-9 > section.types > div.col-8");
            Elements elements = rootCarEngineElements.select("div.row");
            for (Element element : elements) {
                Element engineDiv = element.selectFirst("div.col-6 > h3 > a");
                String engine = getEngineTypeString(engineDiv.text(),unchangedCarGeneration);
                Elements typeDiv = element.select("div.col-2");
                String engineFuel = typeDiv.get(0).text().trim();
                String transmission = typeDiv.get(1).text().trim();

                String fifthPageLink = engineDiv.attr("href");
                if(!fifthPageLink.isEmpty()){
                    scrapeFifthPage(fifthPageLink, carBrand, carGeneration, carModel,engine, engineFuel, transmission);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  void scrapeFifthPage(String fifthPageLink, String carBrand, String carGeneration, String carModel, String engine,String engineFuel, String transmission){
        String transmissionType = "";
        String driveWheel = "";
        String engineType = "";
        String fuelType = "";
        String power = "";
        String topSpeed = "";
        String acceleration = "";
        String cylinders = "";
        String valvesPerCyl = "";
        String engineCapacity = "";
        try {
            Element doc = Jsoup.connect(fifthPageLink).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36").ignoreContentType(true).timeout(60_000).get();
            Elements rootCarEngineElements = doc.select("div.row > div.col-9 > div.col-7 > table");

            for(int i=0; i< rootCarEngineElements.size(); i++){
                if(i == 0){
                    Elements generalElement = rootCarEngineElements.get(0).select("tbody > tr");
                    Element generalElementTr = generalElement.get(5);
                    transmissionType = generalElementTr.select("td").get(1).text();
                }
                if(i == 1){
                    Elements driveElement = rootCarEngineElements.get(1).select("tbody > tr");
                    Element wheelElementTr = driveElement.get(1);
                    driveWheel = wheelElementTr.select("td").get(1).text();
                    Element engineElementTr = driveElement.get(2);
                    engineType = engineElementTr.select("td").get(1).text();
                    Element fuelElementTr = driveElement.get(3);
                    fuelType = fuelElementTr.select("td").get(1).text();
                    Element powerElementTr = driveElement.get(4);
                    power = powerElementTr.select("td").get(1).text();
                }
                if(i == 2){
                    Elements driveElement = rootCarEngineElements.get(2).select("tbody > tr");
                    Element speedElementTr = driveElement.get(1);
                    topSpeed = speedElementTr.select("td").get(1).text();
                    Element accelerationElementTr = driveElement.get(2);
                    acceleration = accelerationElementTr.select("td").get(1).text();
                }
                if(i == 3){
                    Elements driveElement = rootCarEngineElements.get(3).select("tbody > tr");
                    String engineMotorType = driveElement.select("th > h2").text().trim();
                    if(engineMotorType.equalsIgnoreCase("FUEL ENGINE")){
                        Element cylinderElementTr = driveElement.get(1);
                        cylinders = cylinderElementTr.select("td").get(1).text();
                        Element valvesPerCylElementTr = driveElement.get(2);
                        valvesPerCyl = valvesPerCylElementTr.select("td").get(1).text();
                        Element capacityElementTr = driveElement.get(3);
                        engineCapacity = capacityElementTr.select("td").get(1).text();
                    }
                }
            }
            Vehicle vehicle = new Vehicle(carBrand,carGeneration,carModel,engine,driveWheel,engineType,fuelType,power,topSpeed,
                    acceleration,cylinders, valvesPerCyl,engineCapacity,engineFuel, transmission);
            writter.writeVehicle(vehicle);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEngineTypeString(String engine,String model){
        try{
            String[] modelList = model.split(" ");
            String[] engineList = engine.substring(5).trim().split(" ");

            Set<String> modelSet = new HashSet<>(Arrays.asList(modelList));

            List<String> filteredEngine = new ArrayList<>();
            for (String word : engineList) {
                if (!modelSet.contains(word)) {
                    filteredEngine.add(word);
                }
            }
            filteredEngine.remove("specs");

            return String.join(" ", filteredEngine);
        }catch (StringIndexOutOfBoundsException e){
           return engine;
        }
    }
}
