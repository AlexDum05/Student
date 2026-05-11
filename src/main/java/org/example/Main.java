package org.example;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // ==========================================
        // 1. INITIALIZARE SI PREZENTA
        // ==========================================
        List<Student> listaStudenti = new ArrayList<>();
        listaStudenti.add(new Student(3590, "Alexandru", "Rodean", 232));
        listaStudenti.add(new Student(3591, "Marius", "Ionescu", 232));
        listaStudenti.add(new Student(3592, "Rares", "Popescu", 231));
        listaStudenti.add(new Student(3593, "Elena", "Popa", 231));
        listaStudenti.add(new Student(3594, "Andrei", "Stan", 233));

        // Setam prezenta in mod imutabil
        listaStudenti.set(0, listaStudenti.get(0).withPrezenta(true));
        listaStudenti.set(2, listaStudenti.get(2).withPrezenta(true));

        System.out.println("--- Lista initiala de studenti (cu prezenta) ---");
        for (Student s : listaStudenti) {
            System.out.println(s);
        }

        // ==========================================
        // 2. IMPARTIRE IN DOUA FORMATII EGALE
        // ==========================================
        System.out.println("\n--- Impartirea in doua formatii de studiu ---");
        int formatiaNoua1 = 331;
        int formatiaNoua2 = 332;
        int jumatate = listaStudenti.size() / 2;

        for (int i = 0; i < listaStudenti.size(); i++) {
            if (i < jumatate) {
                mutaStudent(listaStudenti, i, formatiaNoua1);
            } else {
                mutaStudent(listaStudenti, i, formatiaNoua2);
            }
        }

        for (Student s : listaStudenti) {
            System.out.println(s.getPrenume() + " " + s.getNume() + " -> Formatia: " + s.getFormatieDeStudiu());
        }

        // ==========================================
        // 3. EXPORT EXCEL (.xls) - APACHE POI
        // ==========================================
        String numeFisierXls = "laborator8_students.xls";
        System.out.println("\n--- Export Excel ---");
        exportaStudentiXls(listaStudenti, numeFisierXls);

        // ==========================================
        // 4. IMPORT EXCEL (.xls) - APACHE POI
        // ==========================================
        System.out.println("\n--- Import Excel ---");
        List<Student> studentiImportati = citesteStudentiXls(numeFisierXls);

        System.out.println("Date citite din fisier:");
        for (Student s : studentiImportati) {
            System.out.println(s.getPrenume() + " " + s.getNume() + " | Formatie: " + s.getFormatieDeStudiu() + " | Prezent: " + s.verificaPrezenta());
        }

        // ==========================================
        // 5. GESTIUNE STUDENTI CU NOTE (EXPORT TXT)
        // ==========================================
        System.out.println("\n--- Gestiune Studenti cu Note (TXT) ---");
        List<StudentCuNota> listaCuNote = new ArrayList<>();
        Student studentRodean = new Student(3590, "Alexandru", "Rodean", 232);
        listaCuNote.add(new StudentCuNota(studentRodean, 8.5));


        scrieStudentiInFisier(listaCuNote, "StudentcuNota_Rezultat.txt");
    }

    // --- METODELE AJUTATOARE ---

    public static void mutaStudent(List<Student> lista, int index, int nouaFormatie) {
        if (index >= 0 && index < lista.size()) {
            lista.set(index, lista.get(index).withFormatieDeStudiu(nouaFormatie));
        }
    }

    public static void scrieStudentiInFisier(List<StudentCuNota> lista, String numeFisier) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(numeFisier))) {
            for (StudentCuNota s : lista) {
                writer.write(s.toString());
                writer.newLine();
            }
            System.out.println("-> Succes! Notele salvate in: " + numeFisier);
        } catch (IOException e) {
            System.err.println("Eroare la scrierea TXT: " + e.getMessage());
        }
    }

    public static void exportaStudentiXls(List<Student> lista, String numeFisier) {
        try (Workbook workbook = new HSSFWorkbook();
             FileOutputStream out = new FileOutputStream(numeFisier)) {

            Sheet sheet = workbook.createSheet("Studenti");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("NumarMatricol");
            headerRow.createCell(1).setCellValue("Prenume");
            headerRow.createCell(2).setCellValue("Nume");
            headerRow.createCell(3).setCellValue("FormatieDeStudiu");
            headerRow.createCell(4).setCellValue("EstePrezent");

            int rowNum = 1;
            for (Student s : lista) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(s.getNumarMatricol());
                row.createCell(1).setCellValue(s.getPrenume());
                row.createCell(2).setCellValue(s.getNume());
                row.createCell(3).setCellValue(s.getFormatieDeStudiu());
                row.createCell(4).setCellValue(s.verificaPrezenta());
            }

            workbook.write(out);
            System.out.println("-> Succes! Exportat in " + numeFisier);

        } catch (IOException e) {
            System.err.println("Eroare export Excel: " + e.getMessage());
        }
    }

    public static List<Student> citesteStudentiXls(String numeFisier) {
        List<Student> studentiCititi = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(numeFisier);
             Workbook workbook = new HSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    int numarMatricol = (int) row.getCell(0).getNumericCellValue();
                    String prenume = row.getCell(1).getStringCellValue();
                    String nume = row.getCell(2).getStringCellValue();
                    int formatieDeStudiu = (int) row.getCell(3).getNumericCellValue();
                    boolean estePrezent = row.getCell(4).getBooleanCellValue();

                    Student s = new Student(numarMatricol, prenume, nume, formatieDeStudiu);
                    if (estePrezent) s = s.withPrezenta(true);

                    studentiCititi.add(s);
                }
            }
            System.out.println("-> Succes! Citit din " + numeFisier);
        } catch (IOException e) {
            System.err.println("Eroare citire Excel: " + e.getMessage());
        }
        return studentiCititi;
    }
}