package gui;

import java.util.Observable;

public class RobotModel extends Observable {
    private volatile double robotPositionX = 100;
    private volatile double robotPositionY = 100;
    private volatile double robotDirection = 0;

    private volatile Target target;

    private static final double maxVelocity = 0.1;
    private static double velocity = 0.0;
    private static final double maxAngularVelocity = 0.001;
    private static final double DeadDist = 35;

    public RobotModel(){
        this.target = new Target(150,150);
    }
    public double getRobotPositionX() {
        return robotPositionX;
    }

    public double getRobotPositionY() {
        return robotPositionY;
    }

    public double getRobotDirection() {
        return robotDirection;
    }

    public int getTargetPositionX() {
        return target.getX();
    }

    public int getTargetPositionY() {
        return target.getY();
    }
    public double getVelocity(){

        return velocity;

    }

    public void setTargetPosition(int x, int y) {
        target.setPosition(x,y);
        setChanged();
        notifyObservers("target");
    }

    public void updateModel() {
        double distance = distance(target.getX(), target.getY(),
                robotPositionX, robotPositionY);
        if (distance < 0.5) {
            return;
        }

        if (distance >= DeadDist) {
            velocity = maxVelocity;
        } else {
            // скорость пропорциональна расстоянию (от 0 до maxVelocity)
            velocity = maxVelocity * (distance / DeadDist);
            // минимальная скорость, чтобы не застыть на месте
            velocity = Math.min(velocity, 0.01);
        }
        double angleToTarget = angleTo(robotPositionX, robotPositionY,
                target.getX(), target.getY());

        // ИСПРАВЛЕННАЯ ЛОГИКА - вынесена в отдельный метод
        double angularVelocity = calculateAngularVelocity(angleToTarget);

        moveRobot(velocity, angularVelocity, 10);

        setChanged();
        notifyObservers("position");
    }
    private void moveRobot(double velocity, double angularVelocity, double duration)
    {
        velocity = applyLimits(velocity, 0, maxVelocity);
        angularVelocity = applyLimits(angularVelocity, -maxAngularVelocity, maxAngularVelocity);
        double newX = robotPositionX + velocity / angularVelocity *
                (Math.sin(robotDirection  + angularVelocity * duration) -
                        Math.sin(robotDirection));
        if (!Double.isFinite(newX))
        {
            newX = robotPositionX + velocity * duration * Math.cos(robotDirection);
        }
        double newY = robotPositionY - velocity / angularVelocity *
                (Math.cos(robotDirection  + angularVelocity * duration) -
                        Math.cos(robotDirection));
        if (!Double.isFinite(newY))
        {
            newY = robotPositionY + velocity * duration * Math.sin(robotDirection);
        }
        robotPositionX = newX;
        robotPositionY = newY;
        double newDirection = asNormalizedRadians(robotDirection + angularVelocity * duration);
        robotDirection = newDirection;
    }
    private double calculateAngularVelocity(double angleToTarget) {
        double angleDifference = angleToTarget - robotDirection;

        // Нормализуем разницу углов в диапазон [-π, π]
        angleDifference = asNormalizedRadians(angleDifference);

        // Если разница больше π, нужно поворачивать в другую сторону
        if (angleDifference > Math.PI) {
            angleDifference = angleDifference - 2 * Math.PI;
        } else if (angleDifference < -Math.PI) {
            angleDifference = angleDifference + 2 * Math.PI;
        }

        // Поворачиваем в ту сторону, где разница меньше по модулю
        if (Math.abs(angleDifference) < 0.01) {
            return 0;
        } else if (angleDifference > 0) {
            return maxAngularVelocity;
        } else {
            return -maxAngularVelocity;
        }

    }

    private static double applyLimits(double value, double min, double max) {
        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
    }

    private static double distance(double x1, double y1, double x2, double y2)
    {
        double diffX = x1 - x2;
        double diffY = y1 - y2;
        return Math.sqrt(diffX * diffX + diffY * diffY);
    }
    private static double angleTo(double fromX, double fromY, double toX, double toY)
    {
        double diffX = toX - fromX;
        double diffY = toY - fromY;

        return asNormalizedRadians(Math.atan2(diffY, diffX));
    }
    private static double asNormalizedRadians(double angle)
    {
        while (angle < 0)
        {
            angle += 2*Math.PI;
        }
        while (angle >= 2*Math.PI)
        {
            angle -= 2*Math.PI;
        }
        return angle;
    }
}
