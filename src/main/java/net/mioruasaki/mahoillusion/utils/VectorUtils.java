package net.mioruasaki.mahoillusion.utils;

import org.bukkit.util.Vector;

public class VectorUtils {

    // 将向量绕Y轴旋转指定角度（度数）
    public static Vector rotateVectorAroundY(Vector vector, double degrees) {
        double radians = Math.toRadians(degrees);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);

        // 构建绕Y轴旋转的旋转矩阵
        double[][] rotationMatrix = {
                { cos, 0, -sin },
                { 0, 1, 0 },
                { sin, 0, cos }
        };

        return applyRotationMatrix(vector, rotationMatrix);
    }

    // 应用旋转矩阵到向量
    private static Vector applyRotationMatrix(Vector vector, double[][] rotationMatrix) {
        double x = vector.getX();
        double y = vector.getY();
        double z = vector.getZ();

        double newX = rotationMatrix[0][0] * x + rotationMatrix[0][1] * y + rotationMatrix[0][2] * z;
        double newY = rotationMatrix[1][0] * x + rotationMatrix[1][1] * y + rotationMatrix[1][2] * z;
        double newZ = rotationMatrix[2][0] * x + rotationMatrix[2][1] * y + rotationMatrix[2][2] * z;

        return new Vector(newX, newY, newZ);
    }

}
