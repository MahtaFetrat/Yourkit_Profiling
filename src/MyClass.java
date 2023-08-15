import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MyClass {
    private static class Customer {
        public int id;
        public int totalProfit;

        public Customer(int id, int totalProfit) {
            this.id = id;
            this.totalProfit = totalProfit;
        }
    }

    private static final int CUSTOMER_COUNT = 10000;
    private static final int QUERY_COUNT = 100000;
    private static ArrayList<Customer> customers;
    private static ArrayList<Integer> queryIds;

    public static void main(String[] args) throws IOException {
        prompt("Start");
        createCustomers();
        createQueryIds();
        queryCustomers();
        prompt("Finish");
    }

    private static void createCustomers() {
        customers = new ArrayList<>(CUSTOMER_COUNT);
        for (int i = 0; i < CUSTOMER_COUNT; i++) {
            Customer customer = new Customer(i, (int) (Math.random() * 100));
            customers.add(customer);
        }
    }

    private static void createQueryIds() {
        queryIds = new ArrayList<>(QUERY_COUNT);
        for (int i = 0; i < QUERY_COUNT; i++) {
            queryIds.add((int) (Math.random() * customers.size()));
        }
    }

    private static void queryCustomers() {
        for (int i = 0; i < QUERY_COUNT; i++) {
            for (Customer c: customers) {
                if (c.id == queryIds.get(i)) {
                    // do something for the customer
                    break;
                }
            }
        }
    }

    private static void prompt(String action) throws IOException {
        System.out.println("Press any key to " + action.toLowerCase() + "...");
        System.in.read();
        System.out.println(action + "ed !");
    }
}
