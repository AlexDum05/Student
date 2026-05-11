package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class StudentCuNota extends StudentDecorator {

    private final double nota;

    // Constructorul folosit de decorator
    public StudentCuNota(IStudent student, double nota) {
        super(student);
        this.nota = nota;
    }

    public double getNota() {
        return nota;
    }

    // Suprascriem descrierea pentru a adăuga nota (comportamentul nou)
    @Override
    public String getDescriere() {
        return super.getDescriere() + " | Nota: " + nota;
    }

    @Override
    public String toString() {
        return getDescriere();
    }

    // --- LOGICA DE PROCESARE (Main) ---

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
                .map(s -> s.getNota() < 4.0 ? new StudentCuNota(s.getStudentDeBaza(), 4.0) : s)
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

    private static List<StudentCuNota> citesteDinFisier(String numeFisier) {
        List<StudentCuNota> rezultate = new ArrayList<>();
        try {
            List<String> linii = Files.readAllLines(Paths.get(numeFisier));
            for (String linie : linii) {
                if (linie.trim().isEmpty()) continue;

                String[] p = linie.trim().split("\\s+");
                if (p.length >= 6) {
                    String prenume = p[0];
                    String nume = p[1];
                    int formatie = Integer.parseInt(p[2]);
                    int matricol = Integer.parseInt(p[4]);
                    double nota = Double.parseDouble(p[5].replace(",", "."));

                    // --- CREARE DECORATOR ---
                    // Cream componenta de baza
                    Student studentDeBaza = new Student(matricol, prenume, nume, formatie);
                    // O decoram cu nota
                    rezultate.add(new StudentCuNota(studentDeBaza, nota));
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
