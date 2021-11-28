package ru.erked.stalmine.common.items;

public class ArtifactDataModel {

    private float antirad;
    private float antipsy;
    private float antichem;
    private float antiterm;
    private float antielectra;
    private float rad;
    private float psy;
    private float chem;
    private float term;
    private float regen;
    private float speed;
    private float resistance;
    private float poison;
    private float slowness;
    private float weakness;
    private float timer;
    private float r;
    private float g;
    private float b;

    public ArtifactDataModel() {
        antirad = 0f;
        antipsy = 0f;
        antichem = 0f;
        antiterm = 0f;
        antielectra = 0f;
        rad = 0f;
        psy = 0f;
        chem = 0f;
        term = 0f;
        regen = 0f;
        speed = 0f;
        resistance = 0f;
        poison = 0f;
        slowness = 0f;
        weakness = 0f;
        timer = 0f;
        r = 1f;
        g = 1f;
        b = 1f;
    }

    public float getTimer() {
        return timer;
    }

    public ArtifactDataModel setTimer(float timer) {
        this.timer = timer;
        return this;
    }

    public float getWeakness() {
        return weakness;
    }

    public ArtifactDataModel setWeakness(float weakness) {
        this.weakness = weakness;
        return this;
    }

    public float getSlowness() {
        return slowness;
    }

    public ArtifactDataModel setSlowness(float slowness) {
        this.slowness = slowness;
        return this;
    }

    public float getPoison() {
        return poison;
    }

    public ArtifactDataModel setPoison(float poison) {
        this.poison = poison;
        return this;
    }

    public float getResistance() {
        return resistance;
    }

    public ArtifactDataModel setResistance(float resistance) {
        this.resistance = resistance;
        return this;
    }

    public float getSpeed() {
        return speed;
    }

    public ArtifactDataModel setSpeed(float speed) {
        this.speed = speed;
        return this;
    }

    public float getRegen() {
        return regen;
    }

    public ArtifactDataModel setRegen(float regen) {
        this.regen = regen;
        return this;
    }

    public float getTerm() {
        return term;
    }

    public ArtifactDataModel setTerm(float term) {
        this.term = term;
        return this;
    }

    public float getChem() {
        return chem;
    }

    public ArtifactDataModel setChem(float chem) {
        this.chem = chem;
        return this;
    }

    public float getPsy() {
        return psy;
    }

    public ArtifactDataModel setPsy(float psy) {
        this.psy = psy;
        return this;
    }

    public float getRad() {
        return rad;
    }

    public ArtifactDataModel setRad(float rad) {
        this.rad = rad;
        return this;
    }

    public float getAntiterm() {
        return antiterm;
    }

    public ArtifactDataModel setAntiterm(float antiterm) {
        this.antiterm = antiterm;
        return this;
    }

    public float getAntichem() {
        return antichem;
    }

    public ArtifactDataModel setAntichem(float antichem) {
        this.antichem = antichem;
        return this;
    }

    public float getAntipsy() {
        return antipsy;
    }

    public ArtifactDataModel setAntipsy(float antipsy) {
        this.antipsy = antipsy;
        return this;
    }

    public float getAntirad() {
        return antirad;
    }

    public ArtifactDataModel setAntirad(float antirad) {
        this.antirad = antirad;
        return this;
    }

    public float getR() {
        return r;
    }

    public ArtifactDataModel setR(float r) {
        this.r = r;
        return this;
    }

    public float getG() {
        return g;
    }

    public ArtifactDataModel setG(float g) {
        this.g = g;
        return this;
    }

    public float getB() {
        return b;
    }

    public ArtifactDataModel setB(float b) {
        this.b = b;
        return this;
    }

    public float getAntielectra() {
        return antielectra;
    }

    public ArtifactDataModel setAntielectra(float antielectra) {
        this.antielectra = antielectra;
        return this;
    }
}
