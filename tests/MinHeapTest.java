import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinHeapTest {

    private MinHeap mh;

    @Before
    public void setUp() {
        mh = new MinHeap();
    }

    @Test
    public void testInit() {
        assertEquals(0, mh.getSize());
    }

    @Test
    public void testInsertHeap() {
        mh.insertHeap(mh.putKey(4, 1), mh.putValue("p1"));
        mh.insertHeap(mh.putKey(3, 1), mh.putValue("p2"));
        mh.insertHeap(mh.putKey(2, 1), mh.putValue("p3"));
        mh.insertHeap(mh.putKey(4, 2), mh.putValue("p4"));
        mh.insertHeap(mh.putKey(1, 1), mh.putValue("p5"));
    }

    @Test
    public void testDeleteNode() {
        mh.insertHeap(mh.putKey(4, 1), mh.putValue("p1"));
        mh.insertHeap(mh.putKey(3, 1), mh.putValue("p2"));
        mh.insertHeap(mh.putKey(2, 1), mh.putValue("p3"));
        mh.insertHeap(mh.putKey(4, 2), mh.putValue("p4"));
        mh.insertHeap(mh.putKey(1, 1), mh.putValue("p5"));

        mh.deleteNode("p5");
        mh.deleteNode("p1");
        mh.deleteNode("p3");
        mh.deleteNode("p2");
        mh.deleteNode("p4");
    }

    @Test
    public void testPrintkPriority() {
        mh.insertHeap(mh.putKey(4, 1), mh.putValue("p1"));
        mh.insertHeap(mh.putKey(3, 1), mh.putValue("p2"));
        mh.insertHeap(mh.putKey(2, 1), mh.putValue("p3"));
        mh.insertHeap(mh.putKey(4, 2), mh.putValue("p4"));
        mh.insertHeap(mh.putKey(1, 1), mh.putValue("p5"));

        mh.findkPriority(5);
    }


}
