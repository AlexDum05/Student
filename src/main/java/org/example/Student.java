package org.example;

import java.util.Objects; // Asigură-te că ai acest import pentru hashCode()

// 1. Adaugi "implements IStudent"
public class Student implements IStudent {

    private final int numarMatricol;
    private final String prenume;
    private final String nume;
    private final int formatieDeStudiu;
    private final boolean estePrezent;

    // Constructorul principal
    public Student(int numarMatricol, String prenume, String nume, int formatieDeStudiu) {
        this.numarMatricol = numarMatricol;
        this.prenume = prenume;
        this.nume = nume;
        this.formatieDeStudiu = formatieDeStudiu;
        this.estePrezent = false;
    }

    // Constructor privat folosit intern pentru a crea copii imutabile
    private Student(int numarMatricol, String prenume, String nume, int formatieDeStudiu, boolean estePrezent) {
        this.numarMatricol = numarMatricol;
        this.prenume = prenume;
        this.nume = nume;
        this.formatieDeStudiu = formatieDeStudiu;
        this.estePrezent = estePrezent;
    }

    public boolean verificaPrezenta() {
        return estePrezent;
    }

    public int getNumarMatricol() {
        return numarMatricol;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getNume() {
        return nume;
    }

    public int getFormatieDeStudiu() {
        return formatieDeStudiu;
    }

    // --- METODE PENTRU IMUTABILITATE (Wither methods) ---

    // Returneaza un student nou cu prezenta modificata
    public Student withPrezenta(boolean status) {
        if (this.estePrezent == status) {
            return this;
        }
        return new Student(this.numarMatricol, this.prenume, this.nume, this.formatieDeStudiu, status);
    }

    // Returneaza un student nou cu formatia de studiu modificata
    public Student withFormatieDeStudiu(int nouaFormatie) {
        if (this.formatieDeStudiu == nouaFormatie) {
            return this;
        }
        return new Student(this.numarMatricol, this.prenume, this.nume, nouaFormatie, this.estePrezent);
    }

    // --- METODELE ADĂUGATE PENTRU INTERFAȚA DECORATORULUI ---

    @Override
    public String getDescriere() {
        return numarMatricol + " " + prenume + " " + nume + " (Grupa: " + formatieDeStudiu + ")";
    }

    @Override
    public Student getStudentDeBaza() {
        return this;
    }

    // --- OVERRIDES STANDARDE ---

    @Override
    public String toString() {
        return "Student{\n" +
                "NumarMatricol=" + numarMatricol + "\n" +
                "Prenume=" + prenume + '\n' +
                "Nume=" + nume + '\n' +
                "FormatieDeStudiu=" + formatieDeStudiu + "\n" +
                "EstePrezent=" + estePrezent + "\n" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return numarMatricol == student.numarMatricol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numarMatricol);
    }
}