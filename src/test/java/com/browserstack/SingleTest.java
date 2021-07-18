package com.browserstack;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Allure;

public class SingleTest extends BrowserStackTestNGTest {

    @Test
    public void test() throws Exception {
    	String baseUrl = "http://18.134.60.157/";
		driver.get(baseUrl);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		JavascriptExecutor js1 = (JavascriptExecutor)driver;
		String title = driver.getTitle();
		System.out.println("Title of the page is: " + title);
		
		String currentUrl = driver.getCurrentUrl();
		System.out.println("Current URL is: " + currentUrl);
		
		WebElement footer= driver.findElement(By.className("ba-footer-link"));
		System.out.println(footer.findElements(By.tagName("a")).size()) ;
		  
		  int i = footer.findElements(By.tagName("a")).size(); //Get number of links

		  for(int j = 0;j<i;j++){    //create loop based upon number of links to traverse all links
		   footer= driver.findElement(By.className("ba-footer-link"));   // We are re-creating "footer" webelement as DOM changes after navigate.back()
		   WebElement elem =footer.findElements(By.tagName("a")).get(j);
		   js.executeScript("arguments[0].click();", elem);
		      Thread.sleep(3000);
		      
		      if(driver.getTitle().contains("404")) {
		    	  
		    	  String status="failed" ;
		    	  String reason="unaccessible link found";
		       
		        
		    		js1.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \""+status+"\", \"reason\": \""+reason+"\"}}");
		    		String page_title=driver.getCurrentUrl();
		    		Allure.addAttachment("Uaccessible Link ",page_title );
		       
		        String filename = getRandomString(10) + ".png";
				String directory = System.getProperty("user.dir") + "//screenshots//";
				File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(sourceFile, new File(directory + filename));
				}
		      
		    
		        // driver.navigate().back()
		      js.executeScript("history.go(-1)");
		      Thread.sleep(50000);
		      }}
		  
		  
		 
	

	public static String getRandomString(int length) {
		StringBuilder sb = new StringBuilder();
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		for (int i = 0; i < length; i++) {
			int index = (int)(Math.random() * characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
	}
    }

