class Printer{
    String docName;
    int pages;

    void printDocs(String docName, int pages){
        
    }
}

class PC1 implements Runnable{
    PC1(Printer p, String docName, int pages){
        this.p = p;
        this.docName = docName;
        this.pages = pages;

        public void run(){
            p.printDocs();
        }
    }

}

class PC2 implements Runnable{
    PC2(Printer p, String docName, int pages){
        this.p = p;
        this.docName = docName;
        this.pages = pages;

        public void run(){
            p.printDocs();
        }
    }
}

public class PrinterDemo{
    public static void main(String[] args) {
        Printer p = new Printer();
        PC1 p1 = new PC1(p,"Income Tax",12);
        PC2 p2 = new PC1(p,"House Tax",15);

        p1.start();
        p2.start();
    }
}