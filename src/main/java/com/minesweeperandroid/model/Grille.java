/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minesweeperandroid.model;

import com.minesweeperandroid.tool;

/**
 *
 * @author p1006126
 */
public class Grille {

    private int difficulte = 0;
    private int hauter;
    private int longeur;
    private int nbBombs;
    private int nbCaseDecouvert = 0;
    private int termine; // 0:continue, 1:win, 2:lost
    private Case[][] tbCase;
    private int[][] tbCompteurBomb;
    private int[][] listBombs; 

    public Grille(int diff) {
        difficulte = diff;
        switch (difficulte) {
            case 0:
                hauter = 9;
                longeur = 9;
                nbBombs = 10;
                break;
            case 1:
                hauter = 16;
                longeur = 16;
                nbBombs = 40;
                break;
            case 2:
                hauter = 16;
                longeur = 30;
                nbBombs = 99;
                break;
        }

        tbCase = new Case[hauter][longeur];
        tbCompteurBomb = new int[hauter][longeur];
        termine = 0;
        for (int i = 0; i < hauter; i++) {
            for (int j = 0; j < longeur; j++) {
                tbCase[i][j] = new Case();
                tbCompteurBomb[i][j] = 0;
            }
        }
    }
    
    public int JeuTermine() {
        return termine;
    }

    public int getDifficulte(){
        return difficulte;
    }
    
    public int getHauter() {
        return hauter;
    }

    public int getLongeur() {
        return longeur;
    }

    public int getNbBombs() {
        return nbBombs;
    }
    
    public int[][] getListBombs(){
        return listBombs;
    }

    public int getCaseEtat(int i, int j) {
        return tbCase[i][j].getEtat();
    }

    public boolean isDecouvert(int i, int j) {
        return tbCase[i][j].isDecouvert();
    }
    
    public boolean isDrapeau(int i, int j){
        return tbCase[i][j].isDrapeau();
    }

    public void setDrapeau(int i, int j, boolean mode) {
        tbCase[i][j].setDrapeau(mode);
    }

    public void InitialiserBombs() {
        listBombs = new int[nbBombs][2];
        for (int i = 0; i < nbBombs; i++) {
            boolean set = false;
            while (!set) {
                int x = tool.monRandom(hauter, 0);
                int y = tool.monRandom(longeur, 0);
                if (!tbCase[x][y].isPiegee()) {
                    tbCase[x][y].setMine();
                    set = true;
                }
                listBombs[i][0] = x;
                listBombs[i][1] = y;
            }
        }
        for (int i = 0; i < hauter; i++) {
            for (int j = 0; j < longeur; j++) {
                if (tbCase[i][j].isPiegee()) {
                    System.out.print("*");
                } else {
                    System.out.print("0");
                }
            }

            System.out.println();
        }
    }

    public int CompterBombsAutour(int x, int y) {
        int n = 0;
        int x1 = x + 1;
        int x2 = x - 1;
        int y1 = y + 1;
        int y2 = y - 1;
        if (x1 < hauter) {
            if (tbCase[x1][y].isPiegee())//sud
            {
                n++;
            }
            if (y1 < longeur) {
                if (tbCase[x1][y1].isPiegee())//Sud est
                {
                    n++;
                }
            }
            if (y2 >= 0) {
                if (tbCase[x1][y2].isPiegee())//Sud Ouest
                {
                    n++;
                }
            }
        }
        if (x2 >= 0) {
            if (tbCase[x2][y].isPiegee())// nord
            {
                n++;
            }
            if (y1 < longeur) {
                if (tbCase[x2][y1].isPiegee())//nord est
                {
                    n++;
                }
            }
            if (y2 >= 0) {
                if (tbCase[x2][y2].isPiegee())//nord ouest
                {
                    n++;
                }
            }
        }
        if (y1 < longeur) {
            if (tbCase[x][y1].isPiegee())//est 
            {
                n++;
            }
        }
        if (y2 >= 0) {
            if (tbCase[x][y2].isPiegee())//ouest 
            {
                n++;
            }
        }
        return n;
    }

    public void CompterBombs() {
        for (int i = 0; i < hauter; i++) {
            for (int j = 0; j < longeur; j++) {
                //tbCompteurBomb[i][j] = CompterMinesAutour(i, j);
                if (!tbCase[i][j].isPiegee()) {
                    tbCase[i][j].setEtat(CompterBombsAutour(i, j));
                }
                //System.out.print(tbCompteurBomb[i][j]);
            }
            //System.out.println();
        }
    }

