package Controller;

import PageObject.Site;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

abstract class Report  {

    private static final String formatStr = "%n%-24s %-20s %-60s %-25s";
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:ms");
    protected ExtentHtmlReporter htmlReporter;
    protected ExtentReports report =  new ExtentReports();
    protected String reportDirctory;
    private String testcaseFolder =null;


    Report(String testcaseFolder) {
        ReportTestate(testcaseFolder);
        report.attachReporter(this.htmlReporter);
    }

    protected void ReportTestate(String testcaseFolder){
        this.testcaseFolder=testcaseFolder;
        htmlReporter =  new ExtentHtmlReporter( setReportDirectory(testcaseFolder) +"extend.html");
    }

    private static String getCurTime(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
        return format.format(date);
    }
    protected String getReportDirectory() {
        return reportDirctory;
    }

    protected String setReportDirectory(String testcaseFolder) {
        return reportDirctory = System.getProperty("user.dir") + "\\Reports\\" +  testcaseFolder+ "\\" + getCurTime()+"\\";
    }

    public void flush(){
        report.flush();
    }


}