package eu.basicairdata.clinometer;

import android.hardware.SensorEvent;

public class SensorData {
    public final long timestamp;
    public final int sensorType;
    public final float x;
    public final float y;
    public final float z;

    public static SensorData from(SensorEvent event) {
        final float[] values = event.values;
        return new SensorData(
                event.timestamp,
                event.sensor.getType(),
                values[0],
                values[1],
                values[2]
        );
    }

    public SensorData(long timestamp, int sensorType, float x, float y, float z) {
        this.timestamp = timestamp;
        this.sensorType = sensorType;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}