    public void ChoisirCase(int x, int y) {
        if (!tbCase[x][y].isDecouvert()) {
            tbCase[x][y].setDecouvert();
            if (tbCase[x][y].isPiegee()) {
                termine = -1;
            }
            if (tbCase[x][y].getEtat() == 0) {
                ChoisirVoisinsDe(x, y);
            } else {
                System.out.println(x + " " + y);
                tbCase[x][y].setDecouvert();
                nbCaseDecouvert++;
            }
        }
        if (nbCaseDecouvert == hauter * longeur - nbBombs) {
            termine = 1;
        }
    }

    public void ChoisirVoisinsDe(int x, int y) {
        int x1 = x + 1;
        int x2 = x - 1;
        int y1 = y + 1;
        int y2 = y - 1;
        nbCaseDecouvert++;
        if (x1 < hauter) {
            if (!tbCase[x1][y].isDecouvert() && !tbCase[x1][y].isDrapeau())//sud
            {
                if (tbCase[x1][y].getEtat() == 0) {
                    tbCase[x1][y].setDecouvert();
                    ChoisirVoisinsDe(x1, y);
                } else if (tbCase[x1][y].getEtat() > 0) {
                    tbCase[x1][y].setDecouvert();
                    nbCaseDecouvert++;
                }
            }
            if (y1 < longeur) {
                if (!tbCase[x1][y1].isDecouvert() && !tbCase[x1][y1].isDrapeau())//Sud est
                {
                    if (tbCase[x1][y1].getEtat() == 0) {
                        tbCase[x1][y1].setDecouvert();
                        ChoisirVoisinsDe(x1, y1);
                    } else if (tbCase[x1][y1].getEtat() > 0) {
                        tbCase[x1][y1].setDecouvert();
                        nbCaseDecouvert++;
                    }
                }
            }
            if (y2 >= 0) {
                if (!tbCase[x1][y2].isDecouvert() && !tbCase[x1][y2].isDrapeau())//Sud est
                {
                    if (tbCase[x1][y2].getEtat() == 0) {
                        tbCase[x1][y2].setDecouvert();
                        ChoisirVoisinsDe(x1, y2);
                    } else if (tbCase[x1][y2].getEtat() > 0) {
                        tbCase[x1][y2].setDecouvert();
                        nbCaseDecouvert++;
                    }
                }
            }
        }
        if (x2 >= 0) {
            if (!tbCase[x2][y].isDecouvert() && !tbCase[x2][y].isDrapeau())//Sud est
            {
                if (tbCase[x2][y].getEtat() == 0) {
                    tbCase[x2][y].setDecouvert();
                    ChoisirVoisinsDe(x2, y);
                } else if (tbCase[x2][y].getEtat() > 0) {
                    tbCase[x2][y].setDecouvert();
                    nbCaseDecouvert++;
                }
            }
            if (y1 < longeur) {
                if (!tbCase[x2][y1].isDecouvert() && !tbCase[x2][y1].isDrapeau())//Sud est
                {
                    if (tbCase[x2][y1].getEtat() == 0) {
                        tbCase[x2][y1].setDecouvert();
                        ChoisirVoisinsDe(x2, y1);
                    } else if (tbCase[x2][y1].getEtat() > 0) {
                        tbCase[x2][y1].setDecouvert();
                        nbCaseDecouvert++;
                    }
                }
            }
            if (y2 >= 0) {
                if (!tbCase[x2][y2].isDecouvert() && !tbCase[x2][y2].isDrapeau())//Sud est
                {
                    if (tbCase[x2][y2].getEtat() == 0) {
                        tbCase[x2][y2].setDecouvert();
                        ChoisirVoisinsDe(x2, y2);
                    } else if (tbCase[x2][y2].getEtat() > 0) {
                        tbCase[x2][y2].setDecouvert();
                        nbCaseDecouvert++;
                    }
                }
            }
        }
        if (y1 < longeur) {
            if (!tbCase[x][y1].isDecouvert() && !tbCase[x][y1].isDrapeau())//Sud est
            {
                if (tbCase[x][y1].getEtat() == 0) {
                    tbCase[x][y1].setDecouvert();
                    ChoisirVoisinsDe(x, y1);
                } else if (tbCase[x][y1].getEtat() > 0) {
                    tbCase[x][y1].setDecouvert();
                    nbCaseDecouvert++;
                }
            }
        }
        if (y2 >= 0) {
            if (!tbCase[x][y2].isDecouvert() && !tbCase[x][y2].isDrapeau())//Sud est
            {
                if (tbCase[x][y2].getEtat() == 0) {
                    tbCase[x][y2].setDecouvert();
                    ChoisirVoisinsDe(x, y2);
                } else if (tbCase[x][y2].getEtat() > 0) {
                    tbCase[x][y2].setDecouvert();
                    nbCaseDecouvert++;
                }
            }
        }
    }
}
