package messenger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Flowable;
import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import io.vertx.core.json.Json;

@ApplicationScoped
public class VehicleGenerator {

    private static final Logger LOG = LoggerFactory.getLogger(VehicleGenerator.class);

    private AtomicLong vehicleIds = new AtomicLong();
    
    @Outgoing("uber")
    public Flowable<KafkaRecord<String, String>> generateUber()
    {
        return Flowable.interval(5000, TimeUnit.MILLISECONDS)
                .map(tick -> {
                    VehicleInfo vehicle = randomVehicle("uber");
                    LOG.info("dispatching vehicle: {}", vehicle);
                    return KafkaRecord.of(String.valueOf(vehicle.getVehicleId()), Json.encodePrettily(vehicle));
                });
    }



    public VehicleInfo randomVehicle(String provider) 
    {
        VehicleInfo info = new VehicleInfo();
        info.setProvider(provider);
        info.setAvailableSpace(2 + (int)(Math.random() * 7)); // 2 - 8
        info.setPricePerMinute(1.0 + Math.random() * 9); // 1.0 - 9.99
        info.setTimeToPickup(1 + (long)(Math.random() * 21)); // 1 - 20
        info.setVehicleId(vehicleIds.incrementAndGet());
        info.setAvailable(true);

        return info;
    }
    
}