package com.adam.model;

public class Facility {
    private int facID;
    private String name;
    private double memberCost;
    private double guestCost;
    private double initialOutlay;
    private double monthlyMaintenance;

    public int getFacID() {
        return facID;
    }

    public void setFacID(int facID) {
        this.facID = facID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMemberCost() {
        return memberCost;
    }

    public void setMemberCost(double memberCost) {
        this.memberCost = memberCost;
    }

    public double getGuestCost() {
        return guestCost;
    }

    public void setGuestCost(double guestCost) {
        this.guestCost = guestCost;
    }

    public double getInitialOutlay() {
        return initialOutlay;
    }

    public void setInitialOutlay(double initialOutlay) {
        this.initialOutlay = initialOutlay;
    }

    public double getMonthlyMaintenance() {
        return monthlyMaintenance;
    }

    public void setMonthlyMaintenance(double monthlyMaintenance) {
        this.monthlyMaintenance = monthlyMaintenance;
    }
}
