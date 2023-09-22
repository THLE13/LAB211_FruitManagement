package controller;

import common.AlgorithmFruit;
import common.Library;
import common.AlgorithmOrder;
import model.Fruit;
import model.Order;
import view.Menu;

public class FruitManagement extends Menu<String> {

    static String[] mc = {"Create Fruit", "View orders", "Shopping(for buyer)", "Exit"};

    protected Library library;
    protected AlgorithmFruit fruitManSys;
    protected AlgorithmOrder orderManager;

    public FruitManagement() {
        super("FRUIT SHOP SYSTEM", mc);
        library = new Library();
        fruitManSys = new AlgorithmFruit();
        orderManager = new AlgorithmOrder();
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:

                fruitManSys.create();
                char choice = library.getChar("Do you want to continue (Y/N)? ");
                while (choice == 'Y' || choice == 'y') {
                    if (choice == 'Y' || choice == 'y') {
                        fruitManSys.create();
                        choice = library.getChar("Do you want to continue (Y/N)? ");
                        if (choice == 'N' || choice == 'n') {

                            fruitManSys.display();
                        }

                    } else if (choice == 'N' || choice == 'n') {
                        fruitManSys.display();
                    }
                }
                break;

            case 2:
                orderManager.display();
                break;
            case 3:
                char choiceContinue = 'N';
                Order order = new Order();
                
                while (choiceContinue == 'N' || choiceContinue == 'n') {
                    fruitManSys.display();
                    int choiceFruit = library.getInt("Input fruit do you want to choice: ");
                    Fruit fr = fruitManSys.search(choiceFruit);
                    
                    System.out.println("Your selected: " + fruitManSys.search(choiceFruit).getFruitName());
                    int quantityBuy = library.getInt("Please input quantity: ");
                    
                    if (quantityBuy > fr.getQuantity()) {
                        System.out.println("The remaining quantity not enough.");
                    } else {
                        int flag = 0;
                        for (Fruit fruit : order.getList()) {
                            if(fruit.getFruitId() == choiceFruit) {
                                fruit.setQuantity(fruit.getQuantity() + quantityBuy);
                                flag++;
                            }
                        }
                        if(flag == 0) {
                            order.getList().add(new Fruit(choiceFruit, fr.getFruitName(),
                                fr.getPrice(), quantityBuy, fr.getOrigin()));
                        }
                        
                        fruitManSys.updateQuantity(choiceFruit, quantityBuy);
                        choiceContinue = library.getChar("Do you want to order now(Y/N)?");

                        switch (choiceContinue) {
                            case 'Y':
                            case 'y':
                                System.out.println(order.displayProductOrder());
                                String name = library.getString("Input your name: ");
                                order.setCustomerName(name);
                                orderManager.create(order);
                                break;
                            case 'N':
                            case 'n':
                                break;
                            default:
                                System.out.println("Your choice invalid!");
                                break;
                        }
                    }

                }
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Your choice invalid! Pls input another choice");
        }
    }
}