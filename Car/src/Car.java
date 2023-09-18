public abstract class Car {
    private String brand;
    private String model;
    private String color;
    private String bodyType;
    private int numberOfWheels;
    private String fuelType;
    private String transmissionType;
    private double engineCapacity;

    public Car(String brand, String model, String color, String bodyType, int numberOfWheels,
               String fuelType, String transmissionType, double engineCapacity) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.bodyType = bodyType;
        this.numberOfWheels = numberOfWheels;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        this.engineCapacity = engineCapacity;
    }

    public abstract void move();

    public abstract void service();

    public abstract void switchGear();

    public abstract void turnOnHeadlights();

    public abstract void turnOnWipers();

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getBodyType() {
        return bodyType;
    }

    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }
}

// Класс Car с методом для подметания улицы (выполняет дополнительную функцию чистки в уличных условиях), который нарушает принцип SRP:

public abstract class Car {
    // свойства и методы

    public abstract void sweepStreet(); // Нарушает SRP
}

// Правильный вариант после разделения обязанностей — создать другой класс, отвечающий за чистку улиц. Например, StreetCleaner:

public class StreetCleaner {
    public void sweepStreet() {
        // Содержит логику чистки улицы
    }
}

public abstract class Car {
    // свойства и методы
}

public class CarWithStreetCleaner extends Car {
    private StreetCleaner streetCleaner;

    public CarWithStreetCleaner(String brand, String model, String color, String bodyType,
                                int numberOfWheels, String fuelType, String transmissionType,
                                double engineCapacity) {
        super(brand, model, color, bodyType, numberOfWheels, fuelType, transmissionType, engineCapacity);
        this.streetCleaner = new StreetCleaner();
    }

    @Override
    public void move() {

    }

    @Override
    public void service() {

    }

    @Override
    public void switchGear() {

    }

    @Override
    public void turnOnHeadlights() {

    }

    @Override
    public void turnOnWipers() {

    }

    public void sweepStreet() {
        this.streetCleaner.sweepStreet();
    }
}

// Класс Car с методами для перевозки груза и включении противотуманных фар, который нарушает принцип OCP:

public abstract class Car {
    // свойства и методы

    public abstract void carryCargo(); // Нарушает OCP

    public abstract void turnOnFogLights(); // Нарушает OCP
}

// Правильный вариант после соблюдения принципа OCP — добавить интерфейс с новыми методами, чтобы можно было добавлять новые функции без изменения существующего класса. Например, можно создать интерфейс CargoTransport, который реализуется только теми классами, которые могут перевозить груз:

public interface CargoTransport {
    void carryCargo();
}

public interface FogLights {
    void turnOnFogLights();
}

public abstract class Car {
    // свойства и методы
}

public class CarWithCargoTransport extends Car implements CargoTransport {
    // реализация методов из интерфейса CargoTransport
}

public class CarWithFogLights extends Car implements FogLights {
    // реализация методов из интерфейса FogLights
}

public class CarWithCargoTransportAndFogLights extends Car
        implements CargoTransport, FogLights {
    // реализация методов из интерфейсов CargoTransport и FogLights
}

// Класс конкретного автомобиля с числом колес, равным 3, нарушающий принцип LSP:

public class ThreeWheeler extends Car {
    // Свойства и методы

    @Override
    public void move() {
        // Реализация движения
    }
}

public static void main(String[] args) {
    Car threeWheeler = new ThreeWheeler(); // Нарушает LSP
}

// Правильный вариант класса, учитывающий принцип LSP, — переопределять существующие методы наследуемого класса, но не делать их менее конкретными или возвращать другой тип:

public class ThreeWheeler extends Car {
    // Свойства и методы

    @Override
    public void move() {
        // Реализация движения для транспортных средств с тремя колесами
    }

    @Override
    public int getNumberOfWheels() {
        return 3;
    }
}

public static void main(String[] args) {
    Car threeWheeler = new ThreeWheeler(); // Соблюдает LSP
}
