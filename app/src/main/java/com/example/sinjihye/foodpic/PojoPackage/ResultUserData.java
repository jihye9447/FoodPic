package com.example.sinjihye.foodpic.PojoPackage;

public class ResultUserData{

    String result;
    Float proper_weight;
    int activation_kcal;
    int basic_kcal;
    Float activation_level;

    public String getWarning_msg() {
        return warning_msg;
    }

    public void setWarning_msg(String warning_msg) {
        this.warning_msg = warning_msg;
    }

    String warning_msg;

    public Float getActivation_level() {
        return activation_level;
    }

    public void setActivation_level(Float activation_level) {
        this.activation_level = activation_level;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Float getProper_weight() {
        return proper_weight;
    }

    public void setProper_weight(Float proper_weight) {
        this.proper_weight = proper_weight;
    }

    public int getActivation_kcal() {
        return activation_kcal;
    }

    public void setActivation_kcal(int activation_kcal) {
        this.activation_kcal = activation_kcal;
    }

    public int getBasic_kcal() {
        return basic_kcal;
    }

    public void setBasic_kcal(int basic_kcal) {
        this.basic_kcal = basic_kcal;
    }

}
