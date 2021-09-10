public class VectorAddition {

    public static void main(String[] args) {

        //ah shit, here we go again

        double[] angleChanges = new double[100];
        IVector speed = new Vector(750.0, 0.0, "la");
        IVector accel;
        double accelAngle = 0.0;
        double prevAngle = 32.0;

        for (int i = 0; i < 99; i++) {

            accelAngle = Math.acos(637.489 / speed.length());
            System.out.println("accelAngle is  " + Math.toDegrees(accelAngle));

            angleChanges[i] = (speed.getAngle() + accelAngle) - prevAngle;

            accel = new Vector(112.511, speed.getAngle() + accelAngle, "la");

            System.out.println("vector angles: speed " + speed.getAngle() + ": accel " + accel.getAngle());
            System.out.println();


            prevAngle = accel.getAngle();

            speed.add(accel);

            System.out.println(speed.length());
            System.out.println("end of iteration");
            System.out.println();
        }

        for (int i = 0; i < angleChanges.length; i++) {
            System.out.print("cl_yawspeed " + String.format("%.1f", Math.toDegrees(angleChanges[i]*66.66)) +
                                ";");
        }

        System.out.println(speed.length());

    }

}
