package Task06_TrainApp;

import java.util.*;

public class App {

    private LinkedHashMap<String, String> stations;

    public App() {
        stations = new LinkedHashMap<>();
    }

    public static void main(String[] args) {

        App app = new App();

        try {

            Scanner scanner = new Scanner(System.in);

            app.getStations().forEach((k, v) -> System.out.print("[" + k + "] " + v + "\t"));

            System.out.println("\n\nEnter start and end stations: ie: [FRT] [BAM]");

            String start = scanner.next();
            String end = scanner.next();

            app.validateInput(start, end);

            app.getStations().keySet().stream();




        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public void seedStations() {
        stations.putAll(Map.of(
                "FRT", "Colombo Fort",
                "CLP", "Colpetty",
                "BMB", "Bambalapitiya",
                "DHW", "Dehiwala",
                "MTL", "Mt. Lavinia",
                "RTM", "Ratmalana",
                "ANG", "Angulana",
                "MRT", "Moratuwa",
                "PND", "Panadura",
                "KAL", "Kalutara"
        ));
    }

    public HashMap<String, String> getStations() {
        return stations;
    }

    public boolean validateInput(String start, String end) throws Exception {
        if (start.isBlank() || end.isBlank()) {
            throw new Exception("Please enter a value [" + start + "] [" + end + "]");
        }

        if (!getStations().containsKey(start)) {
            throw new Exception("Start station [" + start + "] is not available\nPlease try again");
        }

        if (!getStations().containsKey(end)) {
            throw new Exception("End station [" + end + "] is not available\nPlease try again");
        }

        return true;
    }

}
