package ru.erked.stalmine.common.armor;

public class ArmorDataModel {

    private float antirad;
    private float antipsy;
    private float antichem;
    private float antiterm;
    private float antielectra;
    private int nightVisionDevice;

    public ArmorDataModel() {
        antirad = 0f;
        antichem = 0f;
        antipsy = 0f;
        antiterm = 0f;
        antielectra = 0f;
        nightVisionDevice = 0;
    }

    public float getAntirad() {
        return antirad;
    }

    public ArmorDataModel setAntirad(float antirad) {
        this.antirad = antirad;
        return this;
    }

    public float getAntipsy() {
        return antipsy;
    }

    public ArmorDataModel setAntipsy(float antipsy) {
        this.antipsy = antipsy;
        return this;
    }

    public float getAntichem() {
        return antichem;
    }

    public ArmorDataModel setAntichem(float antichem) {
        this.antichem = antichem;
        return this;
    }

    public float getAntiterm() {
        return antiterm;
    }

    public ArmorDataModel setAntiterm(float antiterm) {
        this.antiterm = antiterm;
        return this;
    }

    public float getAntielectra() {
        return antielectra;
    }

    public ArmorDataModel setAntielectra(float antielectra) {
        this.antielectra = antielectra;
        return this;
    }

    public int getNightVisionDevice() {
        return nightVisionDevice;
    }

    public ArmorDataModel setNightVisionDevice(int nvd) {
        this.nightVisionDevice = nvd;
        return this;
    }
}
