import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Object refForWeak = new Object();
        Object refForSoft = new Object();
        Object refForPhantom = new Object();

        SoftReference softReference = new SoftReference(refForSoft);
        refForSoft = null;
        System.gc();
        System.out.println(softReference.get());
        //Остается доступной несмотря на то,что refForSoft = null

        WeakReference weakReference = new WeakReference(refForWeak);
        refForWeak = null;
        System.gc();
        System.out.println(weakReference.get());
        //будет содержать null, при первом вызове gc

        ReferenceQueue referenceQueue = new ReferenceQueue();
        PhantomReference phantomReference = new PhantomReference(refForPhantom,referenceQueue);
        refForPhantom = null;
        System.gc();
        System.out.println(phantomReference.get());
        //Всегда возвращает null
        System.out.println(referenceQueue.remove());
        //Возвращает PhantomReference, если есть ссылка кроме PhantomReference, метод будет ждать,пока он его не освободит


    }
}
