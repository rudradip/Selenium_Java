//package Selenium_Java_Example;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.*;
import java.util.concurrent.TimeUnit;

//comment the above line and uncomment below line to use Chrome
//import org.openqa.selenium.chrome.ChromeDriver;
public class Selenium_Example {


    private static Object WebDriverException;

    public static void main(String[] args) throws Exception {
        // declaration and instantiation of objects/variables
        //System.setProperty("webdriver.firefox.","C:\\users\\S915215\\documents\\geckodriver\\geckodriver.exe");
        // WebDriver driver = new ChromeDriver();
        //comment the above 2 lines and uncomment below 2 lines to use Chrome

        File file = new File("test.log");
        PrintStream ps = new PrintStream(file);

       try {
           System.setProperty ("webdriver.chrome.driver", "C:\\Users\\S915215\\Documents\\chromedriver\\chromedriver.exe");
           WebDriver driver1 = new ChromeDriver ( );

       /* String baseUrl = "https://github.com/login";
        String expectedTitle = "Sign in to GitHub · GitHub";
        String actualTitle = "";

        // launch Fire fox and direct it to the Base URL
        driver1.get (baseUrl);


        // get the actual value of the title
        actualTitle = driver1.getTitle ( );
        System.out.println ("||" + actualTitle + "||");
*/
           readExcel ("C:\\new_folder", "TestData.xlsx", "sheet1", driver1);

       }

        catch(Exception e1)


        {

            /*FileOutputStream fo = new FileOutputStream ("C:\\new_folder\\main_exception.txt");

            fo.write (e1.getMessage ( ).getBytes ( ));
            if (e1.toString ().isEmpty ())
            {
                System.out.println ("Testing.......");
            }*/



                e1.printStackTrace(ps);
            }
            ps.close();

        }





    public static void readExcel(String filePath, String fileName, String sheetName, WebDriver wd) throws NullPointerException, Exception {

        //Create a object of File class to open xlsx file



        /*
         * compare the actual title of the page with the expected one and print
         * the result as "Passed" or "Failed"
         */
        String baseUrl = "https://github.com/login";
        wd.get (baseUrl);
        // String baseUrl = "https://github.com/login";
        String expectedTitle = "Sign in to GitHub · GitHub";
        String actualTitle = wd.getTitle ( );

        if (actualTitle.contentEquals (expectedTitle)) {
            System.out.println ("Title is Matching!");
        } else {
            System.out.println ("Title  did not Match");
        }

        //close Fire fox


        File file = new File (filePath + "\\" + fileName);

        //Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream (file);

        Workbook myWorkbook = null;

        //Find the file extension by spliting file name in substring and getting only extension name
        //indexOf gives the index of . in the file name
        //substring method splits the string starting from index of . to the end
        String fileExtensionName = fileName.substring (fileName.indexOf ("."));
        try {
            //Check condition if the file is xlsx file
            if (fileExtensionName.equals (".xlsx")) {
                //If it is xlsx file then create object of XSSFWorkbook class
                myWorkbook = new XSSFWorkbook (inputStream);
            }

            //Check condition if the file is xls file
            else if (fileExtensionName.equals (".xls")) {
                //If it is xls file then create object of HSSFWorkbook class
                myWorkbook = new HSSFWorkbook (inputStream);
            }

            //Read sheet inside the workbook by its name
            Sheet mySheet = myWorkbook.getSheet (sheetName);

            //Find number of rows in excel file
            int rowCount = mySheet.getLastRowNum ( ) - mySheet.getFirstRowNum ( );

            //Create a loop over all the rows of excel file to read it
            for (int i = 1; i <= rowCount; i++) {
                Row row = mySheet.getRow (i);
                //Create a loop to print cell values in a row
                for (int j = 0; j <= row.getLastCellNum ( ); j++) {
                    //Print excel data in console
                    //System.out.print (row.getCell (j+1).getStringCellValue ( )+"||");

                    String a = row.getCell (j + 1).getStringCellValue ( );

                    String b = row.getCell (j + 2).getStringCellValue ( );

                    wd.findElement (By.name ("login")).sendKeys (a);

                    wd.findElement (By.name ("password")).sendKeys (b);

                    wd.findElement (By.name ("commit")).click ( );

                    wd.manage ( ).timeouts ( ).implicitlyWait (8, TimeUnit.SECONDS);

                    String Message;
                    WebElement element = wd.findElement (By.className ("application-main"));
                    if (!element.toString ( ).isEmpty ( ))

                        System.out.println ("LogIn Successful");
                    //System.out.println (WebDriverException);
                    //  System.out.println ("Failed Login");


                    wd.close ( );
                    /* System.out.println (wd.getTitle ());*/


                }


            }
            // wd.close ( );
        } catch (Exception e) {
            FileOutputStream fo = new FileOutputStream ("C:\\new_folder\\exception.txt");

            fo.write (e.getMessage ( ).getBytes ( ));

        }


    }


    //System.setProperty ("webdriver.chrome.driver", "C:\\Users\\S915215\\Documents\\chromedriver\\chromedriver.exe");
    //WebDriver driver = new ChromeDriver ( );






}






