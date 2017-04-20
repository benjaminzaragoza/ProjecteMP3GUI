/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte;

import java.io.Serializable;

/**
 *
 * @author alumne
 */
public class Dj implements Serializable{
    

        private char eliminar = ' ';
        private String nom = null;
        private String lloc = null;
        private int naixement = 0;
        private boolean home = false;
        private boolean omplit = false;
        private double diners = 0.0;
        private char eshome = ' ';

    /**
     *
     * @param nom
     * @param lloc
     * @param naixement
     * @param home
     * @param omplit
     * @param diners
     */
    public Dj(String nom,String lloc,int naixement,boolean home,boolean omplit,double diners) {
        this.nom = nom;
        this.lloc = lloc;
        this.naixement = naixement;
        this.home = home;
        this.omplit = omplit;
        this.diners = diners;
    }
        
    /**
     *
     */
    public Dj() {
    }
    
    /**
     *
     * @param nom
     */
    public Dj(String nom) {
        this.nom=nom;
    }
    
    /**
     *
     * @param nom
     * @param naixement
     */
    public Dj(String nom, int naixement) {
        this.nom=nom;
        this.naixement=naixement;
    }
    
    /**
     *
     * @return
     */
    public char getEliminar() {
        return eliminar;
    }

    @Override
    public String toString() {
        return
                "\n nom=" + nom + 
                "\n lloc=" + lloc + 
                "\n naixement=" + naixement +
                "\n diners=" + diners +
                (home?"\n Genere masculi":"\n Genere femeni");          
    }

    /**
     *
     * @param eliminar
     */
    public void setEliminar(char eliminar) {
        this.eliminar = eliminar;
    }

    /**
     *
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @return
     */
    public String getLloc() {
        return lloc;
    }

    /**
     *
     * @param lloc
     */
    public void setLloc(String lloc) {
        this.lloc = lloc;
    }

    /**
     *
     * @return
     */
    public int getNaixement() {
        return naixement;
    }

    /**
     *
     * @param naixement
     */
    public void setNaixement(int naixement) {
        this.naixement = naixement;
    }

    /**
     *
     * @return
     */
    public boolean isHome() {
        return home;
    }

    /**
     *
     * @param home
     */
    public void setHome(boolean home) {
        this.home = home;
    }

    /**
     *
     * @return
     */
    public boolean isOmplit() {
        return omplit;
    }

    /**
     *
     * @param omplit
     */
    public void setOmplit(boolean omplit) {
        this.omplit = omplit;
    }

    /**
     *
     * @return
     */
    public double getDiners() {
        return diners;
    }

    /**
     *
     * @param diners
     */
    public void setDiners(double diners) {
        
        if(diners<0) System.out.println("Els diners no poden ser negatius...");
        else this.diners = diners;
    }

    /**
     *
     * @return
     */
    public char getEshome() {
        return eshome;
    }

    /**
     *
     * @param eshome
     */
    public void setEshome(char eshome) {
        this.eshome = eshome;
    }
        
}