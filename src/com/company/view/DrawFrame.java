package com.company.view;


import com.company.model.DrawShape;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Главное окно программы. Фрейм (класс JFrame)
 * является контейнером верхнего уровня
 */
public class DrawFrame extends JFrame {

    // Область для рисования фигур
    private PaintSurface surface;

    // В конструкторе создаем GUI
    public DrawFrame(String title) {

        // Обратите внимание, что мы вызываем
        // конструктор базового класса. Внутри него
        // выполняется вся работа по прорисовке окна
        // Нам остается только использовать код суперкласса
        super(title);

        // Говорим фрейму, что при закрытии окна, программа завершала работу
        // (если это не указать, то программа будет "висеть" в процессах
        // после того как вы закроете окно программмы
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Устанавливаем менеджер разметки
        // (он отвечает за то - как будут
        // располагаться элементы внутри фрейма
        this.setLayout(new BorderLayout());

        // Добавляем верхнюю панель с тремя кнопками
        this.add(setButtonPanel(), BorderLayout.NORTH);

        // Создаем объект области рисования
        surface = new PaintSurface();

        // Добавляем область для рисования фигур во фрейм
        this.add(surface, BorderLayout.CENTER);

        // Этот метод изменяет размер фрейма так
        // чтобы были видны все элементы внутри него
        this.pack();

        // "Показывает" фрейм на экране
        // (устанавливаем видимость фрейма)
        this.setVisible(true);
    }

    /**
     * Данный метод создает и настраивает
     * верхнюю панель с кнопками.
     *
     * @return ссылка на объект панели
     */
    private JPanel setButtonPanel() {

        // Создаем панель для кнопок
        JPanel buttonPanel = new JPanel(true);

        // Сообщаем панели, что элементы внутри него
        // должны идти друг за другом слева направо
        // с выравниваем слева
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        // Цвет фона панели
        buttonPanel.setBackground(Color.CYAN);
        // Граница панели (черная окантовка вокруг панели)
        buttonPanel.setBorder(new LineBorder(Color.BLACK, 2));

        // *** Добавляем кнопки на панель ***

        // 1. Кнопка для прямоугольника
        BigTextButton rect = new BigTextButton("Rectangle");
        // Это т.н. слушатель (Listener). Слушатель - объект некоторого
        // класса, который внутри себя содержит некоторый метод.
        // Этот объект передается кнопке и когда наступает определенное
        // событие, связанное с этой кнопкой (например, мы нажали на эту кнопку),
        // кнопка берет этот слушатель и вызывает его метод.
        // Таким образом, мы можем прописать тот код, который будет
        // выполняться при наступлении определенных событий (например, нажатие на кнопку)
        // *** Этот метод будет выполнен, когда пользователь нажмет на кнопку ***
        rect.addActionListener(e -> {
            // Меняем поле внутри объекта области рисования
            // Чтобы он знал, что теперь нужно рисовать прямоугольники
            surface.setShapeType(DrawShape.SHAPE_RECTANGLE);
        });
        // Добавляем первую кнопку на верхнюю панель
        buttonPanel.add(rect);

        // 2. Кнопка для скругленного прямоугольника
        BigTextButton rounded_rect = new BigTextButton("Rounded rect");
        rounded_rect.addActionListener(e -> {
            // Говорим области рисования, что теперь нужно
            // рисовать скругленные прямоугольники
            surface.setShapeType(DrawShape.SHAPE_ROUNDED_RECT);
        });
        // Добавляем вторую кнопку на верхнюю панель
        buttonPanel.add(rounded_rect);

        // 3. Кнопка для эллипса

        BigTextButton ellipse = new BigTextButton("Ellipse");
        ellipse.addActionListener(e -> {
            // Говорим области рисования, что теперь нужно
            // рисовать скругленные прямоугольники
            surface.setShapeType(DrawShape.ELLIPSE);
        });
        // Добавляем вторую кнопку на верхнюю панель
        buttonPanel.add(ellipse);


        // 4. кнопка отмены
        BigTextButton back = new BigTextButton("Back");
        back.addActionListener(e ->{
            surface.deleteLast();
        });
        buttonPanel.add(back);

        BigTextButton clearAll = new BigTextButton("Clear");
        clearAll.addActionListener(e -> {
            surface.clearAll();
        });
        buttonPanel.add(clearAll);

        // TODO: добавить кнопку для эллипса по аналогии с остальными кнопками

        // TODO: для дополнительных баллов, добавить кнопку "Clear" для очистки всех фигур

        return buttonPanel;
    }
}

