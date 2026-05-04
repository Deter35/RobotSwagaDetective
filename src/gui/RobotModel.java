package gui;

import java.util.Observable;

public class RobotModel extends Observable {
    private volatile double robotPositionX = 100;
    private volatile double robotPositionY = 100;
    private volatile double robotDirection = 0;

    private volatile Target target;

    private static final double maxVelocity = 0.1;
    private static final double maxAngularVelocity = 0.001;
    private static final double DeadDist = 70;       // дистанция начала торможения
    private static final double slowVelocity = 0.015; // скорость при повороте на месте (из 2-го кода)

    public RobotModel() {
        this.target = new Target(150, 150);
    }

    public double getRobotPositionX() { return robotPositionX; }
    public double getRobotPositionY() { return robotPositionY; }
    public double getRobotDirection() { return robotDirection; }
    public int getTargetPositionX() { return target.getX(); }
    public int getTargetPositionY() { return target.getY(); }

    public void setTargetPosition(int x, int y) {
        target.setPosition(x, y);
        setChanged();
        notifyObservers("target");
    }

    public void updateModel() {
        double distance = distance(target.getX(), target.getY(),
                robotPositionX, robotPositionY);
        if (distance < 0.5) {
            return;
        }

        double angleToTarget = angleTo(robotPositionX, robotPositionY,
                target.getX(), target.getY());

        // Вызываем новую логику (адаптированную из GameModel)
        double[] result = calculateMovement(angleToTarget, distance);
        double velocity = result[0];
        double angularVelocity = result[1];

        moveRobot(velocity, angularVelocity, 10);

        setChanged();
        notifyObservers("position");
    }


    private double[] calculateMovement(double angleToTarget, double distance) {
        double diff = angleToTarget - robotDirection;
        diff = asNormalizedRadians(diff);

        double angularVelocity = 0;
        if (diff > Math.PI) {
            angularVelocity = -maxAngularVelocity;
        } else if (diff > 0) {
            angularVelocity = maxAngularVelocity;
        }


        boolean isLookAtTarget = (diff < 0.05 || diff > 2 * Math.PI - 0.05);
        if (isLookAtTarget) {
            angularVelocity = 0;
        }


        double velocity;
        if (distance < DeadDist && !isLookAtTarget) {

            velocity = slowVelocity;
        } else if (distance < DeadDist) {

            velocity = maxVelocity * (distance / DeadDist);
            velocity = Math.max(velocity, 0.01);
        } else {
            velocity = maxVelocity;
        }

        return new double[]{velocity, angularVelocity};
    }



    private void moveRobot(double velocity, double angularVelocity, double duration) {
        velocity = applyLimits(velocity, 0, maxVelocity);
        angularVelocity = applyLimits(angularVelocity, -maxAngularVelocity, maxAngularVelocity);
        double newX = robotPositionX + velocity / angularVelocity *
                (Math.sin(robotDirection + angularVelocity * duration) - Math.sin(robotDirection));
        if (!Double.isFinite(newX)) {
            newX = robotPositionX + velocity * duration * Math.cos(robotDirection);
        }
        double newY = robotPositionY - velocity / angularVelocity *
                (Math.cos(robotDirection + angularVelocity * duration) - Math.cos(robotDirection));
        if (!Double.isFinite(newY)) {
            newY = robotPositionY + velocity * duration * Math.sin(robotDirection);
        }
        robotPositionX = newX;
        robotPositionY = newY;
        double newDirection = asNormalizedRadians(robotDirection + angularVelocity * duration);
        robotDirection = newDirection;
    }

    private static double applyLimits(double value, double min, double max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }

    private static double distance(double x1, double y1, double x2, double y2) {
        double diffX = x1 - x2;
        double diffY = y1 - y2;
        return Math.sqrt(diffX * diffX + diffY * diffY);
    }

    private static double angleTo(double fromX, double fromY, double toX, double toY) {
        double diffX = toX - fromX;
        double diffY = toY - fromY;
        return asNormalizedRadians(Math.atan2(diffY, diffX));
    }

    private static double asNormalizedRadians(double angle) {
        while (angle < 0) angle += 2 * Math.PI;
        while (angle >= 2 * Math.PI) angle -= 2 * Math.PI;
        return angle;
    }
}