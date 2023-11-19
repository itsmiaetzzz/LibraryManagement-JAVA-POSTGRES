package org.example;

public class Subscribers {
    private int visitorId;
    private String visitorName;
    private String reference;

    public Subscribers(int visitorId, String visitorName, String reference) {
        this.visitorId = visitorId;
        this.visitorName = visitorName;
        this.reference = reference;
    }

    public int getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(int visitorId) {
        this.visitorId = visitorId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}


