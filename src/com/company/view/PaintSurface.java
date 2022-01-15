package com.company.view;

import com.company.model.DrawShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Поверхность для рисования фигур. Обычный компонент Swing
 */
public class PaintSurface extends JComponent {

    // Массив фигур, которые находятся на поверхности
    private List<DrawShape> shapes = new ArrayList<>();

    // Тип текущей фигуры
    private int shapeType;

    // Здесь мы храним координаты мыши при начале перетаскивания и
    // при его окончании. Класс Point хранит координаты точки (x,y)
    private Point startDrag, endDrag;

    // Список цветов
    private List<Color> colors = Arrays.asList(Color.YELLOW, Color.MAGENTA, Color.CYAN, Color.RED, Color.BLUE, Color.PINK);

    public PaintSurface() {

        // Изначально, тип фигуры равен 0
        shapeType = 0;

        // Устанавливаем желаемые размеры
        // поверхности. Т.к. мы наследуемся
        // от класса JComponent, то мы вызываем
        // метод этого класса
        super.setPreferredSize(new Dimension(400, 400));

        // Здесь мы прописываем реакции на действия мыши.
        // Мы должны запрограммировать - что произойдет когда мы нажмем на кнопку мыши
        // и что произойдет когда мы отпустим кнопку мыши
        this.addMouseListener(new MouseAdapter() {

            // Этот метод будет выполнен, если пользовать нажимает
            // кнопку мыши
            public void mousePressed(MouseEvent e) {

                // Когда пользовать нажимает на кнопку мыши
                // мы запоминаем координаты курсора относительно нашей поверхности
                // После чего приравниваем endDrag и startDrag (то есть если мы просто кликнем
                // и не будем двигать мышь, то startDrag и endDrag будут идентичны.
                // После этого перерисовываем поверхность (метод repaint()).

                startDrag = new Point(e.getX(), e.getY());
                endDrag = startDrag;
                repaint();
            }

            // Этот метод будет выполнен, если пользовать отпускает
            // кнопку мыши
            public void mouseReleased(MouseEvent e) {

                // Когда мы отпускаем кнопку мыши, в массив shapes добавляется новая фигура.
                // После этого мы очищаем координаты и перерисовываем поверхность

                // Т.к. у нас несколько типов фигур, мы должны:
                // 1 - считать значение поля shapeType - оно содержит
                // тип фигуры, который мы должны нарисовать
                // В зависимости от значения shapeType - создать объект
                // того или иного класса
                // Если поле shapeType содержит какое-то другое значение или поле пустое
                // то ничего не добавляем, т.к. это какая-то ошибка

                DrawShape shape = DrawShape.newInstance(shapeType);

                // Записываем стартовую и конечную точку в объект
                shape.setStartPoint(startDrag);
                shape.setEndPoint(endDrag);
                // Добавляем новую фигуру в массив
                shapes.add(shape);
                // Очищаем стартовые и конечные координаты
                startDrag = null;
                endDrag = null;
                // Перерисовываем
                repaint();
            }
        });

        // Этот метод будет выполнен, если пользовать делает движение
        // drag (перетаскивание). То есть пользователь зажал кнопку мыши и двигает мышь
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Когда мы двигаем мышкой - мы запоминаем новые координаты курсора и
                // перерисовываем поверхность
                endDrag = new Point(e.getX(), e.getY());
                repaint();
            }
        });
    }

    // Установка типа фигуры
    public void setShapeType(int type) {
        this.shapeType = type;
    }

    /**
     * Метод paint вызывается автоматически, когда
     * происходит перерисовка окна. Мы сами его не вызываем!
     * <p>
     * Особенность работы с 2D графикой такова, что каждый раз мы должны
     * заново перерисовывать содержимое поверхности, в том числе, фоновую сетку
     * и все фигуры, которые мы добавили ранее. Как раз для этого и был создан массив
     * shapes. Если бы его не было, то фигуры бы просто исчезали при каждой перерисовке.
     *
     * @param g
     */
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // 1. Устанавливаем сглаживание
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 2. Рисуем фоновую сетку
        paintBackgroundGrid(g2);
        // 3. Устанавливаем размер рамки фигуры в 2 пикселя
        g2.setStroke(new BasicStroke(2));
        // 4. Делаем фон полупрозрачным
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));

        // Берем каждую фигуру из списка фигур и рисуем ее на поверхности
        shapes.forEach(s -> {
            // Черный цвет ободка фигуры
            g2.setPaint(Color.BLACK);
            // Рисуем контур

            g2.draw(s.getShape());
            // Устанавливаем цвет фона фигуры
            g2.setPaint(colors.get(shapes.indexOf(s) % 6));
            // Закрашиваем фигуру
            g2.fill(s.getShape());
        });

        // Если перерисовка происходит в момент когда мы рисуем новую фигуру
        // рисуем серый контур фигуры, которую мы хотим нарисовать
        if (startDrag != null && endDrag != null) {
            g2.setPaint(Color.LIGHT_GRAY);

            // Т.к. у нас несколько типов фигур, мы должны:
            // 1 - считать значение поля shapeType - оно содержит
            // тип фигуры, который мы должны нарисовать
            // В зависимости от значения shapeType - создать объект
            // того или иного класса
            // Если поле shapeType содержит какое-то другое значение или поле пустое
            // то ничего не рисуем
            DrawShape shape = DrawShape.newInstance(shapeType);

            // Рисуем фигуру
            g2.draw(shape.getShape(startDrag, endDrag));
        }
    }

    /**
     * Данный метод отрисовает фон поверхности (сетка)
     *
     * @param g2
     */
    private void paintBackgroundGrid(Graphics2D g2) {
        // Сетка будет иметь серый цвет
        g2.setPaint(Color.LIGHT_GRAY);

        // В цикле рисуем вертикальную линию через каждые 10 пикселей по ширине
        for (int i = 0; i < getSize().width; i += 10) {
            // Создаем простую линию (4 параметра - начальная и конечная точка)
            Shape line = new Line2D.Float(i, 0, i, getSize().height);
            g2.draw(line);
        }

        // Также, через каждые 10 пикселей по высоте рисуем горизонтальную линию
        for (int i = 0; i < getSize().height; i += 10) {
            // Создаем простую линию (4 параметра - начальная и конечная точка)
            Shape line = new Line2D.Float(0, i, getSize().width, i);
            g2.draw(line);
        }
    }
}
