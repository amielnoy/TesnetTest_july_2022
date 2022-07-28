package Tests;


import Selenium.BaseMethod;
//import controllers.DataProviders.textFileProvider;
import Selenium.DataProviders.textFileProvider;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pageObjects.pages.TablePage;
import org.testng.Assert;

import java.util.HashMap;

//@Test
public class testTable extends BaseMethod {



    @Test(dataProvider = "textFileNameAsMethodName",dataProviderClass= textFileProvider.class)
    @Description("test that string values exist in the table")
    public void testTableElementsText(String tableColumnName,String valueToSearchInColumn,String expectedTableCellValue) {
        HashMap searchedColumnNameToIndexMap =new HashMap<String, Integer>();

        searchedColumnNameToIndexMap.put("Company",1);
        searchedColumnNameToIndexMap.put("Contact",2);
        searchedColumnNameToIndexMap.put("Country",3);

        TablePage tablePage=new TablePage();
        WebElement tableWebElement=getWebDriver().findElement(By.id("customers"));


        int intExpectedColumnIndex=(int)searchedColumnNameToIndexMap.get(tableColumnName);
        try {
            String actualCellValue=tablePage.verifyTableCellText(
                    tableWebElement,
                    (int)searchedColumnNameToIndexMap.get(tableColumnName),
                    valueToSearchInColumn,
                    intExpectedColumnIndex,
                    expectedTableCellValue);
            Assert.assertEquals(actualCellValue.length(),expectedTableCellValue.length(),"Error lenghs are not the same");
            Assert.assertEquals(actualCellValue,expectedTableCellValue,"actualCellValue="+actualCellValue+"<>expectedTableCellValue="+expectedTableCellValue);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


}
