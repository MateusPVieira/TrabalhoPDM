package com.edu.trabalhohospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Appointment implements Serializable {
    private String id;
    private String patientName;
    private String doctorName;
    private String specialty;
    private String date;
    private String price;

    public Appointment() {
    }

    public Appointment(String id, String patientName, String doctorName, String specialty, String date, String price) {
        this.id = id;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.specialty = specialty;
        this.date = date;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "patientName='" + patientName + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
