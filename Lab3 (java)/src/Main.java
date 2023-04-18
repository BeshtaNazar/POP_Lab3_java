import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        int storageSize = 5;
        int numberOfProducers = 6;
        int numberOfConsumers = 8;
        Producer [] producers= new Producer[numberOfProducers];
        Consumer [] consumers= new Consumer[numberOfConsumers];
        main.starter(storageSize, producers, consumers);
    }

    private void starter(int storageSize, Producer [] producers, Consumer [] consumers ) {
        Manager manager = new Manager(storageSize);
        int [] counts = new int[producers.length];
        for (int i = 0; i < counts.length; i++) {
            counts[i]=new Random().nextInt(1,2);
            manager.increaseProductsCount(counts[i]);
        }
        
        for (int i = 0; i < producers.length; i++) {            
            producers[i]=new Producer(counts[i], manager);                      
        }

        if(consumers.length>manager.getProductsCount()){
            int partCount=1;
            for (int i = 0; i < manager.getProductsCount(); i++) {
                consumers[i]= new Consumer(partCount,manager);
            }
        }
        else{
            int partCount=manager.getProductsCount()/consumers.length;
            for (int i = 0; i < consumers.length; i++) {
                consumers[i]= new Consumer(partCount,manager);    
            }
            if(manager.getProductsCount()==consumers.length){
                consumers[consumers.length-1]= new Consumer(1,manager);
            }
            else{
                consumers[consumers.length-1]= new Consumer(manager.getProductsCount()-((consumers.length)*partCount),manager);
            }
        }
    }
}