package com.congwei.checkcode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;
import java.util.Random;

public class CreateImageCode {
    // 图片的宽度
    private int width = 160;
    // 图片的高度
    private int height = 40;
    // 验证码字符个数
    private int codeCount = 4;
    // 验证码干扰线数
    private int lineCount = 20;
    // 验证码
    private String code;
    // 验证码图片
    private BufferedImage buffImg;
    private final Random random = new Random();

    public CreateImageCode() {
        createImage();
    }

    public CreateImageCode(int width, int height) {
        this.width = width;
        this.height = height;
        createImage();
    }

    public CreateImageCode(int width, int height, int codeCount) {
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        createImage();
    }

    public CreateImageCode(int width, int height, int codeCount, int lineCount) {
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        this.lineCount = lineCount;
        createImage();
    }

    public String getCode() {
        return this.code.toLowerCase(Locale.ROOT);
    }

    public void write(OutputStream os) throws IOException {
        ImageIO.write(buffImg, "png", os);
        os.close();
    }

    // 生成图片
    private void createImage() {
        int fontWidth = width / codeCount;  // 字体的宽度
        int fountHeight = height - 5;   // 字体的高度
        int codeY = height - 8;

        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = buffImg.getGraphics();
        g.setColor(getRandomColor(1, 255));
        g.fillRect(0, 0, width, height);
        Font font = new Font("Fixedsys", Font.BOLD, fountHeight);
        g.setFont(font);

        // 设置干扰线
        for (int i = 0; i < lineCount; i++) {
            int xs = random.nextInt(width);
            int ys = random.nextInt(height);
            int xe = xs + random.nextInt(width);
            int ye = ys + random.nextInt(height);
            g.setColor(getRandomColor(1, 255));
            g.drawLine(xs, ys, xe, ye);
        }

        // 添加噪点
        float yawpRate = 0.01f; // 噪声率
        int area = (int) yawpRate * width * height;
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            buffImg.setRGB(x, y, random.nextInt(255));
        }

        this.code = randomStr(codeCount);
        for (int i = 0; i < codeCount; i++) {
            String s = this.code.substring(i, i + 1);
            g.setColor(getRandomColor(1, 255));
            g.drawString(s, i * fontWidth + 3, codeY);
        }
    }

    /**
     * 得到随机数
     */
    private String randomStr(int n) {
        String str = "ABCDEFGHIJKLMNOPQRSTVUWXYZabcdefghijklmnopqrstvuwxyz1234567890";
        String code = "";
        int len = str.length() - 1;
        double r;
        for (int i = 0; i < n; i++) {
            r = (Math.random()) * len;
            code = code + str.charAt((int) r);
        }
        return code;
    }

    private Color getRandomColor(int left, int right) {
        if (left > 255) left = 255;
        if (right > 255) right = 255;
        int r = left + random.nextInt(right - left);
        int g = left + random.nextInt(right - left);
        int b = left + random.nextInt(right - left);
        return new Color(r, g, b);
    }
}
