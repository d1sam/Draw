package com.company.model;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedRectangle extends DrawShape {

    public RoundedRectangle() {}

    public RoundedRectangle(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
    }

    // Для отрисовки скругленного прямоугольника мы используем класс RoundRectangle2D.Double
    // (класс Double внутри класса RoundRectangle2D).
    // Класс RoundRectangle2D.Double принимает координаты типа Double.
    @Override
    public Shape getShape(Point startPoint, Point endPoint) {
        return new RoundRectangle2D.Double(Math.min(startPoint.getX(), endPoint.getX()), Math.min(startPoint.getY(), endPoint.getY()),
                Math.abs(startPoint.getX() - endPoint.getX()), Math.abs(startPoint.getY() - endPoint.getY()), 55.0, 55.0);
    }
}
