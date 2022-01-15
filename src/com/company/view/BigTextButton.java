package com.company.view;

import javax.swing.*;
import java.awt.*;

/**
 * Нам нужна обычная кнопка, но чтобы текст кнопки
 * был больше, чем стандартный. Чтобы для каждой кнопки
 * не вызывать метод setFont, мы просто создаем подкласс
 * класса JButton и в конструкторе класса прописываем вызов метода.
 *
 * Таким образом, каждый раз когда будет создаваться объект класса
 * BigTextButton, мы получим обычную кнопку, но с текстом бОльшего размера
 */
public class BigTextButton extends JButton {

    public BigTextButton(String text) {
        super(text);
        this.setFont(new Font("Arial", Font.PLAIN, 22));
    }
}
