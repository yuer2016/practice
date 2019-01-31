package com.yuer.jts;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.util.GeometricShapeFactory;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LineStringTest {
    @Test
    @SneakyThrows
    void lineString() {
        Geometry g1 = new WKTReader().read("LINESTRING (0 0, 10 10, 20 20)");
        System.out.println("Geometry 1: " + g1);

        GeometryFactory gf = new GeometryFactory();
        Point point = gf.createPoint(new Coordinate(12, 11));

        System.out.println(g1.distance(point));

        // create a geometry by specifying the coordinates directly
        Coordinate[] coordinates = new Coordinate[]{new Coordinate(10, 10),
                new Coordinate(25, 20), new Coordinate(30, 30)};
        // use the default factory, which gives full double-precision
        Geometry g2 = new GeometryFactory().createLineString(coordinates);
        System.out.println("Geometry 2: " + g2);

        // compute the intersection of the two geometries
        Geometry g3 = g1.intersection(g2);

        System.out.println("G1 intersection G2: " + g3);

        GeometryFactory fact = new GeometryFactory();
        MultiPoint mpt = fact.createMultiPointFromCoords(new Coordinate[]{new Coordinate(0, 0), new Coordinate(1, 1)});
        System.out.println(mpt);
    }

    @Test
    void pointInPolygon() {
        final GeometryFactory gf = new GeometryFactory();

        final List<Coordinate> points = new ArrayList<>();
        points.add(new Coordinate(-10, -10));
        points.add(new Coordinate(-10, 10));
        points.add(new Coordinate(10, 10));
        points.add(new Coordinate(10, -10));
        points.add(new Coordinate(-10, -10));
        final Polygon polygon = gf.createPolygon(new LinearRing(new CoordinateArraySequence(points
                .toArray(new Coordinate[0])), gf), null);

        final Coordinate coord = new Coordinate(0, 0);
        final Point point = gf.createPoint(coord);

        System.out.println(point.within(polygon));
    }
    @Test
    void pointInLine(){
        GeometryFactory gf = new GeometryFactory();

        Coordinate[] c = new Coordinate[5];
        c[0] = new Coordinate(-49.242986, -16.662430);
        c[1] = new Coordinate(-49.241999, -16.664465);
        c[2] = new Coordinate(-49.239146, -16.663828);
        c[3] = new Coordinate(-49.239832, -16.661443);
        c[4] = new Coordinate(-49.242986, -16.662430);

        Geometry geo = gf.createPolygon(c);

        Point p = gf.createPoint(new Coordinate(-49.246870, -16.665493));

        double distance = geo.distance(p);

        System.out.println("Distance: " + distance);
    }
    @Test
    void pointInCircle(){
        GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
        shapeFactory.setNumPoints(32);
        shapeFactory.setCentre(new Coordinate(40.03378422166342, 116.28518916251298));
        shapeFactory.setSize(676.0);
        Polygon circle = shapeFactory.createCircle();
        GeometryFactory gf = new GeometryFactory();
        Point point = gf.createPoint(new Coordinate(40.032194751606994, 116.27924556865392));

        assert (point.within(circle));
    }
}
