package org.example;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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
    }
}
