package Task01;

class Exercise1a {
    public static void main(String[] args) {
        int x = 1;
        while (x < 10) {
            if (x > 3) {
                System.out.println("big x");
            }
            // This program will be compiled without any errors. When running, this will loop infinitely.
            // To fix: we have to increment the x here
            x++;
        }
    }
}
