
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class Lab11Prob03 {
    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<>();

        try (DataInputStream input = new DataInputStream(new FileInputStream("src/people.dat"))) {
            while (true) {
                int age = input.readInt();
                String name = input.readUTF();
                String address = input.readUTF();
                int zipCode = input.readInt();
                double salary = input.readDouble();

                people.add(new Person(age, name, address, zipCode, salary));
            }
        } catch (EOFException e) {
            // Reached end of input file
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(people);

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/people-salary-sorted-objects.dat"))) {
            for (Person p : people) {
                output.writeObject(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}