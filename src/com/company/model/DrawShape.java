package com.company.model;


import java.awt.*;
import java.util.Deque;

/**
 * Класс "Фигура для рисования".
 * Класс содержит начальную и конечную точку, а также различные методы
 */
public abstract class DrawShape {

    public static DrawShape newInstance(int shapeType) {
        DrawShape shape = null;
        if (shapeType == DrawShape.SHAPE_RECTANGLE) {
            shape = new Rectangle();
        } else if (shapeType == DrawShape.SHAPE_ROUNDED_RECT) {
            shape = new RoundedRectangle();
        } else if (shapeType == DrawShape.ELLIPSE){
            shape = new Ellipse();
        }
        return shape;
    }

    // Константы для типов фигур
    public static final int SHAPE_RECTANGLE = 0;
    public static final int SHAPE_ROUNDED_RECT = 1;
    public static final int ELLIPSE = 3;

    // Начальная и конечная точки
    private Point startPoint;
    private Point endPoint;

    // Конструктор без параметров. В данном случае
    // у нас точки будут null
    public DrawShape() {
        this(new Point(0, 0), new Point(0, 0));
    }

    // Конструктор с начальной и конечной координатами
    public DrawShape(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    // Метод возвращает фигуру, которую можно нарисовать
    public Shape getShape() {
        return this.getShape(startPoint, endPoint);
    }

    // Т.к. мы не можем нарисовать просто "фигуру", то метод
    // возвращает null
    public Shape getShape(Point startPoint, Point endPoint) {
        return null;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }
}
