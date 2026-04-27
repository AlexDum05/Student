package org.example;


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

    // You can expose the inner student if you need its details
    public Student getStudent() {
        return student;
    }

    @Override
    public String toString() {
        // We call student.toString() instead of super.toString()
        return student.toString() + " | Nota: " + nota;
    }
}

