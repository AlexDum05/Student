package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    static void main(String[] args) {
        List<Student> listaStudenti = new ArrayList<>();


        listaStudenti.add(new Student(3590,"Alexandru","Rodean",232));
        listaStudenti.add(new Student(3591,"Marius","Ionescu",232));
        listaStudenti.add(new Student(3592,"Rares","Popescu",231));

        listaStudenti.get(0).punePrezenta(true);
        listaStudenti.get(2).punePrezenta(true);

        System.out.println(" Lista completa de studenti ");

        for (Student s : listaStudenti) {
            System.out.println(s);

            if(s.verificaPrezenta()){
                System.out.println(" -> Status: Studentul este in sala.");
            }else {
                System.out.println("   -> Status: Studentul lipseste.");
            }
            }

        Student s1 = new Student(3590, "Alexandru", "Rodean", 232);
        System.out.println(s1);


        // --- PARTEA NOUĂ PENTRU TEMĂ ---
        System.out.println("\n--- Gestiune Studenti cu Note ---");

        List<StudentCuNota> listaCuNote = new ArrayList<>();
        // Adăugăm studenți folosind noua clasă (care are și câmp de notă)
        listaCuNote.add(new StudentCuNota(3590, "Alexandru", "Rodean", 232, 8.5));
        listaCuNote.add(new StudentCuNota(3599, "Sorin", "Puscasu", 267, 9.5));
        listaCuNote.add(new StudentCuNota(3576, "Rares", "Bradean", 212, 6.0));

        // Apelăm funcția de scriere în fișier
        scrieStudentiInFisier(listaCuNote, "StudentcuNota_Rezultat.txt");
    }

    /**
     * Funcție pentru scrierea în fișier a unei liste de StudentCuNota
     */
    public static void scrieStudentiInFisier(List<StudentCuNota> lista, String numeFisier) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(numeFisier))) {
            for (StudentCuNota s : lista) {
                writer.write(s.toString());
                writer.newLine();
            }
            System.out.println("Succes! Datele au fost salvate in: " + numeFisier);
        } catch (IOException e) {
            System.err.println("Eroare la scrierea în fisier: " + e.getMessage());
        }

    }

    }


