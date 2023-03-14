package Task01;

class Hobbits {
    String name;
    public static void main(String[] args) {
        Hobbits[] h = new Hobbits[3];
        int z = 0;

        // This program will be compiled without any errors.
        // When running, it will throw ArrayIndexOutOfBoundsException
        // To fix: move the variable increment part to the end of the program.
        // and restrict the while loop with array length

        // while (z < 4) {
        while (z < h.length) {
            //z = z + 1;
            h[z] = new Hobbits();
            h[z].name = "bilbo";
            if (z == 1) {
                h[z].name = "frodo";
            }
            if (z == 2) {
                h[z].name = "sam";
            }
            System.out.print(h[z].name + " is a ");
            System.out.println("good Hobbit name");
            z = z + 1; //
        }
    }
}
