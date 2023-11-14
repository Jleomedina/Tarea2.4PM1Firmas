package com.example.tarea24pm1firmas;
public class Signatures {
    private String description;
    private byte[] digitalSignature;

    public Signatures(String description, byte[] digitalSignature) {
        this.description = description;
        this.digitalSignature = digitalSignature;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getDigitalSignature() {
        return digitalSignature;
    }

    @Override
    public String toString() {
        return "Description: " + description;
    }
}


