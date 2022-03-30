package com.example.paoche.entity;


import java.util.Objects;

public class People {

    private String homeId;

    private String address;

    private String identifierId;

    private String name;

    private String relationship;

    private String sex;

    private String trueAddress;

    private String job;

    private String education;

    private String school;

    private String situation;

    private String phoneNumber;

    private String identity;

    private String householdSituation;

    private String other;

    private String age;

    public People() {
    }

    @Override
    public String toString() {
        return "People{" +
                "homeId='" + homeId + '\'' +
                ", address='" + address + '\'' +
                ", identifierId='" + identifierId + '\'' +
                ", name='" + name + '\'' +
                ", relationship='" + relationship + '\'' +
                ", sex='" + sex + '\'' +
                ", trueAddress='" + trueAddress + '\'' +
                ", job='" + job + '\'' +
                ", education='" + education + '\'' +
                ", school='" + school + '\'' +
                ", situation='" + situation + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", identity='" + identity + '\'' +
                ", householdSituation='" + householdSituation + '\'' +
                ", other='" + other + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        People people = (People) o;
        return Objects.equals(age, people.age) &&Objects.equals(homeId, people.homeId) && Objects.equals(address, people.address) && Objects.equals(identifierId, people.identifierId) && Objects.equals(name, people.name) && Objects.equals(relationship, people.relationship) && Objects.equals(sex, people.sex) && Objects.equals(trueAddress, people.trueAddress) && Objects.equals(job, people.job) && Objects.equals(education, people.education) && Objects.equals(school, people.school) && Objects.equals(situation, people.situation) && Objects.equals(phoneNumber, people.phoneNumber) && Objects.equals(identity, people.identity) && Objects.equals(householdSituation, people.householdSituation) && Objects.equals(other, people.other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age,homeId, address, identifierId, name, relationship, sex, trueAddress, job, education, school, situation, phoneNumber, identity, householdSituation, other);
    }

    public String getHomeId() {
        return homeId;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setHomeId(String homeId) {
        this.homeId = homeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentifierId() {
        return identifierId;
    }

    public void setIdentifierId(String identifierId) {
        this.identifierId = identifierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTrueAddress() {
        return trueAddress;
    }

    public void setTrueAddress(String trueAddress) {
        this.trueAddress = trueAddress;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getHouseholdSituation() {
        return householdSituation;
    }

    public void setHouseholdSituation(String householdSituation) {
        this.householdSituation = householdSituation;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public People(String homeId, String address, String identifierId, String name, String relationship, String age, String sex, String trueAddress, String job, String education, String school, String situation, String phoneNumber, String identity, String householdSituation, String other) {
        this.homeId = homeId;
        this.address = address;
        this.identifierId = identifierId;
        this.name = name;
        this.relationship = relationship;
        this.sex = sex;
        this.trueAddress = trueAddress;
        this.job = job;
        this.education = education;
        this.school = school;
        this.situation = situation;
        this.phoneNumber = phoneNumber;
        this.identity = identity;
        this.householdSituation = householdSituation;
        this.other = other;
        this.age = age;
    }
}
