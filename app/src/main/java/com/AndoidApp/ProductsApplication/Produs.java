package com.AndoidApp.ProductsApplication;

class Produs {
    public String denumire, cantitate, pret, categorie, imageUrl;

    public Produs(String denumire, String cantitate, String pret, String categorie, String imageUrl) {
        this.denumire = denumire;
        this.cantitate = cantitate;
        this.pret = pret;
        this.categorie = categorie;
        this.imageUrl = imageUrl;
    }
    public Produs(){
        //empty
    }
}
