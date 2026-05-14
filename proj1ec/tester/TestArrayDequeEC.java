package tester;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.princeton.cs.algs4.StdRandom;
import student.StudentArrayDeque;

public class TestArrayDequeEC {

    @Test
    public void randomizedTest() {
        StudentArrayDeque<Integer> buggyDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solutionDeque = new ArrayDequeSolution<>();
        String errorMessage = "";

        for (int i = 0; i < 5000; i += 1) {
            int operationNumber = StdRandom.uniform(4);

            if (operationNumber == 0) {
                int randVal = StdRandom.uniform(100);
                buggyDeque.addFirst(randVal);
                solutionDeque.addFirst(randVal);
                errorMessage += "addFirst(" + randVal + ")\n";

            } else if (operationNumber == 1) {
                int randVal = StdRandom.uniform(100);
                buggyDeque.addLast(randVal);
                solutionDeque.addLast(randVal);
                errorMessage += "addLast(" + randVal + ")\n";

            } else if (operationNumber == 2) {
                if (solutionDeque.isEmpty()) {
                    continue;
                }
                Integer expected = solutionDeque.removeFirst();
                Integer actual = buggyDeque.removeFirst();
                errorMessage += "removeFirst()\n";
                assertEquals(errorMessage, expected, actual);

            } else if (operationNumber == 3) {
                if (solutionDeque.isEmpty()) {
                    continue;
                }
                Integer expected = solutionDeque.removeLast();
                Integer actual = buggyDeque.removeLast();
                errorMessage += "removeLast()\n";

                assertEquals(errorMessage, expected, actual);
            }
        }
    }
}