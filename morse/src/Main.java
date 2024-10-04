import java.util.Scanner;

// Classe para representar o nó da árvore Morse
class MorseNode {
    char character; // Caractere armazenado no nó
    MorseNode left; // Subárvore para ponto (.)
    MorseNode right; // Subárvore para traço (-)

    // Construtor do nó
    MorseNode(char character) {
        this.character = character;
        this.left = null;
        this.right = null;
    }
}

// Classe que representa a Árvore Binária Morse
class MorseTree {
    private MorseNode root;

    // Construtor para inicializar a árvore
    MorseTree() {
        root = new MorseNode('\0'); // Raiz da árvore
        buildTree(); // Chama método para construir a árvore Morse
    }

    // Método para inserir caracteres na árvore conforme o código Morse
    private void insert(String morseCode, char character) {
        MorseNode currentNode = root;
        for (char symbol : morseCode.toCharArray()) {
            if (symbol == '.') {
                if (currentNode.left == null) {
                    currentNode.left = new MorseNode('\0');
                }
                currentNode = currentNode.left;
            } else if (symbol == '-') {
                if (currentNode.right == null) {
                    currentNode.right = new MorseNode('\0');
                }
                currentNode = currentNode.right;
            }
        }
        currentNode.character = character; // Define o caractere no nó final
    }

    // Método para construir a árvore Morse
    private void buildTree() {
        insert(".-", 'A');
        insert("-...", 'B');
        insert("-.-.", 'C');
        insert("-..", 'D');
        insert(".", 'E');
        insert("..-.", 'F');
        insert("--.", 'G');
        insert("....", 'H');
        insert("..", 'I');
        insert(".---", 'J');
        insert("-.-", 'K');
        insert(".-..", 'L');
        insert("--", 'M');
        insert("-.", 'N');
        insert("---", 'O');
        insert(".--.", 'P');
        insert("--.-", 'Q');
        insert(".-.", 'R');
        insert("...", 'S');
        insert("-", 'T');
        insert("..-", 'U');
        insert("...-", 'V');
        insert(".--", 'W');
        insert("-..-", 'X');
        insert("-.--", 'Y');
        insert("--..", 'Z');
        insert(".----", '1');
        insert("..---", '2');
        insert("...--", '3');
        insert("....-", '4');
        insert(".....", '5');
        insert("-....", '6');
        insert("--...", '7');
        insert("---..", '8');
        insert("----.", '9');
        insert("-----", '0');
    }

    // Método para decodificar uma sequência Morse para um caractere
    public char decode(String morseCode) {
        MorseNode currentNode = root;
        for (char symbol : morseCode.toCharArray()) {
            if (symbol == '.') {
                currentNode = currentNode.left;
            } else if (symbol == '-') {
                currentNode = currentNode.right;
            }
            if (currentNode == null) {
                return '#'; // Retorna # se o código não for encontrado
            }
        }
        return currentNode.character;
    }

    // Método para decodificar uma palavra inteira em Morse
    public String decodeWord(String morseWord) {
        StringBuilder decodedWord = new StringBuilder();
        String[] morseLetters = morseWord.split(" "); // Divide a palavra em letras Morse
        for (String morseLetter : morseLetters) {
            decodedWord.append(decode(morseLetter));
        }
        return decodedWord.toString();
    }

    // Método para imprimir a árvore Morse no console (simples)
    public void printTree() {
        printSubTree(root, "");
    }

    // Função recursiva para imprimir a árvore em pré-ordem
    private void printSubTree(MorseNode node, String indent) {
        if (node != null) {
            System.out.println(indent + (node.character == '\0' ? "." : node.character));
            printSubTree(node.left, indent + "L--");
            printSubTree(node.right, indent + "R--");
        }
    }
}

// Classe principal para executar o programa
public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            MorseTree morseTree = new MorseTree();

            System.out.println("Árvore Morse construída com sucesso!");
            System.out.println("Digite uma palavra em código Morse (separada por espaço entre as letras):");
            String morseWord = scanner.nextLine();

            String decodedWord = morseTree.decodeWord(morseWord);
            System.out.println("Palavra decodificada: " + decodedWord);

            System.out.println("Imprimindo a árvore Morse:");
            morseTree.printTree();
        }
    }
}
