package za.co.whcb.tp2.rikitours.factories.rental;

import za.co.whcb.tp2.rikitours.domain.rental.Vehicle;

/**
 * Created by Scorpian on 2016-10-03.
 */
public class VehicleFactory {


    public static Vehicle getVehicle(long id,String vehicleName, String vehicleModel, String vehicleYear)
    {
        return new Vehicle.Builder()
                .id(id)
                .vehicleName(vehicleName)
                .vehicleModel(vehicleModel)
                .vehicleYear(vehicleYear)
                .build();
    }
}
