package eu.basicairdata.clinoplow;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;


public class MockSensorProvider extends SensorProvider {

    private WITActivity mWITSensor = new WITActivity();
private static boolean ALLOW_UPSIDE_DOWN = false;
    public MockSensorProvider(Context context, WITActivity witActivity) {
        super(context);
    }

    @Override
    public boolean contains(int sensorType) {
        return sensorType == Sensor.TYPE_ACCELEROMETER || sensorType == Sensor.TYPE_GYROSCOPE;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        mWITSensor= WITActivity.getInstance();

        final SensorDataListener listener = this.listener;
        if (listener == null) return;

        final float[] values = event.values;

        final SensorData accData;
        /*
        final float accX = (float)(Math.signum(values[0]) * Math.sqrt(Math.abs(values[0])));
        final float accY = (float)(Math.signum(values[1]) * Math.sqrt(Math.abs(values[1])));
        final float accZ = (float)(Math.signum(values[2]) * Math.sqrt(Math.abs(values[2])));  */
        SensorData gyrData;
        if (mWITSensor != null) {
            float accX = mWITSensor.accX;
            float accY = mWITSensor.accY;
            float accZ = mWITSensor.accZ;

            accData = new SensorData(event.timestamp, Sensor.TYPE_ACCELEROMETER, accX, accY, accZ);
            listener.onSensorChanged(accData);

            /*
        final float gyrX = values[0] * values[0];
        final float gyrY = values[1] * values[1];
        final float gyrZ = values[2] * values[2];*/
            final float gyrX = mWITSensor.gyrX;
            final float gyrY = mWITSensor.gyrY;
            final float gyrZ = mWITSensor.gyrZ;


            gyrData = new SensorData(
                    event.timestamp + 10,
                    Sensor.TYPE_GYROSCOPE, gyrX, gyrY, gyrZ);
            listener.onSensorChanged(gyrData);
        } else {
            accData = new SensorData(event.timestamp, Sensor.TYPE_ACCELEROMETER, 0f, 0f, 0f);
            listener.onSensorChanged(accData);
            gyrData = new SensorData(event.timestamp + 10, Sensor.TYPE_GYROSCOPE, 0f, 0f, 0f);
            listener.onSensorChanged(gyrData);
        }
    }

}