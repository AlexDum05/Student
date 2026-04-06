package org.example;

public class StudentCuNota extends Student {
    private double nota;

    // Constructor care apeleaza constructorul parintelui folosind super()
    public StudentCuNota(int NumarMatricol, String prenume, String nume, int formatieDeStudiu, double nota) {
        super(NumarMatricol, prenume, nume, formatieDeStudiu);
        this.nota = nota;
    }

    public double getNota() {
        return nota;
    }

    // Polimorfism: suprascriem toString pentru a include si nota
    @Override
    public String toString() {
        return super.toString() + " | Nota: " + nota;
    }


}

