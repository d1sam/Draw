package com.company;

import com.company.view.DrawFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // Создание GUI в отдельном потоке
        // Для ознакомления с приложением, см. содержимое класса DrawFrame
        SwingUtilities.invokeLater(() -> new DrawFrame("Программа Draw"));
    }
}
