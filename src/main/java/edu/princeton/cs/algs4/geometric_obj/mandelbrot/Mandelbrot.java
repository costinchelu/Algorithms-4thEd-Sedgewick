package edu.princeton.cs.algs4.geometric_obj.mandelbrot;

import java.awt.Color;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Mandelbrot {
    public static void main(String[] args) throws Exception {
        int width = 15360, height = 8640, max = 8000;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int black = 0;
        int[] colors = new int[max];
        for (int i = 0; i < max; i++) {
            colors[i] = Color.HSBtoRGB(i / 256f, 1, i / (i + 8f));
        }

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                double c_re = (col - (double) width / 2) * 4.0 / width;
                double c_im = (row - (double) height / 2) * 4.0 / width;
                double x = 0, y = 0;

                int iteration = 0;
                while (x * x + y * y < 4 && iteration < max) {
                    double x_new = x * x - y * y + c_re;
                    y = 2 * x * y + c_im;
                    x = x_new;
                    iteration++;
                }
                if (iteration < max) image.setRGB(col, row, colors[iteration]);
                else image.setRGB(col, row, black);
            }
        }

        ImageIO.write(image, "png", new File("mandelbrot.png"));
    }
}