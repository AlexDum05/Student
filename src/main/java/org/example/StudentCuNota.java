package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class StudentCuNota {


    private final Student student;
    private final double nota;


    public StudentCuNota(int numarMatricol, String prenume, String nume, int formatieDeStudiu, double nota) {
        this.student = new Student(numarMatricol, prenume, nume, formatieDeStudiu);
        this.nota = nota;
    }


    public StudentCuNota(Student student, double nota) {
        this.student = student;
        this.nota = nota;
    }

    public double getNota() {
        return nota;
    }


    public Student getStudent() {
        return student;
    }

    @Override
    public String toString() {

        return student.toString() + " | Nota: " + nota;
    }


    public static void main(String[] args) {
        // 1. Să plece de la o listă de StudentCuNota.
        List<StudentCuNota> listaInitiala = citesteDinFisier("src\\main\\java\\StudentcuNota.txt");

        if (listaInitiala.isEmpty()) {
            System.out.println("Fisierul nu a fost gasit sau este gol.");
            return;
        }

        // 2. Să afișeze studenții cu nota 10 (filter).
        System.out.println("--- Studenti cu nota 10 ---");
        listaInitiala.stream()
                .filter(s -> s.getNota() == 10.0)
                .forEach(System.out::println);

        // 3. Să afișeze studenții cu nota sub 5 (filter).
        System.out.println("\n--- Studenti cu nota sub 5 ---");
        listaInitiala.stream()
                .filter(s -> s.getNota() < 5.0)
                .forEach(System.out::println);

        // 4. Să transforme lista într-una în care notele < 4 devin 4 (map).
        List<StudentCuNota> listaModificata = listaInitiala.stream()
                .map(s -> s.getNota() < 4.0 ? new StudentCuNota(s.getStudent(), 4.0) : s)
                .collect(Collectors.toList());

        System.out.println("\nLista a fost transformata (notele < 4 au devenit 4).");

        // 5. Să calculeze suma notelor tuturor studenților (reduce).
        double sumaNotelor = listaInitiala.stream()
                .map(StudentCuNota::getNota)
                .reduce(0.0, Double::sum);

        System.out.println("\nSuma notelor: " + sumaNotelor);

        // 6. Să calculeze media (reduce la suma + împărțire la list.size).
        if (!listaInitiala.isEmpty()) {
            double media = sumaNotelor / listaInitiala.size();
            System.out.printf("Media notelor: %.2f\n", media);
        }
    }


    /**
     * Metodă statică pentru a încărca datele din fișierul text
     */
    private static List<StudentCuNota> citesteDinFisier(String numeFisier) {
        List<StudentCuNota> rezultate = new ArrayList<>();
        try {
            List<String> linii = Files.readAllLines(Paths.get(numeFisier));
            for (String linie : linii) {
                if (linie.trim().isEmpty()) continue;

                // Împărțim după spații: Prenume Nume Ceva Grupa Matricol Nota
                String[] p = linie.trim().split("\\s+");
                if (p.length >= 6) {
                    String prenume = p[0];
                    String nume = p[1];
                    int formatie = Integer.parseInt(p[2]); // ex: 20
                    int matricol = Integer.parseInt(p[4]); // ex: 3590
                    // Transformăm virgula în punct pentru Double.parseDouble
                    double nota = Double.parseDouble(p[5].replace(",", "."));

                    rezultate.add(new StudentCuNota(matricol, prenume, nume, formatie, nota));
                }
            }
        } catch (IOException e) {
            System.err.println("Eroare la citire: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Eroare la procesarea datelor: " + e.getMessage());
        }
        return rezultate;
    }
}

