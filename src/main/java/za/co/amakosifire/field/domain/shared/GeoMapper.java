package za.co.amakosifire.field.domain.shared;


import org.springframework.data.geo.Point;
import za.co.amakosifire.field.domain.location.model.Coordinates;

public class GeoMapper {

    public Point toGeoJsonPoint(Coordinates coordinates) {
        return new Point(coordinates.getLongitude(), coordinates.getLatitude());
    }

    public Coordinates fromGeoJsonPoint(Point point) {
        return new Coordinates(point.getX(),point.getY());
    }

}
