package thread;

/**
 * Created by Administrator on 2016/11/4.
 */
public class PrintGroup {

}

class Printer {
    public void printDocument(byte[] doc){}
}

class PrintService {
    protected PrintService neighbor = null;
    protected Printer printer = null;

    public synchronized void print(byte[] doc) {
        getPrinter().printDocument(doc);
    }

    protected Printer getPrinter() {
        if(printer == null)
            printer = neighbor.takePrinter();
        return printer;
    }

    private Printer takePrinter() {
        if (printer != null) {
            Printer p = printer;
            printer = null;
            return p;
        }
        else
            return neighbor.takePrinter();
    }

    synchronized void setNeighbor(PrintService neighbor) {
        this.neighbor = neighbor;
    }

    synchronized void givePrinter(Printer printer) {
        this.printer = printer;
    }

    public static void startUpServices(int nServices, Printer p) {
        if(nServices <= 0 || p == null)
            throw new IllegalArgumentException();

        PrintService first = new PrintService();
        PrintService pred = first;

        for (int i = 0; i < nServices; i++) {
            PrintService s = new PrintService();
            s.setNeighbor(pred);
            pred = s;
        }
        first.setNeighbor(pred);
        first.givePrinter(p);
    }
}