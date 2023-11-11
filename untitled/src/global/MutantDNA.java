package global;

import java.util.Scanner;

public class MutantDNA {

    public static boolean isValidDNA(char[][] matrix) {
        char[] validLetters = {'A', 'T', 'C', 'G'};
        for (char[] row : matrix) {
            if (row.length != 6) {
                return false; // Debe contener exactamente 6 letras
            }
            for (char letter : row) {
                boolean isValid = false;
                for (char validLetter : validLetters) {
                    if (letter == validLetter) {
                        isValid = true;
                        break;
                    }
                }
                if (!isValid) {
                    return false; // Contiene letras no válidas
                }
            }
        }
        return true;
    }

    public static char[][] formatInput(String[] inputMatrix) {
        char[][] formattedMatrix = new char[inputMatrix.length][6];
        for (int i = 0; i < inputMatrix.length; i++) {
            String row = inputMatrix[i].toUpperCase();
            if (row.length() != 6) {
                return null; // Si no tiene 6 letras, retorna null
            }
            formattedMatrix[i] = row.toCharArray();
        }
        return formattedMatrix;
    }

    public static int countMutantSequences(char[][] dna) {
        int rowCount = dna.length;
        int colCount = dna[0].length;
        int count = 0;

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                // Check horizontal
                if (j + 3 < colCount &&
                        dna[i][j] == dna[i][j + 1] && dna[i][j] == dna[i][j + 2] && dna[i][j] == dna[i][j + 3]) {
                    count++;
                }
                // Check vertical
                if (i + 3 < rowCount &&
                        dna[i][j] == dna[i + 1][j] && dna[i][j] == dna[i + 2][j] && dna[i][j] == dna[i + 3][j]) {
                    count++;
                }
                // Check diagonal (down-right)
                if (i + 3 < rowCount && j + 3 < colCount &&
                        dna[i][j] == dna[i + 1][j + 1] && dna[i][j] == dna[i + 2][j + 2] && dna[i][j] == dna[i + 3][j + 3]) {
                    count++;
                }
                // Check diagonal (down-left)
                if (i + 3 < rowCount && j - 3 >= 0 &&
                        dna[i][j] == dna[i + 1][j - 1] && dna[i][j] == dna[i + 2][j - 2] && dna[i][j] == dna[i + 3][j - 3]) {
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" ");
        System.out.println("Veamos si nuestro candidato es mutante");
        System.out.println("Ingrese las filas de la matriz 6x6 (ATCG):");
        String[] inputMatrix = new String[6];
        for (int i = 0; i < 6; i++) {
            String input = scanner.nextLine();
            if (input.length() != 6 || !input.matches("[ATCGatcg]+")) {
                System.out.println("Entrada invalida");
                return;
            }
            inputMatrix[i] = input;
        }

        char[][] formattedMatrix = formatInput(inputMatrix);

        if (formattedMatrix == null) {
            System.out.println("Cada fila debe contener exactamente 6 letras");
            return;
        }

        int mutantSequenceCount = countMutantSequences(formattedMatrix);

        if (isValidDNA(formattedMatrix)) {
            if (mutantSequenceCount > 1) {
                System.out.print("Es mutante");
                // La siguiente linea no se pide pero me parece informacion util
                System.out.println(" y se encontraron " + mutantSequenceCount + " secuencias de cuatro letras iguales");
                System.out.println(" ");
            } else {
                System.out.println("No es mutante");
            }
        } else {
            System.out.println("La matriz debe contener solo 'A', 'T', 'C' o 'G' ");
        }
    }
}
