import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque61B.

    @Test
    public void addRemoveTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         lld1.addLast(0);
         lld1.removeLast();
         lld1.addLast(1);
         lld1.addFirst(-1);
         assertThat(lld1.toList()).containsExactly(-1, 1).inOrder();

         lld1.removeLast();
         lld1.removeFirst();
         lld1.addFirst(2);
         assertThat(lld1.toList()).containsExactly(2).inOrder();

    }

    @Test
    public void removeTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
         lld1.addLast(0);
         lld1.addLast(1);
         lld1.removeLast();
         assertThat(lld1.toList()).containsExactly(0).inOrder();
         lld1.removeLast();
         assertThat(lld1.isEmpty()).isTrue();
         assertThat(lld1.removeLast()).isNull(); // 检查若为空时仍使用，返回Null

         lld1.addLast(0);
         lld1.addLast(1);
         lld1.removeFirst();
         assertThat(lld1.toList()).containsExactly(1).inOrder();
         lld1.removeFirst();
         assertThat(lld1.toList()).isEmpty();
         assertThat(lld1.removeFirst()).isNull(); // 检查若为空时仍使用，返回Null

    }

    @Test
    public void toListTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
         assertThat(lld1.toList()).isEqualTo(new ArrayList<>());
         lld1.addLast(0);
         lld1.addLast(1);
         lld1.addFirst(-1);

         List<Integer> testArray =  new ArrayList<>();
         testArray.add(-1);
         testArray.add(0);
         testArray.add(1);
         assertThat(lld1.toList()).isEqualTo(testArray);
    }

    @Test
    public void isEmptySizeTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
         assertThat(lld1.isEmpty()).isTrue();
         assertThat(lld1.size()).isEqualTo(0);

         lld1.addLast(0);
         lld1.addLast(1);
         lld1.addFirst(-1);
         assertThat(lld1.isEmpty()).isFalse();
         assertThat(lld1.size()).isEqualTo(3);

         lld1.removeLast();
         assertThat(lld1.isEmpty()).isFalse();
         assertThat(lld1.size()).isEqualTo(2);

         lld1.removeFirst();
         lld1.removeLast();
         assertThat(lld1.isEmpty()).isTrue();
         assertThat(lld1.size()).isEqualTo(0);

    }

    @Test
    public void getTest(){
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
         lld1.addLast(0);
         lld1.addLast(1);
         lld1.addFirst(-1);
         assertThat(lld1.get(0)).isEqualTo(-1);
         assertThat(lld1.get(1)).isEqualTo(0);
         assertThat(lld1.get(2)).isEqualTo(1);
         assertThat(lld1.get(3)).isNull();
         assertThat(lld1.get(-3)).isNull();
         assertThat(lld1.get(234565)).isNull();

         assertThat(lld1.getRecursive(0)).isEqualTo(-1);
         assertThat(lld1.getRecursive(1)).isEqualTo(0);
         assertThat(lld1.getRecursive(2)).isEqualTo(1);
         assertThat(lld1.getRecursive(3)).isNull();
         assertThat(lld1.getRecursive(-3)).isNull();
         assertThat(lld1.getRecursive(234565)).isNull();
    }

    @Test
    public void multipleDequesTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        Deque61B<String> lld2 = new LinkedListDeque61B<>();

        lld1.addLast("a");
        lld1.addLast("b");

        lld2.addLast("c");
        lld2.addLast("d");
        lld2.addLast("e");

        // 检查 lld1 是否受 lld2 影响
        assertThat(lld1.size()).isEqualTo(2);
        assertThat(lld1.toList()).containsExactly("a", "b").inOrder();

        // 检查 lld2
        assertThat(lld2.size()).isEqualTo(3);
        assertThat(lld2.toList()).containsExactly("c", "d", "e").inOrder();
    }

    @Test
    public void bigDequeTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        int N = 500; // 规模适中，测试逻辑正确性

        for (int i = 0; i < N; i++) {
            lld1.addLast(i);
        }

        assertThat(lld1.size()).isEqualTo(N);
        assertThat(lld1.get(N / 2)).isEqualTo(N / 2);
        assertThat(lld1.getRecursive(N - 1)).isEqualTo(N - 1);

        for (int i = 0; i < N; i++) {
            lld1.removeFirst();
        }

        assertThat(lld1.isEmpty()).isTrue();
        assertThat(lld1.size()).isEqualTo(0);
    }
}