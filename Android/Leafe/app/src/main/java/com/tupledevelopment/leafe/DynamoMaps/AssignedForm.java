package com.tupledevelopment.leafe.DynamoMaps;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.*;

import java.util.List;

@DynamoDBTable(tableName = "AssignedForm")
public class AssignedForm {

    private String formID;
    private String employeeID;
    private String signatureUrl;
    private String completedBy;
    private long date;
    private List<String> testArray;
    private double jobTime;
    private double hourRate;
    private String formState;
    private boolean isNull = true;

    @DynamoDBHashKey(attributeName = "FormID")
    public String getFormID() {
        return formID;
    }

    @DynamoDBAttribute(attributeName = "EmployeeID")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "EmployeeID-JobDate-index")
    public String getEmployeeID(){
        return employeeID;
    }

    @DynamoDBAttribute(attributeName = "SignatureUrl")
    public String getSignatureUrl() {
        return signatureUrl;
    }

    @DynamoDBAttribute(attributeName = "JobDate")
    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "EmployeeID-JobDate-index")
    public long getDate(){
        return date;
    }

    @DynamoDBAttribute(attributeName = "CompletedBy")
    public String getCompletedBy() {
        return completedBy;
    }

    @DynamoDBAttribute(attributeName = "testArray")
    public List<String> getTestArray() {
        return testArray;
    }

    @DynamoDBAttribute(attributeName = "JobTime")
    public double getJobTime(){
        return jobTime;
    }

    @DynamoDBAttribute(attributeName = "HourRate")
    public double getHourRate(){
        return hourRate;
    }

    @DynamoDBAttribute(attributeName = "FormState")
    public String getFormState(){
        return formState;
    }

    @DynamoDBIgnore
    public boolean isEmpty(){
        return isNull;
    }

    public void setFormID(String formID) {
        this.formID = formID;
        isNull = false;
    }

    public void setEmployeeID(String employeeID){
        this.employeeID = employeeID;
        isNull = false;
    }

    public void setSignatureUrl(String signatureUrl) {
        this.signatureUrl = signatureUrl;
        isNull = false;
    }

    public void setDate(long date){
        this.date = date;
        isNull = false;
    }

    public void setTestArray(List<String> testArray){
        this.testArray = testArray;
    }

    public void setCompletedBy(String completedBy){
        this.completedBy = completedBy;
        isNull = false;
    }

    public void setJobTime(double jobTime){
        this.jobTime = jobTime;
        isNull = false;
    }

    public void setHourRate(double hourRate){
        this.hourRate = hourRate;
        isNull = false;
    }

    public void setFormState(String formState){
        this.formState = formState;
        isNull = false;
    }
}
