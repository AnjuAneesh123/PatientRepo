package com.ehealth.EhealthWeb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity//to get table access
@Table(name = "Patients")//Table mapping
public class PateientModel
{
    @Id //Auto Increment
    @GeneratedValue//Primary key
    int patientId;
    String patientName;
    String patientPhoneNumber;
    String patientAge;
//empty constructor
    public PateientModel() {
    }
//parametreized constructor
    public PateientModel(int patientId, String patientName, String patientPhoneNumber, String patientAge) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientPhoneNumber = patientPhoneNumber;
        this.patientAge = patientAge;
    }
//Getters and Setters
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientPhoneNumber() {
        return patientPhoneNumber;
    }

    public void setPatientPhoneNumber(String patientPhoneNumber) {
        this.patientPhoneNumber = patientPhoneNumber;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }
}
