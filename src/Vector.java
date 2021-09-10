public class Vector implements IVector {

    private double _x;
    private double _y;
    private double _length;
    private double _angle;

    private final double EPSILON = 0.0001;
    private final double N_EPSILON = -0.0001;

    public Vector(double a, double b, String type) {
        if (type.equals("xy")) {
            _x = a;
            _y = b;
            _length = calculateLength();
            _angle = calculateAngle();
        } else if (type.equals("la")) {
            _length = a;
            _angle = b;
            calculateCoordinates();
        } else {
            throw new IllegalArgumentException("unrecognized vector type");
        }
    }

    public double calculateLength() {
        double length = 0.0;
        if (_x < EPSILON && _x > N_EPSILON && _y > EPSILON)
            length = _y;

        if (_x > EPSILON && _y > EPSILON)
            length = Math.sqrt(_x * _x + _y * _y);

        if (_x > EPSILON && _y < EPSILON && _y > N_EPSILON)
            length = _x;

        if (_x > EPSILON && _y < EPSILON)
            length = Math.sqrt(_x * _x + _y * _y);

        if (_x < EPSILON && _x > N_EPSILON && _y < EPSILON)
            length = Math.negateExact((long)_y);

        if (_x < EPSILON && _y < EPSILON)
            length = Math.sqrt(_x * _x + _y * _y);

        if (_x < N_EPSILON && _y < EPSILON && _y > N_EPSILON)
            length = Math.negateExact((long)_x);

        if (_x < N_EPSILON && _y > N_EPSILON)
            length = Math.sqrt(_x * _x + _y * _y);

        return length;
    }



    public double getX() {
        return _x;
    }

    public double getY() {
        return _y;
    }

    public void calculateCoordinates() {



        if (_angle < EPSILON && _angle > N_EPSILON) {
            _x = 0.0;
            _y = _length;
            System.out.println("calculate coordinates: case 1");
        }

        else if (_angle > EPSILON && ((Math.PI/2 - _angle) > EPSILON)) {
            _x = Math.sin(_angle) * _length;
            _y = Math.cos(_angle) * _length;
            System.out.println("calculate coordinates: case 2");
        }

        else if (_angle - Math.PI/2 < EPSILON && _angle - Math.PI/2 > N_EPSILON) {
            _x = _length;
            _y = 0.0;
            System.out.println("calculate coordinates: case 3");
        }

        else if (_angle > Math.PI/2 && ((Math.PI - _angle) > EPSILON)) {
            _x = Math.cos(_angle - Math.PI/2) * _length;
            _y = Math.negateExact((long)Math.sin(_angle - Math.PI/2)) * _length;
            System.out.println("calculate coordinates: case 4");
        }

        else if (_angle - Math.PI < EPSILON && _angle - Math.PI > N_EPSILON) {
            _x = 0.0;
            _y = Math.negateExact((long)_length);
            System.out.println("calculate coordinates: case 5");
        }

        else if (_angle > Math.PI && ((Math.PI*1.5 - _angle) > EPSILON)) {
            _x = Math.negateExact((long)Math.sin(_angle - Math.PI)) * _length;
            _y = Math.negateExact((long)Math.cos(_angle - Math.PI)) * _length;
            System.out.println("calculate coordinates: case 6");
        }

        else if (_angle - Math.PI * 1.5 < EPSILON && _angle - Math.PI * 1.5 > N_EPSILON) {
            _x = Math.negateExact((long)_length);
            _y = 0.0;
            System.out.println("calculate coordinates: case 7");
        }

        else if (_angle > Math.PI/2 && ((Math.PI - _angle) > EPSILON)) {
            _x = Math.negateExact((long)Math.cos(_angle - Math.PI*1.5)) * _length;
            _y = Math.sin(_angle - Math.PI*1.5) * _length;
            System.out.println("calculate coordinates: case 8");
        }

        else {
            System.out.println("OH NO, SOMETHING IS BREAKING DOWN, ALARM, ALARM");
        }


    }

    public double length() {
        return _length;
    }

    public double calculateAngle() {
        double angle = 0.0;
        if (_x < EPSILON && _x > N_EPSILON && _y > EPSILON)
            angle = 0.0;

        if (_x > EPSILON && _y > EPSILON)
            angle = Math.acos(_y / _length);

        if (_x > EPSILON && _y < EPSILON && _y > N_EPSILON)
            angle = Math.PI / 2.0;

        if (_x > EPSILON && _y < EPSILON)
            angle = Math.acos(_x / _length) + (Math.PI / 2.0);

        if (_x < EPSILON && _x > N_EPSILON && _y < EPSILON)
            angle = Math.PI;

        if (_x < EPSILON && _y < EPSILON)
            angle = Math.atan(_x / _y) + Math.PI;

        if (_x < 0 && _y < EPSILON && _y > N_EPSILON)
            angle = Math.PI * 1.5;

        if (_x < 0 && _y > 0)
            angle = Math.asin(_y / _length) + (Math.PI * 1.5);

        return angle;
    }

    public double getAngle() {
        return _angle;
    }

    public void add(IVector v) {
        System.out.println("add method troubleshoot");

        System.out.println("x is equal " + _x + ", v.x is equal " + v.getX());
        _x += v.getX();
        System.out.println("new x is " + _x + ", v.x is equal " + v.getX());

        System.out.println("y is equal " + _y + ", v.y is equal " + v.getY());
        _y += v.getY();
        System.out.println("new y is " + _y + ", v.y is equal " + v.getY());

        System.out.println("length is equal " + _length + ", v.length is equal " + v.length());
        _length = calculateLength();
        System.out.println("new length is " + _length + ", v.length is equal " + v.length());

        System.out.println("angle is equal " + _angle + ", v.angle is equal " + v.getAngle());
        _angle = calculateAngle();
        System.out.println("new angle is " + _angle + ", v.angle is equal " + v.getAngle());
        System.out.println();
    }

    public String toString() {
        String s = "x: " + String.format("%.3f",_x)
                 + ", y: " + String.format("%.3f",_y)
                 + ", length: " + String.format("%.3f",_length)
                 + ", angle: " + String.format("%.3f",_angle);

        return s;
    }

}
