package ClassExamples.Synchronized;

public class SynchronizedExample01 {
   static  class PrintDemo {
        public void printCount() {
            try {
                for(int i = 5; i > 0; i--) {
                Thread.sleep(1000);
                    System.out.println("Counter   ---   "  + i + " (" + Thread.currentThread().getName() +")" );
                }
            } catch (Exception e) {
                System.out.println("Thread  interrupted.");
            }
        }
       public void printCount2() {
           try {
               for(int i = 10; i > 5; i--) {
                   System.out.println("Counter   ---   "  + i + " (" + Thread.currentThread().getName() +")" );
               }
           } catch (Exception e) {
               System.out.println("Thread  interrupted.");
           }
       }
    }

    static class ThreadDemo extends Thread {
        private Thread t;
        private String threadName;
        PrintDemo  PD;

        ThreadDemo( String name,  PrintDemo pd) {
            threadName = name;
            PD = pd;
        }

        public void run() {
            synchronized (PD)
            {
                PD.printCount();
            }
            System.out.println("Thread " +  threadName + " exiting.");
        }

        public void start () {
            System.out.println("Starting " +  threadName );
            if (t == null) {
                t = new Thread (this, threadName);
                t.start ();
            }
        }
    }


        public static void main(String args[]) throws InterruptedException {

            PrintDemo PD = new PrintDemo();
            ThreadDemo T1 = new ThreadDemo("Thread - 1 ", PD);
            ThreadDemo T2 = new ThreadDemo("Thread - 2 ", PD);

            T1.start();
            T2.start();

            // wait for threads to end
            try {
                T1.join();
                T2.join();
            } catch (Exception e) {
                System.out.println("Interrupted");
            }
        }
    }