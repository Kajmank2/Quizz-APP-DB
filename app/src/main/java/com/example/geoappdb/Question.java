package com.example.geoappdb;

public class Question {
    private String country,capital,currency;
    private Integer population;
    public Question(String cou,String cap,Integer pop,String cur){
        country=cou;
        capital=cap;
        population=pop;
        currency=cur;
    }
    //GETTERS/////
    public String getCountry(){return country;};
    public String getCapital(){return capital;};
    public Integer getPopulation(){return population;};
    public String getCurrency(){return currency;};
}
/*
COUNTRY:CAPITAL:POPULATION:CURRENCY
        Italy:Rome:62:Euro
        Germany:Berlin:82:Euro
        Poland:Warsaw:38:Zloty
        France:Paris:66:Euro
        China:Beijing:1382:Yuan
        USA:Washington:325:Dollar
        Russia:Moscow:147:Ruble
        Japan:Tokyo:127:Yen
        Belgium:Brussels:10:Euro
        Brasil:Brasilia:200:Real
        Indonesia:Jakarta:250:Rupiah
        Argentina:Buenos Aires:40:Peso
        Australia:Canberra:24:Dollar
        Canada:Ottawa:36:Dollar
        Chile:Santiago:17:Peso
        South Korea:Seoul:48:Won
        Turkey:Ankara:77:Lira
        Greece:Athens:10:Euro
        Netherland:Amsterdam:17:Euro
        Egypt:Cairo:89:Pound
        Ireland:Dublin:4:Euro
        Ukraine:Kiev:45:Hryvnia
        Malaysia:Kuala Lumpur:28:Ringgit
        United Kingdom:London:65:Pound
        India:New Delhi:1210:Rupee
        Spain:Madrid:46:Euro
*/