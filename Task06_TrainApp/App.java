package Task06_TrainApp;

import Task05_SortStrings.Station;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {

    private ArrayList<Station> stations;


    public App() {
        stations = new ArrayList<>();
    }

    public static void main(String[] args) {

        App app = new App();
        app.seedStations();

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--------------------------------");
        System.out.println("Welcome to Railway Systems");
        System.out.println("--------------------------------");

        while (true) {
            try {

                System.out.println("All available stations");
                app.getStations().forEach(station -> System.out.println("[" + station.getCode() + "] " + station.getName() + "\t"));
                System.out.println("\nEnter start and end stations: ie: FRT BAM");
                String start = scanner.next();
                String end = scanner.next();
                app.validateInput(start, end);

                ArrayList<Station> sList = app.getStations();

                int iStart = -1, iEnd = -1;
                for (Station station : sList) {
                    if (station.getCode().equalsIgnoreCase(start)) iStart = sList.indexOf(station);
                    if (station.getCode().equalsIgnoreCase(end)) iEnd = sList.indexOf(station);
                }
                app.validateStations(iStart, iEnd);

                System.out.println("\n----------------------------------------------------");
                ;
                System.out.println("You will go through following stations");
                System.out.println("----------------------------------------------------");
                ;
                app.getAffectedStations(sList, iStart, iEnd).stream()
                        .forEach(station -> System.out.println(station.getCode() + " - " + station.getName()));
                System.out.println("----------------------------------------------------");


                // Break the while loop - to terminate after end station
                break;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                scanner.nextLine();
                System.out.println("Press [Enter] to continue");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private List<Station> getAffectedStations(List<Station> sList, int iStart, int iEnd) {

        List<Station> aList = new ArrayList<>();
        for (int i = 0; i < sList.size(); i++) {
            if ((i >= iStart && i <= iEnd) || (i >= iEnd && i <= iStart)) aList.add(sList.get(i));
        }

        if (iStart > iEnd) {
            Collections.reverse(aList);
        }

        return aList;
    }


    public void seedStations() {

        stations.addAll(Arrays.asList(
                new Station("FRT", "Colombo Fort"),
                new Station("CLP", "Colpetty"),
                new Station("BMB", "Bambalapitiya"),
                new Station("DHW", "Dehiwala"),
                new Station("MTL", "Mt.Lavinia"),
                new Station("RTM", "Ratmalana"),
                new Station("ANG", "Angulana"),
                new Station("MRT", "Moratuwa"),
                new Station("PND", "Panadura"),
                new Station("KAL", "Kalutara")
        ));
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public void setStations(ArrayList<Station> stations) {
        this.stations = stations;
    }

    public boolean validateInput(String start, String end) throws Exception {
        if (start.isBlank() || end.isBlank()) {
            throw new Exception("Please enter a value [" + start + "] [" + end + "]");
        }
        return true;
    }

    public boolean validateStations(int iStart, int iEnd) throws Exception {
        if (iStart == -1) {
            throw new Exception("Start station is not available\nPlease try again");
        }

        if (iEnd == -1) {
            throw new Exception("End station is not available\nPlease try again");
        }
        return true;
    }

}
