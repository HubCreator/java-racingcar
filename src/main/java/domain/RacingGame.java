package domain;

import utils.NormalRandomGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class RacingGame {

    private final Cars cars;
    private final TryCount count;

    public RacingGame(List<Name> carNames, TryCount count) {
        List<Car> collect = carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());

        this.cars = new Cars(collect);
        this.count = count;
    }

    public Cars decideWinners() {
        return cars.getWinners();
    }

    public void moveCars(NormalRandomGenerator generator) {
        cars.moveCars(generator);
    }

    public Cars getCars() {
        return cars;
    }
}
