package com.company.model;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle extends DrawShape {

    public Rectangle() {
    }

    public Rectangle(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
    }

    // Для отрисовки прямоугольника мы используем класс Rectangle2D.Double (класс Double внутри класса Rectangle2D).
    // Класс Rectangle2D.Double принимает координаты типа Double
    @Override
    public Shape getShape(Point startPoint, Point endPoint) {
        return new Rectangle2D.Double(Math.min(startPoint.getX(), endPoint.getX()), Math.min(startPoint.getY(), endPoint.getY()),
                Math.abs(startPoint.getX() - endPoint.getX()), Math.abs(startPoint.getY() - endPoint.getY()));
    }
}
