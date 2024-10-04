import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

// Classe principal que executa o algoritmo de Flood Fill
public class FloodFill {

    public static void main(String[] args) {
        try {
            // Carrega a imagem de entrada
            File inputFile = new File("C:/Users/silvi/Documents/Faculdade/JAVA/TDE - PBL Listas Lineares - Flood Fill/src/teste.png");
            BufferedImage image = ImageIO.read(inputFile);

            // Define a cor de preenchimento
            Color fillColor = Color.BLUE;

            // Coordenadas iniciais para o preenchimento
            int startX = 50; // Exemplo de coordenada X
            int startY = 50; // Exemplo de coordenada Y

            // Executa o Flood Fill utilizando Pilha (DFS)
            floodFillWithStack(image, startX, startY, fillColor);

            // Salva a imagem resultante
            File outputFileStack = new File("testeee.png");
            ImageIO.write(image, "png", outputFileStack);

            // Carrega a imagem novamente para preencher com Fila (BFS)
            image = ImageIO.read(inputFile);

            // Executa o Flood Fill utilizando Fila (BFS)
            floodFillWithQueue(image, startX, startY, fillColor);

            // Salva a imagem resultante
            File outputFileQueue = new File("testee.png");
            ImageIO.write(image, "png", outputFileQueue);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para Flood Fill utilizando Pilha (DFS)
    private static void floodFillWithStack(BufferedImage image, int x, int y, Color fillColor) {
        int targetColor = image.getRGB(x, y);
        Stack<Point> stack = new Stack<>();
        stack.push(new Point(x, y));

        while (!stack.isEmpty()) {
            Point p = stack.pop();

            if (p.x >= 0 && p.x < image.getWidth() && p.y >= 0 && p.y < image.getHeight()
                && image.getRGB(p.x, p.y) == targetColor && image.getRGB(p.x, p.y) != fillColor.getRGB()) {
                
                image.setRGB(p.x, p.y, fillColor.getRGB());

                // Adiciona os pontos adjacentes à Pilha
                stack.push(new Point(p.x + 1, p.y));
                stack.push(new Point(p.x - 1, p.y));
                stack.push(new Point(p.x, p.y + 1));
                stack.push(new Point(p.x, p.y - 1));
            }
        }
    }
    
    // Método para Flood Fill utilizando Fila (BFS)
    private static void floodFillWithQueue(BufferedImage image, int x, int y, Color fillColor) {
        int targetColor = image.getRGB(x, y);
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            if (p.x >= 0 && p.x < image.getWidth() && p.y >= 0 && p.y < image.getHeight()
                && image.getRGB(p.x, p.y) == targetColor && image.getRGB(p.x, p.y) != fillColor.getRGB()) {
                
                image.setRGB(p.x, p.y, fillColor.getRGB());

                // Adiciona os pontos adjacentes à Fila
                queue.add(new Point(p.x + 1, p.y));
                queue.add(new Point(p.x - 1, p.y));
                queue.add(new Point(p.x, p.y + 1));
                queue.add(new Point(p.x, p.y - 1));
            }
        }
    }
}

// Classe auxiliar para representar um ponto (x, y) na imagem
class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
