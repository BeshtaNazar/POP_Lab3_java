public class Producer implements Runnable{
    private final int itemNumbers;
    private final Manager manager;    

    public Producer(int itemNumbers, Manager manager) {
        this.itemNumbers = itemNumbers;
        this.manager = manager;

        new Thread(this).start();    
        
        
    }

    @Override
    public void run() {
        for (int i = 0; i < itemNumbers; i++) {
            try {
                manager.full.acquire();
                manager.access.acquire();

                manager.storage.add("item " + manager.itemIndex);
                System.out.println("Added item " + manager.itemIndex);
                
                manager.access.release();
                manager.empty.release();
                manager.itemIndex+=1;   
                Thread.sleep(1000);                            
            } catch (InterruptedException e) {
                e.printStackTrace();
            }            
        }
    }
}