import java.util.*;

class OutOfStockException extends Exception {
    private int shortfall;

    OutOfStockException(int shortfall) {
        super("Insufficient stock. Shortfall: " + shortfall);
        this.shortfall = shortfall;
    }

    public int getShortfall() {
        return shortfall;
    }
}

class InvalidQuantityException extends Exception {
    InvalidQuantityException(String message) {
        super(message);
    }
}

class Warehouse {
    private Map<String, Integer> stock = new HashMap<>();

    Warehouse() {
        stock.put("Pen", 5);
        stock.put("Book", 3);
    }

    public void issue(String item, int qty)
            throws OutOfStockException,
                   InvalidQuantityException {
        if (qty <= 0) {
            throw new InvalidQuantityException(
                    "Quantity must be positive");
        }

        int available = stock.getOrDefault(item, 0);

        if (qty > available) {
            throw new OutOfStockException(qty - available);
        }

        stock.put(item, available - qty);
        System.out.println("Issued " + qty + " " + item);
    }
}

public class WarehouseDemo {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        String[] items = {"Pen", "Book", "Pen", "Pencil"};
        int[] quantities = {2, 5, 0, 1};

        for (int i = 0; i < items.length; i++) {
            try {
                warehouse.issue(items[i], quantities[i]);
            } catch (OutOfStockException e) {
                System.out.println(e.getMessage());
            } catch (InvalidQuantityException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("All requests processed");
    }
}
