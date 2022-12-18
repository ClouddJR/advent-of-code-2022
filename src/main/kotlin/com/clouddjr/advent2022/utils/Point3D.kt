package com.clouddjr.advent2022.utils

data class Point3D(val x: Int, val y: Int, val z: Int) {
    fun neighbours(): List<Point3D> {
        return listOf(
            Point3D(x - 1, y, z), Point3D(x + 1, y, z),
            Point3D(x, y - 1, z), Point3D(x, y + 1, z),
            Point3D(x, y, z - 1), Point3D(x, y, z + 1),
        )
    }
}