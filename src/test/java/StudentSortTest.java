import org.example.Fisier;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;




public class StudentSortTest {
@Test
   public void TestSortStudent() {
Fisier fisier = new Fisier();
    List<String> liniiNesortate = Arrays.asList(
            "Alex Rodean 20 C232",
                     "Sorin Puscasu 21 C267" ,
                    "Rares Bradean 22 C212" ,
                    "David Vasile 19 C222"
    );
    List<String> liniiAsteptate = Arrays.asList(
            "Rares Bradean 22 C212",
         "David Vasile 19 C222",
         "Alex Rodean 20 C232",
         "Sorin Puscasu 21 C267"
    );
    List<String> liniiActuale = Fisier.sorteazaDupaFormatieSiNume(liniiNesortate);
    assertEquals(liniiAsteptate, liniiActuale, "Liniile nu au fost ordonate corect!");
}
}

